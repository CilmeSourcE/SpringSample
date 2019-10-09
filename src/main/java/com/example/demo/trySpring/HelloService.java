package com.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private HelloRepository helloRepository;

    public Employee findOne(int id) {

        Map<String, Object> employee = helloRepository.findOne(id);

        int employeeId = (Integer) employee.get("employee_Id");
        String employeeName = (String) employee.get("employee_name");
        int age = (Integer) employee.get("age");

        Employee emp = new Employee();

        emp.setEmployeeId(employeeId);
        emp.setEmployeeName(employeeName);
        emp.setAge(age);

        return emp;
    }
}
