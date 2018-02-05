package nc.vo.tb.rule.excel;

import java.io.Serializable;

import nc.vo.mdm.pub.NtbLogger;

/**
 * 
 * 存储Excel单元格坐标公式
 * 
 * @author wangzhqa
 * 
 */
public class ExcelCellPlace implements Serializable,Cloneable {
	private static final long serialVersionUID = -1238647084925516476L;

	/**
	 * sheet名称,如果为null或者为""表示取当前表.
	 */
	private String sheetName;

//	/**
//	 * 开始行列
//	 */
//	private String beginRowCol;
//
//	/**
//	 * 结束行列.
//	 */
//	private String endRowCol;
	
	
	private short beginRow = -1;
	private short beginCol = -1;
	private short endRow = -1;
	private short endCol = -1;
	
	
	public  ExcelCellPlace(String placeExpress){
		String[] expressSplit = splitString(placeExpress, ":");
//		String sheetName = null;
		int expressPoint = 0;

		while (expressPoint < expressSplit.length) {
			String str = expressSplit[expressPoint];
			int cellSelect = str.indexOf("!");
			if (cellSelect > -1) {
				String formulaSheetName = str.substring(0, cellSelect);
//				if (sheetName == null){
					sheetName = formulaSheetName;
//					this.sheetName = sheetName;
//				}
				
					
				if (!(sheetName.equalsIgnoreCase(formulaSheetName)))
					NtbLogger.error("sheetName not matches:" + sheetName
							+ "<->" + ((String) formulaSheetName));
			}
			ColumnRow tempColumnRow = ColumnRow
					.convertCellStringToColumnRow(str.substring(cellSelect + 1));
			if (((ColumnRow) tempColumnRow).getColumn() > -1)
				if (((ColumnRow) tempColumnRow).getRow() > -1)
					if (beginCol == -1) {
						beginCol = (short)(tempColumnRow).getColumn();
						endCol = (short)(tempColumnRow).getColumn();
						beginRow = (short)(tempColumnRow).getRow();
						endRow = (short)(tempColumnRow).getRow();
					} else {
						beginCol = (short)Math.min(beginCol,
								(tempColumnRow).getColumn());
						endCol = (short)Math.max(endCol, (tempColumnRow).getColumn());
						beginRow = (short)Math.min(beginRow, (tempColumnRow).getRow());
						endRow = (short)Math.max(endRow, (tempColumnRow).getRow());
					}
			++expressPoint;
		}

		if (sheetName != null) {
			if (sheetName.matches("^'.*'$"))
				sheetName = sheetName.substring(1, sheetName.length() - 1);

		}
	}
	
