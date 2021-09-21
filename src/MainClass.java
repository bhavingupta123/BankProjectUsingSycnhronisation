import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Bank banObj = new Bank();
        ExecutorService executor = Executors.newCachedThreadPool();

        BankThread obj = new BankThread(banObj , 1 , 123 , 10);
        BankThread obj1 = new BankThread(banObj , 2, 123 , 10);
        BankThread obj2 = new BankThread(banObj , 3, 123 , 10);

        Bank banObj2 = new Bank();
        BankThread obj20 = new BankThread(banObj2 , 1 , 124 , 10);
        BankThread obj21 = new BankThread(banObj2 , 2, 124 , 10);
        BankThread obj22 = new BankThread(banObj2 , 3, 124 , 10);

        try {
            executor.execute(obj);
            executor.execute(obj1);
            executor.execute(obj21);
        }
        catch (Exception e){

        }
        executor.shutdown();
    }
}
