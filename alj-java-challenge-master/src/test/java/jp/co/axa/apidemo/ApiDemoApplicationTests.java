package jp.co.axa.apidemo;



import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 
public class ApiDemoApplicationTests {
	
	 @Autowired
	 private MockMvc mvc;

	 @Autowired
	 private EmployeeRepository repository;
	 
	 @After
    public void resetDb() {
		 repository.deleteAll();
    }
	 
	@Test
	@WithMockUser("USER")
	public void addEmpoyeeTest() throws Exception {
		Employee emp = createEmployee("Santhosh","IT",10000000);		
		mvc.perform(post("/api/v1/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp)));
        List<Employee> found = repository.findAll();
        assertThat(found).extracting(Employee::getName).contains("Santhosh");
	}
	@Test
	@WithMockUser("USER")
	public void addEmpoyeeTestWithInvalidDepartment() throws Exception {
		Employee emp = new Employee();
		emp.setName("Santhosh");
		emp.setSalary(10000000);
		emp.setDepartment("SDS");
		 mvc.perform(post("/api/v1/employees")
			.contentType(MediaType.APPLICATION_JSON)
			.content(JsonUtil.toJson(emp)))
		    .andExpect(status().is(400))
		    .andExpect(jsonPath("$.errors", hasItem("Invalid Department for this Employee.")));		   		
	}
	
	
	@Test
	@WithMockUser("USER")
	public void addEmpoyeeTestWithMoreSalary() throws Exception {
		Employee emp = new Employee();
		emp.setName("Santhosh");
		emp.setSalary(100000000);
		emp.setDepartment("IT");
		mvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(emp)))
		        .andExpect(status().is(400))
	            .andExpect(jsonPath("$.errors", hasItem("Employee Salary Cannot be less than 8000000 and cannot be more than 100000000")));        
	}
	
	
	@Test
	@WithMockUser("USER")
	public void updateEmpoyeeTest() throws Exception {
		Employee emp = createEmployee("BINDU","ADMIN",10000000);
		emp.setDepartment("COE");
		
		mvc.perform(put("/api/v1/employees/"+emp.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(emp)));
        List<Employee> found = repository.findAll();
        assertThat(found).extracting(Employee::getDepartment).contains("COE");
	}
	
	@Test
	@WithMockUser("USER")
	public void deleteEmpoyeeTest() throws Exception {
		Employee emp = createEmployee("Sahasra","SALES",10000000);
		Employee emp1 = createEmployee("Shreya","SALES",9000000);		
		
		mvc.perform(delete("/api/v1/employees/"+emp1.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(emp)));
        List<Employee> found = repository.findAll();
        assertThat(found).extracting(Employee::getName).doesNotContain("Shreya");
	}
	
	 
	@Test
	@WithMockUser("USER")
	public void getEmpoyeeTest() throws Exception {		
		createEmployee("Sahasra","SALES",9000000);
		 mvc.perform(get("/api/v1/employees")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Sahasra")));
	}
	
	
	
	
	private Employee createEmployee(String name,String dept,Integer sal) {
        Employee emp = new Employee();
        emp.setDepartment(dept);
        emp.setName(name);
        emp.setSalary(sal);        
        repository.saveAndFlush(emp);
        return emp;
    }

	

	
}
