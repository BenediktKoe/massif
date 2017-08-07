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

import java.util.HashMap;
import java.util.Map;

public class StructMatlabData implements IVisitableMatlabData {
    private Map<String, IVisitableMatlabData> dataMap;

    public StructMatlabData() {
        dataMap = new HashMap<String, IVisitableMatlabData>();
    }

    public int size() {
        return dataMap.size();
    }

    public Map<String, IVisitableMatlabData> getDatas() {
        return dataMap;
    }

    public void addData(String fieldName, IVisitableMatlabData data) {
        dataMap.put(fieldName, data);
    }

    public IVisitableMatlabData getData(String fieldName) {
        return dataMap.get(fieldName);
    }

    public void setDatas(Map<String, IVisitableMatlabData> dataMap) {
        this.dataMap = dataMap;
    }

    public static StructMatlabData asStructMatlabData(IVisitableMatlabData data) {
        if (data instanceof CellMatlabData && ((CellMatlabData) data).size() == 0) {
            return new StructMatlabData();
        }
        return (StructMatlabData) data;
    }

    public static Map<String, IVisitableMatlabData> getStructMatlabDataData(IVisitableMatlabData data) {
        return asStructMatlabData(data).dataMap;
    }

    @Override
    public void acceptDataVisitor(IMatlabDataVisitor visitor) {
        visitor.visit(this);
    }

}
