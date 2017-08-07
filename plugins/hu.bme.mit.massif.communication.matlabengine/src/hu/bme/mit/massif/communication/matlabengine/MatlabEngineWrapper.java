package hu.bme.mit.massif.communication.matlabengine;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mathworks.engine.MatlabEngine;

import hu.bme.mit.massif.communication.IMatlabWrapper;

public class MatlabEngineWrapper implements IMatlabWrapper {

	private MatlabEngine engine;
	private Logger logger;

	public MatlabEngineWrapper(MatlabEngine engine) {
		this.engine = engine;
		logger = Logger.getLogger(MatlabEngineWrapper.class);
		try {
			engine.eval("addpath('/home/marci/matlab-workspace')");
		} catch (CancellationException | InterruptedException | ExecutionException e) {
			logExecutionError("addpath('/home/marci/matlab-workspace') errored", e);
		}
	}

	@Override
	public void eval(String command) {
		try {
			engine.eval(command);
		} catch (CancellationException | InterruptedException | ExecutionException e) {
			logExecutionError(command, e);
		}
	}

	@Override
	public Object[] returningEval(String command, int nargout) {
		try {
			if(nargout < 1 ) {
				eval(command);
			}
			else if(nargout == 1) {
				return new Object[] { engine.<Object>feval("massif_feval_helper",command)};				
			}
			else if(nargout > 1) {
				return engine.<Object[]>feval("massif_feval_helper",command);				
			}
			
		} catch (InterruptedException | ExecutionException e) {
			logExecutionError(command, e);
		}
		return null;
	}

	private void logExecutionError(String command, Exception e) {
		logger.log(Level.ERROR, "Failed to execute command " + command, e);
	}

}
