/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;


import static com.sun.javafx.util.Utils.stripQuotes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

public class DAOImpl implements DAOInterface {
       
    
       public Repository load(String filename){
           Repository repo = new Repository();
           ArrayList<Swipe> items = new ArrayList<Swipe>();
           String data;
           
          
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            data = reader.readLine();
             while(data != null){
                 Calendar cal = Calendar.getInstance();
                 String[] tmpData = data.split(",");
                 int id = Integer.parseInt(tmpData[0]);
                 String cardId = stripQuotes(tmpData[1]);
                 String room = stripQuotes(tmpData[2]);
                 
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try {
                    Date date = dateFormat.parse(stripQuotes(tmpData[3]));
                    cal.setTime(date);
                } catch (ParseException ex) {
                    Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(tmpData.length == 4) {
                    Swipe mySwipe = new Swipe(id, cardId, room, cal);
                    items.add(mySwipe);
                }
                else {
                    String visitor = stripQuotes(tmpData[4]);
                    String company = stripQuotes(tmpData[5]);
                    VisitorSwipe visitorSwipe = new VisitorSwipe(id, cardId, room, cal, visitor, company);
                   items.add(visitorSwipe);
               }
                data = reader.readLine();
              
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException ex) {
               Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
        repo.setItems(items);
        return repo;
       
    }
       
       private String stripQuotes(String param) {
           return param.substring(1,param.length()-1);
       }
          
       @Override
       public void store(String filename, Repository repository){
           char DELIMITER = ',';
           try {
            FileWriter writeOut = new FileWriter(filename);
            writeOut.write(repository.toString(DELIMITER));
            writeOut.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        }
       }
       
}

