import java.sql.SQLException;

public class MainClass {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Bank banObj = new Bank();
        BankThread obj = new BankThread(banObj , 1 , 123 , 10);
        BankThread obj1 = new BankThread(banObj , 2, 123 , 10);
        BankThread obj2 = new BankThread(banObj , 1, 123 , 10);

        Bank banObj2 = new Bank();
        BankThread obj20 = new BankThread(banObj2 , 1 , 124 , 10);
        BankThread obj21 = new BankThread(banObj2 , 2, 124 , 10);
        BankThread obj22 = new BankThread(banObj2 , 1, 124 , 10);

        try {
            obj.start();
            obj1.start();
            obj2.start();

            obj20.start();
            obj21.start();
            obj22.start();
        }
        catch (Exception e){

        }
    }
}
