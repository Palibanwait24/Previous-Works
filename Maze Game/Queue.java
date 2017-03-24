/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs260project2;

import java.util.NoSuchElementException;

/**
 *
 * @author AmritPal
 */
public class Queue {

    private int manyItems;
    private int front;
    private int rear;
    private Cell[] cellQueue;

    public Queue(int initialCapaicty) {
        cellQueue = new Cell[initialCapaicty];
        manyItems = 0;
    }
   

    private int nextIndex(int i) {
        if (++i == cellQueue.length) {
            return 0;
        } else {
            return i;
        }
    }

   public void ensureCapacity( int minimumCapacity ) {
      Cell[] biggerArray;
      int n1, n2;
      if ( cellQueue.length >= minimumCapacity )                                  // No change needed
         return;
      else if ( manyItems == 0 )   // Just increase the capacity ignoring array elements
         cellQueue = new Cell[ minimumCapacity ];
      else if ( front <= rear ) {                            // Create larger array and shift the data
         biggerArray = new Cell[ minimumCapacity ];
         System.arraycopy(cellQueue, front, biggerArray, front, manyItems);
         cellQueue = biggerArray;
      } else {                                // Create a bigger array with more attention to copying 
         biggerArray = new Cell[ minimumCapacity ];
         n1 = cellQueue.length - front;
         n2 = rear + 1;
         System.arraycopy(cellQueue, front, biggerArray, 0, n1);
         System.arraycopy(cellQueue, 0, biggerArray, n1, n2);
         front = 0;
         rear = manyItems-1;  
         cellQueue = biggerArray;
      }
   }


    public void enqueue(Cell item) {
        if (manyItems == cellQueue.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        if (manyItems == 0) {
            front = 0;
            rear = 0;
        } else {
            rear = nextIndex(rear);
        }
        cellQueue[rear] = item;
        manyItems++;
    }

    public Cell dequeue() {
        Cell answer;
        if (manyItems == 0) {
            throw new NoSuchElementException("Queue underflow");
        }
        answer = cellQueue[front];
        front = nextIndex(front);
        manyItems--;
        return answer;
    }
    
    public boolean isEmpty(){
        return(manyItems==0);
    }

}
