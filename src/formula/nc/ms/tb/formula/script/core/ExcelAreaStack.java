package nc.ms.tb.formula.script.core;

import java.util.Stack;


public class ExcelAreaStack <E>  extends Stack<E>{

	
	/**
	 * 
	 */
	
	
	
	
	private static final long serialVersionUID = 880413274686502497L;
	
	private int rowCount =0;
	private int colCount = 0;
	
	
	



	
	
	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}
	
	


	
	

}
