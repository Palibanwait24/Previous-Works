
/**
 *
 * ADT invariant of the DoubleLinkedSeq class
 *
 * 1. The elements of the sequence are stored in a linked list 2. If the list is
 * not empty, head refers to the first node in the list; otherwise it is the
 * null 3. If the list is not empty, the tail the last node in the list, else it
 * is the null 4. If there is a current element, that element is referred to by
 * cursor, otherwise, cursor is the null 5. If cursor != null and it is not the
 * head, then precursor refers to the node just before the cursor, else
 * precursor is the null 6. When a new element is added, it becomes the new
 * current element 7. if methods remove or advanced called, the following
 * element is the new current; if no element exists after, cursor = null
 *
 */
public class LinkedSequence<E> implements Cloneable {

    private Node head;
    private Node tail;
    private Node cursor;
    private Node precursor;
    private Node dummy = new Node(null, null);
    /**
     * creates a linked sequence with the node
     * @param head - a node
     */
    public LinkedSequence(Node head) {
        this.head = head;
        this.tail = Node.getTail(head);
        dummy.setLink(head);
    }
    /**
     * gets the head of the node and returns it
     * precondition is that there is a node
     * @return the head of the node
     */
    public Node getLsHead() {
        return head;
    }
    /**
     * set the head to the head
     * precondition there is a node
     * @param head 
     */
    public void setLsHead(Node head) {
        this.head = head;
    }
    /**
     * gets the tail reference of the linked sequence
     * @return the reference
     */
    public Node getLsTail() {
        return tail;
    }
    /**
     * sets the tail to the new tail reference
     * 
     * @param tail 
     */
    public void setLsTail(Node tail) {
        this.tail = tail;
    }
    /**
     * returns the cursor reference
     * @return 
     */
    public Node getCursor() {
        return cursor;
    }
    /**
     * sets the cursor to the new cursor reference
     * 
     * @param cursor is the new cursor
     */
    public void setCursor(Node cursor) {
        this.cursor = cursor;
    }
    /**
     * returns the precursor reference
     * @return 
     */
    public Node getPrecursor() {
        return precursor;
    }
    /**
     * sets the precursor to the new precursor reference
     * 
     * @param precursor is the new precursor
     */
    public void setPrecursor(Node precursor) {
        this.precursor = precursor;
    }
    /**
     * takes in a new data and adds it after the cursor to the linked sequence
     * @param inputData - new data of type e 
     * precondition - data is the same type as all other data in the node
     * @return - the newly amended node
     */
    public <E> Node addAfter(E inputData) {

        if (head == null) { // adds to head
            setCursor(dummy.addNodeAfter(inputData));
            setPrecursor(null);
            return head;
        } else if (cursor == null && head != null) { // adds to tail
            setPrecursor(getLsTail());
            setLsTail(Node.getTail(getLsTail().addNodeAfter(inputData)));
            return head;
        } else {// adds after cursor 
            setPrecursor(cursor);
            setCursor(cursor.addNodeAfter(inputData));
            setCursor(cursor.addNodeAfter(getCursor().getLink()));
            setCursor(precursor.getLink());
            return head;
        }
    }
    /**
     * takes in a new data and adds it before the cursor to the linked sequence
     * @param newData - new data of type e 
     * precondition - data is the same type as all other data in the node
     * @return - the newly amended node
     */
    public <E> Node addBefore(E newData) {
        Node temp;
        if (precursor == null) { // if null set it to the head
            temp = dummy.addNodeAfter(newData);
            temp.setLink(head);
            head = temp;
            tail = cursor;
            cursor = head;
            precursor = null;
            return temp;
        } else { //els set to before cursor
            temp = cursor;
            cursor = precursor.addNodeAfter(newData);
            cursor.setLink(temp);
            return temp;
        }

    }
    /**
     * adds all of the other linked sequence to the linked sequence
     * @param other - takes in a linkedSequcene to add
     */
    public void addAll(LinkedSequence other) {
        if (other == null || other.getLsTail() == null) { // if it is null stops
            
            return;
        } else if (this.head == null) {// else if the head is null adds the head and tail
            
            this.head = other.getLsHead();
            this.tail = other.getLsTail();
        } else { // sets the tail and head
            
            Node.getTail(head).addNodeAfter(other.getLsHead());
            this.setLsTail(other.getLsTail());
        }
    }

    /**
     * advances the cursor to the next node precondition is that the node is not
     * null post condition is that the curosr is set to the next node that has
     * data
     */
    public void advance() {
        if (cursor != null && cursor != tail) {// advances one place to the right
            precursor = cursor;
            cursor = cursor.getLink();
        } else if (cursor == null) { // maks the cursor the head
            cursor = head;
            precursor = null;
        } else {
            precursor = cursor; // makes the cursor null
            cursor = null;
        }

    }

    /**
     * clones the methods precondition is that the linked sequence is not null
     *
     * @return the clone of the linked sequence
     */
    public LinkedSequence clone() {
        try {
            LinkedSequence myClone = (LinkedSequence) super.clone(); // creates a new linkedSequence
            if (cursor == null) { // sets the clones variables
                myClone.setLsHead(Node.listCopy(head));
                myClone.setLsTail(Node.listCopy(Node.getTail(tail)));
                myClone.setCursor(null);
                myClone.setPrecursor(null);
                return myClone;
            } else { // sets the clones variables 
                myClone.setLsHead(Node.listCopy(head));
                myClone.setLsTail(Node.listCopy(Node.getTail(tail)));
                myClone.setPrecursor(null);
                myClone.setCursor(myClone.getLsHead());
                return myClone; // returns the clone
            }

        } catch (CloneNotSupportedException e) { // exception
            throw new RuntimeException("this class does not implement Cloneable.");
        }
    }

    /**
     * connects two linkedSequences staticly
     *
     * @param one- first linkedsequence
     * @param two -- second linkedSequence precondition is that neither is a
     * null linked sequence
     * @return - the newly connected linked sequence
     */
    public static LinkedSequence concatenate(LinkedSequence one, LinkedSequence two) {
        Node three = Node.listCopy(one.getLsHead()); // makes three a copy of one

        Node.getTail(three).addNodeAfter(Node.listCopy(two.getLsHead())); // adds the nodes after three

        LinkedSequence three3 = new LinkedSequence(three); // converts node to linke sequence
        return three3; // returns linkedSequence
    }

    /**
     * removes the cursor element and connects the rest of the nodes
     *
     * @return the new head node
     */
    public Node<E> removecurrent() {
        if (cursor == head) { // if the cursor is the head return the new head

            cursor = cursor.getLink(); // gets the link

            return head; // returns the head
        }
        if (precursor != null && precursor != tail) {
            precursor.removeNodeAfter();
            cursor = precursor.getLink();
            return head;
        }
        return this.head;
    }

    /**
     * displays the linked list
     *
     * @return the string of the head node
     */
    public String displayList() {
        return this.getLsHead().toString();
    }

    /**
     * returns true or false if the cursor is not null
     *
     * @return true or false
     */
    public boolean isCurrent() {
        return cursor != null;
    }

    /**
     * gets the data that is at the cursor precondition - is current is true
     *
     * @return - the data that is at the cursor
     */
    public E getCurrent() {
        if (isCurrent() == true) { // if isCurrent is ture then return the data 
            return (E) cursor.getData();
        } else {
            return null; // else return null
        }
    }

    /**
     * method that makes the head the cursor precondition cursor != null
     * postcondition - node is not null
     */
    public void start() {
        head = cursor; // sets head to cursor 
        precursor = null; // sets precuror to null
    }

}
