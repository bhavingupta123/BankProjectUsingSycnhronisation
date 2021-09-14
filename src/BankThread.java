public class BankThread extends Thread{

    Bank bankObj;
    int op;
    int acc_id ;
    double amount;

    BankThread(Bank banObj , int operation , int id , double amt){
        bankObj = banObj;
        op = operation;
        acc_id = id;
        amount = amt;
    }

    public void run(){
        System.out.println("thread running");
        if(op==1)
            bankObj.withdraw(acc_id , amount);
        else
            bankObj.deposit(acc_id,amount);
    }
}
