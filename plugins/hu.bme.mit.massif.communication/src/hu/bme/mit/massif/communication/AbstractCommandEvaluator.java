package hu.bme.mit.massif.communication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.bme.mit.massif.communication.command.util.CommandStringGenerator;
import hu.bme.mit.massif.communication.datatype.CellMatlabData;
import hu.bme.mit.massif.communication.datatype.Handle;
import hu.bme.mit.massif.communication.datatype.IVisitableMatlabData;
import hu.bme.mit.massif.communication.datatype.Logical;
import hu.bme.mit.massif.communication.datatype.MatlabString;
import hu.bme.mit.massif.communication.datatype.StructMatlabData;

public class AbstractCommandEvaluator implements ICommandEvaluator {

    protected IMatlabWrapper matlabInstance;

	@Override
	public IVisitableMatlabData evaluateCommands(String commandName, List<IVisitableMatlabData> params,
			int outputArgumentCount) {
		String[] commandStrings = CommandStringGenerator.generate(commandName, params);
	    
		IVisitableMatlabData result = null;
        if (commandStrings.length > 1) {
            result = new CellMatlabData();
            for (String commandString : commandStrings) {
                CellMatlabData.asCellMatlabData(result).getDataList()
                        .add(evaluateCommand(commandString, outputArgumentCount));
            }
        } else if (commandStrings.length == 1){
            result = evaluateCommand(commandStrings[0], outputArgumentCount);
        }
        return result;
    }
    
    protected IVisitableMatlabData evaluateCommand(String command, int nargout) {

        IVisitableMatlabData result = null;

        if (nargout > 1) {
            String resultString = "[";
            for (int i = 0; i < nargout; i++) {
                resultString = resultString.concat("r" + i + ",");
            }

            resultString = resultString.replaceAll(",$", "").concat("]");

            matlabInstance.returningEval(resultString + " = " + command, 0);
            matlabInstance.returningEval("ImporterTmpResult = " + resultString.replace("[", "{").replace("]", "}"), 0);
            result = dataRetriever();
        } else if (nargout == 1) {
            matlabInstance.returningEval("ImporterTmpResult = " + command, 0);
            result = dataRetriever();
        } else {
            matlabInstance.returningEval(command, 0);
            result = null;
        }

        return result;
    }

    protected IVisitableMatlabData dataRetriever() {
        IVisitableMatlabData result = null;
        String type = (String) matlabInstance.returningEval("class(ImporterTmpResult)", 1)[0];
        Object[] data = matlabInstance.returningEval("ImporterTmpResult(1:end)", 1);
        // Switch according to the result of type = class(ImporterTmpResult)
        if (type.equals("struct")) {
            result = processStruct("ImporterTmpResult", data);
        } else {
            result = processNonStruct("ImporterTmpResult", type, data[0]);
        }
        return result;
    }
    

    protected IVisitableMatlabData processStruct(String structName, Object[] data) {

        // //////
        // DEBUG
        // Get all data in the struct with the fieldnames
        Object[] _data = (Object[]) matlabInstance.returningEval(structName + "(1:end)", 1)[0];

        // In case of a struct data[0] is always a String[] containing the field names
        Map<String, IVisitableMatlabData> fieldsToValues = new HashMap<String, IVisitableMatlabData>();
        Object[] structDataArray = ((Object[]) _data[1]);

        IVisitableMatlabData result = null;
        if (structDataArray.length <= 0) {
            // TODO null length array of struct
            return null;
        } else if (structDataArray.length == 1) {
            StructMatlabData structResult = new StructMatlabData();

            for (int i = 0; i < structDataArray.length; i++) {
                for (int j = 0; j < ((String[]) _data[0]).length; j++) {
                    String fieldName = ((String[]) _data[0])[j];
                    // Get the reference name for the field. Caution: MATLAB indexing
                    String currVarName = structName + "(" + (i + 1) + ")." + fieldName;

                    // Determine the type of each field
                    String type = (String) matlabInstance.returningEval("class(" + currVarName + ")", 1)[0];
                    // Branch according to the type
                    IVisitableMatlabData fieldValue = null;
                    if ("struct".equals(type)) {
                        fieldValue = processStruct(currVarName, (Object[]) (((Object[]) structDataArray[i])[j]));
                    } else {
                        fieldValue = processNonStruct(currVarName, type, ((Object[]) structDataArray[i])[j]);
                    }
                    fieldsToValues.put(fieldName, fieldValue);
                }
            }
            structResult.setDatas(fieldsToValues);
            result = structResult;
        } else {
            CellMatlabData cellResult = new CellMatlabData();
            for (int i = 0; i < structDataArray.length; i++) {
                StructMatlabData structResult = new StructMatlabData();
                for (int j = 0; j < ((String[]) _data[0]).length; j++) {
                    String fieldName = ((String[]) _data[0])[j];
                    // fieldNames.add(fieldName);
                    // Get the reference name for the field. Caution: MATLAB indexing
                    String currVarName = structName + "(" + (i + 1) + ")." + fieldName;

                    // Determine the type of each field
                    String type = (String) matlabInstance.returningEval("class(" + currVarName + ")", 1)[0];
                    // Branch according to the type
                    IVisitableMatlabData fieldValue = null;
                    if ("struct".equals(type)) {
                        fieldValue = processStruct(currVarName, (Object[]) (((Object[]) structDataArray[i])[j]));
                    } else {
                        fieldValue = processNonStruct(currVarName, type, ((Object[]) structDataArray[i])[j]);
                    }
                    fieldsToValues.put(fieldName, fieldValue);
                }
                structResult.setDatas(fieldsToValues);
                fieldsToValues = new HashMap<String, IVisitableMatlabData>();
                cellResult.addData(structResult);
                result = cellResult;
            }
        }

        return result;
    }


