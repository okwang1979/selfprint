package nc.ms.tb.formula.script.core.parser;

import java.util.List;
import java.util.Map;

import nc.ms.tb.formula.script.Calculator;

/**
 * 最终表达式节点。
 * 
 */
public class Atom extends AbstractNode {
	Object object;

	public Atom() {
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object obj) {
		object = obj;
	}

	public Object eval(Calculator calculator) throws UtilEvalError {
		if (object == null)
			throw new InterpreterError("object should not be null");
		if (object instanceof Node)
			return ((Node) object).eval(calculator);
		else
			return object;
	}

	public void collect(Map map) {
		if (object != null && (object instanceof Node))
			((Node) object).collect(map);
	}

	public String toString() {
		if (object instanceof ConditionalExpression)
			return " ( " + object + " ) ";
		else
			return object.toString();
	}

	@Override
	public String toValue(Calculator calculator) {
		if (object instanceof ConditionalExpression)
			return " ( " + ((ConditionalExpression) object).toValue(calculator)
					+ " ) ";
		else if (object instanceof AbstractNode)
			return ((AbstractNode) object).toValue(calculator);
		else {
			return object.toString();
		}
	}

	@Override
	public String toDesc(DescriptionContext descContext) {
		if (object instanceof ConditionalExpression)
			return " ( " + ((ConditionalExpression) object).toDesc(descContext)
					+ " ) ";
		else if (object instanceof AbstractNode)
			return ((AbstractNode) object).toDesc(descContext);
		else {
			return object.toString();
		}
	}

	@Override
	public List<Node> getNodes(List<Node> nodeList) {
		nodeList.add(this);
		if (object instanceof AbstractNode) {
			((AbstractNode) object).getNodes(nodeList);
		}

		return nodeList;
	}

}
