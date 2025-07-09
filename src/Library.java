import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Library {

	LocalDate date = LocalDate.now();
	Random random = new Random();
	private Scanner scanner;
	private Employee employee;
	private Member member;

	private ArrayList<Member> members = new ArrayList<>();
	private ArrayList<Borrows> memberBorrows = new ArrayList<>();
 	private ArrayList<Book> books = new ArrayList<>();
	private ArrayList<Book> matches = new ArrayList<>();
	private ArrayList<Employee> employees = new ArrayList<>();
	

	public Library(Scanner scanner){
		this.scanner = scanner;
		this.employee = new Employee(employees, scanner, members, memberBorrows, LocalDate.now().plusDays(5));
		this.member = new Member(members, scanner, this, LocalDate.now().plusDays(5), memberBorrows);
	}

	userInterface userInterface = new userInterface();

	

//A built in index in ArrayList to run a loop
	void builtIn() {
		books.add(new Book("George", "Orwell", "1984", 1949, "AVAILABLE"));
		books.add(new Book("Harper", "Lee", "To Kill a Mockingbird", 1960, "AVAILABLE"));
		books.add(new Book("J.K.", "Rowling", "Harry Potter and the Sorcerer's Stone", 1997, "AVAILABLE"));
		books.add(new Book("Jane", "Austen", "Pride and Prejudice", 1813, "AVAILABLE"));
		books.add(new Book("F. Scott", "Fitzgerald", "The Great Gatsby", 1925, "AVAILABLE"));
		books.add(new Book("Mary", "Shelley", "Frankenstein", 1818, "AVAILABLE"));
		books.add(new Book("Mark", "Twain", "Adventures of Huckleberry Finn", 1884, "AVAILABLE"));
		books.add(new Book("J.R.R.", "Tolkien", "The Hobbit", 1937, "AVAILABLE"));
		books.add(new Book("Leo", "Tolstoy", "War and Peace", 1869, "AVAILABLE"));
		books.add(new Book("Ernest", "Hemingway", "The Old Man and the Sea", 1952, "AVAILABLE"));
		members.add(new Member("rapha", "sansan", 1234));
		employees.add(new Employee("lyka", "abad"));
	}
	
	public void startPoint() {
		System.out.println("*********WELCOME TO SAN JUAN LIBRARY*********");
		
		if(askUser("Are you a member: ")) {
			member.checkMember();
		} else if(askUser("Are you an Employee: ")) {
			employee.checkMember();
		} else if(askUser("Do you want to be a member: ")) {
			memberShip();
		}
	}
	
//To easily use for asking question
     private boolean askUser(String question) {
		System.out.print(question);
		String answer = scanner.nextLine().trim();
		if(answer.equalsIgnoreCase("yes")) {
			return true;
		} else if (answer.equalsIgnoreCase("no")) {
			return false;
		} else {
			System.out.println("INVALID!!!");
			return askUser(question);
		}
	}

//To create an account
     void memberShip() {
    	 System.out.println("**************SIGN UP****************");
    	 String username = userInterface.promptString("Create Username: ", scanner);
    	 String password = userInterface.promptString("Create Password: ", scanner);
    	 int id = generateId();    	 
    	 idChecker(username, password, id);
   }
//To check if their there is same number of ID
     void idChecker(String username, String password, int id) {
    	 for(Member i : members) {
    		 if(i.getId() == id) {
    			id = generateId();
    		 } else {
    			 System.out.println("Your id number is: " + id);
    			 Member member = new Member(username, password, id);
    			 System.out.println("**************SIGN UP COMPLETE****************");
    			 members.add(member);
    			 break;
    		 }
    	 }
     }
//To check if there is a matching ID
     void memberCheckId(int id) {
    	 
    	 boolean checkAccount = false;
    	 
    	 for(Member i : members) {
    		 if(i.getId() == id) {
    			 System.out.println("**************LOGIN SUCCESSFULLY****************");
    			 allFunction(i);
    			 checkAccount = true;
    			 break;
    		 }
    	 }
    	 if(!checkAccount){
    			 System.out.println("Try again!");
    	 }
     }

//To generate a new ID
     int generateId() {
    	 int num = (int)(Math.random()* 2000) + 1;
    	 return num;
     }

     void allFunction(Member i) {
    	    boolean start = true;
    	    while(start) {
   
    	            userInterface.startingMenu();
    	            int userAns = userInterface.promptInt("Input: ", scanner);

    	            switch(userAns) {
    	                case 1 -> addBooks();
    	                case 2 -> returnBooks(i);
    	                case 3 -> viewAllBooks();
    	                case 4 -> borrowBooks(i);
    	                case 5 -> member.checkDetails(i);
    	                case 6 -> {
    	                    System.out.println("**************EXIT****************");
    	                    start = false;
    	                }
    	                default -> System.out.println("Invalid Option");
    	            }

    	            if (start) {
    	                start = userInterface.back(scanner);
    	         }
    	    }
    	}

     
     void addBooks() {
    	 System.out.println(userInterface.addingMessage());
    	 String firstName = userInterface.promptString("Author Firstname: ", scanner);
    	 String lastName = userInterface.promptString("Author Lastname: ", scanner);
    	 String title = userInterface.promptString("Book Title:  ", scanner);
    	 
    	 int published = 0;
    while(true) {
    	 try {
    		 System.out.print("Date Published: ");
    		 published = Integer.parseInt(scanner.nextLine().trim());
        	 books.add(new Book(firstName, lastName, title, published, "AVAILABLE"));
        	 System.out.println(userInterface.doneAdding());
        	 break;
    	 } catch (NumberFormatException e) {
    		 System.out.println("Invalid Input");
    	 }
     }
   }
//It calls the members borrow book and if the member doesnt have any borrow the target will stay null and the returning will not happen, but if there 
//is a borrow it stores into the target and change to Available again and removed to the checkdetails and view all book
     void returnBooks(Member i) {
    	 int num = 0;
    	 Borrows target = null;

     	 for(Borrows m : memberBorrows) {
    		if(m.getMember().equals(i)) {
    			target = m;
    			m.getBorrowed().setAvailability("Currently Borrowing");
    			num++;
    			System.out.print(num + ". " + m.getBorrowed().getDescription());
    			System.out.println(" ----- Due Date of Return: " + member.dueDate());
    		}
   }
     	 if(target == null) {
     		 System.out.println("You have no books to return!");
     	 } else {
      		
     		userInterface.returning();
     		
     		String title = userInterface.promptString("Title: ", scanner);
     		String firstName = userInterface.promptString("Firstname: ", scanner);
     		
     		if(title.equalsIgnoreCase(target.getBorrowed().getTitle()) && firstName.equalsIgnoreCase(target.getBorrowed().getFirstname())){
     			memberBorrows.remove(target);
     			for(Book m : books) {
     				if(m.getTitle().equalsIgnoreCase(target.getBorrowed().getTitle())) {
     					m.setAvailability("AVAILABLE");
     		      		System.out.println(userInterface.returnSuccess());
     					break;
     			}
     	  }	
     	  } else {
     		  System.out.println("No Match!");
     		
     	  }
      }  
    }
     
     void viewAllBooks() {
    	 int num = 0;
 
    	 for(Book i : books) {
    		 num++;
    		 System.out.println(num + ". " + i.getDescription());
    	}
    	 searchBook();
     }
     
 //Logic where you can borrow books and every time it runs it clears out to reset the matches size and use the arraylist void to automatically stores the match of the book
     void borrowBooks(Member member) {
    	 	matches.clear();
    	 	
    	    String title = userInterface.promptString("Title: ", scanner);
    	    matches = findBooksByTitle(title);
    	    
    	    boolean check = false;
    	    
    	    for(Book i : matches) {
    	    	if(!i.getAvailability().equalsIgnoreCase("borrowed")) {
    	    		System.out.println(matches.size() + ". " + i.getDescription());
    	    		check = true;
    	    	}
    	       }
    	    
    	    if(check) {
    	    	borrowService(member);
    	    } else {
    	    	System.out.println("The Book is already Borrowed! ");
    	    }
    	    
    	}

     
     void searchBook() {
    	 System.out.println(" ");
    	 System.out.print("Search ");
    	 String title = userInterface.promptString("Title: ", scanner);
    	 
    	 int num = 0;
 	    
    	 boolean noSearch = false;
 	    for(Book i : books) {
 	    	if(i.getTitle().equalsIgnoreCase(title)) {
 	    		num++;
 	    		noSearch = true;
 	    		System.out.println(num + ". " + i.getDescription());
 	    	}
 	    }
 	    if(!noSearch) {
 	    	System.out.println("There is no Available!");
 	    }
     }
     
//The logic where you can you choose what number you will borrow and the book index will store in the memberBorrows arrayList
     void borrowService(Member member) {
    	 
    	 if(matches.isEmpty()) {
 	    	System.out.println("There is no stocks!");
 	    } else {
 	    
 	    int number = 0;
 	    
 	    while(true) {	
 	    	try {
 	    		System.out.print("Choose what number: ");
 	    		String userInput = scanner.nextLine();
 	    		number = Integer.parseInt(userInput);
 	     	    if(number >= 1 && number <= matches.size()) {
 	     	    	Book m = matches.get(number - 1);
 	     	    	
 	     	    	m.setAvailability("BORROWED");
 	     	    	
 	     	    	System.out.println("You borrowed " + m.getDescription());
 	     	    	System.out.println("***********BOOK BORROWED SUCCESSFULY********");
 	     	 
 	     	    	Borrows currentMember = new Borrows(member, m);
 	     	    	memberBorrows.add(currentMember);
 	     	    	break;
 	     	    }
 	    	} catch (NumberFormatException e) {
 	    		System.out.println("Invalid Input!");
 	    	}
 	    }
 	    }
     }
     
//It returns an index that is equal to the title that is being borrowed, and stores it in matches
     ArrayList<Book> findBooksByTitle(String title){
 		ArrayList<Book> result = new ArrayList<>();
 
 		for(Book i : books) {
 			if(i.getTitle().equalsIgnoreCase(title)) {
 				result.add(i);
 			}
 		}
 		return result;
 	}
}