package nc.ms.tb.formula.script;

import java.util.ArrayList;
import java.util.List;

/**
 * 公式规则执行信息类
 * 信息格式
 * 信息主体:
 * 1.
 * 2.
 * @author wangzhqa
 *
 */
public class FormulaExecuteInfo {
	
	
	
	/**
	 * 设置公式信息的级别.TYPE_ERROR和TYPE_EXCEPTION.
	 */
	private String infoType = "";
	
	public static final String TYPE_ERROR ="error";
	
	public static final String TYPE_EXCEPTION ="exception";
	
	/**
	 * 存执行过程中信息类.
	 */
	private List<String>  messageList = new ArrayList<String>();

	/**
	 * 信息主体,那些公式,那个规则
	 */
	private String formulaMessage ="";
	
	
	public void clearMessage(){
		messageList.clear();
	}
	
	
	
	

	public void setError(){
		this.infoType = this.TYPE_ERROR;
	}
	public void setException(){
		if(this.TYPE_ERROR.equals(this.infoType)){
			return;
		}else{
			this.infoType = this.TYPE_EXCEPTION;
		}
	}
	
	
	
	
}
