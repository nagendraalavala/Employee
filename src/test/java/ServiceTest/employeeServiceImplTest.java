package ServiceTest;

import com.practice.employee.Entity.employeeEntity;
import com.practice.employee.Repository.employeeRepo;
import com.practice.employee.Service.employeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class employeeServiceImplTest
{
    @InjectMocks
    private employeeServiceImpl service;

    @Mock
    private employeeRepo repo;

    private MockMvc mvc;


    @Before
    public void setup(){

        mvc = MockMvcBuilders.standaloneSetup(service).build();
    }

    @Test
    public void addEmployeeTest() throws Exception
    {
        //given
        employeeEntity mockentity = new employeeEntity((long) 1,"ABC","CDE","dev","00");

        //when
        when(repo.save(mockentity)).thenReturn(mockentity);

        //then
        assertEquals(mockentity,service.addEmployee(mockentity));
    }


    @Test
    public void getEmployees() throws Exception
    {
        //given
        List<employeeEntity> entityList = new ArrayList<>();

        //when
        when(repo.findAll()).thenReturn(entityList);

        //then
        assertEquals(entityList, service.getEmployees());
    }

    @Test
    public void getEmployeeByIdTest() throws Exception
    {
        //given
        Optional<employeeEntity> mockentity = Optional.of(new employeeEntity((long) 1, "ABC", "CDE", "dev", "00"));

        //when
        when(repo.findById((long) 1)).thenReturn(mockentity);

        //then
        assertEquals(mockentity, service.getEmployeeById((long) 1));

    }

//    @Test
//    public void deleteEmployee() throws Exception
//    {
//        //given
//        employeeEntity mockentity = new employeeEntity((long) 1,"ABC","CDE","dev","00");
//
//
//
//
//    }








}
