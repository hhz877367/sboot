import com.baizhi.Application;
import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestDaoModel {
    @Autowired
    private StudentDao studentDao;

    //测试selectById
    @Test
    public void tesetSelectById(){
        Student student = studentDao.selectById(13);
        System.out.println(student.toString());
    }




}
