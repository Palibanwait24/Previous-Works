package banwas01_lab10;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setVisible(true);

	}
}

class MyFrame extends JFrame {
	public MyFrame() {
		MyPanel panel = new MyPanel();
		add(panel);
		setSize(630,430);

	}
}

class ImageArray {
	int w;
	int h;
	int size;
	Square[][] squares;
	MyPanel p;

	public ImageArray(String fileName, int size, MyPanel p) {
		this.p = p;
		this.size = size;
		squares = new Square[size][size];
		try {

			BufferedImage image = ImageIO.read(new File(fileName));

			w = image.getWidth() / size;
			h = image.getHeight() / size;

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					squares[i][j] = new Square(image.getSubimage(i * w, j * h,
							w, h), i, j, w, h);
				}
			}
			shuffle();
		} catch (Exception e) {
			System.out.println("Can't open file!");
		}
	

	}

	/**
	 * 
	 * @param oldX
	 *            virtual coordinates
	 * @param oldY
	 * @param newX
	 * @param newY
	 */

	public void swap(int oldX, int oldY, int newX, int newY) {
		System.out.println(oldX+" "+oldY+" "+newX+" "+newY+" ");
		Square newSquare = squares[newX][newY];
		Square oldSquare = squares[oldX][oldY];

		squares[newX][newY] = oldSquare;
		squares[oldX][oldY] = newSquare;

	}

	public void swapPoints(int oldX, int oldY, int newX, int newY) {
		swap(oldX / w, oldY / h, newX / w, newY / h);
	}

	public void shuffle() {
		System.out.println("SHUFFLE");
		for (int x = 0; x < (size * size); x++) {
			int x1 = getRandomIndex();
			System.out.println("x1 " + x1);
			int x2 = getRandomIndex();
			System.out.println("x2 " + x2);
			int y1 = getRandomIndex();
			System.out.println("y1 " + y1);
			int y2 = getRandomIndex();
			System.out.println("y2 " + y2);
			swap(x1, y1, x2, y2);
		}
		p.repaint();
	}

	public int getRandomIndex() {
		int c =(int) (Math.random() * squares.length);
		System.out.println(c);
		return c;
		

	}

	public void draw(Graphics2D g2) {
		for (int x = 0; x < squares.length; x++) {
			for (int y = 0; y < squares.length; y++) {
				Square square = squares[x][y];
				square.drawSquare(g2, x, y);
			}
		}
	}

	public boolean isCorrect() {
		 for (int i = 0; i < squares.length; i++) {
	            for (int j = 0; j < squares.length; j++) {
	                Square currentSquare = squares[i][j];
	                if (currentSquare.isCorrect(i, j) == false) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	}


class Square {
	BufferedImage image;
	int h;
	int w;
	int xcord;
	int ycord;

	public Square(BufferedImage bufferedImage, int i, int j, int w, int h) {
		this.h = h;
		this.w = w;
		this.image = bufferedImage;
		this.xcord = i;
		this.ycord = j;

	}

	public boolean isCorrect(int newX, int newY) {
		return (newX == xcord && newY == ycord);
	}

	public void drawSquare(Graphics2D g2, int newX, int newY) {
		g2.drawImage(image, newX * w, newY * h, w, h, null);

	}

}

class MyPanel extends JPanel {
	final int size = 5;
	private int oldX = -1;
	private int oldY = -1;
	ImageArray imageArray;

	public MyPanel() {
		imageArray = new ImageArray("picture.jpg", size, this);

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1 && oldX == -1) {
					oldY = e.getY();
					oldX = e.getX();
				} else if (e.getButton() == 1 && (oldX != -1)) {
					imageArray.swapPoints(oldX, oldY, e.getX(), e.getY());
					oldX=-1;
					oldY=-1;
					repaint();
				}
			}
		});

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		imageArray.draw(g2);
		 if (imageArray.isCorrect() == true) {
	            Font myFont = new Font("Sanserif", Font.PLAIN, 50);
	            g2.setFont(myFont);
	            g2.setColor(Color.WHITE);
	            g2.drawString("YOU WIN!!", 200, 350);
	            
	        }
	}
}
