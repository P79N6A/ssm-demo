package mocktest;

import java.util.ArrayList;
import java.util.List;

import com.fc.bean.User;
import com.fc.controller.TestController;
import com.fc.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author SYSTEM on 2017/11/16.
 */
public class TestControllerByMock {

    @InjectMocks
    private TestController testController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void testQueryUser() throws Exception {

        List<User> userList = new ArrayList<>();
        Mockito.when(userService.queryUser("wangwu")).thenReturn(userList);
        Assert.assertNotNull(userList);

        this.mockMvc.perform(get("/test/query.json").param("name", "wangwu"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andDo(print())
            .andReturn();
    }

}
