package ControllerTest;

import com.practice.employee.Controller.employeeController;
import com.practice.employee.Entity.employeeEntity;
import com.practice.employee.Service.employeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class employeeControllerTest
{
    @InjectMocks
    private employeeController controller;

    @Mock
    private employeeServiceImpl service;

    private MockMvc mvc;


    @Before
    public void setup(){

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void postEmployeeTest() throws Exception
    {
        //given
        employeeEntity mockentity = new employeeEntity((long) 1,"ABC","CDE","dev","00");

        //when
        when(service.addEmployee(mockentity)).thenReturn(mockentity);

        String url = "/api/v1/add";

        RequestBuilder request = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andReturn();

        assertEquals(mockentity,service.addEmployee(mockentity));




    }


    @Test
    public void allEmployeesTest() throws Exception
    {
        //given
        List<employeeEntity> employees = new ArrayList<>();
        employeeEntity mockentity1 = new employeeEntity((long) 1,"ABC","CDE","dev","00");
        employeeEntity mockentity2 = new employeeEntity((long) 2,"abc","cde","test","11");

        employees.add(mockentity1);
        employees.add(mockentity2);


        //when
        when(service.getEmployees()).thenReturn(employees);

        String url = "/api/v1/";

        //then
        RequestBuilder request = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isOk()).andReturn();

        assertEquals(employees,service.getEmployees());
        assertEquals(2,employees.size());

    }

    @Test
    public void getEmployeeByIdTest() throws Exception
    {
        //given
        Optional<employeeEntity> mockentity1 = Optional.of(new employeeEntity((long) 1, "ABC", "CDE", "dev", "00"));
        Optional<employeeEntity> mockentity2 = Optional.of(new employeeEntity((long) 2, "abc", "cde", "test", "11"));

        //when
        when(service.getEmployeeById((long) 1)).thenReturn(mockentity1);
        when(service.getEmployeeById((long) 2)).thenReturn(mockentity2);

        //then
        String url1 = "/api/v1/1";
        String url2 = "/api/v1/2";

        RequestBuilder request1 = MockMvcRequestBuilders.get(url1).accept(MediaType.APPLICATION_JSON);
        RequestBuilder request2 = MockMvcRequestBuilders.get(url2).accept(MediaType.APPLICATION_JSON);

        mvc.perform(request1).andExpect(status().isOk()).andReturn();
        mvc.perform(request2).andExpect(status().isOk()).andReturn();

        assertEquals(mockentity1,service.getEmployeeById((long)1));
        assertEquals(mockentity2,service.getEmployeeById((long)2));
    }

    @Test
    public void updateEntityTest() throws Exception
    {
        //given
        //employeeEntity mockentity = new employeeEntity((long) 1,"ABC","CDE","dev","00");

        String jsonString = "{\n" +
                "\"id\":2,\n" +
                "\"empLName\":\"abc\",\n" +
                "\"empFName\":\"cde\"\n" +
                 "\"empDept\":\"test\"\n"+
                 "\"empAge\":\"11\"\n"+
                "}";
        employeeEntity newMockentity = new employeeEntity((long) 2,"abc","cde","test","11");

        //when
        when(service.updateEmployee((long) 1,newMockentity)).thenReturn(newMockentity);

        //then
        String url = "/api/v1/update/1";

        RequestBuilder request = MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON);
        mvc.perform(request)

                .andExpect(MockMvcResultMatchers.jsonPath("$.empLName").value("abc"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empFName").value("cde"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empDept").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.empAge").value("11"))
                .andReturn();

        assertEquals(newMockentity,service.updateEmployee((long) 1,newMockentity));



    }


    @Test
    public void deleteEmployeeTest() throws Exception
    {
        //give
        employeeEntity mockentity = new employeeEntity((long) 1,"ABC","CDE","dev","00");

        //when
        String expected = "Employee Deleted";
        when(service.deleteEmployee((long) 1)).thenReturn(expected);

        String url = "/api/v1/delete/1";
        RequestBuilder response = MockMvcRequestBuilders.delete(url).accept(MediaType.APPLICATION_JSON);

        mvc.perform(response);

        //then
        assertEquals(expected, service.deleteEmployee((long) 1));



    }






}
