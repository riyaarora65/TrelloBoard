package trello.models;

import java.util.ArrayList;
import java.util.List;

public class BoardList{
    private static int count = 0;
    String Id;
    String name;
    List<Card> cards;

    public BoardList(String name){
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

}