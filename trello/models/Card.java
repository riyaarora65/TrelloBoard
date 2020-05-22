package trello.models;

public class Card{

    private static int count = 0;
    private String Id;
	private String name;
	private String listID;
    private String description;
    private User assignedUser;

    public Card(String name, String description, String listId){
        this.name = name;
        this.description = description;
		this.Id = Integer.toString(count++);
		this.listID = listId;
		this.assignedUser = null;
    }

	public String getId() {
		return Id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getListID() {
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
	}

    
}