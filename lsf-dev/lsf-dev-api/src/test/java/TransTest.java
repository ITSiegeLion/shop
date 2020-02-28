import com.lsf.Application;
import com.lsf.service.StuService;
import com.lsf.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransTest {
    @Autowired
    private StuService stuService;
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        System.out.println(userService.queryUsernameIsExist("1lsf"));
    }

}
