/*******************************************************************************
 * Copyright (c) 2010, 2014, Embraer S.A., Budapest University of Technology and Economics
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 *     Abel Hegedus - initial API and implementation 
 *******************************************************************************/
package hu.bme.mit.massif.communication.matlabcontrol;

import hu.bme.mit.massif.communication.ICommandEvaluator;
import hu.bme.mit.massif.communication.ICommandEvaluatorFactory;

import java.util.Map;

/**
 * This class is responsible for returning the appropriate object with the ICommandEvaluator interface for command
 * execution.
 */
public class MatlabControlCommandEvaluatorFactory implements ICommandEvaluatorFactory {

	@Override
	public ICommandEvaluator createCommandEvaluator(
			Map<String, Object> parameters) {
		// Get the parameters
		String matlabPath = (String) parameters.get("matlabPath");
		
		ICommandEvaluator result = new MatlabControlCommandEvaluator(matlabPath);
		
        return result;
	}

	@Override
	public String getConnectorName() {
		return "MatlabControl";
	}

}
