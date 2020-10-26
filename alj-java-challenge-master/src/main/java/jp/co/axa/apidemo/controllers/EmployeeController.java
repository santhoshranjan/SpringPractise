package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.ApiDemoApplication;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        LOGGER.error("Get Employees"+employees);
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {    	
    		return employeeService.getEmployee(employeeId);
    			                   
    }
    /**
     * Changed Parameter for Enabling Spring Validataion
     * @Valid Text for Spring Bean Validation.
     * */
    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){    	
         return employeeService.saveEmployee(employee);           
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
      
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){        
            employeeService.updateEmployee(employee,employeeId);
       

    }

}
