package hu.bme.mit.massif.communication.matlabengine;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import hu.bme.mit.massif.communication.AbstractCommandEvaluator;

public class MatlabEngineCommandEvaluator extends AbstractCommandEvaluator {

	private MatlabEngine engine;

	public MatlabEngineCommandEvaluator() {
		try {
			engine = MatlabEngine.connectMatlab();
		} catch (EngineException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		matlabInstance = new MatlabEngineWrapper(engine);
	}

}
