
/**
 *
 * @author AmritPal
 */
public class Rectangle {

    private int width;
    private int length;

    public Rectangle(int width, int length) { // creates a rectangle
        this.width = width;
        this.length = length;
    }

    public String toString() { // returns a string of the rectangle
        return "width: " + width + " length: " + length;
    }

    public int getWidth() { // returns the width
        return width;
    }

    public int getLength() { // returns the length
        return length;
    }

    public boolean equals(Object obj) { // returns true or false that a two rectangles are equal
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Rectangle check = (Rectangle) obj;
        if (check.getLength() == this.length && check.getWidth() == this.width) {
            return true;
        }
        return false;
    }
}
