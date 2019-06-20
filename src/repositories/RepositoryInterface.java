package repositories;

import model.Swipe;

public interface RepositoryInterface {

    /**
     *
     * @param item
     */
    void add(Swipe item);

    /**
     *
     * @param id
     * @return
     */
    Swipe getItem(int id);

    //CollectionChoice<Swipe> getItems();

    /**
     *
     * @param id
     */
    
    void remove(int id);

    //void setItems(CollectionChoice<Swipe> items);

    /**
     *
     * @param filename
     */
    
    void store(String filename);

    /**
     *
     * @return
     */
    @Override
    String toString();
    
}
