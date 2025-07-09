import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends CheckAccount {
	private String username;
	private String password;

	userInterface userInterface = new userInterface();
	
	private ArrayList<Borrows> memberBorrows;
	private ArrayList<Employee> employees;
	private Scanner scanner;
	private ArrayList<Member> members;
	private LocalDate dueDate;
	
	Employee(ArrayList<Employee> employees, Scanner scanner, ArrayList<Member> members, ArrayList<Borrows> borrowed, LocalDate dueDates){
		this.employees = employees;
		this.scanner = scanner;
		this.members = members;
		this.memberBorrows = borrowed;
		this.dueDate = dueDates;
	}
	
	Employee(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	String getUsername() {
		return this.username;
	}
	
	String getPassword() {
		return this.password;
	}

	
	@Override
    void checkMember() {
      	 
      	 boolean signIn = true;
      	 int incorrect = 0;
      	 
      while(signIn) {
      	 userInterface.signIn();
      	 
      	 	String username = userInterface.promptString("Username: ", scanner);
      	 	String password = userInterface.promptString("Password: ", scanner);
      	 
      	 boolean checkedAccount = false;
      	 
      	 
      	 for(Employee i : employees) {
      		 if(i.getUsername().equals(username) &&
      		    i.getPassword().equals(password)) {
      			 checkDetails();
      			checkedAccount = true;
      			signIn = false;
      			break;
      		} else if(!i.getUsername().equals(username) && 
      				   i.getPassword().equals(password)) {
      			System.out.println("INCORRECT USERNAME");
      			incorrect++;
      			checkedAccount = true;
      			break;
      		} else if(i.getUsername().equals(username) && 
   			   !i.getPassword().equals(password)) {
      			System.out.println("INCORRECT PASSWORD");
      			incorrect++;
      			checkedAccount = true;
      			break;
      		} 
      	 }
      	 
      	 if(!checkedAccount) {
      		 	incorrect++;
      			System.out.println("ALL INCORRECT");
      			signIn = false;
      	 }
      	 if(incorrect == 5) {
      		 System.out.println("Too many attempts!");
      		 signIn = false;
      	 }
       
       }
      }

	void checkDetails() {
		
		for(Member m : members) {
			System.out.println("Member: " + m.getUsername());
			System.out.println("Id: " + m.getId());
			System.out.println(" ");
			int num = 0;
			for(Borrows n : memberBorrows) {
				if(m.getUsername().equals(n.getMember().getUsername())) {
					num++;
	    			System.out.print(num + ". " + n.getBorrowed().getTitle() + " by " + n.getBorrowed().getFirstname() + " " + n.getBorrowed().getLastname() + " published in " + n.getBorrowed().getPublish() + " --- " + "Currently Borrowing");
	    			System.out.println(" ----- Due Date of Return: " + dueDate);
				}
			}
		}
		System.out.println(" ");
	}	
	
}
