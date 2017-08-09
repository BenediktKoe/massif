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

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents data from MATLAB that contains more than 1 single value
 */
public class CellMatlabData implements IVisitableMatlabData {

    private List<IVisitableMatlabData> dataList;

    public CellMatlabData() {
        dataList = new ArrayList<IVisitableMatlabData>();
    }

    public int size() {
        return dataList.size();
    }

    public List<IVisitableMatlabData> getDataList() {
        return dataList;
    }

    public void addData(IVisitableMatlabData data) {
        dataList.add(data);
    }

    public IVisitableMatlabData getData(int i) {
        return dataList.get(i);
    }

    public void setDatas(List<IVisitableMatlabData> datas) {
        this.dataList = datas;
    }

    public static CellMatlabData asCellMatlabData(IVisitableMatlabData data) {
        if (data instanceof CellMatlabData) {
            return (CellMatlabData) data;
        } else {
            CellMatlabData tmp = new CellMatlabData();
            tmp.addData(data);
            return tmp;
        }
    }

    public static List<IVisitableMatlabData> getCellMatlabDataData(IVisitableMatlabData data) {
        return asCellMatlabData(data).dataList;
    }

    @Override
    public void acceptDataVisitor(IMatlabDataVisitor visitor) {
        visitor.visit(this);
    }

}
