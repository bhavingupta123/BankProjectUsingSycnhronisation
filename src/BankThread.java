public class BankThread implements Runnable{

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
        try{
            System.out.println("thread running");
            if(op==1)
                bankObj.withdraw(acc_id , amount);
            else if(op==2)
                bankObj.deposit(acc_id,amount);
            else if(op==3)
                System.out.println("Bal for acc id :" + acc_id + " is:" + bankObj.getBal(acc_id) + "\n" + "--------------------------------------------------");
        }

        catch (Exception e){

        }

    }
}
