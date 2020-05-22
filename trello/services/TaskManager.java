package trello.services;

import trello.services.TaskService;
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
    List<Card> cardMap;
    List<BoardList> boardListMap;

    
    public TaskManager(){
        userMap = new ArrayList<User>();
        boardMap = new ArrayList<Board>();
        cardMap = new ArrayList<Card>();
        boardListMap = new ArrayList<BoardList>();
    }


    public void addUser(String name, String email){
        userMap.add(new User(name, email));
    }

    public User getUserByName(String userName){
        User matchedUser = null;
        for(User user: userMap){
            if((user.getName()).equals(userName))
                matchedUser =  user;
                System.out.println("matched"+matchedUser.getName());
        }

        if(matchedUser == null)
            throw new NullPointerException("User does not exist");
        else
            return matchedUser;

    }

    public void createBoard(String boardName, String privacy){
        Board board = TaskService.createBoard(boardName, privacy);
        boardMap.add(board);
    }

    public void addMemberToBoard(String boardName, String userName){
        List<User> members;

        for(Board board: boardMap){
            if((board.getName()).equals(boardName))
            {
                members = board.getMembers();
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

    public void deleteAllListsFromBoard(Board board)
    {
        for(BoardList boardList: boardListMap){
            if(boardList.getBoardID().equals(board.getId())){
                deleteAllCardsFromList(board, boardList);
                boardListMap.remove(boardList);
            }
        }
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

        Board board = getBoardByName(boardName);
        List<BoardList> lists = board.getLists();
        BoardList boardList = TaskService.createBoardList(listName, board.getId());
        lists.add(boardList);
        board.setLists(lists);
        boardListMap.add(boardList);
    }

    public void deleteList(String listname, String boardName)
    {
        Board board = getBoardByName(boardName);
        List<BoardList> lists = board.getLists();
        for(BoardList boardList : boardListMap){
            if( (boardList.getBoardID()).equals(board.getId()) && 
                boardList.getName().equals(listname) )
            {
                lists.remove(boardList);
                deleteAllCardsFromList(board, boardList);
                boardListMap.remove(boardList);
                break;
            }
        }
        board.setLists(lists);
    }

    public BoardList getListByName(String listName, String boardName)
    {
        String boardId = getBoardByName(boardName).getId();
        BoardList matchedList = null;
        for(BoardList boardList: boardListMap){
            if((boardList.getName()).equals(listName) && 
                (boardList.getBoardID().equals(boardId))  
            )
            {
                matchedList = boardList;
                break;
            }
        }
        if(matchedList == null)
            throw new IllegalArgumentException("List does not exists");
        return matchedList;
    }

    public void createCard(String cardName, String cardDescription, String boardName, String listName)
    {
        BoardList boardList = getListByName(listName, boardName);
        Card card = TaskService.createCard(cardName, cardDescription, boardList.getId());
        List<Card> cards = boardList.getCards(); 
        cards.add(card);
        boardList.setCards(cards);
        cardMap.add(card);
    }

    public Card getCardByName(String cardName, String listName, String boardName)
    {
        Card matchedcard = null;
        String listID = (getListByName(listName, boardName)).getId();
        for(Card card: cardMap)
        {
            if( ( (card.getListID().equals(listID))) && 
                  ( (card.getName()).equals(cardName) )) 
            {
                matchedcard = card;
                break;
            } 
        }
        if(matchedcard == null)
            throw new IllegalArgumentException("Card does not exits");
        return matchedcard;

    }

    public void assignCardToUser(String cardName,String listName, String boardName, String userName)
    {
        Card card = getCardByName(cardName, listName, boardName);
        User user = getUserByName(userName);
        card.setAssignedUser(user);
    }

    public void unassignUserfromCard(String cardName,String listName, String boardName){
        Card card = getCardByName(cardName, listName, boardName);
        card.setAssignedUser(null);
    }

    public void deleteCard(String cardName, String listName, String boardName)
    {
        BoardList list = getListByName(listName, boardName);
        Card card = getCardByName(cardName, listName, boardName);
        cardMap.remove(card);
        List<Card> cards = list.getCards();
        cards.remove(card);
        list.setCards(cards);
    }


    public void deleteAllCardsFromList(Board board, BoardList boardList)
    {
        for(BoardList list: boardListMap)
        {
            if( ((list.getName()).equals(boardList.getName())) &&
                ( ( list.getBoardID() ).equals( board.getId() ) ) )
                {
                    List<Card> cards = list.getCards();
                    for(Card card: cards){
                        cardMap.remove(card);
                    }
                    cards.clear();
                    list.setCards(cards); 
                }
        }
    }


    public void showBoard(String boardName)
    {
        Board board = getBoardByName(boardName);
        printBoard(board);
    }

    public void printBoardMembers(Board board){
        System.out.println("Board Members are: ");
        List<User> members = board.getMembers();
        for(User user: members){
            System.out.println("Name: " + user.getName() );
            System.out.println("Email: "+ user.getEmail());
        }
    }

    public void printBoardLists(Board board){
        System.out.println("Board Lists are: ");
        List<BoardList> lists = board.getLists();
        for(BoardList boardList: lists){
            printList(boardList);
        }
    }

    public void printBoardListCards(BoardList boardList){
        System.out.println("Cards are: ");
        List<Card> cards = boardList.getCards();
        for(Card card: cards){
            printCard(card);
        }
    }

    public void printBoard(Board board){
        System.out.println("Board Name: "+board.getName());
        System.out.println("Board privacy: "+ board.getPrivacy());
        System.out.println("Board Url: "+board.getUrl());
        printBoardMembers(board);
        printBoardLists(board);
    }

    public void showAllBoards()
    {
        for(Board board: boardMap)
            printBoard(board);
    }

    public void printList(BoardList boardList){
        System.out.println("List name is: "+ boardList.getName());
        printBoardListCards(boardList);
    }

    public void showList(String listName, String boardName)
    {
        String boardId = getBoardByName(boardName).getId();
        for(BoardList boardList: boardListMap){
            if((boardList.getName()).equals(listName) && 
                (boardList.getBoardID().equals(boardId))  
            ){
                printList(boardList);
            }
        }
    }

    public void showBoardLists(String boardName)
    {
        Board board = getBoardByName(boardName);
        printBoardLists(board);

    }

    public void printCard(Card card)
    {
        System.out.println("updated");
        System.out.println("Card Name is: "+ card.getName());
        System.out.println("Card Description is: "+ card.getDescription());
        //this can be null and give null pointer exception
        if(card.getAssignedUser() != null){
            System.out.println("Card assigned user is: "+ card.getAssignedUser().getName());
        }
        else{
            System.out.println("Card not assigned");
        }
    }

    public void showCard(String cardName, String listName, String boardName)
    {
        Card card = getCardByName(cardName, listName, boardName);
        printCard(card);
    }

    public void showListCards(String listName, String boardName)
    {
        System.out.println("List is: "+listName);
        BoardList boardList = getListByName(listName, boardName);
        printBoardListCards(boardList);
    }

}