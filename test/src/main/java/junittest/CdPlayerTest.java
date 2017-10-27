package junittest;

import com.fc.service.CompactDisc;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SYSTEM on 2017/10/24.
 */
public class CdPlayerTest extends BaseManagerTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public void testCompactDistNotNull() {
        Assert.assertNotNull(compactDisc);
    }

    @Test
    public void testPlay() {
        compactDisc.play();
        String expectStr = "playing sgt paper's lonely heart club band by The beatles";
        Assert.assertTrue(systemOutRule.getLog().indexOf(expectStr) > -1);
    }

}
