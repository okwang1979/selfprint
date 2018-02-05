package nc.ms.tb.formula.script.core.parser;

import java.util.List;
import java.util.Map;


import nc.ms.tb.formula.script.Calculator;
import nc.ms.tb.formula.script.Primitive;
//一元运算节点
public class UnaryExpression extends AbstractNode
{

    public UnaryExpression()
    {
    }

    public Atom getAtom()
    {
        return atom;
    }

    public void setAtom(Atom atom1)
    {
        atom = atom1;
    }

    public String getOp()
    {
        return op;
    }

    public void setOp(String s)
    {
        op = s;
    }

    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        if(atom == null)
            throw new InterpreterError("atom should not be null");
        Object obj = atom.eval(calculator);
        if(op != null)
            obj = primitiveWrapperUnaryOperation(obj, op);
        return obj;
    }

    private Object primitiveWrapperUnaryOperation(Object obj, String s)
        throws UtilEvalError
    {
        Object obj1 = OperationUtils.promoteToInteger(obj);
        if(obj1 instanceof Boolean)
            return new Boolean(OperationUtils.booleanUnaryOperation((Boolean)obj1, s));
        if(obj1 instanceof Integer)
        {
            int i = OperationUtils.intUnaryOperation((Integer)obj1, s);
            return i;
        }
        if(obj1 instanceof Long)
            return OperationUtils.longUnaryOperation((Long)obj1, s);
        if(obj1 instanceof Float)
            return new Float(OperationUtils.floatUnaryOperation((Float)obj1, s));
        if(obj1 instanceof Double)
            return new Double(OperationUtils.doubleUnaryOperation((Double)obj1, s));
        if(obj1 instanceof Number){
        	  return new Double(OperationUtils.doubleUnaryOperation(((Number)obj1).doubleValue(), s));
        }
        if(obj instanceof Primitive){
        	return 	obj; 
        }
        else
            throw new InterpreterError("An error occurred.  Please call technical support.");
    }

    public void collect(Map map)
    {
        if(atom != null)
            atom.collect(map);
    }

    public String toString()
    {
        return (op == null ? "" : op) + atom.toString();
    }
    
    

    @Override
	public String toValue(Calculator calculator) {
    	 return (op == null ? "" : op) + atom.toValue(calculator);
	}
    
    
    
    



	@Override
	public String toDesc(DescriptionContext descContext) {
		 return (op == null ? "" : op) + atom.toDesc(descContext);
	}



	 @Override
		public List<Node> getNodes(List<Node> nodeList) {
	    	nodeList.add(this);
	    	
	    	atom.getNodes(nodeList);
	    	
			return nodeList;
		}



	String op;
    Atom atom;
}
