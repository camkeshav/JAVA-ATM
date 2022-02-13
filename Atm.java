package atm;

import java.util.Scanner;

class Automatic{
    Scanner s = new Scanner(System.in);
    public void start() throws Exception {
        Users user = new Users();
        System.out.print("Enter account no.: ");
        user.setUseracc(s.nextInt());
        System.out.print("Enter PIN: ");
        user.setUserpin(s.nextInt());
        boolean is_logined = userCheck(user);
        afterLoginTry(is_logined, user.getUseracc(), user);
    }
        public void afterLoginTry(boolean is_logined, int useracc, Users user) throws Exception {
        if(is_logined){
            printMenu();
            int in = s.nextInt();
            switch (in){
                case 1 -> balanceEnq(user);
                case 2 -> {
                    System.out.println("Enter the amount you wish to withdraw: ");
                    double amt = s.nextDouble();
                    withdraw(user, amt);
                }
                case 3 -> userDetails(user);
                case 4 -> {
                    System.out.println("Enter the amount you wish to deposit: ");
                    double amt = s.nextDouble();
                    deposit(user, amt);
                }
                default -> {
                    System.out.println("Wrong option!!! Restart transaction...");
                    start();
                }
            }
        }
        else {
            System.out.println("Login Failure..Try again");
            start();
        }
    }

    public boolean userCheck(Users user){
        if(user.getUseracc() == 123456789 && user.getUserpin() == 2007) return true;
        else return false;
    }

    public void printMenu(){
        System.out.println("Welcome User!!!");
        System.out.println("Kindly choose any of the below options to proceed...");
        System.out.println("1. Balance Enquire");
        System.out.println("2. Cash Withdrawal");
        System.out.println("3. User Details");
        System.out.println("4. Cash Deposit");
    }

    public void userDetails(Users user) throws Exception {
        System.out.println("Account Holder name: Keshav Pareek");
        System.out.println("Account number: "+user.getUseracc());
        System.out.println("Press 1 to exit and 2 to go back...");
        int inp = s.nextInt();
        if(inp == 1) System.exit(0);
        else if(inp == 2) afterLoginTry(true, user.getUseracc(), user);
    }
    public void balanceEnq(Users user) throws Exception {
        System.out.println("ACCOUNT NUMBER: "+user.getUseracc());
        System.out.println("Account balance: "+user.getBalance());
        System.out.println("Press 1 to exit and 2 to go back...");
        int inp = s.nextInt();
        if(inp == 1) System.exit(0);
        else if(inp == 2) afterLoginTry(true, user.getUseracc(), user);
    }
    public void deposit(Users user, double amt) throws Exception{
        if(amt<=0){
            throw new Exception("Amount must be greater than 0....Transaction cancelled!!");
        }
        user.setBalance(amt+user.getBalance());
        System.out.println("Amount deposited successfully!!!");
        System.out.println("Press 1 to exit and 2 to go back...");
        int inp = s.nextInt();
        if(inp == 1) System.exit(0);
        else if(inp == 2) afterLoginTry(true, user.getUseracc(), user);
    }
    public void withdraw(Users user, double amt) throws Exception{
        if(amt<=0){
            throw new Exception("Amount must be greater than 0....Transaction cancelled!!");
        }
        if(amt> user.getBalance()){
            throw new Exception("You have insufficient balance!! Transaction Cancelled...");
        }
        user.setBalance(user.getBalance()-amt);
        System.out.println("Amount withdrawn successfully!!!");
        System.out.println("Press 1 to exit and 2 to go back...");
        int inp = s.nextInt();
        if(inp == 1) System.exit(0);
        else if(inp == 2) afterLoginTry(true, user.getUseracc(), user);
    }
}
public class Atm {

    public static void main(String[] args) throws Exception {
        Automatic atmmach = new Automatic();
        atmmach.start();
    }

}
class Users{
    private double balance=0;
    private int useracc = 0;
    private int userpin = 0;
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUseracc() {
        return this.useracc;
    }

    public void setUseracc(int useracc) {
        this.useracc = useracc;
    }

    public int getUserpin() {
        return this.userpin;
    }

    public void setUserpin(int userpin) {
        this.userpin = userpin;
    }
}
