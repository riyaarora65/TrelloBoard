package trello.services;

import trello.models.Board;
import trello.models.Card;
import trello.models.User;
import trello.models.BoardList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    List<User> userMap;
    List<Board> boardMap;
    //boardid, boardlis
    Map<String, BoardList> boardListMap;
    //boardid, listid, card
    Map<String, Card>  cardListMap;

    
    public TaskManager(){
        userMap = new ArrayList<User>();
        boardMap = new ArrayList<Board>();
        boardListMap = new HashMap<String,BoardList>();
        cardListMap = new HashMap<String, Card>();
    }


    public void addUser(String name, String email){
        userMap.add(new User(name, email));
    }

    public User getUserByName(String userName){
        User matchedUser = null;
        for(User user: userMap){
            if((user.getName()).equals(userName))
                matchedUser =  user;
        }

        if(matchedUser == null)
            throw new NullPointerException("User does not exist");
        else
            return matchedUser;

    }

    public void createBoard(String boardName, String privacy){
        Board board = new Board(boardName, privacy);
        boardMap.add(board);
    }

    public void addMemberToBoard(String boardName, String userName){
        List<User> members;

        for(Board board: boardMap){
            if((board.getName()).equals(boardName))
            {
                members = board.getMembers();
                if(members == null)
                    members = new ArrayList<User>();

                members.add(getUserByName(userName));
                board.setMembers(members);        
            }
        }
    }

    public void removeMemberfromBoard(String boardName, String userName){
        List<User> members;

        for(Board board: boardMap){
            if((board.getName()).equals(boardName))
            {
                members = board.getMembers();
                members.remove(getUserByName(userName));

                if((members.size()) == 0)
                    members = null;
                board.setMembers(members);        
            }

        }
    }

    public void deleteBoard(String boardName){
        for(Board board: boardMap){
            if((board.getName()).equals(boardName)){
                deleteAllListsFromBoard(board);
                boardMap.remove(board);
            }
            else 
                throw new IllegalArgumentException("Board does not exists");
        }
    }

    public void deleteAllListsFromBoard(Board board){
        if(boardListMap.containsKey(board.getId()))
            boardListMap.remove(board.getId());
    }

    public Board getBoardByName(String boardName){
        Board matchedboard = null;
        for(Board board: boardMap){
            if((board.getName()).equals(boardName))
            {
                matchedboard = board;
                break;
            }
        }
        if(matchedboard == null)
            throw new IllegalArgumentException("Board does not exists");
        return matchedboard;
    }

    public void createList(String listName, String boardName)
    {

        String boardID = (getBoardByName(boardName)).getId();
        BoardList boardList = new BoardList(listName);
        boardListMap.put(boardID, boardList);
    }

    public void deleteList(String listname, String boardName)
    {
        String boardID = (getBoardByName(boardName)).getId();
        for(Map.Entry<String, BoardList> currentBoardListMap : boardListMap.entrySet()){
            if( ((currentBoardListMap.getKey()).equals(boardID)) &&
                ((currentBoardListMap.getValue().getName()).equals(listname)) )
            {
                boardListMap.remove(currentBoardListMap.getKey());
                break;
            }
        }
    }

    public BoardList getListByName(String listName, String boardName)
    {
        BoardList matchedList = null;
        String boardID = (getBoardByName(boardName)).getId();
        for(Map.Entry<String, BoardList> currentBoardListMap : boardListMap.entrySet())
        {
            if( ( ((currentBoardListMap.getValue()).getName()).equals(listName)) && 
                  ((currentBoardListMap.getKey()).equals(boardID)) )
            {
                matchedList = currentBoardListMap.getValue();
                break;
            } 
        }
        if(matchedList == null)
            throw new IllegalArgumentException("List does not exits");
        return matchedList;
    }

    public void createCard(String cardName, String cardDescription, String boardName, String listName)
    {
        Card card = new Card(cardName, cardDescription);
        BoardList boardList = getListByName(listName, boardName);
        cardListMap.put(boardList.getId(), card);
    }

    public void getCardByName(String cardName, String listName, String boardName)
    {
        Card matchedcard = null;
        String listID = (getListByName(listName, boardName)).getId();
        for(Map.Entry<String, BoardList> currentBoardListMap : boardListMap.entrySet())
        {
            if( ( ((currentBoardListMap.getValue()).getName()).equals(listName)) && 
                  ((currentBoardListMap.getKey()).equals(boardID)) )
            {
                matchedList = currentBoardListMap.getValue();
                break;
            } 
        }
        if(matchedList == null)
            throw new IllegalArgumentException("List does not exits");
        return matchedList;

    }

    public void assignCardToUser(String cardName, String userName)
    {
        
    }

    public void unassignUserfromCard(String cardName, String userName){

    }

    public void deleteCard(String cardName, String listName){

    }

    public void showBoard(String boardName){

    }

    public void showAllBoards(){

    }

    public void showList(String listName, String boardName){

    }

    public void showBoardLists(String boardName){

    }

    public void showCard(String cardName, String listName){

    }

    public void showListCards(String listName){

    }

}