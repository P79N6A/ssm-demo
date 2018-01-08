package junittest;

import com.fc.service.TestSetBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SYSTEM on 2017/11/9.
 */
@Configuration
public class AppConfigBean {

    @Bean
    public TestSetBean testSetBean() {
        return ()-> System.out.println("configuration generate bean ...");
    }
}
