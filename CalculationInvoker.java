package Calculations;

// Command Invoker
public class CalculationInvoker {
	private CalculationInterface command;
	
	public void setCommand(CalculationInterface command) {
		this.command = command;
	}
	
	public CalculationInterface getCommand() {
		return command;
	}
	
	public Result calculate() {
		return command.calculate();
	}
	
}
