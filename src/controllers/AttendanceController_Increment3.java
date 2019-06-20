package controllers;

import helpers.InputHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import repositories.Repository;
import model.Swipe;
import static model.Swipe.SwipeDateTimeComparator;
import model.VisitorSwipe;
/**
 *
 * @author Michael Kofi Badu
 */
public class AttendanceController_Increment3 {
    private final Repository repository;
    
    /**
     *
     */
        
    public AttendanceController_Increment3() {
      InputHelper input = new InputHelper();
       char choice = input.readCharacter("Do you want to choose from an existing attendance file? (Y/N)");
       if(choice == 'Y' || choice == 'y'){
           String fileName = input.readString("Enter the filename here: ");
           repository = new Repository(fileName);
       }
       else
           repository = new Repository();
   }
   
    /**
     *
     */
    public void run() {
        boolean finished = false;
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesByCardIdOrderedByDateTime(); // 
                    break;                    
                case 'D': 
                    listSwipeStatistics(); //
                    break;
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");       
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }    
    
    private void addSwipe() {
        InputHelper input = new InputHelper();
        String myData  = " ";
  
        String choice = input.readString("What kind of swipe is this?  \n 1.Normal Swipe \t 2.Visitor Swipe");
        String cardId = input.readString("Enter the card Id");
        String room = input.readString("Enter your room number");
        switch(choice){
            case "1":
                Swipe swipe = new Swipe();
                swipe.setcardId(cardId);
                swipe.setRoom(room);
                this.repository.add(swipe);
            break;
            case "2":
           
                VisitorSwipe visSwipe = new   VisitorSwipe();
                visSwipe.setcardId(cardId);
                visSwipe.setRoom(room);
                visSwipe.setVisitorName(input.readString("Enter visitor name"));
                visSwipe.setVisitorCompany(input.readString("Enter visitor company"));
                this.repository.add(visSwipe);
            break;
            default:
                System.out.println("Invalid input! Please enter a different value");
            break;
        }
        this.repository.store("swipes.txt");
        
    }


        
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");
        for(Swipe swipe :repository.getItems()){
            System.out.println(swipe);
       }      
    }
      

    private void listSwipesByCardIdOrderedByDateTime() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
        String input = new InputHelper().readString("Enter card Id");       
        List<Swipe> items = new ArrayList<>();
        
         items = repository.getItems();
          Collections.sort(items,SwipeDateTimeComparator.reversed());
        for(Swipe swipe: items ){
            if(swipe.getCardId().equals(input)) 
            System.out.println(swipe.toString());
        } 
        
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
    }
}
