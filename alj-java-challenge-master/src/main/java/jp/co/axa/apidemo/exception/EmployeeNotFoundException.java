package jp.co.axa.apidemo.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id, String operation) {
        super("Employee ID  : " + id + " not found in DB for "+ operation + "  Operation" );
    } 

}
