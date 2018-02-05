package nc.ms.tb.formula.script;

import nc.ms.tb.formula.excel.core.ITbbExcel;
import nc.ms.tb.formula.excel.core.IWorkSheet;
import nc.ms.tb.formula.excel.core.TbbExcelHelper;
import nc.ms.tb.formula.script.core.ErrColumnRow;
import nc.ms.tb.formula.script.core.ObjectArray;
import nc.ms.tb.formula.script.core.parser.UtilEvalError;
import nc.vo.mdm.pub.NtbLogger;
import nc.vo.tb.rule.excel.CellElement;
import nc.vo.tb.rule.excel.ColumnRow;
import nc.vo.tb.rule.excel.ExcelFormula;

/**
 * 正常计算委托给这个类.
 * 
 * @author wangzhqa
 * 
 */
public class EvalParseCellElementDelegate implements IParseCellElementDelegate {

	public Object parseCellElementList(Calculator paramCalculator, String paramString) {
		Object localObject1 = Primitive.NULL;
		String[] arrayOfString = splitString(paramString, ":");
		String localObject2 = null;
		int i = -1;
		int j = -1;
		int k = -1;
		int l = -1;
		int i1 = 0;
		int i2 = arrayOfString.length;
		while (i1 < i2) {
			String str = arrayOfString[i1];
			int i4 = str.indexOf("!");
			if (i4 > -1) {
				String localObject4 = str.substring(0, i4);
				if (localObject2 == null)
					localObject2 = localObject4;
				if (!(localObject2.equalsIgnoreCase(localObject4)))
					NtbLogger.error("sheetName not matches:" + localObject2 + "<->" + ((String) localObject4));
			}
			Object localObject4 = ColumnRow.convertCellStringToColumnRow(str.substring(i4 + 1));
			if (((ColumnRow) localObject4).getColumn() > -1)
				if (((ColumnRow) localObject4).getRow() > -1)
					if (i == -1) {
						i = ((ColumnRow) localObject4).getColumn();
						j = ((ColumnRow) localObject4).getColumn();
						k = ((ColumnRow) localObject4).getRow();
						l = ((ColumnRow) localObject4).getRow();
					} else {
						i = Math.min(i, ((ColumnRow) localObject4).getColumn());
						j = Math.max(j, ((ColumnRow) localObject4).getColumn());
						k = Math.min(k, ((ColumnRow) localObject4).getRow());
						l = Math.max(l, ((ColumnRow) localObject4).getRow());
					}
			++i1;
		}

		ITbbExcel tbbExcel = paramCalculator.getTbbExcel();
		IWorkSheet localReport = null;
		if (tbbExcel == null)
			NtbLogger.error("currentTbbExcel can not be resolved!");
		int i5;
		if (localObject2 != null) {
			if (localObject2.matches("^'.*'$"))
				localObject2 = localObject2.substring(1, localObject2.length() - 1).trim();
			if (tbbExcel != null) {

				localReport = tbbExcel.getWorkSheet(localObject2);

			}
		} else {

			localReport = tbbExcel.getCurrentSheet();
		}
		if (localReport == null) {
			NtbLogger.error("未找到单元格:"+ paramString);
			return 0;
			
//			throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0", "01050rule002-0059")/*
//																																		 * @res
//																																		 * "未找到单元格："
//																																		 */
//					+ paramString);
		}

		if (i != -1) {
			i = i - localReport.getColOffSet();
		}
		if (j != -1) {
			j = j - localReport.getColOffSet();
		}
		if (k != -1) {
			k = k - localReport.getRowOffSet();
		}
		if (l != -1) {
			l = l - localReport.getRowOffSet();
		}

		ObjectArray localObjectArray = new ObjectArray();
		localObjectArray.setRowCount(l - k + 1);
		localObjectArray.setColCount(j - i + 1);
		if (i < 0 || j < 0 || k < 0 || l < 0) {
			return new ErrColumnRow();
		}
		for (i5 = i; i5 <= j; ++i5)
			for (int i4 = k; i4 <= l; ++i4) {

				CellElement localCellElement = localReport.getCellElement(i4, i5);

				if (localCellElement == null) {
					localObjectArray.addValue(0D);
					continue;
				}
		 

				if (i5 != localCellElement.getCol())
					continue;
				if (i4 != localCellElement.getRow())
					continue;
				Object cellValue = localCellElement.getValue();

				if (paramCalculator.isCellValueIsCell()) {
					cellValue = localCellElement;
					localObjectArray.addValue(cellValue);
					continue;
				}

				boolean isCalFormula = true;
				if (paramCalculator.getContext().getValue("Excel_CHECK_TYPE") != null) {
					isCalFormula = false;
				}
				if (localCellElement.isFormulaCell() && isCalFormula) {
					paramCalculator.setCalculatorLevel(paramCalculator.getCalculatorLevel() + 1);
					if (paramCalculator.getCalculatorLevel() > 40) {
						// paramCalculator.get
						ColumnRow columnRow = new ColumnRow(localCellElement.getRow() + localReport.getRowOffSet(), localCellElement.getCol()
								+ localReport.getColOffSet());

						// ColumnRow columnRow2 = new ColumnRow(
						// localCellElement.getRow(),
						// localCellElement.getCol());
						//

						// throw new
						// BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule001-0056")/*@res
						// "存在公式循环,请重新设置!"*/);
						//

						paramCalculator.setCalculatorLevel(40);
						String info = "存在公式循环,请重新设置!";
																															
					 
						NtbLogger.error(info);

						cellValue = localCellElement.getValue();

						// throw new BusinessRuntimeException("存在公式循环,请重新设置!",
						// new RuntimeException(localReport.getName()
						// + columnRow.toSheetString()
						// + ";Express:"
						// + localCellElement.getFormula()
						// .getExpress() + ";RowOffSet:"
						// + localReport.getRowOffSet()
						// + ";ColOffSet:"
						// + localReport.getColOffSet()));
						String key = "CatchValue:Row" + localCellElement.getRow() + "Col" + localCellElement.getCol() + "" + localReport.getName();
						paramCalculator.getContext().setValue(key, cellValue);
					}else{
						CellElement oldCell = paramCalculator.getTbbExcel().getCurrentCell();
						try {
						

							paramCalculator.getTbbExcel().setCurrentCellElement(localCellElement);
							String key = "CatchValue:Row" + localCellElement.getRow() + "Col" + localCellElement.getCol() + "" + localReport.getName();
							if (paramCalculator.getContext() != null && paramCalculator.getContext().getValue(key) != null) {
								cellValue = paramCalculator.getContext().getValue(key);
							} else {
								cellValue = executeFormula(paramCalculator, localCellElement.getFormula(), localReport, localCellElement);
							}
							
							paramCalculator.getContext().setValue(key, cellValue);

						
						} catch (Exception e) {

							String info = localReport.getName()
									+ " 表:" 
									+ (new ColumnRow(localCellElement.getRow(), localCellElement.getCol())).toSheetString()
									+  "单元格" 
									+ " 执行公式失败! ";
							 
							NtbLogger.error(info);
							NtbLogger.error("----->>" + e.getMessage());
							cellValue = localCellElement.getValue();

							// throw new
							// BusinessRuntimeException(localReport.getName()+nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule001-0057")/*@res
							// " 表:"*/+(new ColumnRow(localCellElement.getRow(),
							// localCellElement.getCol())).toSheetString()+nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule001-0058")/*@res
							// "单元格"*/+NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule", "01420rul_000138")/*执行公式失败!*/, e);

						}
						paramCalculator.getTbbExcel().setCurrentCellElement(oldCell);
						paramCalculator.setCalculatorLevel(paramCalculator.getCalculatorLevel() - 1);
					}
					

				}

				localObjectArray.addValue(cellValue);
			}
		if (localObjectArray.getValueList().size() == 1 && !paramCalculator.isCellValueIsCell()) {
			Object localObject3 = localObjectArray.getValueList().get(0);
			if (localObject3 == null || "".equals(String.valueOf(localObject3)))
				localObject1 = Primitive.NULL;
			else
				localObject1 = localObject3;
		} else if (localObjectArray.getValueList().size() > 1) {
			// 如果取到多个目标直接返回多个目标数组,多个目标只能出现在函数的一个参数.
			return localObjectArray;
			// localObject1 = 0D;
			// for (Object objValue : localObjectArray.getValueList()) {
			// try {
			// if(objValue==null){
			// continue;
			// }
			// localObject1 = OperationUtils.binaryOperation(localObject1,
			// objValue, "+");
			// } catch (UtilEvalError e) {
			// NtbLogger.error(e);
			// localObject1 = 0D;
			// }
			// }

		} else if (paramCalculator.isCellValueIsCell()) {
			return localObjectArray;
		} else {
			localObject1 = Primitive.NULL;
		}
		return localObject1;
	}

