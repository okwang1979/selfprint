package nc.vo.tb.rule.excel;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import nc.ms.tb.formula.excel.core.IWorkBook;
import nc.ms.tb.formula.excel.core.IWorkSheet;
import nc.ms.tb.formula.excel.parser.ExcelColumnRowLexer;
import nc.ms.tb.formula.excel.parser.ExcelColumnRowLexerTokenTypes;
import nc.vo.mdm.pub.NtbLogger;
import antlr.Token;
import antlr.TokenStreamException;

/**
 * 封装公式类
 * 
 * @author wangzhqa
 * 
 * 
 */
public class ExcelFormula implements Serializable{
	private static final long serialVersionUID = 328944678563973442L;
	
	private static Map<String, ExcelFormula>  formulaMap = new HashMap<String, ExcelFormula>();

	public static String CELL_PLACE_SING = "@CellPlace@";

	/**
	 * 表达式
	 */
	private String express;

	private List<ExcelCellPlace> cellPlaces = new ArrayList<ExcelCellPlace>();

	/**
	 * 该公式是否计算过
	 */
	private boolean finishedCalculator = false;
	public ExcelFormula(){
		
	}
	public static ExcelFormula getInstance(String express){
		if(formulaMap.get(express)!=null){
		  return	  formulaMap.get(express).getClone();
		}else{
			ExcelFormula rtn = new ExcelFormula(express);
			formulaMap.put(express, rtn.getClone());
			return rtn;
		}
	}
	public ExcelFormula(String express) {

		if(express!=null){
			this.setExpress(express.replaceAll("$", ""));
		}else{
			this.express= "";
		}
		
//		this.express = express;
	}

	public String getExpress() {
		String returnExpress = express;
		if (express.startsWith("=")) {
			returnExpress = express.replaceFirst("=", "");
		}
		for (ExcelCellPlace cellPlace : cellPlaces) {
			returnExpress = returnExpress.replaceFirst(CELL_PLACE_SING,
					cellPlace.toString());
		}

		return returnExpress;
	}

	public void setExpress(String express) {
		this.cellPlaces.clear();
		 
		if(express!=null){
			try{
				initExpress( express.replaceAll("[$]", ""));
			}catch(Exception ex){
//				NtbLogger.error(new Exception("excel express err:"+express));
				
				this.express="";
			}
		
		}else{
			this.express="";
		}
		
	}

	public boolean isFinishedCalculator() {
		return finishedCalculator;
	}

	public void setFinishedCalculator(boolean finishedCalculator) {
		this.finishedCalculator = finishedCalculator;
	}

	private void initExpress(String express) {
		try{
			
		
		StringReader stringReader = new StringReader(express);
		ExcelColumnRowLexer localFRLexer = new ExcelColumnRowLexer(stringReader);
		StringBuffer sb = new StringBuffer();
		int i = 1;
		int j = 1;
		Token localToken = null;
		try {
			while ((localToken = localFRLexer.nextToken()).getText() != null) {

				for (int k = i; k < localToken.getLine(); ++k)
					sb.append("\n");
				i = localToken.getLine();
				for (int k = j; k < localToken.getColumn(); ++k)
					sb.append(" ");
				j = localToken.getColumn() + localToken.getText().length();
				if (localToken.getType() == ExcelColumnRowLexerTokenTypes.COLUMNROW) {
					sb.append(CELL_PLACE_SING);
//					this.addCellPlace(localToken.getText());
					this.cellPlaces
							.add(new ExcelCellPlace(localToken.getText()));
				} else {
					sb.append(localToken.getText());
				}

			}
		} catch (TokenStreamException localTokenStreamException) {
			throw new RuntimeException(localTokenStreamException.getMessage(),
					localTokenStreamException);
		}
		this.express = sb.toString();
		}catch(Exception ex){
			throw new RuntimeException(express +":is error");
		}

	}

	public void selfSheetInsertOrDelRow(int baseRow, int addRowCount,int rowSetoff) {
		for(ExcelCellPlace place: cellPlaces){
			if(place.getSheetName()==null||place.getSheetName().trim().length()==0){
				place.addOrDelRow(baseRow,addRowCount,rowSetoff);
			}
		}
		
	}
	
	
	public void selfSheetInsertOrDelCol(int baseCol, int addColCount,int colSetoff) {
		for(ExcelCellPlace place: cellPlaces){
			if(place.getSheetName()==null||place.getSheetName().trim().length()==0){
				place.addOrDelCol(baseCol,addColCount,colSetoff);
			}
		}
		
	}
	
	
	public void otherSheetInsertOrDelRow(String name, int baseRow,
			int addRowCount,int rowSetoff) {
		for(ExcelCellPlace place: cellPlaces){
			String formulaName = "";
			if(place.getSheetName()!=null){
				formulaName = place.getSheetName();
			}
			if(formulaName.startsWith("'")){
				formulaName = formulaName.substring(1,formulaName.length());
			}
			if(formulaName.endsWith("'")){
				formulaName = formulaName.substring(0,formulaName.length()-1);
			}
			if(name.equals(formulaName.trim())){
			 
					place.addOrDelRow(baseRow,addRowCount,rowSetoff);
				
			} 
		}
		
	}
	
	
	public void otherSheetInsertOrDelCol(String name, int baseCol, int addColCount, int colOffSet) {
		for(ExcelCellPlace place: cellPlaces){
			String formulaName = "";
			if(place.getSheetName()!=null){
				formulaName = place.getSheetName();
			}
			if(formulaName.startsWith("'")){
				formulaName = formulaName.substring(1,formulaName.length());
			}
			if(formulaName.endsWith("'")){
				formulaName = formulaName.substring(0,formulaName.length()-1);
			}
			if(name.equals(formulaName)){
				 
					place.addOrDelCol(baseCol,addColCount,colOffSet);
				
			} 
		}
		
	}
	
