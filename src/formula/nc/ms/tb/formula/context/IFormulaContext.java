package nc.ms.tb.formula.context;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.ms.tb.formula.excel.core.IWorkBook;
import nc.ms.tb.formula.script.core.parser.Node;

/**
 * ��ʽ����ʱ�������Ľӿ�
 * 
 */
/**
 * @author wangzhqa <br>
 * @since 2012-2-17 <br>
 * @version 1.0 <br>
 */
public interface IFormulaContext {
	

	/**
	 * �����ȫ�־�̬key������setValue��getValue���õ�ȡֵ�ļ�ֵ
	 * 
	 */

	/**
	 * ����ǰ�꣬��ǰ���ȣ���ǰ�¡�
	 * 
	 */
	public static final String KEY_TASK_YEAR = "YEAR";

	public static final String KEY_TASK_QUARTER = "QUARTER";

	public static final String KEY_TASK_MONTH = "MONTH";
	
	/**
	 * ��ǰ�����ҵ�񷽰�
	 */
	public static final String KEY_TASK_MVTYPE ="TASK_MVTYPE";
	
	
	public static final String  KEY_TASK_ENTITY="TASK_ENTITY";
	
	

	/**
	 * ��ǰ����������֯��Key
	 */
	public static final String KEY_CURRENT_ORG = "@CurrentOrgKey";

	/**
	 * ��ǰ��������ṹ��Key
	 */
	public static final String KEY_CURRENT_ENTITY_HIERARCHY = "@CurrentEntityHierarchyKey";

	/**
	 * ����where����ȷ�ϵ�ǰmember����һЩ������Ӧ�ÿ���ͨ������������context���ҵ���ǰ��DimMember��
	 */
	public static final String KEY_CURRENT_MEMBER = "@CurrentMember";

	/**
	 * ����where����ȷ�ϵ�ǰmember����һЩ������Ӧ�ÿ���ͨ������������context���ҵ���ǰ��DimMember��
	 */
	public static final String KEY_CURRENT_TIME = "@CurrentTime";

	/**
	 * ����where����ȷ�ϵ�ǰHierarchy����һЩ������Ӧ�ÿ���ͨ������������context���ҵ���ǰ��DimMember��
	 */
	public static final String KEY_CURRENT_HIERARCHY = "@CurrentHierarchyKey";

	/**
	 * ����map����������Ҫ��Ŀ����֣�value��Ŀ����ֵ�LevelValue��
	 */
	public static final String KEY_TARTET_CURRENCY = "@TargetCurrency";
	
	
	
	/**
	 * ���÷��ص�ǰ�����Node.
	 * @return
	 */
	public Node getCurrentNode();
	
	public void setCurrentNode(Node node);




	public void setValue(String key, Object value);

	public Object getValue(String key);

	// /**
	// * ��ʽִ���ǣ���select��update ʱ��Ҫ��Ĭ��ֵ��д��ά��ɸѡ����ˣ��������ø���ʶ���Ƿ���Ҫ���Ĭ��ֵ��
	// *
	// * @return
	// */
	// public boolean haveDefaultTuple();
	//
	// /**
	// * @param haveDefaultTuple
	// */
	// public void setDefaultTuple(boolean haveDefaultTuple);

	/**
	 * ��Ϊ{[]}������select ��update��Ҫת����value ��where������Ҫת����celllist�����Ҫ֪����ǰ�ķ���ֵ��ʲô��
	 * 
	 * @return
	 */
	public boolean isTubleToValue();

	public void setTubleToValue(boolean tupleToValue);

	/**
	 * ����ɸѡʱ����Ҫȷ�����ݵ�ȡ����ʽ���μ� ICutCube�����͡�
	 * 
	 * @author wangzhqa
	 * @since 2012-2-13<br>
	 * @param <br>
	 * @param <br>
	 * @return <br>
	 */
	public int getCutCubeType();

	public void setCutCubeType(int type);


	
	
	/**
	 * ��ǰ��������WorkBook
	 * @author wangzhqa
	 * @since 2013-1-5
	 * @return
	 * IWorkBook
	 */
	public  IWorkBook  getWorkBook();
	public void setWorkBook(IWorkBook workBook);
	
	
	
	/**
	 * ��ǰִ��sheet�����б�
	 * 
	 * @return
	 * @author: wangzhqa@yonyou.com
	 */
	public List<String> getExecuteSheetNames();
	
	public void setExecuteSheetNames(List<String> sheetNames);
	
	
	

}
