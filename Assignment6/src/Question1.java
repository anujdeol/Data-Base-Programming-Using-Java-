public class Question1 {

	public static void main(String[] args) {
		// create the tasks
		Runnable printA = new PrintChar('a', 3000);
		Runnable printB = new PrintChar('b', 3000);
		Runnable print100 = new PrintNum(5000);

		// create the threads
		Thread thread1 = new Thread(printA);
		Thread thread2 = new Thread(printB);
		Thread thread3 = new Thread(print100);

		thread1.setPriority(Thread.MAX_PRIORITY);

		// start the threads
		thread2.start();
		thread1.start();
		thread3.start();
	}

}

// The task for printing the characters
class PrintChar implements Runnable {

	private char charToPrint; // the char to print
	private int times; // the times to repeat

	// construct a task public
	public PrintChar(char c, int t) {
		charToPrint = c;
		times = t;
	}

	@Override
	public void run() {
		for (int i = 0; i < times; i++) {
			System.out.print(charToPrint);
			if (i % 50 == 49) {
				System.out.println();
			}
		}
	}
}

class PrintNum implements Runnable {
	private int lastNum;

	public PrintNum(int n) {
		lastNum = n;
	}

	@Override
	public void run() {
		for (int i = 1; i <= lastNum; i++) {
			System.out.print(" " + i);
			if (i % 50 == 0) {
				System.out.println();
			}
			Thread.yield();
		}
	}

}
