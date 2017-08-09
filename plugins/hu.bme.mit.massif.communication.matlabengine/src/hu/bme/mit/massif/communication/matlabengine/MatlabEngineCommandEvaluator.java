package hu.bme.mit.massif.communication.matlabengine;

import java.util.List;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import hu.bme.mit.massif.communication.AbstractCommandEvaluator;
import hu.bme.mit.massif.communication.ICommandEvaluator;
import hu.bme.mit.massif.communication.datatype.IVisitableMatlabData;

public class MatlabEngineCommandEvaluator implements ICommandEvaluator {

	private MatlabEngine engine;

	public MatlabEngineCommandEvaluator() {
		try {
			engine = MatlabEngine.connectMatlab();
		} catch (EngineException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public IVisitableMatlabData evaluateCommands(String commandName, List<IVisitableMatlabData> params,
			int outputArgumentCount) {
		engine.feval(commandName, params.get(0).)
		return null;
	}

}
