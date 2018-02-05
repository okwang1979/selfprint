package nc.ms.tb.formula.script;

import java.util.HashMap;
import java.util.logging.Level;

import nc.ms.tb.formula.excel.core.ITbbExcel;
import nc.ms.tb.formula.excel.core.IWorkBook;
import nc.ms.tb.formula.excel.core.IWorkSheet;
import nc.ms.tb.formula.script.core.ObjectArray;
import nc.ms.tb.formula.script.core.parser.InterpreterError;
import nc.ms.tb.formula.script.core.parser.OperationUtils;
import nc.ms.tb.formula.script.core.parser.UtilEvalError;
import nc.vo.tb.rule.excel.CellElement;
import nc.vo.tb.rule.excel.ColumnRow;

//名字空间，获取函数事例，调用函数
public abstract class AbstractNameSpace implements NameSpace {

	private Boolean isFreeFunction;

	public AbstractNameSpace() {
	}

	public Object getVariable(String s, Calculator calculator) {
		Object obj = null;
		if (s.equalsIgnoreCase("null"))
			obj = Primitive.NULL;
		else if (s.equalsIgnoreCase("true"))
			obj = Boolean.TRUE;
		else if (s.equalsIgnoreCase("false"))
			obj = Boolean.FALSE;
		// else if (s.equals("$$$") || s.matches("\\$\\w+"))

		else if (s.equals("$$$"))
			obj = Primitive.NULL;
		// else if (true||s
		// .matches("((\\w+||'[^']+')!)?[\\w&&\\D]+\\d+(:((\\w+||'[^']+')!)?[\\w&&\\D]+\\d+)*"))
		// {
		// obj = parseCellElementList(calculator, s);
		else if (s.startsWith("@")) {
			obj = "";
		} else if (s.indexOf("!") > 0) {
			obj = parseCellElementList(calculator, s);
		} else if (true || s.matches("((\\w+||'[^']+')!)?[\\w&&\\D]+\\d+(:((\\w+||'[^']+')!)?[\\w&&\\D]+\\d+)*")) {
			obj = parseCellElementList(calculator, s);
		}

		return obj;
	}

	public Function getMethod(String s, Calculator calculator) {

		if (s == null)
			throw new InterpreterError("function name should not be null");
		if (initedFunctionClasses == null)
			initedFunctionClasses = new HashMap();
		Class class1 = (Class) initedFunctionClasses.get(s);

		if (class1 == null) {
			String s1 = "nc.ms.tb.formula.script.function." + s;
			try {
				class1 = Class.forName(s1);
			} catch (ClassNotFoundException ex) {

			}
		}
		if (class1 == null || String.class.equals(class1)) {
			initedFunctionClasses.put(s, String.class);
			return null;
		}

		initedFunctionClasses.put(s, class1);
		Object obj = null;
		try {
			obj = class1.newInstance();

		} catch (Exception exception) {
			return null;
		}

		if (obj instanceof Function) {
			return (Function) obj;
		} else {
			return null;
		}

	}

	private HashMap initedFunctionClasses;

	private Boolean getIsFreeFunction() {
		return isFreeFunction;
	}

	private void setIsFreeFunction(Boolean isFreeFunction) {
		this.isFreeFunction = isFreeFunction;
	}