	private static Object executeFormula(Calculator paramCalculator, ExcelFormula formula, IWorkSheet localReport, CellElement localCellElement)
			throws UtilEvalError {

		String currentSheetName = paramCalculator.getTbbExcel().getCurrentSheet().getName();

		Object returnValue = null;

		if (currentSheetName.equals(localReport.getName())) {
			if (formula == null) {
				returnValue = 0;
			} else {
				returnValue = paramCalculator.evalExcel(formula.getExpress());
			}

		} else {
			paramCalculator.getTbbExcel().setCurrentSheet(localReport.getName());
			returnValue = paramCalculator.evalExcel(formula.getExpress());
			paramCalculator.getTbbExcel().setCurrentSheet(currentSheetName);
		}

		String key = "CatchValue:Row" + localCellElement.getRow() + "Col" + localCellElement.getCol() + "" + localReport.getName();

		if (paramCalculator.getContext() != null) {
			if (returnValue instanceof Number) {
				Double convertValue = TbbExcelHelper.getCellValue(((Number) returnValue).doubleValue(), localCellElement);
				paramCalculator.getContext().setValue(key, convertValue);
				returnValue = convertValue;
			} else {
				paramCalculator.getContext().setValue(key, returnValue);
			}

		}
		return returnValue;
	}

	private static String[] splitString(String paramString, char paramChar) {
		return splitString(paramString, "" + paramChar);
	}

	private static String[] splitString(String paramString1, String paramString2) {
		if ((paramString1 == null) || (paramString1.length() == 0))
			return new String[0];
		return paramString1.split("\\Q" + paramString2 + "\\E");
	}

}