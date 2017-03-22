
/**
 *
 * @author AmritPal
 */
public class Node<E> {

    private E data;
    private Node link;

    /**
     * initializes the constructor of the class
     *
     * @param data -- data of any type to fill the node
     * @param link -- the previous node to be linked to the new node
     * postCondition the new node contains the link to the data and a link to
     * the new node
     */
    public Node(E data, Node link) {
        this.data = data; // sets data to this.data
        this.link = link; // sets link to this.link
    }

    /**
     * gets the data from the node
     *
     * @return the data from the node
     */
    public E getData() {
        return data;    // returns the data 
    }

    /**
     * sets the link to the node
     *
     * @param link - new node to link
     */
    public void setLink(Node link) {
        this.link = link; // sets the link to link
    }

    /**
     * gets the link from the node
     *
     * @return the link from the node
     */
    public Node getLink() {
        return link; // returns the link
    }

    /**
     * adds a new node after this node
     *
     * @param element -- a element of the same type as previous
     * @return -- the newly modified node
     */
    public Node<E> addNodeAfter(E element) {

        if ((element.getClass()).equals(Node.class)) { // if teh class is equal to a node
            // then cast input to node and set to link and return

            link = (Node) element;
            return link;
        } else { // else create a new node with the element and use the link to be the link then return new node

            link = new Node(element, link);
            return link;
        }
    }

    /**
     * removes the current node by setting link to the link's link
     *
     * @return returns the link;
     */
    public Node removeNodeAfter() {
        link = link.link;
        return link;
    }

    /**
     * takes the node and copies the node to a new node
     *
     * @param source - head of a node
     * @return the newly created node reference;
     */
    public static Node listCopy(Node source) {
        Node copyHead; // creates new node 
        Node copyTail; // creates new node

        if (source == null) { // if the node is null then return null
            return null;
        }

        copyHead = new Node(source.data, null); // sets the head of copyhead to a new head 
        copyTail = copyHead; // sets node copytail to the head

        while (source.link != null) { // while the link is not null
            source = source.link; // add the link

            copyTail = copyTail.addNodeAfter(source.data); // add the node to copy tai
        }
        return copyHead; // return the head of the node
    }

    /**
     * lists the node at a certain position
     *
     * @param head - head of the node
     * @param position - position in node head that the user wants found
     * @return the node at the position
     */
    public static Node listPosition(Node head, int position) {
        Node cursor;
        int i;

        if (position <= 0) { // if wrong input throws error
            throw new IllegalArgumentException("position is not positive.");
        }
        cursor = head; // sets cursor to the head
        for (i = 1; (i < position) && (cursor != null); i++) { // finds the link
            cursor = cursor.link;
        }
        return cursor; // returns the node
    }

    /**
     * finds out how long the node is
     *
     * @param head - takes in the head of the node
     * @return the quantity of nodes in the node
     */
    public static int listLength(Node head) {
        Node cursor;
        int answer = 0;
        for (cursor = head; cursor != null; cursor = cursor.link) {// interates though the array to find the quantity
            answer++;
        }
        return answer; // returns the quantity of nodes
    }

    /**
     * takes in the start of a node and iterates to find the last node
     *
     * @param start - node that user wants the end of
     * @return - the last node link in the node
     */
    public static Node getTail(Node start) {
        Node tail;
        if (start == null) { // if the node is null return null
            return null;
        }

        while (start.link != null) {// goes through all the links till its null
            start = start.link;
        }

        return start; //returns the tail node
    }

    /**
     * returns a string representation of the nodes
     *
     * @return the string of the node
     *
     */
    public String toString() {
        String field1 = "";
        String field2 = "";
        if (data == null) { // if data is null then set the fild to dummy
            field1 = "dummy";
        } else {
            field1 = data.toString(); // gets the string
        }
        if (link == null) { // if link is null then puts null in tail
            field2 = "null in tail!";
        } else { // else gets teh tail
            field2 = link.toString();
        }
        return "data: " + field1 + "\n" + "link: " + field2; // makes a string of the values
    }
}
