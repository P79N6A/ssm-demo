package junittest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.fc.bean.FileDO;
import com.fc.common.SqlUtils;
import com.fc.service.FileUploadManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author fangcong on 2018/3/1.
 */
public class FileManagerTest extends BaseManagerTest {

    @Resource
    private FileUploadManager fileUploadManager;

    @Test
    public void testForEachQuery() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("ddd");
        list.add("ccc");
        List<Set<String>> param = SqlUtils.split(list);
        List<FileDO> result = fileUploadManager.testForEach(param);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("doc", result.get(0).getType());
    }
}
