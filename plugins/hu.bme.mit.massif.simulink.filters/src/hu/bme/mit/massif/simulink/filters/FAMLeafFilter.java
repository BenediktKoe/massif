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
package hu.bme.mit.massif.simulink.filters;

import hu.bme.mit.massif.communication.command.MatlabCommand;
import hu.bme.mit.massif.communication.command.MatlabCommandFactory;
import hu.bme.mit.massif.communication.datatype.MatlabString;
import hu.bme.mit.massif.simulink.api.extension.ISimulinkImportFilter;

/**
 * This class is a subsystem filter extension for the importer. Only the non-leaf FAM elements are not filtered.
 */
public class FAMLeafFilter implements ISimulinkImportFilter {

	@Override
	public String getName() {
		return "FAM filter";
	}
	
	@Override
	public String getDescription() {
		return "This filters all internal blocks of subsystems with 'Tag' parameter value of 'FAM_Leaf'";
	}

	@Override
    public boolean filter(MatlabCommandFactory commandFactory, String blockFQN) {

    	MatlabCommand getTagValue = commandFactory.getParam().addParam(blockFQN).addParam("Tag");
        String tagValue = MatlabString.getMatlabStringData(getTagValue.execute());

        return tagValue.startsWith("FAM_Leaf");
    }


}
