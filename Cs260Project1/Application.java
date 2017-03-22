/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AmritPal
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    static long squares = 0;
    static long occurrences = 0;

    public static void main(String[] args) {
        // tests all methods in Node Class
        // ******* uncomment next line to Test Node Methods
        // TestNode test = new TestNode();

    // ******* uncomment next line to test linkedSequence methods
        //TestLinkedLists();
        // uncomment first "/*" to unlock code
       /*
         // no copy variavbles
         long timeStart;
         long timeStop;
        
         Rectangle one;
         int size;
         Node<Rectangle> rectangleNode;
         Node rectangleNodeTail;
         LinkedSequence rectangleLs;
         Rectangle[] myArray;
         Node start;
         Rectangle target;        
         // create before each step
         long programStart1;
         long programEnd1;
         long stepOne1; 
         long stepTwo1;      
         long stepThree1;
         long stepFour1;
         long totalStep1;

         programStart1= System.currentTimeMillis();
         timeStart = System.currentTimeMillis();
        
         size= 10000000;  //***** change size of the list and rectangle array
         // *step one*
         one = new Rectangle(15,15);
         rectangleNode = new Node<>(one,null);
         rectangleNodeTail = rectangleNode; 
        
         for(int i = 1; i<size;i++){
         rectangleNodeTail = rectangleNodeTail.addNodeAfter(new Rectangle(15,15));
         }
         rectangleLs = new LinkedSequence(rectangleNode);
         
         timeStop = System.currentTimeMillis();
         stepOne1 = timeStop - timeStart;
         
         //*step two*
         timeStart = System.currentTimeMillis();
         if (Node.listPosition(rectangleLs.getLsHead(), size) ==(rectangleLs.getLsTail())){
         System.out.println("Step two verified");
         }
         else{
         System.out.println("Step Two NOT verified\n");
         }
         timeStop = System.currentTimeMillis();
         stepTwo1 = timeStop - timeStart;
        
        
         // *step three*
         timeStart = System.currentTimeMillis();
         myArray = new Rectangle[Node.listLength(rectangleLs.getLsHead())];
         start = rectangleLs.getLsHead();
         for(int i = 0; i < myArray.length; i++){
        
         myArray[i]= (Rectangle) start.getData();
         start = start.getLink();
         }
         timeStop = System.currentTimeMillis();
         stepThree1 = timeStop - timeStart;
        
         // step 4
         timeStart = System.currentTimeMillis();
         target = new Rectangle(15,15);
         counting(myArray, target);
         timeStop = System.currentTimeMillis();
         stepFour1 = timeStop - timeStart;
         programEnd1 = System.currentTimeMillis();
         totalStep1 = programEnd1 - programStart1;
         System.out.println("Code 1: " + size);
         System.out.println("==================");
         System.out.println("step 1 runtime: " + stepOne1);
         System.out.println("step 2 runtime: " + stepTwo1);
         System.out.println("step 3 runtime: " + stepThree1);
         System.out.println("step 4 runtime: " + stepFour1);
         System.out.println("total program1 run time: " + totalStep1 );
        
         /*
         Code 1: 100,000
         ===============
         step 1 runtime: 15
         step 2 runtime: 0
         step 3 runtime: 0
         step 4 runtime: 16
         total program1 run time: 31
        
         Code 2: 1,000,000
         ================
         step 1 runtime: 313
         step 2 runtime: 16
         step 3 runtime: 31
         step 4 runtime: 15
         total program1 run time: 375
        
         Code 3: 10,000,000
         ==================
         step 1 runtime: 13505
         step 2 runtime: 78
         step 3 runtime: 156
         step 4 runtime: 47
         total program1 run time: 13786
        
         Code 4: 10,000,000
         ==================
         step 1 runtime: 11438
         step 2 runtime: 62
         step 3 runtime: 141
         step 4 runtime: 47
         total program1 run time: 11688
        
        
         Analysis of runing time
        
        
         the running time if random or not random is not a significant 
         overhead since it only changes by two thousand miliseconds
       
         step one had the most time diffence between the all codes.
         the big O for step one is O(n).
         the big O for step three is also O(n).
         the big O for step four is also O(n).
        
         the big O for step two is just O(1).
         */
    }

    public static void counting(Rectangle[] rAngle, Rectangle target) {
        for (Rectangle el : rAngle) {
            if (el.getLength() == el.getWidth()) {
                squares++;
            }
            if (el.getLength() == target.getLength() && el.getWidth() == target.getLength()) {
                occurrences++;
            }
        }
    }

    public static void TestLinkedLists() {
        Node<String> lsNode1 = new Node<>("c", null);
        Node lsNode1Tail = lsNode1;
        String[] node1String = {"f", "e", "d"};
        for (String el : node1String) {
            lsNode1Tail.addNodeAfter(el);

        }
        LinkedSequence myList = new LinkedSequence(lsNode1);
        // test addBefore
        String[] beforeString = {"a"};
        for (String el : beforeString) {
            myList.setLsHead(myList.addBefore(el));
        }

        //test add after and display
        myList.setLsHead(myList.addAfter("b"));
        System.out.println(myList.displayList());

        // test addAll
        Node<String> lsNode2 = new Node<>("g", null);
        Node lsNode2Tail = lsNode2;
        String[] node2String = {"h", "i", "j"};
        for (String el : node2String) {
            lsNode2Tail.addNodeAfter(el);

        }
        LinkedSequence myList2 = new LinkedSequence(lsNode2);
        myList.addAll(myList2);
        System.out.println(myList.getLsHead() + "\n");
        // advances cursor;
        myList.advance();
        System.out.println(myList.getCursor() + "\n");

        // clones list and prints result
        LinkedSequence cloneList = myList.clone();
        System.out.println(cloneList.getLsHead().toString() + "\n");

        // concatonates lists
        LinkedSequence three = LinkedSequence.concatenate(myList, myList2);
        System.out.println(three.getLsHead().toString() + "\n");

        //remove currunt test
        three.removecurrent();
        System.out.println(three.getLsHead().toString() + "\n");

    }
}
