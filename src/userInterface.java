import java.util.Scanner;

public class userInterface {
	
	String addingMessage() {
		return ("***********ADDING BOOKS********");
	}
	
	String doneAdding() {
		 return ("***********BOOK ADDED SUCCESSFULY********");
	}
	
    void startingMenu() {
		 System.out.println("SAN JUAN LIBRARY");
		 System.out.println();
		 System.out.println("1: Add Books");
		 System.out.println("2: Return Books");
		 System.out.println("3: View all Books");
		 System.out.println("4: Borrow Books");
		 System.out.println("5: Check Details");
		 System.out.println("6: Exit");
    }
    
    String signIn() {
    	return ("**************SIGN IN****************");
    }
    
    String returning() {
    	return ("**************RETURNING BOOK**********");
    }
    
    String returnSuccess() {
    	return ("**************RETURN BOOK SUCCESSFULLY**************");
    }
    
    String promptString(String message, Scanner scanner) {
   	 System.out.print(message);
   	 return scanner.nextLine().trim();
    }
    
//A repetitive input interface if not written correctly
    int promptInt(String message, Scanner scanner) {
    while(true) {
      	System.out.print(message);
      	String input = scanner.nextLine().trim();
      	try {
      		return Integer.parseInt(input);
      	} catch (NumberFormatException e) {
      		System.out.println("Invalid Input");
      	}
       }
    }
    void back() {
    	System.out.println(" ");
    	System.out.print("back: ");
   }
    
//A reusable back press so it can just return true or false
    public boolean back(Scanner scanner) {
        System.out.print("back: ");
        String input = scanner.nextLine().trim();
        return input.equalsIgnoreCase("back");
    }

}
