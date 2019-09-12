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

public class InsertionSortRunnable implements Runnable {
	
	private JFrame frame;
	private Lock sortStateLock;
	private ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	public static ArrayList<Integer> size = new ArrayList<Integer>();
	public int xplace = 50;
	public int yplace = 15;
	
	//makes the rectangles
	public class RectangleComponent extends JComponent {
		public void paintComponent(Graphics g){
			Graphics2D graphic = (Graphics2D) g;
			for(Rectangle rec: recs){
				graphic.fill(rec);
			}
		}
	}
	
	public InsertionSortRunnable() {
		//Creates frame on which the animations will be displayed
		frame = new JFrame();
		frame.setSize(800, 1450);
		frame.setTitle("Insertion Sort");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i = 0; i<51; i++) {
			int randomwidth = (int)(400*Math.random() + 25);
			size.add(randomwidth);
			Rectangle box = new Rectangle(xplace, yplace, randomwidth, 20);
			recs.add(box);
			yplace += 25;
		}
		//Adds rectangles to the frame, will be sorting based on length of the rectangles
		RectangleComponent boxes = new RectangleComponent();
		frame.add(boxes);
		sortStateLock = new ReentrantLock();
	}
	public void run() {
		int wide = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		frame.setLocation((1*wide)/24,50);
		frame.setVisible(true);
		sortStateLock.lock();
		//Algorithm for Insertion Sort
		try {
			for(int i = 1; i < recs.size(); i++) {
				for(int j = i; j>0; j--) {
					if(recs.get(j).getWidth() < recs.get(j-1).getWidth()) {
						double rectholder = recs.get(j-1).getWidth();
						recs.get(j-1).setSize((int)recs.get(j).getWidth(), 20);
						recs.get(j).setSize((int)rectholder, 20);
						
						frame.repaint();
						Thread.sleep(300);
					}
					else {
						break;
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
