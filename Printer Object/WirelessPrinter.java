package hw1;

/**
 * @author Noah Hoss Computer Science 227 Homework 1
 */

/*
 * This class represents a wireless printer.
 */
public class WirelessPrinter {
	/** Setting PAGES_PER_CARTRIDGE constant to given value. */
	public static final int PAGES_PER_CARTRIDGE = 1000;
	/** Setting TRAY_CAPACITY constant to given value. */
	public static final int TRAY_CAPACITY = 500;
	/** Setting NEW_CARTRIDGE_INK_LEVEL constant to given value. */
	public static final double NEW_CARTRIDGE_INK_LEVEL = 1.0;

	/** Creating ink variable for use.(% of ink left in printer) */
	private double inkLeft = 0;
	/** Creating paper variable for use.(# of pages left in printer) */
	private int paperLeft = 0;
	/** Creating isPrinterConnected value for use. */
	private boolean isPrinterConnected = false;
	/** Creating isPrinterOn value for use. */
	private boolean isPrinterOn = false;
	/** Creating paperUsed value for use. (Includes pages used with no ink.) */
	private int pagesUsed = 0;
	/** Creating pagesPrinted value for use.(Only counts pages with ink on them.) */
	private int pagesPrinted = 0;

	/**
	 * Create a new printer. The printer initially has a cartridge that is half full
	 * and no paper.
	 */
	public WirelessPrinter() {
		inkLeft = NEW_CARTRIDGE_INK_LEVEL / 2;
		paperLeft = 0;
	}

	/**
	 * Create a new printer with a specified amount of ink and paper.
	 * 
	 * @param ink   Amount of ink being loaded into printer
	 * @param paper Amount of paper being loaded into printer.
	 */
	public WirelessPrinter(double ink, int paper) {
		inkLeft = ink;
		paperLeft = paper;
	}

	/** Connect the printer to the network. */
	public void connect() {
		isPrinterConnected = true;
	}

	/** disconnect the wireless connection. */
	public void disconnect() {
		isPrinterConnected = false;
	}

	/**
	 * return the current ink level as a double value that is between 0.0 and 1.0.
	 * 
	 * @return ink left in cartridge.
	 */
	public double getInkLevel() {
		// This will return a non-rounded % which instructor said is fine?
		return inkLeft;
	}

	/**
	 * return how much paper, in percentage, is left, rounded to the nearest
	 * percentage
	 * 
	 * @return Paper left in % rounded
	 */
	public int getPaperLevel() {
		// I need to cast TRAY_CAPACITY to double for the calculation to work? Dumb.
		/**
		 * double tempCast is created for non-integer divison.
		 */
		double tempCast = TRAY_CAPACITY;
		return (int) ((paperLeft / tempCast) * 100);
	}

	/**
	 * return the exact count of the sheets of paper left in the tray.
	 * 
	 * @return Paper left in printer.
	 */
	public int getPaperLevelExact() {
		return paperLeft;
	}

	/**
	 * return the total number of pages printed with full ink (i.e., blank pages do
	 * not count).
	 * 
	 * @return Total pages printed.
	 */
	public int getTotalPagesPrinted() {
		return pagesPrinted;
	}

	/**
	 * return the total number of pages of papers used.
	 * 
	 * @return Total pages used.
	 */
	public int getTotalPaperUsed() {
		return pagesUsed;
	}

	/**
	 * return the status of current wireless connection.
	 * 
	 * @return If the printer is connected.
	 */
	public boolean isConnected() {
		return isPrinterConnected;
	}

	/**
	 * return the power status of the printer
	 * 
	 * @return If the printer is on.
	 */
	public boolean isOn() {
		return isPrinterOn;
	}

	/**
	 * load the paper. The number of pages in the tray cannot surpass its capacity.
	 * 
	 * @param pages Amount of pages to load into printer.
	 */
	public void loadPaper(int pages) {
		paperLeft = Math.min(paperLeft + pages, TRAY_CAPACITY);
	}

	/**
	 * This method simulates printing certain number of pages. If there is not
	 * enough paper left in the tray, the printing will stop once the paper runs
	 * out, and the remaining job (if any) is cancelled. If ink runs out (i.e., not
	 * enough to print one more page) during the printing, the printing will NOT
	 * Stop, but only blank pages are printed (i.e., the printer continues to
	 * consume paper, but not ink, until the job has completed).
	 * 
	 * @param pages Amount of pages to print.
	 */
	public void print(int pages) {
		// Amount of ink being used is only used twice in this function, I feel it's not
		// needed to create a variable for that.
		if (!isPrinterOn || !isPrinterConnected) {
			return;
		}
		while (pages >= 1) {
			if (paperLeft == 0) {
				return;
			} else if (inkLeft == 0) {
				inkLeft = inkLeft - 0.001;
				paperLeft = paperLeft - 1;
				pagesUsed++;
			} else {
				inkLeft = inkLeft - 0.001;
				paperLeft = paperLeft - 1;
				pagesPrinted++;
				pagesUsed++;
			}
			pages--;
		}
	}

	/** replace the cartridge. The ink level is reset to NEW_CARTRIDGE_INK_LEVEL. */
	public void replaceCartridge() {
		inkLeft = NEW_CARTRIDGE_INK_LEVEL;
	}

	/** turn the printer off and disconnect the wireless connection. */
	public void turnOff() {
		isPrinterOn = false;
		isPrinterConnected = false;
	}

	/** turn the printer on and immediately connect it to the network. */
	public void turnOn() {
		isPrinterOn = true;
		isPrinterConnected = true;
	}
}
