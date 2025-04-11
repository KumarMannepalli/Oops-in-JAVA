package oops;

import java.util.Scanner;
abstract class Transaction{
	protected long ACno;
	protected double balance;
	public Transaction(long ACno,double balance) {
		this.ACno=ACno;
		this.balance=balance;
	}
	public long getACno() {
		return ACno;
	}
	public void setACno(long ACno) {
		this.ACno= ACno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public abstract void process();
	
	
}
class Diposit extends Transaction{

	private double amount;

	public Diposit(long ACno, double balance,double amount) {
		super(ACno, balance);
		this.amount=amount;
	}

	@Override
	public void process() {
		balance=balance+amount;
		System.out.println(amount+".rs deposited successfully");
		System.out.println("updated balance: "+balance);
		project2.setBalance(balance);
		
	}
	
}
class Withdrawl extends Transaction{

	private double amount;

	public Withdrawl(long ACno, double balance,double amount) {
		super(ACno, balance);
		this.amount=amount;
	}

	@Override
	public void process() {
		if(balance<amount) {
			System.out.println("Insufficient Balance!");
		}else {
			balance-=amount;
			System.out.println(amount+".rs withdrawn successfully");
			System.out.println("updated balance: "+balance);
			project2.setBalance(balance);
		}
		
	}
	
}
public class project2 {
	private static double balance;
	private static long ACno;
	private static boolean isAccountCreated=false;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int ch;
		do {
			displaymenu();
			System.out.println("choose your choice: ");
		     ch=sc.nextInt();
			switch(ch) {
			case 1:CreateAccount();
			       break;
			case 2:DipositMoney();
			       break;
			case 3:WithdrawMoney();
			       break;
			case 4:checkBalance();
			       break;
			case 5:
                System.out.println("Thank you for using our banking system!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
			}
			
		} while (ch>0);
	}

	public static void setBalance(double newbalance) {
	      balance = newbalance;
		
		
	}

	private static String checkBalance() {
		if(!isAccountCreated) {
			System.out.println("create account first!");
		}else {
			System.out.println("account number: "+ACno);
			System.out.println("current balance: "+balance);
		}
		return null;
	}

	private static String WithdrawMoney() {
		Scanner sc=new Scanner(System.in);
		if(!isAccountCreated) {
			System.out.println("create account first!");
		}else {
			System.out.println("enter withdrawl amount");
			double amount=sc.nextDouble();
			Withdrawl w=new Withdrawl(ACno, balance, amount);
			w.process();
		}
		return null;
	}

	private static String DipositMoney() {
		Scanner sc=new Scanner(System.in);
		if(!isAccountCreated) {
			System.out.println("create account first!");
		}else {
			System.out.println("enetr deposit amount:");
			double amount=sc.nextDouble();
			Diposit d=new Diposit(ACno, balance, amount);
			d.process();
		}
		return null;
	}

	private static String CreateAccount() {
		Scanner sc=new Scanner(System.in);
		if(isAccountCreated) {
			System.out.println("account alredy exist!");
			
		}else {
			System.out.println("enter account number:");
			ACno=sc.nextLong();
			System.out.println("enter initial deposit amount:");
			balance=sc.nextDouble();
			isAccountCreated=true;
			System.out.println("Account created successfullu");
		}
		return null;
		
	}

	private static void displaymenu() {
		System.out.println("\t1 create account");
		System.out.println("\t2 Diposit");
		System.out.println("\t3 Withdrawl");
		System.out.println("\t4 check balance");
		System.out.println("\t5 exit");
		
	}

}
