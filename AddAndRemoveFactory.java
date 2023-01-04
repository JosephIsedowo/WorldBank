package AddAndRemove;

import java.util.Vector;

public class AddAndRemoveFactory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Viewer getPlusOrMinus(Vector<String> viewsNames, boolean chosePlus, boolean choseMinus) {
		if(chosePlus == true) {
			return AddViewer(Vector<String> viewsNames, true, false);
		}
		else if(choseMinus == true) {
			return AddViewer(Vector<String> viewsNames, false, true);
		}
	}

}
