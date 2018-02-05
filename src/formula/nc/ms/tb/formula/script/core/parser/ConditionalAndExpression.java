package nc.ms.tb.formula.script.core.parser;

import java.util.*;

import nc.ms.tb.formula.script.Calculator;


/**
 * 与条件表达式  节点。
 *
 */
public class ConditionalAndExpression extends AbstractNode
{
   private	List<RelationExpression> relationList;

    public ConditionalAndExpression()
    {
    }

    public void addRelation(RelationExpression relationexpression)
    {
        if(relationList == null)
            relationList = new ArrayList();
        relationList.add(relationexpression);
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(relationList == null || relationList.size() == 0)
            throw new InterpreterError("relationList should not be empty");
        Object obj = null;
        for(int i = 0; i < relationList.size(); i++)
            if(i == 0)
            {
                obj = ((RelationExpression)relationList.get(i)).eval(calculator);
            } else
            {
                Object obj1 = ((RelationExpression)relationList.get(i)).eval(calculator);
                obj = OperationUtils.binaryOperation(obj, obj1, "&&");
            }

        return obj;
    }

    public void collect(Map map)
    {
        if(relationList != null && relationList.size() > 0)
        {
            int i = 0;
            for(int j = relationList.size(); i < j; i++)
                ((RelationExpression)relationList.get(i)).collect(map);

        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; relationList != null && i < relationList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(" && ");
            stringbuffer.append(relationList.get(i).toString());
        }

        return stringbuffer.toString();
    }

	@Override
	public String toValue(Calculator calculator) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; relationList != null && i < relationList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(" && ");
            stringbuffer.append(relationList.get(i).toValue(calculator));
        }

        return stringbuffer.toString();
    }

	@Override
	public String toDesc(DescriptionContext descContext) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; relationList != null && i < relationList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(" AND ");
            stringbuffer.append(relationList.get(i).toDesc(descContext));
        }

        return stringbuffer.toString();
    }
	
    @Override
	public List<Node> getNodes(List<Node> nodeList) {
    	nodeList.add(this);
    	for(RelationExpression relationExpression:this.relationList){
    		relationExpression.getNodes(nodeList);
    	}
    	
		return nodeList;
	}
	
    

    
}
