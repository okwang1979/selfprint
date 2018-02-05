package nc.ms.tb.formula.script.core.parser;

import java.util.*;

import nc.ms.tb.formula.script.Calculator;



/**
 * 或表达式节点
 *
 */
public class ConditionalOrExpression extends AbstractNode
{
	 List<ConditionalAndExpression> conditionalAndList;

    public ConditionalOrExpression()
    {
    }

    public void addAnd(ConditionalAndExpression conditionalandexpression)
    {
        if(conditionalAndList == null)
            conditionalAndList = new ArrayList();
        conditionalAndList.add(conditionalandexpression);
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(conditionalAndList == null || conditionalAndList.size() == 0)
            throw new InterpreterError("conditionalAndList should not be empty");
        Object obj = null;
        for(int i = 0; i < conditionalAndList.size(); i++)
            if(i == 0)
            {
                obj = ((ConditionalAndExpression)conditionalAndList.get(i)).eval(calculator);
            } else
            {
                Object obj1 = ((ConditionalAndExpression)conditionalAndList.get(i)).eval(calculator);
                obj = OperationUtils.binaryOperation(obj, obj1, "||");
            }

        return obj;
    }

    public void collect(Map map)
    {
        if(conditionalAndList != null && conditionalAndList.size() > 0)
        {
            int i = 0;
            for(int j = conditionalAndList.size(); i < j; i++)
                ((ConditionalAndExpression)conditionalAndList.get(i)).collect(map);

        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; conditionalAndList != null && i < conditionalAndList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(" || ");
            stringbuffer.append(conditionalAndList.get(i).toString());
        }

        return stringbuffer.toString();
    }

	@Override
	public String toValue(Calculator calculator) {

        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; conditionalAndList != null && i < conditionalAndList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(" || ");
            stringbuffer.append(conditionalAndList.get(i).toValue(calculator));
        }

        return stringbuffer.toString();
    
	}

	@Override
	public String toDesc(DescriptionContext descContext) {
		   StringBuffer stringbuffer = new StringBuffer();
	        for(int i = 0; conditionalAndList != null && i < conditionalAndList.size(); i++)
	        {
	            if(i > 0)
	                stringbuffer.append(" OR ");
	            stringbuffer.append(conditionalAndList.get(i).toDesc(descContext));
	        }

	        return stringbuffer.toString();
	}
	
	
    @Override
	public List<Node> getNodes(List<Node> nodeList) {
    	nodeList.add(this);
    	for(ConditionalAndExpression andExpression:this.conditionalAndList){
    		andExpression.getNodes(nodeList);
    	}
    	
		return nodeList;
	}

    

   
}
