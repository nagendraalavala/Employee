package IntegrationTest;

import com.practice.employee.Entity.employeeEntity;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT,classes = com.practice.employee.EmployeeApplication.class)
public class employeeIntegrationTest

{

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql("/test.sql")
    public void getEmployeeByIdTest()
    {
        String url = "/api/v1/1";
        employeeEntity response = testRestTemplate.getForObject(url,employeeEntity.class);

        //assertEquals((long) 1),response.getId());



    }


    @Test
    public void addEmployeeTest()
    {
        employeeEntity entity = new employeeEntity();

        entity.setId((long)1);
        entity.setEmpLName("ABC");
        entity.setEmpFName("CDE");
        entity.setEmpDept("DEV");
        entity.setEmpAge("11");

        HttpEntity<employeeEntity> request = new HttpEntity<>(entity);

        String url = "/api/v1/add";
        ResponseEntity<employeeEntity> response = testRestTemplate.postForEntity(url,request,employeeEntity.class);

        assertNotNull(response.getBody().getId());
        //assertEquals();
    }

}
