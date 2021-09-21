import java.security.cert.TrustAnchor;
import java.sql.* ;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    String url = "jdbc:mysql://localhost/bank";
    String user = "root";
    String pass = "";
    Connection con;
    int bal;
    String AccUserName;
    String AccId;
    ReentrantLock l = new ReentrantLock();

    public void makeConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection(url , user ,pass);
        con.setAutoCommit(false);
    }

    public void SelectAllAcc() throws SQLException {

        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from users");

        while(rs.next()) {

            AccId = rs.getString(1);
            AccUserName = rs.getString(2);
            bal = rs.getInt(3);

            System.out.println(AccId);
        }
        con.close();
    }

     public double getBal(int id) throws SQLException, ClassNotFoundException {
        l.lock();
        makeConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select bal from users where AccNo = " + id + " FOR UPDATE ");
        double bal =0 ;

        while (rs.next()){
            bal = rs.getDouble("bal");
        }
        l.unlock();
        return bal;
    }

       public void withdraw(int id , double Amt){

        try {
            l.lock();
            double bal = getBal(id);
            System.out.println("curr bal: of acc " + bal + " " + id);

            if(Amt >= bal - 50)
                System.out.println("sorry");
            else {
                //Thread.sleep(1000);
                bal = bal - Amt;
                System.out.println("updated bal after withdrawl:" + bal);
                String QUERY = "UPDATE users " + "SET bal =  " + bal + " WHERE AccNo =" + id;
                Statement stmt=con.createStatement();
                stmt.executeUpdate(QUERY);
                System.out.println("Done");
                con.commit();
                con.setAutoCommit(true);
                con.close();
                System.out.println("------------------------------------------------------------------------------");
            }
        }
        catch (Exception e){
            System.out.println("error in withdraw:" + e);
        }
        l.unlock();
    }

      public void deposit(int id , double amt){
        try {
            l.lock();
            //con.setAutoCommit(false);
            double bal = getBal(id);
            System.out.println("curr bal: of acc " + bal + " " + id);

            if(amt <= 0) {
                System.out.println("too low");
            }
            else{
                Thread.sleep(100);
                bal = bal + amt;
                System.out.println("updated bal after deposit:" + bal);
                String QUERY = "UPDATE users " + "SET bal =  " + bal + " WHERE AccNo =" + id;
                Statement stmt=con.createStatement();
                stmt.executeUpdate(QUERY);
                System.out.println("Done");
                con.commit();
                con.setAutoCommit(true);
                con.close();
                System.out.println("------------------------------------------------------------------------------");
            }

        }
        catch (Exception e){
            System.out.println("error in deposit " + e);
        }

        l.unlock();
    }
}
