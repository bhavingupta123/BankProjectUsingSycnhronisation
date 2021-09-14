import java.sql.SQLException;

public class MainClass {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Bank banObj = new Bank();
        BankThread obj = new BankThread(banObj , 1 , 123 , 10);
        BankThread obj1 = new BankThread(banObj , 2, 123 , 10);
        BankThread obj2 = new BankThread(banObj , 1, 123 , 10);


        try {
            obj.start();
            obj1.start();
            obj2.start();
        }
        catch (Exception e){

        }
    }
}