	private ExcelCellPlace(){
		
	}
	
	
	private static String[] splitString(String paramString1, String paramString2) {
		if ((paramString1 == null) || (paramString1.length() == 0))
			return new String[0];
		return paramString1.split("\\Q" + paramString2 + "\\E");
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
//
//	public String getBeginRowCol() {
//		return beginRowCol;
//	}
//
//	public void setBeginRowCol(String beginRowCol) {
//		this.beginRowCol = beginRowCol;
//	}
//
//	public String getEndRowCol() {
//		return endRowCol;
//	}
//
//	public void setEndRowCol(String endRowCol) {
//		this.endRowCol = endRowCol;
//	}
//	
//	
//
//	public int getBeginRow() {
//		return beginRow;
//	}
//
//	public void setBeginRow(int beginRow) {
//		this.beginRow = beginRow;
//	}
//
//	public int getBeginCol() {
//		return beginCol;
//	}
//
//	public void setBeginCol(int beginCol) {
//		this.beginCol = beginCol;
//	}
//
//	public int getEndRow() {
//		return endRow;
//	}
//
//	public void setEndRow(int endRow) {
//		this.endRow = endRow;
//	}
//
//	public int getEndCol() {
//		return endCol;
//	}
//
//	public void setEndCol(int endCol) {
//		this.endCol = endCol;
//	}

	@Override
	public String toString() {
		String returnStr = "";
		if (this.sheetName != null && this.sheetName.trim().length() > 0) {
			returnStr = "'"+sheetName.trim() + "'!";
		}
		returnStr = returnStr + ColumnRow.convertColumnRowToCellString(new ColumnRow(this.beginRow, this.beginCol));
		if (this.endRow!= this.beginRow || this.endCol != this.beginCol) {
			returnStr = returnStr + ":" + ColumnRow.convertColumnRowToCellString(new ColumnRow(this.endRow, this.endCol));
		}
		return returnStr;
	}
	public void addOrDelRow(int baseRow, int addRowCount,int rowSetoff) {
		baseRow = baseRow +rowSetoff;
//		if(TbParamUtil.isSheetLockEnable()){
//			if (beginRow > baseRow) {
//				beginRow = (short)(beginRow + addRowCount);
//
//			}
//			if (endRow > baseRow) {
//				endRow = (short)(endRow + addRowCount);
//			}
//		}else{
			if (beginRow >= baseRow) {
				beginRow = (short)(beginRow + addRowCount);

			}
			if (endRow >= baseRow) {
				endRow = (short)(endRow + addRowCount);
			}
//		}
	
		
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void addOrDelCol(int baseCol, int addColCount,int colSetoff) {
		baseCol = baseCol +colSetoff;
//		if(TbParamUtil.isSheetLockEnable()){
//			if (beginRow > baseRow) {
//				beginRow = (short)(beginRow + addRowCount);
//
//			}
//			if (endRow > baseRow) {
//				endRow = (short)(endRow + addRowCount);
//			}
//		}else{
			if (beginCol >= baseCol) {
				beginCol = (short)(beginCol + addColCount);

			}
			if (endCol >= baseCol) {
				endCol = (short)(endCol + addColCount);
			}
//		}
	
		
		// TODO Auto-generated method stub
		
	}
	
	
//	public void addRowSelfUerRowSetoffOtherAdd(int baseRow,int addRowCount,int selfSetoff){
//		baseRow = baseRow +selfSetoff;
//		if (beginRow > baseRow) {
//			beginRow = (short)(beginRow + addRowCount);
//
//		}
//		if (endRow > baseRow) {
//			endRow = (short)(endRow + addRowCount);
//		}
//	}
	
	/**
	 * 太平鸟特有方法,由于模型加载方式发生变化对于,其他页签引用使用这个,其他还用原来的
	 */
	public void addOrDelRowOtherSheet(int baseRow, int addRowCount,int rowSetoff){
		baseRow = baseRow +rowSetoff;
		if (beginRow > baseRow) {
			beginRow = (short)(beginRow + addRowCount);

		}
		if (endRow > baseRow) {
			endRow = (short)(endRow + addRowCount);
		}
		
	}
	
	
	
	
	

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = (short)beginRow;
	}

	public int getBeginCol() {
		return beginCol;
	}

	public void setBeginCol(int beginCol) {
		this.beginCol = (short)beginCol;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = (short)endRow;
	}

	public int getEndCol() {
		return endCol;
	}

	public void setEndCol(int endCol) {
		this.endCol = (short)endCol;
	}

	@Override
	protected ExcelCellPlace clone()  {
		ExcelCellPlace place = new  ExcelCellPlace();
		if(getSheetName()!=null){
			place.setSheetName(new String(this.getSheetName()));
		}
	
		place.beginCol = this.beginCol;
		place.endCol = this.endCol;
		place.beginRow = this.beginRow;
		place.endRow = this.endRow;
		return  place;
	}

	public void addOrDelColOtherSheet(int baseCol, int addColCount, int colOffSet) {
		baseCol = baseCol +colOffSet;
		if (beginCol > baseCol) {
			beginCol = (short)(beginCol + addColCount);

		}
		if (endCol > baseCol) {
			endCol = (short)(endCol + addColCount);
		}
		
	}
	
	

}
