package hu.bme.mit.massif.communication.matlabengine;

import java.util.Map;

import hu.bme.mit.massif.communication.ICommandEvaluator;
import hu.bme.mit.massif.communication.ICommandEvaluatorFactory;

public class MatlabEngineCommandEvaluatorFactory implements ICommandEvaluatorFactory {


	@Override
	public ICommandEvaluator createCommandEvaluator(
			Map<String, Object> parameters) {
		// Get the parameters
//		String matlabPath = (String) parameters.get("matlabPath");
		
		ICommandEvaluator result = new MatlabEngineCommandEvaluator();
		
        return result;
	}

	@Override
	public String getConnectorName() {
		return "MatlabEngine";
	}


}
