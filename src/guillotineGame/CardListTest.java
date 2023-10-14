package guillotineGame;

import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * testing class for guillotineGame.CardList methods -> guillotineGame.Guillotine
 * @author rtp32
 */

public class CardListTest {

    // list of one card
    private static final CardList list1 = new CardList();

    // list of two cards
    private static final CardList list2 = new CardList();

    // list of 10 cards, to emulate in-progress game
    private static final CardList list3 = new CardList();

    // list of 20 cards, to emulate new default game
    private static final CardList list4 = new CardList();

    // list of 30 cards, to emulate new user-decided game initiation
    private static final CardList list5 = new CardList();


    /**
     * makes possible array like in guillotineGame.GuillotineFramework
     * @return array of all possible cards
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
                new Card(ID.HRO), new Card(ID.STD), new Card(ID.STD), new Card(ID.STD), new Card(ID.STD)
        };
    }


    /**
     * makes a cardList
     * @param c guillotineGame.CardList
     * @param numCards amount of Cards in guillotineGame.CardList c
     */
    private static void makeList(CardList c, int numCards) {
        Card[] possibleArray = makePossibleArray();  // array of all possible cards
        for (int i = 0; i < numCards; i++) {
            Random rand = new Random();  // random object
            int picked = rand.nextInt(39 - i);  // gets correct amount of random cards
            c.addToEnd(possibleArray[picked]);
            for (int j = picked; j < 40 - i - 1; j++)
                possibleArray[j] = possibleArray[j + 1];
        }
    }


    /**
     * refreshes all guillotineGame.CardList fields
     */
    private static void makeAllLists() {
        CardList[] list = new CardList[] {list1, list2, list3, list4, list5};  // list of all guillotineGame.CardList fields
        LLNode<Card> ptr;  // node pointer

        // clear lists
        for (CardList item : list) {
            ptr = item.getFirstNode();
            while (ptr != null) {
                item.removeFromFront();
                ptr = ptr.getNext();
            }
        }

        // repopulate lists
        makeList(list1, 1);
        makeList(list2, 2);
        makeList(list3, 10);
        makeList(list4, 20);
        makeList(list5, 30);
    }


    /**
     * test for moveBack() method
     */
    @Test
    public void testMoveBack() {
        makeAllLists();
        LLNode<Card> save;  // node pointer
        save = list1.getFirstNode();
        list1.moveBack(0);
        assertEquals(save, list1.getFirstNode());  // test no fail for list size 1
        list1.moveBack(3);
        assertEquals(save, list1.getFirstNode());  // test lack of functionality if k>length

        save = list2.getFirstNode();
        list2.moveBack(1);
        assertEquals(save, list2.getFirstNode().getNext());  // seeing if new first = second
        assertNotEquals(save, list2.getFirstNode());  // testing to make sure new first != first

        save = list3.getFirstNode();
        list3.moveBack(5);
        assertNotEquals(save, list3.getFirstNode());  // make sure it works for standard value 5
        assertEquals(save, list3.getFirstNode().getNext().getNext().getNext().getNext().getNext());
    }


    /**
     * tester for moveFirstToLast() method
     */
    @Test
    public void testMoveFirstToLast() {
        makeAllLists();
        LLNode<Card> save = list1.getFirstNode();  // save of first node
        LLNode<Card> ptr;  // pointer
        LLNode<Card> save2;  // second saved node
        list1.moveFirstToLast();
        assertEquals(save, list1.getFirstNode());  // nothing should happen to list length 1

        save = list2.getFirstNode();
        list2.moveFirstToLast();
        assertEquals(save, list2.getFirstNode().getNext());
        assertNotEquals(save, list2.getFirstNode());

        save = list3.getFirstNode();
        save2 = save.getNext();
        list3.moveFirstToLast();
        ptr = list3.getFirstNode();
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
        }
        assertEquals(save, ptr);  // should equal last node
        assertEquals(save2, list3.getFirstNode());  // second should be new first
        assertNotEquals(save, list3.getFirstNode());  // should not equal first node
        assertNotEquals(save, list3.getFirstNode().getNext().getNext().getNext());  // should not equal random node

