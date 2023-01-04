package AddAndRemove;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class Viewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Viewer() {
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");
		
		boolean chosePlus = false;
		boolean choseMinus = false;
	}
	
	
	
}
