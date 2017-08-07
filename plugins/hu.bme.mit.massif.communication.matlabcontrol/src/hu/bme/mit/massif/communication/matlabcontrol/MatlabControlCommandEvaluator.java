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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import hu.bme.mit.massif.communication.AbstractCommandEvaluator;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;
import matlabcontrol.MatlabProxyFactoryOptions.Builder;

/**
 * Class responsible for the low level operations with MATLAB
 * 
 * (The successor class of BasicOperationsApi utility class)
 */
public class MatlabControlCommandEvaluator extends AbstractCommandEvaluator {

	private MatlabProxyFactory factory = null;
	private MatlabProxy proxy = null;

	private Builder optionsBuilder = new MatlabProxyFactoryOptions.Builder();
	private MatlabProxyFactoryOptions options = null;

	public MatlabControlCommandEvaluator(String matlabPath) {

		if (!"".equals(matlabPath)) {
			optionsBuilder = optionsBuilder.setMatlabLocation(matlabPath);
		}
		options = optionsBuilder.build();
		factory = new MatlabProxyFactory(options);
		try {
			proxy = factory.getProxy();
		} catch (MatlabConnectionException e) {
			Logger logger = Logger.getLogger(MatlabControlCommandEvaluator.class);
			logger.log(Level.ERROR, "Failed to get Matlab proxy from proxy factory.", e);
		}
		
		matlabInstance = new MatlabControlWrapper(proxy);
	}

}