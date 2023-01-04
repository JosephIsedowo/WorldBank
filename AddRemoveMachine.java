package Viewer;

public class AddRemoveMachine implements AddRemoveState{
	
	private AddRemoveState arState;

	public void performState	() {
		// TODO Auto-generated method stub
		this.arState.performState();;
	}
	
	public void setState(AddRemoveState arState) {
		this.arState = arState;
	}
	
	public AddRemoveState getState() {
		return this.arState;
	}
	

}


