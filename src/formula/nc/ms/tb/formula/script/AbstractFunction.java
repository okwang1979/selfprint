package nc.ms.tb.formula.script;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import nc.ms.tb.formula.context.IFormulaContext;
import nc.ms.tb.formula.script.core.parser.DescriptionContext;

/**
 * 所有函数的抽象类
 * 
 * @author wangzhqa <br>
 * @version 1.0 <br>
 */
public abstract class AbstractFunction implements Function {

	public AbstractFunction() {
		calculator = null;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public void setCalculator(Calculator calculator1) {
		calculator = calculator1;
	}

	/**
	 * 验证context是否可用
	 * 
	 * @author wangzhqa
	 * @since 2012-3-20<br>
	 * @return last editor：wangzhqa;at 2012-3-20.
	 */
	public boolean isContextCanUse() {
		if (this.getCalculator() == null) {
			return false;
		}
		if (this.getCalculator().getContext() == null) {
			return false;
		}
		return true;
	}

	protected IFormulaContext getContext() {
		if (this.isContextCanUse()) {
			return this.getCalculator().getContext();
		} else {
			return null;
		}
	}

	public String toDesc(Stack stack, DescriptionContext descContext) {
		return this.getClass().getSimpleName() + "()";
	}

	

	public boolean isCellValue() {
		return true;
	}

	
	public boolean isRecountParam() {

		return false;
	}

	public List<Stack> getParamStack() {

		return paramStackList;
	}

	protected boolean checkFunction(Stack stack) {
		if (stack.size() == 3) {
			boolean checkPass = Boolean.parseBoolean(String.valueOf(stack.pop()));

			String psssInfo = String.valueOf(stack.pop());
			if (psssInfo != null && psssInfo.length() > 0) {
				psssInfo = psssInfo.substring(0, psssInfo.length() - 1);
			}
			String errInfo = String.valueOf(stack.pop());
			if (errInfo != null && errInfo.length() > 0) {
				errInfo = errInfo.substring(0, errInfo.length() - 1);
			}
		 

			return checkPass;

		} else {
			return false;
		}
	}

	private transient Calculator calculator;
	private List<Stack> paramStackList = new ArrayList<Stack>();

	public int cellPlace() {
		return -1;
	}

	/**
	 * 判断是否需要执行表达式
	 * 
	 * @param filterValue
	 * @return
	 * @author: wangzhqa@yonyou.com
	 */
	protected String execExpress(String filterValue) {

		try {
			return String.valueOf(this.calculator.eval(filterValue));
		} catch (Exception e) {
			 
			return "";
		}
	}

	protected boolean isNeedExec(String filterValue) {
		String key = "needExpress" + filterValue;
		if(this.getContext()==null){
			return false;
		}
		Object obj = this.getContext().getValue(key);
		if (obj == null) {
			if (filterValue.toUpperCase().contains("TASKCHILDRENLEVEL(")) {
				this.getContext().setValue(key, true);
				return true;
			}  else {
				this.getContext().setValue(key, false);
				return false;
			}
		} else {
			return (Boolean) obj;
		}

	}
}