	public void cellAddRow_SelfUerRowSetoffOtherAdd(int baseRow,
			int addRowCount,int rowSetoff,String sheetName){
		for(ExcelCellPlace place: cellPlaces){
			// lrx V65_20161221_SUMIF函数偏移问题: 引用其它表单的单元格在浮动区增行复制时不偏移
			if(place.getSheetName()==null||place.getSheetName().trim().length()==0||place.getSheetName().trim().equals(sheetName)){
				place.addOrDelRowOtherSheet(baseRow-1,addRowCount,rowSetoff);
			}/*else{
				place.addOrDelRowOtherSheet(0,addRowCount,0);
			}*/
			
		}
		
	}
	
	public void copyAddCol(int addColCount){
		for(ExcelCellPlace place: cellPlaces){
			if(place.getSheetName()==null||place.getSheetName().trim().length()==0){
				place.addOrDelColOtherSheet(0,addColCount,0);
			}else{
				place.addOrDelColOtherSheet(0,addColCount,0);
			}
			
		}
	}
	
	public void cellAddRow(int baseRow,
			int addRowCount,int rowSetoff) {
		for(ExcelCellPlace place: cellPlaces){
			place.addOrDelRowOtherSheet(baseRow,addRowCount,rowSetoff);
		 
					
			 
			
				
			

//				
//				
		}
		
	}
	

//	public void otherSheetInsertOrDelRow(String name, int baseRow,
//			int addRowCount,int rowSetoff) {
//		for(ExcelCellPlace place: cellPlaces){
//			String formulaName = "";
//			if(place.getSheetName()!=null){
//				formulaName = place.getSheetName();
//			}
//			if(formulaName.startsWith("'")){
//				formulaName = formulaName.substring(1,formulaName.length());
//			}
//			if(formulaName.endsWith("'")){
//				formulaName = formulaName.substring(0,formulaName.length()-1);
//			}
//			if(name.equals(formulaName)){
//				place.addOrDelRow(baseRow,addRowCount,rowSetoff);
//			}
//		}
//		
//	}
//	
//	public void cellAddRow(int baseRow,
//			int addRowCount,int rowSetoff) {
//		for(ExcelCellPlace place: cellPlaces){
// 
//			if(place.getSheetName()==null||place.getSheetName().trim().length()==0){
////				place.addOrDelRow(baseRow,addRowCount,rowSetoff);
//			}else{
//				place.addOrDelRow(baseRow,addRowCount,rowSetoff);
//			}
//		}
//		
//	}
//	
	
	
	

	public String getInDataExpress(IWorkBook workBook,String currentSheetName){
		
		try{
			String returnExpress = express;
			if (express.startsWith("=")) {
				returnExpress = express.replaceFirst("=", "");
			}
			for (ExcelCellPlace cellPlace : cellPlaces) {
				ExcelCellPlace inDataPlace = cellPlace.clone();

				IWorkSheet  offsetSheet = null;
				if(inDataPlace.getSheetName()==null||inDataPlace.getSheetName().trim().length()==0){
					  offsetSheet = workBook.getWorkSheet(currentSheetName);
					 
				}else{
					String sheetName = inDataPlace.getSheetName();
					if(sheetName.startsWith("'")){
						sheetName = sheetName.replaceAll("'", "");
					}
					offsetSheet = workBook.getWorkSheet(sheetName);
				}
				if(offsetSheet == null){
				 
					inDataPlace.setBeginCol(inDataPlace.getBeginCol());
					inDataPlace.setEndCol(inDataPlace.getEndCol());
					inDataPlace.setBeginRow(inDataPlace.getBeginRow());
					inDataPlace.setEndRow(inDataPlace.getEndRow());
					returnExpress = returnExpress.replaceFirst(CELL_PLACE_SING,
							inDataPlace.toString());
				}else{
					int rowOffset = offsetSheet.getRowOffSet();
					int colOffset = offsetSheet.getColOffSet();
					inDataPlace.setBeginCol(inDataPlace.getBeginCol()-colOffset);
					inDataPlace.setEndCol(inDataPlace.getEndCol()-colOffset);
					inDataPlace.setBeginRow(inDataPlace.getBeginRow()-rowOffset);
					inDataPlace.setEndRow(inDataPlace.getEndRow()-rowOffset);
					returnExpress = returnExpress.replaceFirst(CELL_PLACE_SING,
							inDataPlace.toString());
				}
				
				
			}

			return returnExpress;
		}catch(Exception ex){
			NtbLogger.error(currentSheetName+"表 ,单元格公式错误!");
			return "";
		}

		
		 
	}

	
	public ExcelFormula getClone()   {
		
		
		
		ExcelFormula cloneObj = new ExcelFormula();
		
		cloneObj.express=this.express;
		cloneObj.cellPlaces=new ArrayList<ExcelCellPlace>();
		for(int i=0;i<cellPlaces.size();i++){
			ExcelCellPlace ecp=cellPlaces.get(i);
			ExcelCellPlace ecpN=ecp.clone();
			cloneObj.cellPlaces.add(ecpN);
		}
		 
		return cloneObj;
	}

	public List<ExcelCellPlace> getCellPlaces() {
		return cellPlaces;
	}

	
	

}
