package nc.ms.tb.formula.script;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

import nc.ms.tb.formula.script.core.parser.DescriptionContext;

//函数抽象接口
public interface Function
    extends Serializable
{

	/**
	 * 在生成公式时,记录是否生成详细的参数列表.
	 * @author wangzhqa
	 * @since 2013-3-21
	 * @return
	 * boolean
	 */
	public boolean isRecountParam();
	
	/**
	 * 按照位置记录详细的参数列表.
	 * 例如:sumif(A1:b2,">100",C1:D2),
	 * 生成三个stack:s1(A1:B2的每一个value),s2(">100"),s1(C1:D2的每一个value)
	 * @author wangzhqa
	 * @since 2013-3-21
	 * @return
	 * List<Stack>
	 */
	public List<Stack> getParamStack();
	/**
	 * 返回函数的参数返回单元格的值,否则需要返回单元格本身.
	 * @return
	 */
	public boolean isCellValue();
	
	
	
    public abstract Calculator getCalculator();

    public abstract void setCalculator(Calculator calculator);

    public abstract Object run(Stack stack);
    
    public String toDesc(Stack stack,DescriptionContext descContext);
	public int cellPlace();
    
    
}
