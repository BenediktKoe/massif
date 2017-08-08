/*******************************************************************************
 * Copyright (c) 2010-2013, Embraer S.A., Budapest University of Technology and Economics
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 *     Marton Bur, Abel Hegedus, Akos Horvath - initial API and implementation 
 *******************************************************************************/
package hu.bme.mit.massif.communication;

import java.util.List;

import hu.bme.mit.massif.communication.datatype.IVisitableMatlabData;

/**
 * Interface for classes that are responsible for the low level interactions
 * with MATLAB
 * 
 * (The successor class of the removed <code>BasicOperationsApi</code> utility
 * class)
 */
public interface ICommandEvaluator {

	/**
	 * Evaluates a given command with the provided parameters. Multiple return values are
	 * put into a newly created CellMatlabData.
	 * 
	 * @param commandName
	 *            the name of the command (or function) to be executed
	 * @param params
	 *            a list containing the command parameters
	 * @param outputArgumentCount
	 *            the expected number of results provided by MATLAB
	 * @return the result encapsulated in an <code>IVisitableMatlabData</code>
	 *         object
	 */
	public IVisitableMatlabData evaluateCommands(String commandName, List<IVisitableMatlabData> params,
			int outputArgumentCount);

}
