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

public class SelectionSortRunnable implements Runnable {
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
	
	public SelectionSortRunnable() {
		//Creates frame on which the animations will be displayed
		frame = new JFrame();
		frame.setSize(800, 1450);
		frame.setTitle("Selection Sort");
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
		frame.setLocation((9*wide)/24,50);
		frame.setVisible(true);
		sortStateLock.lock();
		//Algorithm for Selection Sort
		try {
			for(int i = 0; i < recs.size()-1; i++) {
				Rectangle minRec = recs.get(i);
				for(int j = i+1; j<recs.size();j++) {
					if((int)recs.get(j).getWidth() < minRec.getWidth()) {
						minRec = recs.get(j);
					}
				}
		
				int w = (int)recs.get(i).getWidth();
				recs.get(i).setSize((int)minRec.getWidth(), 20);
				minRec.setSize(w,20);
				
				frame.repaint();
				Thread.sleep(300);
			}
		}
		catch(InterruptedException exception) {
		}
		finally {
			sortStateLock.unlock();
		}
	}
}