	public static Object parseCellElementList(Calculator paramCalculator, String paramString) {
		return paramCalculator.getParseCellElementDelegate().parseCellElementList(paramCalculator, paramString);
		// Object localObject1 = Primitive.NULL;
		// String[] arrayOfString = splitString(paramString, ":");
		// String localObject2 = null;
		// int i = -1;
		// int j = -1;
		// int k = -1;
		// int l = -1;
		// int i1 = 0;
		// int i2 = arrayOfString.length;
		// while (i1 < i2) {
		// String str = arrayOfString[i1];
		// int i4 = str.indexOf("!");
		// if (i4 > -1) {
		// String localObject4 = str.substring(0, i4);
		// if (localObject2 == null)
		// localObject2 = localObject4;
		// if (!(localObject2.equalsIgnoreCase(localObject4)))
		// NtbLogger.error("sheetName not matches:" + localObject2
		// + "<->" + ((String) localObject4));
		// }
		// Object localObject4 = ColumnRow.convertCellStringToColumnRow(str
		// .substring(i4 + 1));
		// if (((ColumnRow) localObject4).getColumn() > -1)
		// if (((ColumnRow) localObject4).getRow() > -1)
		// if (i == -1) {
		// i = ((ColumnRow) localObject4).getColumn();
		// j = ((ColumnRow) localObject4).getColumn();
		// k = ((ColumnRow) localObject4).getRow();
		// l = ((ColumnRow) localObject4).getRow();
		// } else {
		// i = Math.min(i, ((ColumnRow) localObject4).getColumn());
		// j = Math.max(j, ((ColumnRow) localObject4).getColumn());
		// k = Math.min(k, ((ColumnRow) localObject4).getRow());
		// l = Math.max(l, ((ColumnRow) localObject4).getRow());
		// }
		// ++i1;
		// }
		//
		// ITbbExcel tbbExcel = paramCalculator.getTbbExcel();
		// IWorkSheet localReport = null;
		// if (tbbExcel == null)
		// NtbLogger.error("currentTbbExcel can not be resolved!");
		// int i5;
		// if (localObject2 != null) {
		// if (localObject2.matches("^'.*'$"))
		// localObject2 = localObject2.substring(1,
		// localObject2.length() - 1);
		// if (tbbExcel != null) {
		//
		// localReport = tbbExcel.getWorkSheet(localObject2);
		//
		// }
		// } else {
		//
		// localReport = tbbExcel.getCurrentSheet();
		// }
		//
		// if(i!=-1){
		// i=i-localReport.getColOffSet();
		// }
		// if(j!=-1){
		// j=j-localReport.getColOffSet();
		// }
		// if(k!=-1){
		// k=k-localReport.getRowOffSet();
		// }
		// if(l!=-1){
		// l=l-localReport.getRowOffSet();
		// }
		//
		//
		// ObjectArray localObjectArray = new ObjectArray();
		// for (int i4 = k; i4 <= l; ++i4)
		// for (i5 = i; i5 <= j; ++i5) {
		// CellElement localCellElement = localReport.getCellElement(i4,
		// i5);
		// if (localCellElement == null)
		// continue;
		// if (i5 != localCellElement.getCol())
		// continue;
		// if (i4 != localCellElement.getRow())
		// continue;
		// Object localObject5 = localCellElement.getValue();
		// if (localCellElement.isFormulaCell()) {
		// paramCalculator.setCalculatorLevel(paramCalculator
		// .getCalculatorLevel() + 1);
		// if (paramCalculator.getCalculatorLevel() > 5) {
		// ColumnRow columnRow = new ColumnRow(
		// localCellElement.getRow(),
		// localCellElement.getCol());
		// throw new BusinessRuntimeException("存在公式循环,请重新设置!",
		// new RuntimeException(localReport.getName()
		// + columnRow.toSheetString()));
		// }
		// try {
		// localObject5 = executeFormula(paramCalculator,
		// localCellElement.getFormula(), localReport,
		// localCellElement);
		// } catch (UtilEvalError e) {
		//
		// throw new BusinessRuntimeException("执行公式失败!", e);
		//
		// }
		// paramCalculator.setCalculatorLevel(paramCalculator
		// .getCalculatorLevel() - 1);
		//
		//
		//
		// }
		//
		// localObjectArray.addValue(localObject5);
		// }
		// if (localObjectArray.getValueList().size() == 1) {
		// Object localObject3 = localObjectArray.getValueList().get(0);
		// if (localObject3 == null)
		// localObject1 = new Integer(0);
		// else
		// localObject1 = localObject3;
		// } else if (localObjectArray.getValueList().size() > 1) {
		// localObject1 = 0D;
		// for (Object objValue : localObjectArray.getValueList()) {
		// try {
		// localObject1 = OperationUtils.binaryOperation(localObject1,
		// objValue, "+");
		// } catch (UtilEvalError e) {
		// NtbLogger.error(e);
		// localObject1 = 0D;
		// }
		// }
		//
		// } else {
		// localObject1 = new Integer(0);
		// }
		// return localObject1;
	}

	// private static Object executeFormula(Calculator paramCalculator,
	// ExcelFormula formula, IWorkSheet localReport,
	// CellElement localCellElement) throws UtilEvalError {
	//
	// String currentSheetName = paramCalculator.getTbbExcel()
	// .getCurrentSheet().getName();
	//
	// Object returnValue = null;
	//
	// if (currentSheetName.equals(localReport.getName())) {
	// returnValue = paramCalculator.evalExcel(formula.getExpress());
	// } else {
	// paramCalculator.getTbbExcel()
	// .setCurrentSheet(localReport.getName());
	// returnValue = paramCalculator.evalExcel(formula.getExpress());
	// paramCalculator.getTbbExcel().setCurrentSheet(currentSheetName);
	// }
	//
	// return returnValue;
	// }
	//
	// private static String[] splitString(String paramString, char paramChar) {
	// return splitString(paramString, "" + paramChar);
	// }
	//
	// private static String[] splitString(String paramString1, String
	// paramString2) {
	// if ((paramString1 == null) || (paramString1.length() == 0))
	// return new String[0];
	// return paramString1.split("\\Q" + paramString2 + "\\E");
	// }

}
