package trello;

import java.util.Scanner;

import trello.services.TaskManager;

public class Driver {

    public static void main(String args[]){

        TaskManager taskManager = new TaskManager();
        taskManager.addUser("riya", "riya@gmail.com");
        taskManager.addUser("priya", "priya@gmail.com");
        taskManager.addUser("kriya", "kriya@gmail.com");
        taskManager.addUser("siya", "siya@gmail.com");
        taskManager.addUser("diya", "diya@gmail.com");
        taskManager.addUser("tiya", "tiya@gmail.com");
        taskManager.addUser("shriya", "shriya@gmail.com");
        taskManager.addUser("piya", "piya@gmail.com");
        taskManager.addUser("jiya", "jiya@gmail.com");
        taskManager.addUser("kiya", "kiya@gmail.com");


        Scanner sc = new Scanner(System.in);
        while(true){
            String input = sc.nextLine();
            String[] commands = input.split(" ");
            String command = commands[1];

            int commandLength = commands.length;
            
            switch(commands[0])
            {
                case "BOARD":

                    if(command.equals("CREATE")){
                        taskManager.createBoard(commands[2], commands[3]);
                    }

                    if(command.equals("ADD_MEMBER")){
                        taskManager.addMemberToBoard(commands[2], commands[3]);
                    }
                    
                    if(command.equals("REMOVE_MEMBER")){
                        taskManager.removeMemberfromBoard(commands[2], commands[3]);
                    }

                    if(command.equals("DELETE")){
                        taskManager.deleteBoard(commands[2]);
                    }
                break;

                case "LIST":

                //   list name , board name
                    if(command.equals("CREATE")){
                        taskManager.createList(commands[2], commands[3]);
                    }
                
                //list name, board name
                    if(command.equals("DELETE")){
                        taskManager.deleteList(commands[2], commands[3]);
                    }
                break;

                // card name, description, boardname, listname
                case "CARD": 

                     if(command.equals("CREATE")){
                        taskManager.createCard(commands[2], commands[3], commands[4], commands[5]);
                    }

                //cardname,listname, boardname, user

                    if(command.equals("ASSIGN")){
                        taskManager.assignCardToUser(commands[2], commands[3], commands[4], commands[5]);
                    }
                    
                    if(command.equals("UNASSIGN")){
                        taskManager.unassignUserfromCard(commands[2], commands[3], commands[4]);
                    }
                
                //cardname, listname, boardname

                    if(command.equals("DELETE")){
                        taskManager.deleteCard(commands[2], commands[3], commands[4]);
                    }
                break;
                
                case "SHOW":
                    switch(command){
                        case "BOARD":
                            if(commandLength > 2){
                                taskManager.showBoard(commands[2]);
                            }
                            else
                                taskManager.showAllBoards();
                        break;
                        
                        case "LIST":
                            
                            if(commandLength > 3){
                                //this is listname, boardname
                                taskManager.showList(commands[2], commands[3]);
                            }
                            else
                                taskManager.showBoardLists(commands[2]);
                            break;
                        
                        case "CARD": 
                            if(commandLength > 4)
                            //card name, listname, boardName
                                taskManager.showCard(commands[2], commands[3], commands[4]);
                            else
                                taskManager.showListCards(commands[2], commands[3]);
                            break;

                    }
                break;
            }
        }
    }
}


//edge cases like
//try to assign a card who is not member of that board
//list is empty
//cards are not ther
//assigned user is not there
//list not part of that board
//card not part of that list
//not a valid input
///empty string printed
//aray out of bound for inputs
