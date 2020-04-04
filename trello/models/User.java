package trello.models;

public class User
{
	private static int count = 0;
    String id;
    String name;
	String email;
	
	public User(String name, String email){
		this.id = Integer.toString(count++);
		this.name = name;
		this.email = email;
	}

	public String getid() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}    

}