package rec;

public class GUI {

	/*
	 * 
	 * Startet die grafischen Oberflaechen
	 * 
	 */

	public GUI(int width) {
		new normalGUI(width);
		new orderGUI(width);
	}
}