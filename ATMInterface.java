import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Transaction{
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount, String date)
    {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
    public String getType()
    {
        return type;
    }
    public double getAmount()
    {
        return amount;
    } 
    public String getDate()
    {
        return date;
    }
}
class User {
    private String userId;
    private String pin;
    private List<Transaction> transactionHistory;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(String type, double amount, String date) {
        Transaction transaction = new Transaction(type, amount, date);
        transactionHistory.add(transaction);
    }
}
class ATMfunctions{
    private User user;
    private Scanner scanner;
    public ATMfunctions(User user)
    {
        this.user = user;
        this.scanner = new Scanner(System.in);

    }
    public void showMenu()
    {
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }
    public void processSelection(int choice)
    {
        switch (choice)
        {
            case 1:
                TransactionHistory();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                depositamount();
                break;
            case 4:
                transferamount();
                break;
            case 5:
                System.out.println("thank you ");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");

        }
    }

    public void transferamount()
    {
      System.out.println("enter the amount to tranfer :");
      double amount = scanner.nextDouble();
      scanner.nextLine();
      
      if(amount <= 100)
        {
            System.out.println("Invalid amount");
        }
        else if(amount<=100000)
        {
            user.addTransaction("transfer ",amount,getcurrentDate());
            System.out.println("transfer successful. Amount: " + amount);
        }
        else{
            System.out.println("Sorry cannot transfer this amount ");
        }
    }

    private String getcurrentDate()
    {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy    HH:mm:ss");
        String formattedDate = currentDate.format(myFormatObj);
        return formattedDate;
    }

    public void depositamount()
    {
        System.out.println("enter the amount to deposit : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if(amount <= 100)
            {
                System.out.println("Invalid amount");
            }
            else if(amount<=100000) {
                user.addTransaction("Deposit ",amount, getcurrentDate());
                System.out.println("Deposit successful. Amount : " + amount);
            }
            else{
                System.out.println("Sorry cannot deposit this amount");
            }
    }
    public void withdraw()
    {
        System.out.println("enter the amount to Withdraw : ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if(amount <= 100)
        {
            System.out.println("Invalid amount");
        }
        else if(amount<=50000)
        {
            user.addTransaction("withdrawal ", amount , getcurrentDate());
            System.out.println("Withdrawal successful. Amount : " + amount);
        }
        else{
            System.out.println("Sorry cannot withdrawal this amount");
        }
    }
    public void TransactionHistory()
    {
        List<Transaction> history = user.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transaction history found.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : history) {
                System.out.println("Type: " + transaction.getType());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Date: " + transaction.getDate());
                System.out.println();
            }
        }
    }

}
public class ATMInterface {
    public static void main(String args[])
    {
        User user = new User("user123","1234");
        ATMfunctions atmfunctions = new ATMfunctions(user);

        Scanner scanner = new Scanner(System.in);

        System.out.print("enter user ID: ");
        String userId = scanner.nextLine();

        System.out.print("enter the PIN: ");
        String pin = scanner.nextLine();

        if (user.getUserId().equals(userId) && user.validatePin(pin))
        {
           System.out.println("Authentication successful.Welcome, " + userId + "!");
            while(true)
            {
                atmfunctions.showMenu();

                System.out.print("Enter your choice:");
                int choice = scanner.nextInt();
                scanner.nextLine();
                atmfunctions.processSelection(choice);
                System.out.println();

            }
        }else{
            System.out.println("Authentication Failed . Exiting The Atm.");
        }
    }
}
