package nc.ms.tb.formula.script.core.parser;

import java.util.*;


import nc.ms.tb.formula.script.Calculator;

//比较运算节点
public class RelationExpression extends AbstractNode
{

    public RelationExpression()
    {
    }

    public void addOp(String s)
    {
        if(opList == null)
            opList = new ArrayList();
        opList.add(s);
    }

    public void addAdd(AddExpression addexpression)
    {
        if(addList == null)
            addList = new ArrayList();
        addList.add(addexpression);
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(addList == null || addList.size() == 0)
            throw new InterpreterError("addList should not be empty");
        Object obj = null;
        Object beginValue = null;
        for(int i = 0; i < addList.size(); i++)
            if(i == 0)
            {
            	beginValue = ((AddExpression)addList.get(i)).eval(calculator);
            	if(addList.size()==1){
            		return beginValue;
            	}
            } else
            {
                Object obj1 = ((AddExpression)addList.get(i)).eval(calculator);
                Object temp = OperationUtils.binaryOperation(beginValue, obj1, opList.get(i - 1).toString());
                beginValue = obj1;
                if(obj==null){
                	obj = temp;
                }else{
                	if(obj instanceof Boolean && temp instanceof Boolean){
                		obj  = OperationUtils.binaryOperation(obj,temp,"&&");
                	}else{
                		return false;
                	}
                	
                }
                
                
            }

        return obj;
    }

    public void collect(Map map)
    {
        if(addList != null && addList.size() > 0)
        {
            int i = 0;
            for(int j = addList.size(); i < j; i++)
                ((AddExpression)addList.get(i)).collect(map);

        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; addList != null && i < addList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(opList.get(i - 1));
            stringbuffer.append(addList.get(i).toString());
        }

        return stringbuffer.toString();
    }
    
    

    @Override
	public String toValue(Calculator calculator) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; addList != null && i < addList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(opList.get(i - 1));
            stringbuffer.append(addList.get(i).toValue(calculator));
        }

        return stringbuffer.toString();
    }
    
    



	@Override
	public String toDesc(DescriptionContext descContext) {

        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; addList != null && i < addList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(opList.get(i - 1));
            stringbuffer.append(addList.get(i).toDesc(descContext));
        }

        return stringbuffer.toString();
    
	}

	  @Override
		public List<Node> getNodes(List<Node> nodeList) {
	    	nodeList.add(this);
	    	for(AddExpression addExpression:this.addList){
	    		addExpression.getNodes(nodeList);
	    	}
	    	
			return nodeList;
		}



	List opList;
    List<AddExpression> addList;
}
