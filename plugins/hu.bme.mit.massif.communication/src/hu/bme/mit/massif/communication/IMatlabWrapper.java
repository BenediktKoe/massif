package hu.bme.mit.massif.communication;

public interface IMatlabWrapper {

	public void eval(String command);
	
	public Object[] returningEval(String command, int nargout);

}
