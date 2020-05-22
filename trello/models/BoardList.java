package trello.models;

import java.util.ArrayList;
import java.util.List;

public class BoardList{
	private static int count = 0;
	String Id;
	String boardID;
    String name;
    List<Card> cards;

    public BoardList(String name, String boardId){
		this.boardID = boardId;
        this.Id = Integer.toString(count++);
        this.name = name;
        cards = new ArrayList<Card>();
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
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}

}