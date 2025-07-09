import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Library library = new Library(scanner); 
		
		boolean running = true;
		
		library.builtIn();

		while(running) {
			library.startPoint();
			System.out.println(" ");
			System.out.print("Do you want to continue: ");
			String doYou = scanner.nextLine().trim();
			if(doYou.equalsIgnoreCase("yes")) {
				running = true;
			} else if (doYou.equalsIgnoreCase("no")) {
				running = false;
				System.out.println("Thank You! Have a nice day! ");
			} else {
				System.out.println("INVALID!!!");
			}
		}
	}

}
