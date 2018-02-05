package nc.ms.tb.formula.script.core.parser;

import java.util.*;

import nc.ms.tb.formula.script.Calculator;

/**
 * 
 * 加减表达式节点
 */

public class AddExpression extends AbstractNode {
	
	/**
	 *操作符列表。	
	 */
	List<String> opList;
	//乘除表达式列表
	List<MultiExpression> multiList;

	public AddExpression() {
	}

	/**
	 * 添加操作符
	 * 
	 * @param s
	 *            ：操作符
	 */
	public void addOp(String s) {
		if (opList == null)
			opList = new ArrayList<String>();
		opList.add(s);
	}

	/**
	 * @param multiexpression
	 *            ：乘除表达式节点。
	 */
	public void addMulti(MultiExpression multiexpression) {
		if (multiList == null)
			multiList = new ArrayList<MultiExpression>();
		multiList.add(multiexpression);
	}

	public Object eval(Calculator calculator) throws UtilEvalError {
		if (multiList == null || multiList.size() == 0)
			throw new InterpreterError("multiList should not be empty");
		Object obj = null;
		for (int i = 0; i < multiList.size(); i++)
			if (i == 0) {
				obj = ((MultiExpression) multiList.get(i)).eval(calculator);
			} else {
				Object obj1 = ((MultiExpression) multiList.get(i))
						.eval(calculator);
				obj = OperationUtils.binaryOperation(obj, obj1,
						opList.get(i - 1).toString());
			}

		return obj;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; multiList != null && i < multiList.size(); i++) {
			if (i > 0)
				stringbuffer.append(opList.get(i - 1));
			stringbuffer.append(multiList.get(i).toString());
		}

		return stringbuffer.toString();
	}
	
	

	@Override
	public String toValue(Calculator calculator) {
		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; multiList != null && i < multiList.size(); i++) {
			if (i > 0)
				stringbuffer.append(opList.get(i - 1));
			stringbuffer.append(multiList.get(i).toValue(calculator));
		}

		return stringbuffer.toString();
	}

	public void collect(Map map) {
		if (multiList != null && multiList.size() > 0) {
			int i = 0;
			for (int j = multiList.size(); i < j; i++)
				((MultiExpression) multiList.get(i)).collect(map);

		}
	}

	@Override
	public String toDesc(DescriptionContext descContext) {

		StringBuffer stringbuffer = new StringBuffer();
		for (int i = 0; multiList != null && i < multiList.size(); i++) {
			if (i > 0)
				stringbuffer.append(opList.get(i - 1));
			stringbuffer.append(multiList.get(i).toDesc(descContext));
		}

		return stringbuffer.toString();
	
	
	}
	
	  @Override
		public List<Node> getNodes(List<Node> nodeList) {
	    	nodeList.add(this);
	    	for(MultiExpression expression:this.multiList){
	    		expression.getNodes(nodeList);
	    	}
	    	
			return nodeList;
		}


}
