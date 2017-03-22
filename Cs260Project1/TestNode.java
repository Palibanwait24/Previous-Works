/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AmritPal
 */
public class TestNode {

    public TestNode() {
        Node<String> node1 = new Node<>("1", null);

        // test addNodeAfter Method utilizes tostring method for all printing
        Node node1tail = node1;
        String[] test1 = {"2", "3", "4", "5"};
        for (String el : test1) {
            node1tail = node1tail.addNodeAfter(el);
        }

        System.out.println("\n Node1 removeNodeAfter()");
        System.out.println("before remove on node1");
        System.out.println(node1.toString());
        System.out.println("\nafter remove 3 on node1");
        node1.getLink().removeNodeAfter();
        System.out.println(node1.toString() + "\n");

        // test list copy method
        Node<String> node1Copy = Node.listCopy(node1);
        System.out.println(node1Copy + "\n");

        // tests listpositon 
        Node myNode = Node.listPosition(node1, 3);
        System.out.println(myNode);

        // tests list length after 3 is removed
        int howlong = Node.listLength(node1);
        System.out.println("list is: " + howlong + " long");

        // test getTail
        System.out.println("\ntail node1 = " + Node.getTail(node1).toString());

    }
}
