package guillotineGame;

import java.util.Random;

/**
 * Non-javafx related methods for guillotineGame.Guillotine game
 * @author rtp32
 */
public class GuillotineFramework {

    // private field for user turn
    private static boolean isUser1Turn = true;

    // private field for list of unselected cards
    private static final CardList unclaimedList = new CardList();

    // private field for right side player list of cards
    private static final Card[] user1List = new Card[40];

    // private field for left side player list of cards
    private static final Card[] user2List = new Card[40];

    // private field for current index in user 1 card array
    private static int user1Index = -1;

    // private field for current index in user 2 card array
    private static int user2Index = -1;


    /**
     * public getter method for turn boolean
     * @return true if it is user 1's turn
     */
    public static boolean getIsUser1Turn() {
        return isUser1Turn;
    }


    /**
     * toggles whose turn it is
     */
    public static void toggleUserTurn() {
        isUser1Turn = !isUser1Turn;
    }


    /**
     * public getter method for unclaimed cards
     * @return list of unclaimed cards
     */
    public static CardList getUnclaimedList() {
        return unclaimedList;
    }


    /**
     * public getter method for user 1 cards
     * @return array of user 1 cards
     */
    public static Card[] getUser1List() {
        return user1List;
    }


    /**
     * public getter method for user 2 cards
     * @return array of user 2 cards
     */
    public static Card[] getUser2List() {
        return user2List;
    }


    /**
     * getter method for current index in user 1's card list
     * @return current index
     */
    public static int getUser1Index() {
        return user1Index;
    }


    /**
     * getter method for current index in user 2's card list
     * @return current index
     */
    public static int getUser2Index() {
        return user2Index;
    }


    /**
     * adds a card to the end of a user's list once they pick a card
     * @param list the user's list of cards
     */
    public static void addToUserList(Card[] list) {
        if (isUser1Turn)
            list[++user1Index] = getUnclaimedList().getFirstNode().getElement();
        else
            list[++user2Index] = getUnclaimedList().getFirstNode().getElement();

        getUnclaimedList().setFirstNode(getUnclaimedList().getFirstNode().getNext());

    }


    /**
     * makes array full of possible cards that may be in play
     * @return an array to be picked from
     */
    private static Card[] makePossibleArray() {
        return new Card[] {
            new Card(ID.KGL), new Card(ID.MA), new Card(ID.REG), new Card(ID.DUK), new Card(ID.BAR),
            new Card(ID.CT), new Card(ID.CTS), new Card(ID.LRD), new Card(ID.LDY), new Card(ID.CRD),
            new Card(ID.ARB), new Card(ID.NUN), new Card(ID.BSH), new Card(ID.PR), new Card(ID.PR),
            new Card(ID.HER), new Card(ID.GOV), new Card(ID.MAY), new Card(ID.CLM), new Card(ID.JDG),
            new Card(ID.JDG), new Card(ID.TXC), new Card(ID.SHF), new Card(ID.SHF), new Card(ID.PAL),
            new Card(ID.PAL), new Card(ID.PAL), new Card(ID.PAL), new Card(ID.PAL), new Card(ID.GEN),
            new Card(ID.COL), new Card(ID.CPT), new Card(ID.LUT), new Card(ID.LUT), new Card(ID.TRG),
            new Card(ID.HRO), new Card(ID.STD), new Card(ID.STD), new Card(ID.STD), new Card(ID.STD), null
        };
    }


    /**
     * returns a string saying who won
     * @return a string saying who won
     */
    public static String getWinner() {
        if (calculateScore(getUser1List()) > calculateScore(getUser2List()))
            return "Player 1 WINS!!";
        else if (calculateScore(getUser1List()) < calculateScore(getUser2List()))
            return "Player 2 WINS!!";
        return "DRAW";
    }


    /**
     * calculates a user's score
     * @param list the list of cards the player has
     * @return the player's score
     */
    public static int calculateScore(Card[] list) {
        int totalScore = 0;               // aggregate total score

        boolean hasCount = false;         // true if player has the count
        boolean hasCountess = false;      // true if player has the countess
        boolean hasLord = false;          // true if player has the lord
        boolean hasLady = false;          // true if player has the lady
        boolean hasHeretic = false;       // true if player has the heretic
        int numChurchCards = 0;           // amount of cards with guillotineGame.Group CHU/Church
        boolean hasTaxCollector = false;  // true if player has tax collector
        int numCivCards = 0;              // amount of cards with guillotineGame.Group CIV/Civic
        int numPalaceGuards = 0;          // amount of cards with guillotineGame.ID PAL/Palace Guard
        boolean hasTragic = false;        // true if player has the tragic figure
        int numCommonerCards = 0;         // amount of cards with guillotineGame.Group COM/Commoner

        for (Card c : list) {
            if (c != null) {
                // check first for group
                if (c.getID().getGroup() == Group.CHU)
                    numChurchCards++;
                else if (c.getID().getGroup() == Group.CIV)
                    numCivCards++;
                else if (c.getID().getGroup() == Group.COM)
                    numCommonerCards++;

                // check for specific roles
                if (c.getID() == ID.CT)
                    hasCount = true;
                else if (c.getID() == ID.CTS)
                    hasCountess = true;
                else if (c.getID() == ID.LRD)
                    hasLord = true;
                else if (c.getID() == ID.LDY)
                    hasLady = true;
                else if (c.getID() == ID.HER)
                    hasHeretic = true;
                else if (c.getID() == ID.TXC)
                    hasTaxCollector = true;
                else if (c.getID() == ID.PAL)
                    numPalaceGuards++;
                else if (c.getID() == ID.TRG)
                    hasTragic = true;
                else
                    totalScore += c.getID().getPoints();
            }
        }

        // add up special cases
        if (hasCount && hasCountess)
            totalScore += 4;
        else if (hasCount || hasCountess)
            totalScore += 2;

        if (hasLady && hasLord)
            totalScore += 4;
        else if (hasLady || hasLord)
            totalScore += 2;

        if (hasHeretic)
            totalScore += numChurchCards;
        if(hasTaxCollector)
            totalScore += numCivCards;
        if(hasTragic)
            totalScore -= numCommonerCards;

        totalScore += numPalaceGuards * numPalaceGuards;

        return totalScore;
    }


    /**
     * makes unclaimed list to begin the game
     * @param numCards number of cards, taken from cmd line args
     */
    private static void makeUnclaimedList(int numCards) {
        Card[] possibleArray = makePossibleArray();    // array of all possible cards to be in the game
        for (int i = 0; i < numCards; i++) {
            Random rand = new Random();                // random object
            int picked = rand.nextInt(40 - i);  // random number in the bounds of how many cards there should be
            getUnclaimedList().addToEnd(possibleArray[picked]);
            for (int j = picked; j < 40 - i - 1; j++)
                possibleArray[j] = possibleArray[j + 1];
        }
    }


    /**
     * main method for class guillotineGame.Guillotine
     * @param args potential number of cards to be in play, must 40 or less, defaults to 20
     */
    public static void main(String[] args) {
        int numCards = 20;  // number of unclaimed cards to start the game
        if (args != null && args.length > 0)
            numCards = Integer.parseInt(args[0]);
        makeUnclaimedList(numCards);
    }

}
