package nc.ms.tb.formula.script.core.parser;

import java.util.List;
import java.util.Map;

import nc.ms.tb.formula.script.Calculator;
import nc.ms.tb.formula.script.Primitive;


// 表达式根节点，最终生成节点
//            AbstractNode, InterpreterError, UtilEvalError, ConditionalExpression

public class Expression extends AbstractNode
{

    public Expression()
    {
    }

    public void setConditionalExpression(ConditionalExpression conditionalexpression)
    {
        conditionalExpression = conditionalexpression;
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(conditionalExpression == null)
            throw new InterpreterError("conditionalExpression should not be null");
        Object obj = conditionalExpression.eval(calculator);
        if(obj == null)
            obj = Primitive.NULL;
        return obj;
    }

    public void collect(Map map)
    {
        conditionalExpression.collect(map);
    }

    public String toString()
    {
        return conditionalExpression.toString();
    }
    
    

    
    
    
    @Override
	public List<Node> getNodes(List<Node> nodeList) {
    	nodeList.add(this);
    	conditionalExpression.getNodes(nodeList);
		return nodeList;
	}

	@Override
	public String toDesc(DescriptionContext descContext) {
		return conditionalExpression.toDesc(descContext);
	}
	
	




	@Override
	public String toValue(Calculator calculator) {
		return conditionalExpression.toValue(calculator);
	}






	ConditionalExpression conditionalExpression;
}
