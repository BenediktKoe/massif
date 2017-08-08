/*******************************************************************************
 * Copyright (c) 2010, 2014, Embraer S.A., Budapest University of Technology and Economics
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 *     Marton Bur - initial API and implementation 
 *******************************************************************************/
package hu.bme.mit.massif.communication.command.util;

import java.util.List;

import hu.bme.mit.massif.communication.datatype.IVisitableMatlabData;
import hu.bme.mit.massif.communication.datavisitor.ParameterVisitor;

/**
 * Utility class to generate all MATLAB commands as strings using the given parameters
 */
public class CommandStringGenerator {

	public static String[] generate(String commandName, List<IVisitableMatlabData> params) {
	
        String[] commandStrings;
		if (params.size() > 0) {

            commandStrings = new String[] { commandName + "(" };
            ParameterVisitor parameterVisitor = new ParameterVisitor(commandStrings);
            for (IVisitableMatlabData data : params) {
                data.acceptDataVisitor(parameterVisitor);
            }
            commandStrings = parameterVisitor.getCommandStrings();
            for (int i = 0; i < commandStrings.length; i++) {
                commandStrings[i] = commandStrings[i].replaceAll(",$", ""); // remove the last unnecessary comma
                commandStrings[i] = commandStrings[i] + ")";
            }

        } else {
            commandStrings = new String[] { commandName };
        }

		return commandStrings;
	}
	
}
