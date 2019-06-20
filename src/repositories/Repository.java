package repositories;

//import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.function.Predicate;
import model.Swipe;
import daos.DAOImpl;

//import model.Swipe;


public class Repository implements RepositoryInterface {
    private ArrayList<Swipe> items;    
    
    public Repository() {
        this.items = new ArrayList<>();       
    }
    
    public Repository(ArrayList<Swipe> items) {        
        this.items = items;
    }
    
    public Repository(String filename) {
        DAOImpl dao = new DAOImpl();
        this.items= dao.load(filename).items;
    }
    
    public ArrayList<Swipe> getItems(){        
        return this.items;
    }
    
    /**
     *
     * @param items
     */
    public void setItems(ArrayList<Swipe> items) {        
        this.items = items;
    }
    
    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }
       
    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = e->e.getId() == id;       
        this.items.removeIf(predicate);
    }
    
    @Override
    public Swipe getItem(int id) {
        for (Swipe item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    
    public String toString(char DELIMITER) {
       String end = "";
       for(Swipe swipe : items){
           end = end.concat(swipe.toString(DELIMITER) + "\n");
       }
       return end;
    }    
    
    @Override
    public void store(String filename) {       
        // execution of the dao 
      DAOImpl dao = new DAOImpl();
      dao.store(filename, this);
    }        
}