    protected IVisitableMatlabData processNonStruct(String name, String type, Object data)  {
        IVisitableMatlabData result = null;

        if (type.equals("double")) {
            if (data instanceof double[]) {
                if (((double[]) data).length == 1) {
                    Handle handle = new Handle();
                    handle.setValue((((double[]) data)[0]));
                    result = handle;
                } else {
                    double[] handles = (double[]) data;
                    CellMatlabData compositeData = new CellMatlabData();
                    for (int i = 0; i < handles.length; i++) {
                        Handle handle = new Handle();
                        handle.setValue((((double[]) (data))[i]));
                        compositeData.addData(handle);
                    }
                    result = compositeData;
                }
            } else if (((Object[]) (data)).length > 1) {
                CellMatlabData compositeData = new CellMatlabData();
                for (int j = 0; j < ((Object[]) (data)).length; j++) {
                    // Most of the times values are stored in an array of one element
                    // TODO check if iteration with i is really needed, or can be indexed with 0
                    for (int i = 0; i < ((double[]) ((Object[]) (data))[j]).length; i++) {
                        Handle handle = new Handle();
                        handle.setValue(((double[]) ((Object[]) (data))[j])[i]);
                        compositeData.addData(handle);
                    }
                }
                result = compositeData;
            }            
        } else if (type.equals("logical")) {
        	if (data instanceof boolean[]) {
                if (((boolean[]) data).length == 1) {
                    Logical logical = new Logical();
                    logical.setValue((((boolean[]) data)[0]));
                    result = logical;
                }
        	}
        } else if (type.equals("char")) {
            // TODO handle char arrays
            MatlabString string = new MatlabString();
            String charData = (String) data;
            string.setValue(charData.replaceAll("\n", " "));
            // string.setData(((String) (data[0])).replace("\n"," "));
            result = string;
        } else if (type.equals("cell")) {
            CellMatlabData cellData = new CellMatlabData();
            result = processCell((Object[]) data, cellData);
        }
        return result;
    }

    private CellMatlabData processCell(Object[] datas, CellMatlabData cellData) {

        for (Object data : datas) {
            if (data instanceof Object[]) {
                CellMatlabData containedCellData = new CellMatlabData();
                containedCellData = processCell(((Object[]) data), containedCellData);
                cellData.addData(containedCellData);
            } else if (data instanceof double[]) {
                double[] doubleData = (double[]) data;
                if (doubleData.length == 1) {
                    Handle handle = new Handle();
                    handle.setValue(doubleData[0]);
                    cellData.addData(handle);
                } else {
                    CellMatlabData containedArrayData = new CellMatlabData();
                    for (int i = 0; i < doubleData.length; i++) {
                        Handle handle = new Handle();
                        handle.setValue(doubleData[i]);
                        containedArrayData.addData(handle);
                    }
                    cellData.addData(containedArrayData);
                }
            } else if (data instanceof String[]) {
                String[] stringData = (String[]) data;
                if (stringData.length == 1) {
                    MatlabString string = new MatlabString();
                    string.setValue(stringData[0]);
                    cellData.addData(string);
                } else {
                    CellMatlabData containedArrayData = new CellMatlabData();
                    for (int i = 0; i < stringData.length; i++) {
                        MatlabString string = new MatlabString();
                        string.setValue(stringData[i]);
                        containedArrayData.addData(string);
                    }
                    cellData.addData(containedArrayData);
                }

            } else if (data instanceof String) {
                MatlabString string = new MatlabString();
                string.setValue((String) data);
                cellData.addData(string);
            }

        }
        return cellData;
    }

}
