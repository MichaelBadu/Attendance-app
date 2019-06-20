package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import static model.Swipe.formatSwipeDateTime;

/**
 *
 * @author mga
 */
public class Swipe {

    /**
     *
     */
    protected final int id;

    /**
     *
     */
    protected String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;
    
    private static int lastSwipeIdUsed = 0;
    static final char EOLN='\n';       
    static final String QUOTE="\""; 
    
    //public static Comparator<Swipe> SwipeDateTimeComparator;
    
    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    }    
    
    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     
    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }
    
    // Methods required: getters, setters, hashCode, equals, compareTo, comparator
    public String getCardId(){
        return this.cardId;
    }
    
    public void setcardId(String cardId){
    this.cardId = cardId;
    }
    
    public String getRoom(){
    return this.room;
   }
    
    public String Room(){
    return this.room;
    }
    
   public void setRoom(String room){
   this.room = room;
   }
   
   public Calendar getSwipeDate(){
   return this.swipeDateTime;
   }
   
   
    /**
     *
     * @param calendar
     * @return
     */
        
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    }    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    

public String toString(char delimiter){
return Integer.toString(this.id) + delimiter
        + QUOTE + this.cardId + QUOTE + delimiter
        + QUOTE + this.room + QUOTE + delimiter 
        + QUOTE + formatSwipeDateTime(this.swipeDateTime)+ QUOTE;
}

@Override
public int hashCode(){
 return getId() * 31 + getCardId().hashCode() * 31 +  getRoom().hashCode() * 31;
}

@Override
public boolean equals(Object o){
if (o instanceof Swipe) {
            Swipe e = (Swipe)o;
            return  (e.getCardId() == null ? getCardId() == null : e.getCardId().equals(getCardId())) && e.getRoom().equals(getRoom()) && e.getId() == (this.getId());
        } else {
            return false;
        }
}

public int compareTo(Swipe compareSwipe){
  return this.id - compareSwipe.getId();
  }
 public static Comparator<Swipe> SwipeDateTimeComparator
         = new Comparator<Swipe>(){
    @Override         
    public int compare( Swipe swipe1 , Swipe swipe2){
        return swipe1.swipeDateTime.compareTo(swipe2.swipeDateTime);
    }
         };
}


