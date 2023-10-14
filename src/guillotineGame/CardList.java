package guillotineGame;

/**
 * A guillotineGame.LinkedList made up of guillotineGame.Card objects
 * Contains methods to be executed by buttons
 */
public class CardList extends LinkedList<Card> {


    /**
     * moves the first node back n spaces. n should be 5 or less but works up to list.length() - 1
     * @param n number of spaces for the first node to be moves
     */
    public void moveBack(int n) {
        if (length() > n && n > 0) {
            LLNode<Card> first = getFirstNode();  // first node save
            LLNode<Card> ptr = getFirstNode();    // pointer
            setFirstNode(ptr.getNext());
            while (n > 0) {
                ptr = ptr.getNext();
                n--;
            }

            first.setNext(ptr.getNext());
            ptr.setNext(first);
        }
    }


    /**
     * moves first node to end of list and shifts all other nodes over
     */
    public void moveFirstToLast() {
        moveBack(length() - 1);
    }


    /**
     * switches beginning and ending nodes, leaving all others unchanged
     */
    public void moveLastToFirst() {
        if (length() > 2) {
            LLNode<Card> ptr = getFirstNode();               // pointer
            LLNode<Card> second = getFirstNode().getNext();  // second node save

            for (int i = 0; i < length() - 2; i++) {
                ptr = ptr.getNext();
            }

            LLNode<Card> save = ptr.getNext();               // last node save

            ptr.setNext(getFirstNode());
            getFirstNode().setNext(null);
            setFirstNode(save);
            getFirstNode().setNext(second);
        }
        else if (length() == 2)
            moveBack(1);
    }


    /**
     * reverses entire list, front to back
     */
    public void reverseList() {
        LLNode<Card> prev = null;            // previous node pointer
        LLNode<Card> curr = getFirstNode();  // current node pointer
        LLNode<Card> next;                   // next node pointer

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        setFirstNode(prev);

    }


    /**
     * reverses first k nodes of guillotineGame.CardList
     * @param k number of nodes to be reversed
     */
    public void reverseFirstK(int k) {
        if (k <= length()) {
            LLNode<Card> prev = null;             // previous node pointer
            LLNode<Card> curr = getFirstNode();   // current node pointer
            LLNode<Card> next = null;             // next node pointer
            LLNode<Card> lim = getFirstNode();    // stopping point save

            for (int i = 0; i < k; i++) {
                lim = lim.getNext();
            }

            LLNode<Card> first = getFirstNode();  // first node save

            while (curr != lim) {
                next = curr.getNext();
                curr.setNext(prev);
                prev = curr;
                curr = next;
            }

            setFirstNode(prev);
            first.setNext(next);
        }
    }

}
