
public class Book {
	
	private String firstName;
	private String lastName;
	private String title;
	private int publish;
	private String availability;
	
	Book(String firstName, String lastName, String title, int publish, String availability){
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.publish = publish;
		this.availability = availability;
	}
	
	String getFirstname() {
		return firstName;
	} 
	
	String getLastname() {
		return lastName;
	}
	
	String getTitle() {
		return title;
	}
	
	int getPublish() {
		return publish;
	}
	
	String getAvailability() {
		return availability;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}

//A reusable print prompt to easily print the interface for showing all the details of the book
	public String getDescription() {
	    return String.format(
	        "%s by %s %s published in %d - %s",
	        title, firstName, lastName, publish, availability
	    );
	}

}
