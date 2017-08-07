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
package hu.bme.mit.massif.communication.datatype;

import hu.bme.mit.massif.communication.datavisitor.IMatlabDataVisitor;

/**
 * This class represents a string in MATLAB
 */
public class MatlabString extends PrimitiveMatlabData<String> {

    public MatlabString() {
    }

    @Override
    public String getValue() {
        return value.replaceAll("\\n", " ");
    }
    
    public MatlabString(String data) {
        this.value = data.replaceAll("\\n", " ");
    }

    public static MatlabString asMatlabString(IVisitableMatlabData data) {
        return (MatlabString) data;
    }

    public static String getMatlabStringData(IVisitableMatlabData data) {
        return ((MatlabString) data).value.replaceAll("\\n", " ");
    }

    @Override
    public void acceptDataVisitor(IMatlabDataVisitor visitor) {
        visitor.visit(this);
    }

    // TODO revisit escaping of characters
    @Override
    public String toString() {
        // the string "/" is not always has to be escaped, this case has to be handled differently
        return "'" + value.replaceAll("'", "''").replace('\n', ' ') + "'";
    }

}
