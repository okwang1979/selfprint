package nc.ms.tb.formula.script;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

import nc.ms.tb.formula.script.core.parser.DescriptionContext;

//��������ӿ�
public interface Function
    extends Serializable
{

	/**
	 * �����ɹ�ʽʱ,��¼�Ƿ�������ϸ�Ĳ����б�.
	 * @author wangzhqa
	 * @since 2013-3-21
	 * @return
	 * boolean
	 */
	public boolean isRecountParam();
	
	/**
	 * ����λ�ü�¼��ϸ�Ĳ����б�.
	 * ����:sumif(A1:b2,">100",C1:D2),
	 * ��������stack:s1(A1:B2��ÿһ��value),s2(">100"),s1(C1:D2��ÿһ��value)
	 * @author wangzhqa
	 * @since 2013-3-21
	 * @return
	 * List<Stack>
	 */
	public List<Stack> getParamStack();
	/**
	 * ���غ����Ĳ������ص�Ԫ���ֵ,������Ҫ���ص�Ԫ����.
	 * @return
	 */
	public boolean isCellValue();
	
	
	
    public abstract Calculator getCalculator();

    public abstract void setCalculator(Calculator calculator);

    public abstract Object run(Stack stack);
    
    public String toDesc(Stack stack,DescriptionContext descContext);
	public int cellPlace();
    
    
}
