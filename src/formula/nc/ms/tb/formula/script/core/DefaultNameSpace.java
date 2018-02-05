package nc.ms.tb.formula.script.core;

import nc.ms.tb.formula.script.AbstractNameSpace;
import nc.ms.tb.formula.script.NameSpace;


//���ֿռ䣬��ȡ�������������ú���

public class DefaultNameSpace extends AbstractNameSpace
{

    private DefaultNameSpace()
    {
    }

    public void setParent(NameSpace namespace)
    {
        throw new UnsupportedOperationException("It's forbidden to modify parent!");
    }

    public static NameSpace getInstance()
    {
        return ns;
    }

    private static NameSpace ns = new DefaultNameSpace();

}