        save = list4.getFirstNode();
        save2 = save.getNext();
        list4.moveFirstToLast();
        ptr = list4.getFirstNode();
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
        }
        assertEquals(save, ptr);  // should equal last node
        assertEquals(save2, list4.getFirstNode());  // second should be new first
        assertNotEquals(save, list4.getFirstNode());  // should not equal first node
        assertNotEquals(save, list4.getFirstNode().getNext().getNext().getNext().getNext());  // should not equal random
    }


    /**
     * test for moveLastToFirst() method
     * virtually the same as testMoveFirstToLast(), but assures no replacement
     */
    @Test
    public void testMoveLastToFirst() {
        makeAllLists();
        LLNode<Card> save = list1.getFirstNode();  // saved first node
        LLNode<Card> ptr;  // pointer
        LLNode<Card> save2;  // second saved node
        list1.moveFirstToLast();
        assertEquals(save, list1.getFirstNode());  // nothing should happen to list length 1

        save = list2.getFirstNode();
        list2.moveFirstToLast();
        assertEquals(save, list2.getFirstNode().getNext());
        assertNotEquals(save, list2.getFirstNode());

        save = list3.getFirstNode();
        save2 = save.getNext();
        list3.moveFirstToLast();
        ptr = list3.getFirstNode();
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
        }
        assertEquals(save, ptr);  // should equal last node
        assertEquals(save2, list3.getFirstNode());
        assertNotEquals(save, list3.getFirstNode());  // should not equal first node
        assertNotEquals(save, list3.getFirstNode().getNext().getNext().getNext());  // should not equal random node

        save = list4.getFirstNode();
        save2 = save.getNext();
        list4.moveFirstToLast();
        ptr = list4.getFirstNode();
        while (ptr.getNext() != null) {
            ptr = ptr.getNext();
        }
        assertEquals(save, ptr);  // should equal last node
        assertEquals(save2, list4.getFirstNode());  // second should be new first
        assertNotEquals(save, list4.getFirstNode());  // should not equal first node
        assertNotEquals(save, list4.getFirstNode().getNext().getNext().getNext().getNext());  // should not equal random

    }


    /**
     * test for reverseList() method
     */
    @Test
    public void testReverseList() {
        makeAllLists();
        LLNode<Card> ptr = list4.getFirstNode();  // pointer
        LLNode<Card> save1 = list4.getFirstNode();  // original first node
        LLNode<Card> save2;  // original random node
        LLNode<Card> save3;  // original last node

        for (int i = 0; i < 13; i++)
            ptr = ptr.getNext();
        save2 = ptr;

        while (ptr.getNext() != null)
            ptr = ptr.getNext();
        save3 = ptr;

        list4.reverseList();
        ptr = list4.getFirstNode();

        // now we test
        assertEquals(save3, ptr);  // test old and new first node
        assertNotEquals(save1, ptr);

        for (int i = 0; i < 6; i++)
            ptr = ptr.getNext();
        assertEquals(save2, ptr); // test to see if random was flipped

        for (int i = 0; i < 6; i++)
            ptr = ptr.getNext();
        assertNotEquals(save2, ptr);  // make sure pos has changed

        while (ptr.getNext() != null)
            ptr = ptr.getNext();
        assertEquals(save1, ptr);  // test old and new last node
        assertNotEquals(save3, ptr);

        // i'll test list5 for good measure

        makeAllLists();
        ptr = list5.getFirstNode();
        save1 = list5.getFirstNode();  // original first node
        save2 = null;  // original random node
        save3 = null;  // original last node

        for (int i = 0; i < 26; i++)
            ptr = ptr.getNext();
        save2 = ptr;

        while (ptr.getNext() != null)
            ptr = ptr.getNext();
        save3 = ptr;

        list5.reverseList();
        ptr = list5.getFirstNode();

        // now we test
        assertEquals(save3, ptr);  // test old and new first node
        assertNotEquals(save1, ptr);

        for (int i = 0; i < 3; i++)
            ptr = ptr.getNext();
        assertEquals(save2, ptr); // test to see if random was flipped

        for (int i = 0; i < 18; i++)
            ptr = ptr.getNext();
        assertNotEquals(save2, ptr);  // make sure pos has changed

        while (ptr.getNext() != null)
            ptr = ptr.getNext();
        assertEquals(save1, ptr);  // test old and new last node
        assertNotEquals(save3, ptr);

        // TA's, if you're reading this, thank you for all the work you've done this semester!!

    }


    /**
     * test for reverseFirstK() method
     */
    @Test
    public void testReverseFirstK() {
        makeAllLists();
        LLNode<Card> ptr = list4.getFirstNode();  // first test for value 5
        LLNode<Card> save1 = ptr;                 // saved node
        LLNode<Card> save2;                       // saved node
        LLNode<Card> save3;                       // saved node
        LLNode<Card> save4;                       // saved node

        for (int i = 0; i < 2; i++)
            ptr = ptr.getNext();
        save2 = ptr;
        for (int i = 0; i < 2; i++)
            ptr = ptr.getNext();
        save3 = ptr;
        for (int i = 0; i < 6; i++)
            ptr = ptr.getNext();
        save4 = ptr;

        list4.reverseFirstK(5);
        ptr = list4.getFirstNode();

        assertEquals(save3, ptr);
        assertNotEquals(save1, ptr);

        for (int i = 0; i < 2; i++)
            ptr = ptr.getNext();
        assertEquals(save2, ptr);

        ptr = ptr.getNext();
        assertNotEquals(save2, ptr);

        ptr = ptr.getNext();
        assertEquals(save1, ptr);
        assertNotEquals(save3, ptr);

        for (int i = 0; i < 6; i++)
            ptr = ptr.getNext();
        assertEquals(save4, ptr);

    }

}
