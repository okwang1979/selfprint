package nc.ms.tb.formula.script.core.parser;

import java.util.ArrayList;
import java.util.List;

import nc.ms.tb.formula.script.Calculator;
import nc.vo.mdm.pub.NtbLogger;

public class TupleSetNode extends AbstractNode {
	
	

	private List<FunctionCall> functionList = new ArrayList<FunctionCall>();

	private List<String> tupleExpressList = new ArrayList<String>();

	public void addTupleExpress(String express) {
		tupleExpressList.add(express);
	}

	public void addFunction(FunctionCall functionCall) {
		this.functionList.add(functionCall);
	}

	/**
	 * 返回类型是一个datacell列表。根据类型判断是否取值放到datacell上
	 */
	public Object eval(Calculator paramCalculator) throws UtilEvalError {
		


//		CubeFilter cubeFilter = new CubeFilter(paramCalculator.getContext()
//				.getCurrentCubeDef());
//
//		// paramCalculator.getContext().haveDefaultTuple();
//		// 根据字符串添加
//		addExpressFilter(cubeFilter, paramCalculator);
//		
//		
//		CountTimeCost costFindData = new CountTimeCost();
//		costFindData.beginCost();
//		
//		// 根据function进行添加
//		addFunctionFilter(cubeFilter, paramCalculator);
//		
//	
//		if(paramCalculator.getContext().isAcrossCube()){
//			costFindData.addCost("add function filter", costFindData.getCost());
//		}
//		
//		// 设置缓存key，如果是where部分不进行缓存处理（后续可以加上where部分）
//		if (!paramCalculator.getContext().isRunningWhere()) {
//			cubeFilter.setContextKey("");
//			cubeFilter.setContextKey(paramCalculator.getContext()
//					.getCurrentCubeDef().getObjcode()
//					+ ":" + this.toString());
//		} else {
//			cubeFilter.setContextKey("");
//		}
//
//
//		// 添加默认项目
//		addDefaultFilter(cubeFilter, paramCalculator);
//		
//		
//	    if(ICutCube.TYPE_ONLY_TUPLE ==paramCalculator.getContext().getCutCubeType()){   
//	       return cubeFilter;  
//	    }  
//		if(paramCalculator.getContext().isRunningWhere()){
//			paramCalculator.getContext().setWhereCubeFilter(cubeFilter);
//		}
//		
//
//		try {
////			CountTimeCost costFindData = new CountTimeCost();
////			costFindData.beginCost();
//			List<IFormulaDataCell> cells = CutCubeFactory.getCutCube(
//					paramCalculator.getContext(), cubeFilter)
//					.getFilterDataCell();
//			if(paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_PTPSUM_PARENT||paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_PTPSUM_SELF||paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_PTPSUM||paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_DATA||paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_DATA_SELF||paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_DATA_PARENT){
//				return cells;
//			}
//			if(paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_TRACING){
//				if(paramCalculator.getContext().isRunningWhere()){
//					return cells;
//				}else{
//						if(cells instanceof List){
//							 FunctionTracing ft = new FunctionTracing();
//							 ft.setFunction("FIND");
//							for(Object obj :(List)cells ){
//								IFormulaDataCell fcell = (IFormulaDataCell)obj;
//								ft.addCell(fcell.getDataCell());
//							}
//							paramCalculator.getContext().getContextTracings().add(ft);
//						}
//					 
//							return 0;
//						 
//				}
//			
//				
//			}
//			
//			if (paramCalculator.getContext().isTubleToValue()&&!paramCalculator.getContext().isRunningWhere()) {
//				if(paramCalculator.getContext().getOwnerCell()!=null&&paramCalculator.getContext().getOwnerCell().getDataCell().isTextCell()){
//					
//					StringBuffer sb = new StringBuffer();
//					for (IFormulaDataCell cell : cells) {
//						
//						sb.append(this.getCellTextValue(cell));
////						returnValue = returnValue + getDouble(cell);
//					}
//					return sb.toString();
//				}else{
//					UFDouble returnValue = new UFDouble(0);
//					for (IFormulaDataCell cell : cells) {
//						
//						returnValue = returnValue.add(getDouble(cell,paramCalculator));
////						returnValue = returnValue + getDouble(cell,paramCalculator);
//					}
//
//					return returnValue.doubleValue();
//				}
//	
//
//			} else if (paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_EXECUTE_RANGE
//					&& paramCalculator.getContext().isTubleToValue()) {
//				return 0;
//
//			} else if (paramCalculator.getContext().getCutCubeType() == ICutCube.TYPE_ALLOT
//					&& !paramCalculator.getContext().isRunningWhere()) {
//				UFDouble returnValue = new UFDouble(0);
//				for (IFormulaDataCell cell : cells) {
//					returnValue = returnValue.add(getDouble(cell,paramCalculator));
//				}
//			
//				return returnValue.doubleValue();
//			} else {
//				return cells;
//			}
//
//		} catch (BusinessException e) {
//			NtbLogger.error(e);
//		}
		return null;

	}

	

	

	@Override
	public String toString() {
		String function = "";
		for (FunctionCall fc : this.functionList) {
			function = function + fc.toString() + "->";
		}
		if (function.length() > 2) {
			function = function.substring(0, function.length() - 2);
		}
		String members = "";
		for (String express : this.tupleExpressList) {
			members = members + express + "->";
		}
		if (members.length() > 2) {
			members = members.substring(0, members.length() - 2);
		}
		if (members.length() > 0 && function.length() > 0) {
			return "{" + members + "->" + function + "}";
		} else {
			return "{" + members + function + "}";
		}

	}
	
	
	

	@Override
	public String toDesc(DescriptionContext descContext) {
		String function = "";
		if(descContext.isSimpleDesc()){
			
			String measure = "";
			for (FunctionCall fc : this.functionList) {
				measure = fc.toDesc(descContext);
				 if(measure!=null&&measure.length()>0){
					 break;
				 }
			}
			
			 if(measure!=null&&measure.length()>0){
				 return "FIND("+measure+")";
			 }else{
				 return "FIND";
			 }
			
		}
		for (FunctionCall fc : this.functionList) {
			function = function + fc.toDesc(descContext) + "->";
		}
		if (function.length() > 2) {
			function = function.substring(0, function.length() - 2);
		}
		String members = "";
		for (String express : this.tupleExpressList) {
			members = members + express + "->";
		}
		if (members.length() > 2) {
			members = members.substring(0, members.length() - 2);
		}
		if (members.length() > 0 && function.length() > 0) {
			return "{" + members + "->" + function + "}";
		} else {
			return "{" + members + function + "}";
		}

	}

	

	
	@Override
	public String toValue(Calculator calculator) {
		try {
			int oldCutType = calculator.getContext().getCutCubeType();
//			calculator.getContext().setCutCubeType(ICutCube.TYPE_WORKBOOK_DEFAULT);
			calculator.getContext().setTubleToValue(true);
			String returnValue =  String.valueOf(this.eval(calculator));
			calculator.getContext().setCutCubeType(oldCutType);
			return returnValue;
		} catch (UtilEvalError e) {
			NtbLogger.error(e);
			return "0";
		}
	}

}
