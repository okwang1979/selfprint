package nc.ms.tb.formula.script.core;

/** 
 * 
 * 当公式运行是缺少上下文信息
 * <b>Application name:</b>NC63<br>
 * <b>Application describing:</b> <br>
 * <b>Copyright:</b>Copyright &copy; 2015 用友软件股份有限公司版权所有。<br>
 * <b>Company:</b>yonyou<br>
 * <b>Date:</b>2015-6-30<br>
 * @author：wangzhqa@yonyou.com
 * @version V6.35
 */ 
public class FormulaContextException extends RuntimeException{
	
	public FormulaContextException(String message){
		super(message);
	}

}
