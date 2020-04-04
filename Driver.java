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
            String[] inputs = input.split(" ");
            String command = inputs[1];

            switch(inputs[0])
            {
                case "BOARD":

                    if(command.equals("CREATE")){
                        taskManager.createBoard(inputs[2], inputs[3]);
                    }

                    if(command.equals("ADD_MEMBER")){
                        taskManager.addMemberToBoard(inputs[2], inputs[3]);
                    }
                    
                    if(command.equals("REMOVE_MEMBER")){
                        taskManager.removeMemberfromBoard(inputs[2], inputs[3]);
                    }

                    if(command.equals("DELETE")){
                        taskManager.deleteBoard(inputs[2]);
                    }
                break;

                case "LIST":

                //   list name , board name
                    if(command.equals("CREATE")){
                        taskManager.createList(inputs[2], inputs[3]);
                    }
                
                //list name, board name
                    if(command.equals("DELETE")){
                        taskManager.deleteList(inputs[2], inputs[3]);
                    }
                break;

                // card name, description, boardname, listname
                case "CARD": 

                     if(command.equals("CREATE")){
                        taskManager.createCard(inputs[2], inputs[3], inputs[4], inputs[5]);
                    }

                //cardname, user

                    if(command.equals("ASSIGN")){
                        taskManager.assignCardToUser(inputs[2], inputs[3]);
                    }
                    
                    if(command.equals("UNASSIGN")){
                        taskManager.unassignUserfromCard(inputs[2], inputs[3]);
                    }
                
                //cardname, listname

                    if(command.equals("DELETE")){
                        taskManager.deleteCard(inputs[2], inputs[3]);
                    }
                break;
                
                case "SHOW":
                    switch(command){
                        case "BOARD":
                            if(inputs[2]!= ""){
                                taskManager.showBoard(inputs[2]);
                            }
                            else
                                taskManager.showAllBoards();
                        break;
                        
                        case "LIST":
                            
                            if(inputs[3]!= ""){
                                //this is listname, boardname
                                taskManager.showList(inputs[2], inputs[3]);
                            }
                            else
                                taskManager.showBoardLists(inputs[2]);
                            break;
                        
                        case "CARD": 
                            if(inputs[3] != "")
                            //card name, listname
                                taskManager.showCard(inputs[2], inputs[3]);
                            else
                                taskManager.showListCards(inputs[2]);
                            break;

                    }
                break;
            }
        }
    }
}