package nc.ms.tb.formula.script.core.parser;

 


import java.util.List;
import java.util.Map;

import nc.ms.tb.formula.script.Calculator;



/**
 * 所有节点的抽象类
 *
 */
public abstract class AbstractNode
    implements Node
{

    public AbstractNode()
    {
    }

    /* (non-Javadoc)
     * @see nc.ms.tb.formula.script.core.parser.Node#eval(nc.ms.tb.formula.script.Calculator)
     */
    public Object eval(Calculator calculator)
        throws UtilEvalError
    {
        return null;
    }

    /* (non-Javadoc)
     * @see nc.ms.tb.formula.script.core.parser.Node#collect(java.util.Map)
     */
    public void collect(Map map)
    {
    }
    
    public String toValue(Calculator calculator){
    	return this.toString();
    }
    public List<Node> getNodes(List<Node> nodeList){
    	  nodeList.add(this);
    	  return nodeList;
    }
    
    public String toDesc(DescriptionContext descContext){
    	return this.toString();
    }

}
