package trello.services;

import trello.models.Board;
import trello.models.BoardList;
import trello.models.Card;

public class TaskService {

    public static Board createBoard(String boardName, String privacy){
        Board board = new Board(boardName, privacy);
        return board;
    }

    public static BoardList createBoardList(String listName, String boardID){
        BoardList boardList = new BoardList(listName, boardID);
        return boardList;
    }

    public static Card createCard(String cardName, String cardDescription, String boardListId)
    {
        Card card = new Card(cardName, cardDescription, boardListId);
        return card;
    }


}