package nc.ms.tb.formula.script.core.parser;

import java.util.*;


import nc.ms.tb.formula.script.Calculator;


/**
 * ÃÝÔËËã½Úµã
 *
 */
public class PowerExpression extends AbstractNode
{

    public PowerExpression()
    {
    }

    public void addUnary(UnaryExpression unaryexpression)
    {
        if(unaryList == null)
            unaryList = new ArrayList();
        unaryList.add(unaryexpression);
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(unaryList == null || unaryList.size() == 0)
            throw new InterpreterError("unaryList should not be empty");
        Object obj = null;
        for(int i = 0; i < unaryList.size(); i++)
            if(i == 0)
            {
                obj = ((UnaryExpression)unaryList.get(i)).eval(calculator);
            } else
            {
                Object obj1 = ((UnaryExpression)unaryList.get(i)).eval(calculator);
                obj = OperationUtils.binaryOperation(obj, obj1, "^");
            }

        return obj;
    }

    public void collect(Map map)
    {
        if(unaryList != null && unaryList.size() > 0)
        {
            int i = 0;
            for(int j = unaryList.size(); i < j; i++)
                ((UnaryExpression)unaryList.get(i)).collect(map);

        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; unaryList != null && i < unaryList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append("^");
            stringbuffer.append(unaryList.get(i).toString());
        }

        return stringbuffer.toString();
    }
    

    @Override
	public String toValue(Calculator calculator) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; unaryList != null && i < unaryList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append("^");
            stringbuffer.append(unaryList.get(i).toValue(calculator));
        }

        return stringbuffer.toString();
    }

    

	@Override
	public String toDesc(DescriptionContext descContext) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; unaryList != null && i < unaryList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append("^");
            stringbuffer.append(unaryList.get(i).toDesc(descContext));
        }

        return stringbuffer.toString();
    }

	 @Override
		public List<Node> getNodes(List<Node> nodeList) {
	    	nodeList.add(this);
	    	for(UnaryExpression expression:this.unaryList){
	    		expression.getNodes(nodeList);
	    	}
	    	
			return nodeList;
		}

	List<UnaryExpression> unaryList;
}
