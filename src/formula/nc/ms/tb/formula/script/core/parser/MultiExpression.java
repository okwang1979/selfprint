package nc.ms.tb.formula.script.core.parser;

import java.util.*;

import nc.ms.tb.formula.script.Calculator;
import nc.ms.tb.formula.script.Primitive;

/**
 * ³Ë³ý½Úµã
 *
 */
public class MultiExpression extends AbstractNode
{

	  List<String> opList;
	  List<PowerExpression> powerList;
	
    public MultiExpression()
    {
    }

    public void addOp(String s)
    {
        if(opList == null)
            opList = new ArrayList<String>();
        opList.add(s);
    }

    public void addPower(PowerExpression powerexpression)
    {
        if(powerList == null)
            powerList = new ArrayList<PowerExpression>();
        powerList.add(powerexpression);
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(powerList == null || powerList.size() == 0)
            throw new InterpreterError("powerList should not be empty");
        Object obj = null;
        for(int i = 0; i < powerList.size(); i++)
            if(i == 0)
            {
                obj = ((PowerExpression)powerList.get(i)).eval(calculator);
            } else
            {
                Object obj1 = ((PowerExpression)powerList.get(i)).eval(calculator);
                obj = OperationUtils.binaryOperation(obj, obj1, opList.get(i - 1).toString());
            }

        return obj;
    }

    public void collect(Map map)
    {
        if(powerList != null && powerList.size() > 0)
        {
            int i = 0;
            for(int j = powerList.size(); i < j; i++)
                ((PowerExpression)powerList.get(i)).collect(map);

        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; powerList != null && i < powerList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(opList.get(i - 1));
            stringbuffer.append(powerList.get(i).toString());
        }

        return stringbuffer.toString();
    }

	@Override
	public String toValue(Calculator calculator) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; powerList != null && i < powerList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(opList.get(i - 1));
            stringbuffer.append(powerList.get(i).toValue(calculator));
        }

        return stringbuffer.toString();
    }

	@Override
	public String toDesc(DescriptionContext descContext) {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; powerList != null && i < powerList.size(); i++)
        {
            if(i > 0)
                stringbuffer.append(opList.get(i - 1));
            stringbuffer.append(powerList.get(i).toDesc(descContext));
        }

        return stringbuffer.toString();
    }
	
	
	  @Override
		public List<Node> getNodes(List<Node> nodeList) {
	    	nodeList.add(this);
	    	for(PowerExpression expression:this.powerList){
	    		expression.getNodes(nodeList);
	    	}
	    	
			return nodeList;
		}
    

  
}
