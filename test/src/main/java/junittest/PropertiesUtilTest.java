package junittest;

import java.util.List;

import com.fc.file.PropertiesUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author fangcong on 2018/3/9.
 */
public class PropertiesUtilTest {

    /**
     * 1、name前要带上“/”
     * 2、会在所有resources目录下查找，多个同名文件只返回第一个
     * 3、资源目录下有子目录时要带上子目录路径,例如"/test/test.properties"
     */
    @Test
    public void testConstructor() {
        PropertiesUtil propertiesUtil = new PropertiesUtil(PropertiesUtilTest.class, "/test.properties");
        Assert.assertNotNull(propertiesUtil);
        List<Object> list = propertiesUtil.getKeyList();
        Assert.assertNotNull(list);
        for (Object str : list) {
            System.out.print(str + "---");
        }
        //String userName = (String)propertiesUtil.get("userName");
        //Assert.assertEquals("zhangsan", userName);
    }
}
