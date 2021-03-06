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
package hu.bme.mit.massif.simulink.api.internal;

import hu.bme.mit.massif.common.MassifCommonPlugin;
import hu.bme.mit.massif.common.tracer.IMassifTracer;
import hu.bme.mit.massif.common.tracer.MassifTracerOptions;
import hu.bme.mit.massif.simulink.api.util.ISimulinkAPILogger;

import org.eclipse.core.runtime.Status;

public class PluginSimulinkAPILogger implements ISimulinkAPILogger {

    IMassifTracer tracer = MassifCommonPlugin.getDefault().getTracer(MassifTracerOptions.SIMULINKAPI);

    @Override
    public void error(String message) {
        error(message, null);
    }

    @Override
    public void error(String message, Throwable cause) {
        Status status = new Status(Status.ERROR, SimulinkApiPlugin.PLUGIN_ID, message, cause);
        logStatusToPluginLogger(status);
    }

    public void logStatusToPluginLogger(Status status) {
        SimulinkApiPlugin.getDefault().getLog().log(status);
    }

    @Override
    public void warning(String message) {
        Status status = new Status(Status.WARNING, SimulinkApiPlugin.PLUGIN_ID, message);
        logStatusToPluginLogger(status);
    }

    @Override
    public void debug(String message) {
        tracer.trace(message);
    }

    @Override
    public boolean isDebugging() {
        return tracer.isTracingEnabled();
    }

}
