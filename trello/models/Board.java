package trello.models;

import java.util.List;
import java.util.ArrayList;

public class Board
{
	private static int count = 0;
    String id;
    String name;
    String privacy;
    String url;
	List<User> members;
	List<BoardList> lists;

	public Board(String name, String privacy){
		this.name = name;
		this.privacy = privacy;
		this.id = Integer.toString(count++);
		this.url = name + "'/''"+ id;
		members = new ArrayList<User> ();
		lists = new ArrayList<BoardList> ();
	}
    
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
	public List<BoardList> getLists() {
		return lists;
	}
	public void setLists(List<BoardList> lists) {
		this.lists = lists;
	}
    
}

