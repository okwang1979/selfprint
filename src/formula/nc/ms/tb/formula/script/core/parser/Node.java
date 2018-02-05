package nc.ms.tb.formula.script.core.parser;

import java.io.Serializable;
import java.util.Map;

import nc.ms.tb.formula.script.Calculator;

/**
 * �ڵ����ӿ�
 *
 */
public interface Node extends Serializable {

	public abstract Object eval(Calculator calculator) throws UtilEvalError;

	public abstract void collect(Map map);
}
