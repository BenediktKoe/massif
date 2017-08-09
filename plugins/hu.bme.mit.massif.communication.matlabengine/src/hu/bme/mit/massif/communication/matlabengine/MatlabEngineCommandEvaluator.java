package hu.bme.mit.massif.communication.matlabengine;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

import hu.bme.mit.massif.communication.ICommandEvaluator;
import hu.bme.mit.massif.communication.command.util.CommandStringGenerator;
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

		IVisitableMatlabData result = null;

		try {

			/* @formatter:off
			 *
			 * From the documentation of <code>feval</code> found under 
			 * https://www.mathworks.com/help/matlab/apiref/com.mathworks.engine.matlabengine-class.html#bvarmi5-1
			 *
			 * Number of expected outputs. Default is 1.
			 * If nlhs is greater than 1, the returned type T must be <Object[]>.
			 * If nlhs is 0, the returned type T must be <Void> or <?>.
			 * If nlhs is 1, the returned type T can be the expected type or <Object> if the type is not known.
			 *
			 * @formatter:on
			 */

			String[] commandStrings = CommandStringGenerator.generate(commandName, params);
			 
			
			if (params.size() > 0) {

			} else {
				engine.feval(outputArgumentCount, commandName);
			}

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
