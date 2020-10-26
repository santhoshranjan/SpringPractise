package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;


/**
 * This is Service Component which communciates to DATA Layer
 * @CacheConfig Text is for defining Caffenie Cache
 * */
@Service
@CacheConfig(cacheNames = {"employee"})
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired   
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    /**
     * This method is for retrievig Employees
     * @Cahcebale Text is for specifying this method as Caching
     * */
    @Cacheable
    public List<Employee> retrieveEmployees() {    	
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
    
    /**
     * This method is for Retriving Employee by passing EmployeeID
     * @Cahcebale Text is for specifying this method as Caching
     * 
     * ***/
    @Cacheable
    public Employee getEmployee(Long employeeId) {
    	Employee optEmp = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId,"fetch"));
        return optEmp;
    }

    public Employee saveEmployee(Employee employee){
    	 Employee emp = employeeRepository.save(employee);        
    	 return emp;
    }

    public void deleteEmployee(Long employeeId){    	
    	employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeId,"delete"));         
        employeeRepository.deleteById(employeeId);
    }

    public Employee updateEmployee(Employee employee , Long employeeId) {  	
    	 
    	 return employeeRepository.findById(employeeId)    			
               .map(dbEmployee -> {            	   
            	   dbEmployee.setName(employee.getName());
            	   dbEmployee.setSalary(employee.getSalary());
            	   dbEmployee.setDepartment(employee.getDepartment());
            	     return employeeRepository.save(dbEmployee);            	   
               })
               .orElseThrow(() -> new EmployeeNotFoundException(employeeId,"update"));
    			
        
    }
}