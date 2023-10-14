package guillotineGame;

/**
 * class for card, for use in linked lists
 * @author rtp32
 */
public class Card {

    // private field for guillotineGame.ID enum of card
    private final ID id;


    /**
     * class constructor
     * @param id guillotineGame.ID enum of card
     */
    public Card(ID id) {
        this.id = id;
    }


    /**
     * public accessor method for guillotineGame.ID
     * @return the guillotineGame.ID of the card
     */
    public ID getID() {
        return id;
    }


    /**
     * makes sure point value isn't printed as "0"
     * @return String containing number of points
     */
    public String pointsString() {
        if (getID().getPoints() == 0)
            return "*";
        return getID().getPoints() + "";
    }


    /**
     * overridden toString() method, for use on big unclaimed buttons
     * @return long string for unclaimed buttons
     */
    @Override
    public String toString() {
        return getID().toString() + "\n" + pointsString();
    }


    /**
     * user collected cards need to be smaller and need more compact strings
     * @return a shorter string for user collected buttons
     */
    public String compactString() {
        return getID().toString() + " - " + pointsString();
    }

}
