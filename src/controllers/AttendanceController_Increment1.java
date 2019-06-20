package controllers;

import helpers.InputHelper;
import repositories.Repository;
import model.Swipe;
import model.VisitorSwipe;

/**
 *
 * @author Michael Kofi Badu
 */
public class AttendanceController_Increment1 {
    private final Repository repository;
    
    /**
     *
     */
        
    public AttendanceController_Increment1() {
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
       
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
    }
}
