//Ruolin Chen (rlc8my)
//Homework 7: Concurrency

public class ConcurrencyThreadRunner {
	
	public static void main(String[] args) {

		SelectionSortRunnable s = new SelectionSortRunnable();
		InsertionSortRunnable i = new InsertionSortRunnable();
		BubbleSortRunnable b = new BubbleSortRunnable();
		
		//Create three threads, one for each sort
		Thread selection = new Thread(s);
		Thread insertion = new Thread(i);
		Thread bubble = new Thread(b);
		selection.start();
		insertion.start();
		bubble.start();
	}
}

/** Questions:
 * 1. I chose to include locks in my design because locks control access to resources that are shared by
 * multiple threads. Locks are useful because my threads share some resources so the locks would limit
 * the use of each resource to one thread at a time.
 * 
 * 2. The difference between a thread that sleeps by calling sleep() and a thread that waits by calling
 * await() is that calling sleep puts the current thread to sleep for a given number of milliseconds.
 * Calling await() makes the current thread wait and allows another thread to acquire the lock object.
 * 
 * 3. When a thread calls await() and no other thread calls signalAll() or signal(), it remains blocked
 * and does not try again until signalAll() or signal() is called. 
 */
