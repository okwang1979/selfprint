package nc.ms.tb.formula.script.core.parser;

import java.util.List;

import antlr.collections.impl.BitSet;

/**
 * 公式执行的主类，生成公式后在这里进行计算并返回结果。
 *
 * @author wangzhqa
 *
 */
public class TbbFormulaExpression extends AbstractNode {

	public static final String TYPE_SELECT = null;

	public List<ConditionalExpression> getBodyExpressionList() {
		return null;
//		return bodyExpressionList;
	}

	public void setCubeCode(String text) {
		// TODO 自动生成的方法存根
		
	}

	public void setWhereExpress(ConditionalExpression whereExpression) {
		// TODO 自动生成的方法存根
		
	}


//	public static final int TYPE_SIMPLE = 0;
//
//	public static final int TYPE_SELECT = 1;
//
//	public static final int TYPE_UPDATE = 2;
//
//	public static final int TYPE_CONTROL = 3;
//
//	private int expressionType = 0;
//
//	private List<ConditionalExpression> bodyExpressionList = new ArrayList<ConditionalExpression>();
//
//	private String cubeCode;
//
//	private ConditionalExpression whereExpress;
//
//	/**
//	 * 返回类型必须是RuleDataSet 这里逻辑有点混乱，后续要重构一下。
//	 *
//	 */
//	@Override
//	public Object eval(Calculator calculator) throws UtilEvalError {
//		// 执行Where执行结果放到formuladatacell中
//		RuleDataSet returnSet = new RuleDataSet();
//
//		calculator.getContext().setTubleToValue(false);
//		calculator.getContext().setQueryServiceDataCell(false);
//		calculator.getContext().setRunningWhere(true);
//		// 计算where部分
//		Object tuples = whereExpress.eval(calculator);
//		// 如果分配需要单独处理
//		if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_ALLOT) {
//			calculator.getContext().setRunningWhere(false);
//			if (tuples instanceof List) {
//				List<FormulaDataCell> cells = (List) tuples;
//				setAllotDataCells(cells, calculator.getContext(), calculator);
//				return returnSet;
//			}
//
//		}
//
//		if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_CTRL_ALLOT) {
//			calculator.getContext().setRunningWhere(false);
//			if (tuples instanceof List) {
//				List<FormulaDataCell> cells = (List) tuples;
//				setAllotDataCells(cells, calculator.getContext(), calculator);
//				return returnSet;
//			}
//		}
//		if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_ALLOT_FROM_WORKBOOK
//				|| calculator.getContext().getCutCubeType() == ICutCube.TYPE_CTRL_ALLOT_FROM_WORKBOOK) {
//			calculator.getContext().setRunningWhere(false);
//			if (tuples instanceof List) {
//				List<FormulaDataCell> cells = (List) tuples;
//				for (ConditionalExpression ce : bodyExpressionList) {
//					calculator.getContext().setCurrentNode(ce);
//				}
//				//控制不进行递归调用  2012-2-19
//				calculator.setCalculatorLevel(6);
//				WhereDataCellInfo whereDataCellInfo = new WhereDataCellInfo();
//				List<DataCell> dCells = new ArrayList<DataCell>();
//				for(FormulaDataCell fc:cells){
//					if(fc.getDataCell()!=null){
//						dCells.add(fc.getDataCell());
//					}
//				}
//				whereDataCellInfo.addCells(dCells);
//				calculator.getContext().setValue("AbstractCutCube.WhereCells", whereDataCellInfo);
//				setAllotWorkBookDataCells(cells, calculator.getContext(),
//						calculator);
//
//			}
//			return returnSet;
//		}
//
//		// 如果只计算单元格是否含有公式，直接返回dataset因为不需要进行计算，只修改dataCell的状态。
//		if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_SET_FORMULA_DATACELL) {
//			return new RuleDataSet();
//		}
//		// 预测取数范围放到context中
//		// calculator.getContext().setTubleToValue(true);
//		// int currentCutType = calculator.getContext().getCutCubeType();
//		// calculator.getContext().setCutCubeType(ICutCube.TYPE_EXECUTE_RANGE);
//		// for (ConditionalExpression ce : bodyExpressionList) {
//		// ce.eval(calculator);
//		// }
//		// 设置回原来的取数。
//		// calculator.getContext().setCutCubeType(currentCutType);
//		calculator.getContext().setRunningWhere(false);
//		if(calculator.getContext().isFormulaEnd()){
//			
//			if (tuples instanceof List) {
//				List<FormulaDataCell> cells = (List) tuples;
//				calculator.getContext().setCurrentWhereCells(cells);
//				for(FormulaDataCell cell:cells){
//					RuleDataRow dataRow = new RuleDataRow();
//					 
//					dataRow.setFormulaDataCell(cell);
//					returnSet.getRowList().add(dataRow);
//				}
//				 
//				return returnSet;
//			}
//			return returnSet;
//		}
//		if (tuples instanceof List) {
////			List<FormulaDataCell> cells = (List) tuples;
//			
//			
//			if(calculator.getContext().getConsolidationContext()!=null&&calculator.getContext().getConsolidationContext().getMaxDuad()!=null&&calculator.getContext().getConsolidationContext().getMaxDuad().size()>0){
//				
//				List<FormulaDataCell> cells = (List) tuples;
////				if(	calculator.getContext().getCurrentWhereCells()!=null){
////					calculator.getContext().getCurrentWhereCells().addAll((List) tuples);
////				}else{
////					calculator.getContext().setCurrentWhereCells(cells);
////				}
//
//				// 执行完where部分的代码，需要把TubleToValue设置成ture，在执行其他部分时，TubleNode会返回double值。
//				calculator.getContext().setTubleToValue(true);
//				calculator.getContext().setQueryServiceDataCell(true);
//
//
//				CubeDef cube = CubeHelper.getCubeDefByCode(cubeCode);
//				IDimManager dm = DimServiceGetter.getDimManager();
//				DimLevel dimEntity = dm.getDimLevelByBusiCode(IDimLevelCodeConst.ENTITY);
//				DimLevel dimCust = dm.getDimLevelByBusiCode("BM_CUST");
//				
//				for (FormulaDataCell fcc : cells) {
//					
//					for(AccountDuad duad:calculator.getContext().getConsolidationContext().getMaxDuad()){
//
//						DimMember entityMember = cube.getDimHierarchy(dimEntity.getDimDef()).getMemberReader()
//								.getMemberByLevelValues(duad.getLocalEntity());
//
//						DimMember custMember = cube.getDimHierarchy(dimCust.getDimDef()).getMemberReader()
//								.getMemberByLevelValues(duad.getOtherCustomer());
//
//						DimVector rtnDv = fcc.getDataCell().getDimVector().addOrReplaceDimMember(entityMember).addOrReplaceDimMember(custMember);
//						DataCell cell = new DataCell(cube, rtnDv);
//		 
//						FormulaDataCell fc  = new FormulaDataCell(cell);
//						
//						fc.setDuad(duad);
//						calculator.getContext().setOwnerCell(fc);
//						RuleDataRow dataRow = new RuleDataRow();
//	
//						CountTimeCost setEval = new CountTimeCost();
//						setEval.beginCost();
//						for (ConditionalExpression ce : bodyExpressionList) {
//							calculator.getContext().setCurrentNode(ce);
//							dataRow.getValueList().add(ce.eval(calculator));
//	
//						}
//
//						 
//						if(dataRow.getDefaultValue() instanceof Number){
//							if(0==((Number)dataRow.getDefaultValue()).doubleValue()){
//								continue;
//							}
//						}
//						ConsolidationFormulaVO formula = calculator.getContext().getConsolidationContext().getFormula();
//						if(calculator.getContext().getConsolidationContext().getFormulaDefaultCellMap().get(formula)==null){
//							calculator.getContext().getConsolidationContext().getFormulaDefaultCellMap().put(formula, cell);
//						}
//	
//						setEval.addCost("setEval", setEval.getCost());
//	
//						if (calculator.getContext().isFormulaEnd()) {
//							// calculator.getContext().setOwnerCell(null);
//							return returnSet;
//						}
//						if (this.getExpressionType() == TYPE_UPDATE) {
//	
//							fc.setValue(dataRow.getDefaultValue());
//	
//							// 如果是一致性计算，需要把DataCell放到传入的CubeDataSet中。
//							if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_CONSIST
//									&& fc.isSaveCell()) {
//								String cubeCode = fc.getDataCell().getCubeDef()
//										.getObjcode();
//								ICubeDataSet cubeSet = calculator.getContext()
//										.getCubeDataSet(cubeCode);
//								DataCell fCell = fc.getDataCell();
//								if (cubeSet != null) {
//									DataCell dc = cubeSet.getDataCell(fCell
//											.getDimVector());
//									if (dc != null) {
//										dc.setCellValue(fCell.getCellValue());
//									}
//	
//								}
//	
//							}
//	
//						}
//						FormulaTuple formulaTuple = new FormulaTuple();
//						formulaTuple.setDimMemberMap(fc.getCellTupleMap());
//						dataRow.setTuple(formulaTuple);
//						dataRow.setFormulaDataCell(fc);
//						returnSet.getRowList().add(dataRow);
//						calculator.getContext().getCurrentWhereCells().add(fc);
//						// 如果是审核规则需要把结果放到context中进行存储
//						if (calculator.getContext().isCheckRule()) {
//							addCheckResult(fc, dataRow, calculator.getContext());
//						}
//					}
//				}
//				if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_CONSIST_AFTER) {
//					List<DataCell> saveCells = new ArrayList<DataCell>();
//
//					for (FormulaDataCell cell : cells) {
//						cell.getDataCell().setFlag_Changed(true);
//						saveCells.add(cell.getDataCell());
//					}
//				}
//
//				calculator.getContext().getConsolidationContext().clearMaxDuad();
//					
//			}else{
//				
//				List<FormulaDataCell> cells = (List) tuples;
//				if(	calculator.getContext().getCurrentWhereCells()!=null){
//					calculator.getContext().getCurrentWhereCells().addAll((List) tuples);
//				}else{
//					calculator.getContext().setCurrentWhereCells(cells);
//				}
//
//				// 执行完where部分的代码，需要把TubleToValue设置成ture，在执行其他部分时，TubleNode会返回double值。
//				calculator.getContext().setTubleToValue(true);
//				calculator.getContext().setQueryServiceDataCell(true);
//
//				batchFunction(cells, calculator.getContext());
//				try {
//					batchUfoFunction(cells, calculator.getContext());
//				} catch (Exception e) {
//					throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule002-0048")/*@res "UFO批量执行错误,对应规则为:"*/+this.toString(),e);
//				}
//				
//		
//
//				for (FormulaDataCell fc : cells) {
//					calculator.getContext().setOwnerCell(fc);
//					RuleDataRow dataRow = new RuleDataRow();
//
//					CountTimeCost setEval = new CountTimeCost();
//					setEval.beginCost();
//					for (ConditionalExpression ce : bodyExpressionList) {
//						calculator.getContext().setCurrentNode(ce);
//						dataRow.getValueList().add(ce.eval(calculator));
//
//					}
//
//					setEval.addCost("setEval", setEval.getCost());
//
//					if (calculator.getContext().isFormulaEnd()) {
//						// calculator.getContext().setOwnerCell(null);
//						return returnSet;
//					}
//					if (this.getExpressionType() == TYPE_UPDATE) {
//
//						fc.setValue(dataRow.getDefaultValue());
//
//						// 如果是一致性计算，需要把DataCell放到传入的CubeDataSet中。
//						if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_CONSIST
//								&& fc.isSaveCell()) {
//							String cubeCode = fc.getDataCell().getCubeDef()
//									.getObjcode();
//							ICubeDataSet cubeSet = calculator.getContext()
//									.getCubeDataSet(cubeCode);
//							DataCell fCell = fc.getDataCell();
//							if (cubeSet != null) {
//								DataCell dc = cubeSet.getDataCell(fCell
//										.getDimVector());
//								if (dc != null) {
//									dc.setCellValue(fCell.getCellValue());
//								}
//
//							}
//
//						}
//
//					}
//					FormulaTuple formulaTuple = new FormulaTuple();
//					formulaTuple.setDimMemberMap(fc.getCellTupleMap());
//					dataRow.setTuple(formulaTuple);
//					dataRow.setFormulaDataCell(fc);
//					returnSet.getRowList().add(dataRow);
//					// 如果是审核规则需要把结果放到context中进行存储
//					if (calculator.getContext().isCheckRule()) {
//						addCheckResult(fc, dataRow, calculator.getContext());
//					}
//				}
//				if (calculator.getContext().getCutCubeType() == ICutCube.TYPE_CONSIST_AFTER) {
//					List<DataCell> saveCells = new ArrayList<DataCell>();
//
//					for (FormulaDataCell cell : cells) {
//						cell.getDataCell().setFlag_Changed(true);
//						saveCells.add(cell.getDataCell());
//					}
//				}
//
//			
//				
//			}
//
//		} else {
//			throw new BusinessRuntimeException(NCLangRes4VoTransl
//					.getNCLangRes().getStrByID("tbb_rule", "01420rule-000187")/*
//																			 * where
//																			 * 部分表达式错误
//																			 * ！
//																			 */);
//		}
//		// 分配公式
//
//		// 执行select or update
//
//		// 返回结果
//		calculator.getContext().setOwnerCell(null);
//
//		return returnSet;
//	}
//
//
//
//
//
//	/**
//	 * 批量执行
//	 *
//	 * @author wangzhqa
//	 * @since 2012-6-16<br>
//	 * @param cells
//	 * @param context
//	 *            last editor：wangzhqa;at 2012-6-16.
//	 */
//	private void batchFunction(List<FormulaDataCell> cells,
//			IFormulaContext context) {
//
//		List<FunctionCall> functions = new ArrayList<FunctionCall>();
//		List<Node> nodeList = new ArrayList<Node>();
//		for (ConditionalExpression expression : this.bodyExpressionList) {
//			nodeList = expression.getNodes(nodeList);
//		}
//		for (Node node : nodeList) {
//			if (node instanceof FunctionCall) {
//				if ("UFIND".equals(((FunctionCall) node).getName())
//						|| "PREFIND".equals(((FunctionCall) node).getName())) {
//					functions.add((FunctionCall) node);
//				}
//			}
//		}
//
//		Set<String> expressSet = new HashSet<String>();
//
//		ICtlScheme service = context.getService(ICtlScheme.class);
//		CostTime ct = new CostTime();
//		for (FunctionCall fCall : functions) {
//			for (FormulaDataCell fCell : cells) {
//				StringBuffer exeExpress = new StringBuffer(fCall.getName()
//						+ "(\"").append(
//						String.valueOf((fCall.getArguments().get(0)))
//								.replaceAll("'", "")).append("\")");
//
//				try {
//					String _express = service.getExeFormulaExpress(
//							fCell.getDataCell(), exeExpress.toString(),
//							fCall.getName());
//					expressSet.add(_express);
//				} catch (BusinessException e) {
//					throw new BusinessRuntimeException(e.getMessage(), e);
//
//				}
//			}
//
//		}
//		RuleTimeCost rc = RuleTimeCost.getContextCost(context);
//		List<String> allExpress = new ArrayList<String>();
//		allExpress.addAll(expressSet);
//		ct.printStepCost(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule004-0055")/*@res "业务系统取数共"*/+allExpress.size()+nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule004-0056")/*@res "条,生成表达式时间"*/);
//		if (allExpress.size() > 0) {
//			try {
//				CostTime execTime = new CostTime();
//				rc.reStart();
//				NtbParamVO[] vos = service.getExeData(allExpress
//						.toArray(new String[0]));
//				rc.addStartCost("Rule Execute UFind  get data cost.");
//				execTime.printTotalCost(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule004-0057")/*@res "业务系统取数执行耗时，数共"*/+allExpress.size()+nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule004-0058")/*@res "条"*/);
//				if (vos != null && vos.length == allExpress.size()) {
//					for (int i = 0; i < vos.length; i++) {
//						int dataType = vos[i].getCurr_type();
//
//						if(allExpress.get(i).startsWith("PRE")){
//							if( vos[i].getReadydata()!=null){
//								UFDouble vaue = vos[i].getReadydata()[dataType];
//								context.setValue(allExpress.get(i), vaue);
//							}else{
//								context.setValue(allExpress.get(i), new UFDouble(0));
//							}
//
//						}else{
//							if(vos[i].getRundata()!=null){
//								UFDouble vaue = vos[i].getRundata()[dataType];
//								context.setValue(allExpress.get(i), vaue);
//							}else{
//								context.setValue(allExpress.get(i), new UFDouble(0));
//							}
//
//						}
//
////						getRundata
////						getReadydata
////
////						if( vos[i].getReadydata()!=null){
////							getRundata
////							UFDouble vaue = vos[i].getReadydata()[dataType];
////							context.setValue(allExpress.get(i), vaue);
////						}else{
////							context.setValue(allExpress.get(i), new UFDouble(0));
////						}
//
//					}
//				} else {
//					NtbLogger.error("Ufind return Result not rigth");
//				}
//			} catch (BusinessException e) {
//				throw new BusinessRuntimeException(e.getMessage(), e);
//			}
//		}
//		ct.printTotalCost(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_rule_0","01050rule004-0059")/*@res "取业务系统总耗时"*/);
//
//	}
//
//	private void addCheckResult(FormulaDataCell fc, RuleDataRow dataRow,
//			IFormulaContext context) {
//		Object obj = dataRow.getDefaultValue();
//		ExcelTupleAdapter adapter = null;
//		String adapterKey = "ExcelAdapter"
//				+ fc.getDataCell().getCubeDef().getPk_obj();
//		if (context.getValue(adapterKey) == null) {
//			adapter = new ExcelTupleAdapter(fc.getDataCell().getCubeDef()
//					.getDimHiers());
//			context.setValue(adapterKey, adapter);
//		} else {
//			adapter = (ExcelTupleAdapter) context.getValue(adapterKey);
//		}
//
//		// ExcelTupleAdapter adapter = new ExcelTupleAdapter(cd.getDimHiers());
//
//		if (obj != null) {
//
//			CheckRuleResultVO cv = null;
//			if (obj instanceof Boolean) {
//				CountTimeCost setEval = new CountTimeCost();
//				setEval.beginCost();
//				String dvExpress = getExcelDimVectorExpress(fc.getDataCell()
//						.getCubeDef(), fc.getDataCell().getDimVector(), adapter);
//				setEval.addCost("convert cost:", setEval.getCost());
//
//				if (fc.getCheckVo() != null) {
//					fc.getCheckVo().setCheckPass((Boolean) obj);
//					fc.getCheckVo().setCubeCode(
//							fc.getDataCell().getCubeDef().getObjcode());
//					fc.getCheckVo().setCheckDimVector(fc.getDimVector());
//
//					fc.getCheckVo().setDvExpress(dvExpress);
//					cv = fc.getCheckVo();
//					// context.addCheckRuleResultVO(fc.getCheckVo());
//
//				} else {
//					cv = new CheckRuleResultVO();
//					cv.setCheckPass((Boolean) obj);
//					cv.setCheckDimVector(fc.getDimVector());
//					cv.setDvExpress(dvExpress);
//					cv.setCubeCode(fc.getDataCell().getCubeDef().getObjcode());
//					// context.addCheckRuleResultVO(cv);
//				}
//
//			} else if (obj instanceof CheckRuleResultVO) {
//				String dvExpress = getExcelDimVectorExpress(fc.getDataCell()
//						.getCubeDef(), fc.getDataCell().getDimVector(), adapter);
//
//				((CheckRuleResultVO) obj).setCheckDimVector(fc.getDimVector());
//				fc.getCheckVo().setDvExpress(dvExpress);
//				fc.getCheckVo().setCubeCode(
//						fc.getDataCell().getCubeDef().getObjcode());
//				cv = (CheckRuleResultVO) obj;
//				// context.addCheckRuleResultVO((CheckRuleResultVO) obj);
//			}
//			if (cv != null) {
//				cv.getContainDataCell().addAll(fc.getContainCells());
//				context.addCheckRuleResultVO(cv);
//
//			}
//
//		}
//
//	}
//
//	private String getExcelDimVectorExpress(CubeDef cd, DimVector dv,
//			ExcelTupleAdapter adapter) {
//		ArrayList<DimLevel> list = new ArrayList<DimLevel>();
//		List<DimHierarchy> dhs = cd.getDimHiers();
//		for (DimHierarchy dh : dhs) {
//			list.addAll(dh.getDimLevels());
//		}
//		DimLevel[] dls = list.toArray(new DimLevel[0]);
//		Arrays.sort(dls, new DefaultDimSort.DimLevelComparator());
//
//		StringBuffer dvSb = new StringBuffer();
//		for (int i = 0; i < dls.length; i++) {
//
//			LevelValue lv = dv.getLevelValue(dls[i]);
//			if (lv == null) {
//				dvSb.append(IFormConst.celldv_null);
//			}
//
//			else {
//				dvSb.append(lv.getUniqCode());
//			}
//
//			dvSb.append(IFormConst.celldv_seperator);
//		}
//		// ExcelTupleAdapter adapter = new ExcelTupleAdapter(cd.getDimHiers());
//		return adapter.filterDimLevel(dvSb.toString());
//
//	}
//
//	/**
//	 * 设置Data到context中。
//	 *
//	 * @author wangzhqa
//	 * @since 2012-4-27<br>
//	 * @param cells
//	 *            last editor：wangzhqa;at 2012-4-27.
//	 * @param calculator
//	 * @param iFormulaContext
//	 */
//	private void setAllotDataCells(List<FormulaDataCell> cells,
//			IFormulaContext context, Calculator calculator) {
//		for (FormulaDataCell fc : cells) {
//			if (this.getBodyExpressionList() != null
//					&& this.getBodyExpressionList().size() > 0) {
//				// 现在只分配第一个单元。
//				AllotFormulaVo vo = new AllotFormulaVo();
//				if (context.getCutCubeType() == ICutCube.TYPE_CTRL_ALLOT) {
//					vo.setSectionTuple(fc.getSectionTuple());
//					vo.setCellExpress(this.getBodyExpressionList().get(0)
//							.toString());
//				} else {
//					vo.setAllotCell(fc.getDataCell());
//					calculator.getContext().setOwnerCell(fc);
//					String express = this.bodyExpressionList.get(0).toValue(
//							calculator);
//					// vo.setCellExpress(this.bodyExpressionList.get(0).toString());
//					vo.setCellExpress(express);
//				}
//
//				context.addAllotFormula(vo);
//			}
//
//		}
//
//	}
//
//	private void setAllotWorkBookDataCells(List<FormulaDataCell> cells,
//			IFormulaContext context, Calculator calculator) {
//		for (FormulaDataCell fc : cells) {
//			if (this.getBodyExpressionList() != null
//					&& this.getBodyExpressionList().size() > 0) {
//				// 现在只分配第一个单元。
//				AllotFormulaForTModelVO vo = new AllotFormulaForTModelVO();
//				vo.setDataCell(fc.getDataCell());
//				if (context.getCutCubeType() == ICutCube.TYPE_ALLOT_FROM_WORKBOOK) {
//					vo.setCellExpress(this.getBodyExpressionList().get(0)
//							.toString());
//				} else {
//					calculator.getContext().setOwnerCell(fc);
//					calculator.getContext().setCurrentWhereCells(cells);
//					String express = this.bodyExpressionList.get(0).toValue(
//							calculator);
//					// vo.setCellExpress(this.bodyExpressionList.get(0).toString());
//					vo.setCellExpress(express);
//				}
//
//				context.getAllotFormulaForTModelList().add(vo);
//			}
//
//		}
//
//	}
//
//
//	private void batchUfoFunction(List<FormulaDataCell> cells,
//			IFormulaContext context) throws Exception{
//
//		List<FunctionCall> functions = new ArrayList<FunctionCall>();
//		List<Node> nodeList = new ArrayList<Node>();
//		for (ConditionalExpression expression : this.bodyExpressionList) {
//			nodeList = expression.getNodes(nodeList);
//		}
//		for (Node node : nodeList) {
//			if (node instanceof FunctionCall) {
//				if ("UFO".equals(((FunctionCall) node).getName())) {
//					functions.add((FunctionCall) node);
//				}
//			}
//		}
//
//
//
//		List<UfoResult> ufos = new ArrayList<UfoResult>();
//
//		for (FunctionCall fCall : functions) {
//			for (FormulaDataCell fCell : cells) {
//				UfoResult ur = new UfoResult( String.valueOf(fCall.getArguments().get(0)), fCell.getDataCell());
//				ufos.add(ur);
//
//			}
//
//		}
//
//		if (ufos.size()>0) {
//
//				CostTime ct = new CostTime();
//				RuleTimeCost ruleCost = RuleTimeCost.getContextCost(context);
//				TbbUFOExecutor exceutor = new TbbUFOExecutor();
////				exceutor.executeUfoResult(ufos);
//				ISimpleFormulaExecute manager = context.getService(ISimpleFormulaExecute.class);
//				ruleCost.reStart();
//				exceutor.executeUfoResult(ufos,manager);
//				ruleCost.addStartCost("Execute UFO cost.");
//				for(UfoResult ur:ufos){
//					context.setValue(TbbUFOExecutor.getFunctionKey("", ur.getParam(), ur.getDataCell()), ur.getResult());
//				}
//
//				ct.printTotalCost("Execute UFO("+ufos.size()+") ");
//
//		}
//
//	}
//
//	public String getCubeCode() {
//		return cubeCode;
//	}
//
//	public void setCubeCode(String cubeCode) {
//		this.cubeCode = cubeCode;
//	}
//
//	public ConditionalExpression getWhereExpress() {
//		return whereExpress;
//	}
//
//	public void setWhereExpress(ConditionalExpression whereExpress) {
//		this.whereExpress = whereExpress;
//	}
//
//	public List<ConditionalExpression> getBodyExpressionList() {
//		return bodyExpressionList;
//	}
//
//	public int getExpressionType() {
//		return expressionType;
//	}
//
//	public void setExpressionType(int expressionType) {
//		this.expressionType = expressionType;
//	}
//
//	@Override
//	public String toString() {
//		switch (this.expressionType) {
//		case TYPE_UPDATE:
//			return "update " + this.cubeCode + " set "
//					+ this.bodyExpressionList.get(0).toString() + " where "
//					+ this.whereExpress;
//		case TYPE_SELECT:
//			return "select " + this.bodyExpressionList.get(0).toString()
//					+ " from " + this.cubeCode + "   where "
//					+ this.whereExpress;
//
//		}
//		return super.toString();
//
//	}
//
//	@Override
//	public String toValue(Calculator calculator) {
//		switch (this.expressionType) {
//		case TYPE_UPDATE:
//			return "update " + this.cubeCode + " set "
//					+ this.bodyExpressionList.get(0).toValue(calculator)
//					+ " where " + this.whereExpress;
//
//		case TYPE_SELECT:
//			return "select "
//					+ this.bodyExpressionList.get(0).toValue(calculator)
//					+ " from " + this.cubeCode + "   where "
//					+ this.whereExpress;
//
//		}
//		return super.toString();
//	}
//
//	@Override
//	public String toDesc(DescriptionContext descContext) {
//		DescriptionContext context = new DescriptionContext();
//		if(descContext ==null){
//			context = new DescriptionContext();
//		}else{
//			context = descContext;
//		}
//
//		CubeDef cubeDef;
//		String str = "";
//		try {
//			cubeDef = CubeServiceGetter.getCubeDefQueryService()
//					.queryCubeDefByBusiCode(this.getCubeCode().replaceAll("'", ""));
//			if (cubeDef == null) {
//				return "";
//			}
//			context.setCubedef(cubeDef);
//			if(descContext.isSimpleDesc()){
////				str = ""
////						+ this.bodyExpressionList.get(0).toDesc(context);
//				str =  this.whereExpress.toDesc(context) + "="
//						+ this.bodyExpressionList.get(0).toDesc(context);
//			}else{
//				str = "FIND(" + cubeDef.getObjname() + ","
//						+ this.whereExpress.toDesc(context) + ")="
//						+ this.bodyExpressionList.get(0).toDesc(context);
//			}
//
//		} catch (BusinessException e) {
//			NtbLogger.error(e.getMessage());
//		}
//		return str;
//	}

}