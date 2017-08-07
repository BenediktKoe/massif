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
package hu.bme.mit.massif.communication.commandevaluation;

import java.util.HashMap;
import java.util.Map;

import br.com.embraer.massif.commandevaluation.client.MatlabClient;
import hu.bme.mit.massif.communication.AbstractCommandEvaluator;

/**
 * Class responsible for the low level operations with MATLAB
 * 
 * (The successor class of BasicOperationsApi utility class)
 */
public class CommandEvaluatorImpl extends AbstractCommandEvaluator {

    private static Map<MatlabClient, CommandEvaluatorImpl> commandEvaluators = new HashMap<MatlabClient, CommandEvaluatorImpl>();

	private MatlabClientWrapper matlabClientWrapper;

    public CommandEvaluatorImpl() {
    }

    public MatlabClientWrapper getMatlabClientWrapper() {
    	return matlabClientWrapper;
    }
    
    public void setMatlabClientWrapper(MatlabClientWrapper matlabClientWrapper) {
    	this.matlabClientWrapper = matlabClientWrapper;
    }

    public CommandEvaluatorImpl(MatlabClient matlabClient) {
        matlabClientWrapper = new MatlabClientWrapper(matlabClient);
    }

    public static CommandEvaluatorImpl getInstance(MatlabClient matlabClient) {
        CommandEvaluatorImpl commandEvaluator = commandEvaluators.get(matlabClient);
        if (commandEvaluator == null) {
            commandEvaluator = new CommandEvaluatorImpl(matlabClient);
            commandEvaluators.put(matlabClient, commandEvaluator);
        }
        return commandEvaluator;
    }


}