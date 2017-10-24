package junittest;

import com.fc.service.CompactDisc;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SYSTEM on 2017/10/24.
 */
public class CdPlayerTest extends BaseManagerTest {

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public void testCompactDistNotNull() {
        Assert.assertNotNull(compactDisc);
    }
}
