import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Member extends CheckAccount{
	
	userInterface userInterface = new userInterface();
	
	private String username;
	private String password;
	private int id;
	private ArrayList<Member> members;
	private Scanner scanner;
	private Library library;
	private LocalDate dueDate;
	private ArrayList<Borrows> memberBorrows;
	
	
	Member(ArrayList<Member> members, Scanner scanner, Library library, LocalDate dueDate, ArrayList<Borrows> memberBorrow){
		this.members = members;
		this.scanner = scanner;
		this.library = library;
		this.dueDate = dueDate;
		this.memberBorrows = memberBorrow;
	}
	
	Member(String username, String password, int id){
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	
	String getUsername() {
		return this.username;
	}
	
	String getPassword() {
		return this.password;
	}
	
	int getId() {
		return this.id;
	}
	
	LocalDate dueDate() {
		return this.dueDate;
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
   	 
   	 
   	 for(Member i : members) {
   		 if(i.getUsername().equals(username) &&
   		    i.getPassword().equals(password)) {
   			System.out.println("**************LOGIN SUCCESSFULLY****************");
   			library.allFunction(i);
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
   			System.out.print("Enter ID: ");
   	
   			int id = 0;
   			
   			try {
   				id = Integer.parseInt(scanner.nextLine().trim());
   			} catch(NumberFormatException e) {
   				System.out.println("Invalid input!");
   			}
   			
   			library.memberCheckId(id);
   			signIn = false;
   	 }
   	 if(incorrect == 5) {
   		 System.out.println("Too many attempts!");
   		 signIn = false;
   	 }
    
    }
   }
    
	@Override
    void checkDetails(Member i) {
    	System.out.print("Username: ");
    	System.out.println(i.getUsername());
    	System.out.print("Password: ");
    	int length = i.getPassword().length();
    	String pass = "*";
    	System.out.println(pass.repeat(length));
    	System.out.print("Id: ");
    	System.out.println(i.getId());
    	int num = 0;
    	for(Borrows m : memberBorrows) {
    		if(m.getMember().equals(i)) {
    			num++;
    			System.out.print(num + ". " + m.getBorrowed().getTitle() + " by " + m.getBorrowed().getFirstname() + " " + m.getBorrowed().getLastname() + " published in " + m.getBorrowed().getPublish());
    			System.out.println(" ----- Due Date of Return: " + dueDate);
    }
   }
	}
}

