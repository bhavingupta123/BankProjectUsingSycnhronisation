import java.sql.* ;

public class Bank {

    String url = "jdbc:mysql://localhost/bank";
    String user = "root";
    String pass = "";
    Connection con;
    int bal;
    String AccUserName;
    String AccId;

    public void makeConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection(url , user ,pass);
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

        makeConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select bal from users where AccNo = " + id);
        double bal =0 ;

        while (rs.next()){
            bal = rs.getDouble("bal");
        }

        return bal;
    }

      synchronized public void withdraw(int id , double Amt){
        try {
            double bal = getBal(id);
            System.out.println("curr bal: of acc " + bal + " " + id);

            if(Amt >= bal - 50)
                System.out.println("sorry");
            else {
                Thread.sleep(10000);
                bal = bal - Amt;
                System.out.println("updated bal after withdrawl:" + bal);
                String QUERY = "UPDATE users " + "SET bal =  " + bal + " WHERE AccNo =" + id;
                Statement stmt=con.createStatement();
                stmt.executeUpdate(QUERY);
                System.out.println("Done");
                con.close();
                System.out.println("------------------------------------------------------------------------------");
            }
        }
        catch (Exception e){

            System.out.println("error in withdraw:" + e);
        }
    }

     synchronized public void deposit(int id , double amt){
        try {
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
                con.close();
                System.out.println("------------------------------------------------------------------------------");
            }

        }
        catch (Exception e){
            System.out.println("error in deposit " + e);
        }
    }
}
