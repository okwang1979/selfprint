package nc.ms.tb.formula.script;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʽ����ִ����Ϣ��
 * ��Ϣ��ʽ
 * ��Ϣ����:
 * 1.
 * 2.
 * @author wangzhqa
 *
 */
public class FormulaExecuteInfo {
	
	
	
	/**
	 * ���ù�ʽ��Ϣ�ļ���.TYPE_ERROR��TYPE_EXCEPTION.
	 */
	private String infoType = "";
	
	public static final String TYPE_ERROR ="error";
	
	public static final String TYPE_EXCEPTION ="exception";
	
	/**
	 * ��ִ�й�������Ϣ��.
	 */
	private List<String>  messageList = new ArrayList<String>();

	/**
	 * ��Ϣ����,��Щ��ʽ,�Ǹ�����
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
