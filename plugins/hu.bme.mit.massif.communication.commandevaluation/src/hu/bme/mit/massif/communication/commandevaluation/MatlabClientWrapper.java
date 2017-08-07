package hu.bme.mit.massif.communication.commandevaluation;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import br.com.embraer.massif.commandevaluation.client.MatlabClient;
import br.com.embraer.massif.commandevaluation.exception.MatlabOutputException;
import br.com.embraer.massif.commandevaluation.exception.MatlabRMIException;
import hu.bme.mit.massif.communication.IMatlabWrapper;

public class MatlabClientWrapper implements IMatlabWrapper {

	private MatlabClient client;
	private Logger logger;

	public MatlabClient getClient() {
		return client;
	}
	
	public MatlabClientWrapper(MatlabClient client) {
		this.client = client;
		logger = Logger.getLogger(MatlabClientWrapper.class);
	}
	
	@Override
	public void eval(String command) {
		try {
			client.executeEval(command, 0);
		} catch (MatlabRMIException e) {
			logExecutionError(command, e);
		} catch (MatlabOutputException e) {
			logExecutionError(command, e);
		}
	}

	@Override
	public Object[] returningEval(String command, int nargout) {
		try {
			return client.executeEval(command, nargout);
		} catch (MatlabRMIException e) {
			logExecutionError(command, e);
		} catch (MatlabOutputException e) {
			logExecutionError(command, e);
		}
		return null;
	}

	private void logExecutionError(String command, Exception e) {
		logger.log(Level.ERROR, "Failed to execute command " + command, e);
	}

}
