package nc.ms.tb.formula.script.core;

import java.util.ArrayList;
import java.util.List;

import nc.vo.mdm.pub.NtbLogger;

public class ExcelAreaUtil {




	public ExcelAreaStack  stack ;

	private List<Object> list = new ArrayList<Object>();

	public ExcelAreaUtil(ExcelAreaStack stack){

		this.stack = stack;

		if(stack.size()!=stack.getRowCount()*stack.getColCount()){
			throw new RuntimeException("ExcelAreaStack 行列与数据不符!");
		}

		for(Object obj:stack){
			list.add(obj);
		}

	}


	public List<Object> getColumnData(int column){
		int returnColumn = column;
		if(column>this.stack.getColCount()){
			NtbLogger.error(new RuntimeException("传入的列超出最大值!"));
			returnColumn = this.stack.getColCount();
		}
		int beginIndex = (column-1)*stack.getRowCount();
		List<Object> rtn = new ArrayList<Object>();
		for(int i=0;i<this.stack.getRowCount();i++){
			rtn.add(list.get(i+beginIndex));
		}
		return rtn;
	}

}