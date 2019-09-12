//Ruolin Chen (rlc8my)
//Homework 7: Concurrency

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class BubbleSortRunnable implements Runnable {
	private JFrame frame;
	private Lock sortStateLock;
	private ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public int xplace = 50;
	public int yplace = 15;
	
	public class RectangleComponent extends JComponent {
		public void paintComponent(Graphics g) {
			Graphics2D graphic = (Graphics2D) g;
			for(Rectangle rec : recs){
				graphic.fill(rec);
			}	
		}
	}
	
	public BubbleSortRunnable() {
		//Creates frame on which the animations will be displayed
		frame = new JFrame();
		frame.setSize(800, 1450);
		frame.setTitle("Bubble Sort");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	
		
		for(int i = 0; i<InsertionSortRunnable.size.size();i++) {
			recs.add(new Rectangle(xplace, yplace, InsertionSortRunnable.size.get(i),20));
			yplace += 25;
		}
		//Adds rectangles to the frame, will be sorting based on length of the rectangles
		RectangleComponent boxes = new RectangleComponent();
		frame.add(boxes);
		sortStateLock = new ReentrantLock();
	}
	public void run() {
		int wide = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		frame.setLocation((17*wide)/24,50);
		frame.setVisible(true);
		sortStateLock.lock();
		//Algorithm for Bubble Sort
		try {
			for(int i = 0; i < recs.size()-1; i++) {
				for(int j = 0; j< recs.size()-(i+1); j++) {
					if(recs.get(j).getWidth()>recs.get(j+1).getWidth()) {
						int n = (int)recs.get(j).getWidth();
						recs.get(j).setSize((int)recs.get(j+1).getWidth(),20);
						recs.get(j+1).setSize(n, 20);
						
						frame.revalidate();
						frame.repaint();
						
						Thread.sleep(300);
		    		 }  
		    	 }
		     }
		}
		catch(InterruptedException exception) {
		}
		finally {
			sortStateLock.unlock();
		}
	}
}
