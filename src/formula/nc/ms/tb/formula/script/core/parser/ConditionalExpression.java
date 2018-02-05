package nc.ms.tb.formula.script.core.parser;

import java.util.List;
import java.util.Map;

import nc.ms.tb.formula.script.Calculator;
import nc.ms.tb.formula.script.Primitive;


/**
 * 逻辑表达式根节点
 *
 */
public class ConditionalExpression extends AbstractNode
{

	
    ConditionalOrExpression condition;
    ConditionalOrExpression yExpression;
    ConditionalOrExpression nExpression;
    public ConditionalExpression()
    {
    }

    public void setCondition(ConditionalOrExpression conditionalorexpression)
    {
        condition = conditionalorexpression;
    }

    public void setNExpression(ConditionalOrExpression conditionalorexpression)
    {
        nExpression = conditionalorexpression;
    }

    public void setYExpression(ConditionalOrExpression conditionalorexpression)
    {
        yExpression = conditionalorexpression;
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(condition == null)
            throw new InterpreterError("condition should not be null");
        Object obj = condition.eval(calculator);
        if(yExpression == null || nExpression == null)
            return obj;
        if(obj instanceof Boolean)
        {
            if(((Boolean)obj).booleanValue())
                return yExpression.eval(calculator);
            else
                return nExpression.eval(calculator);
        } else
        {
            return Primitive.ERROR_NAME;
        }
    }

    public void collect(Map map)
    {
        if(condition != null)
            condition.collect(map);
        if(yExpression != null)
            yExpression.collect(map);
        if(nExpression != null)
            nExpression.collect(map);
    }

    public String toString()
    {
        if(yExpression == null || nExpression == null)
            return condition.toString();
        else
            return condition.toString() + "?" + yExpression.toString() + ":" + nExpression.toString();
    }

	@Override
	public String toValue(Calculator calculator) {
	    if(yExpression == null || nExpression == null)
            return condition.toValue(calculator);
	    return "";
	}

	@Override
	public String toDesc(DescriptionContext descContext) {
	    if(yExpression == null || nExpression == null)
            return condition.toDesc(descContext);
	    return "";
	}
	
	
    
    
    @Override
	public List<Node> getNodes(List<Node> nodeList) {
    	nodeList.add(this);
    	condition.getNodes(nodeList);
		return nodeList;
	}


}
