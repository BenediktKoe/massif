package hu.bme.mit.massif.communication.matlabcontrol;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import hu.bme.mit.massif.communication.IMatlabWrapper;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;

public class MatlabControlWrapper implements IMatlabWrapper {

	private MatlabProxy proxy;

	public MatlabControlWrapper(MatlabProxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public void eval(String command) {
		try {
			proxy.eval(command);
		} catch (MatlabInvocationException e) {
			Logger logger = Logger.getLogger(MatlabControlWrapper.class);
			logger.log(Level.ERROR, "Failed to execute command " + command, e);
		}
	}

	@Override
	public Object[] returningEval(String command, int nargout) {
		try {
			return proxy.returningEval(command, nargout);
		} catch (MatlabInvocationException e) {
			Logger logger = Logger.getLogger(MatlabControlWrapper.class);
			logger.log(Level.ERROR, "Failed to execute command " + command, e);
		}
		return null;
	}

}
