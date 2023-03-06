package com.example.basic.controller;

import com.example.basic.entity.Student;
import com.example.basic.entity.Role;
import com.example.basic.entity.User;
import com.example.basic.exception.ResourceNotFoundException;
import com.example.basic.repository.studentRepository;
import com.example.basic.service.RoleService;
import com.example.basic.service.UserService;
import com.example.basic.service.studentservice;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@CrossOrigin("*")
@RestController
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private studentRepository studentRepository;
    private final studentservice studentservice;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;



    public String currentUser()
    {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return loggedInUser.getName();
    }

    public StudentController(studentservice studentservice){
        super();
        this.studentservice = studentservice;
    }
    @GetMapping("/students/list")
    public List<Student> liststudents(){
        return studentservice.getAllstudents();
    }

    @PostMapping("/students/create")
    public Student saveStudent(@RequestBody Student student) {
        return studentservice.saveStudent(student);
    }

    /*@PostMapping("/register")
    public User register(@RequestBody User user){
        return employeeservice.register(user);
    }*/


    @PutMapping("/students/edit/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student studentDetails, @PathVariable long id) {
        Student updateStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        updateStudent.setStudentName(studentDetails.getStudentName());
        updateStudent.setSchoolName(studentDetails.getSchoolName());
        updateStudent.setClassName(studentDetails.getClassName());


        Student updatedStudent = studentRepository.save(updateStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/students/remove/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new <String>ResourceNotFoundException("Not Found"));


        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = {"/students","/search"})
    public String home(Student student, Model model, String keyword) {
        if(keyword!=null) {
            List<Student> list = studentservice.getByKeyword(keyword);
            model.addAttribute("listStudent",list);
        }else {
            List<Student> list = studentservice.getAllstudents();
            model.addAttribute("listStudent", list);}
        return studentservice.getByKeyword(keyword).toString();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable long id) {

        return studentservice.getStudentbyId(id);
    }

    @PostMapping("/createRole")
    public Role CreateNewRole(@RequestBody Role role){
        return roleService.CreateNewRole(role);
    }

    @PostMapping("/registerUser")
    public User RegisterNewUser( @RequestBody User user){
        return userService.RegisterNewUser(user);
    }

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRoleAndUser();
    }





    /*@GetMapping("/")
    public String listEmployees(Model model,String keyword){
        model.addAttribute("employees",employeeservice.getAllemployees());
        model.addAttribute("User",currentUser());
        logger.trace("Home Page Accessed");
        return "employees";

    }

    @GetMapping("/employee/new")
    public String CreateEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        logger.trace("Create page Accessed");
        return "Create_employee";
    }
    @PostMapping("/employees")
    public String saveStudent(@ModelAttribute("employee") Employee employee) {
        employeeservice.saveStudent(employee);
        return "redirect:/";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeservice.getEmployeebyId(id));
        logger.trace("update page accessed");
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id,
                                @ModelAttribute("employee") Employee employee) {

        Employee existingEmployee = employeeservice.getEmployeebyId(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeservice.updateEmployee(existingEmployee);
        return "redirect:/";
    }

    @GetMapping("/employees/remove/{id}")
    public String DeleteEmployee(@PathVariable Long id){
        employeeservice.DeleteEmployee(id);
        logger.trace("Successfully deleted");
        return "redirect:/";

    }
    @GetMapping("/search")
    public String home(Employee employee, Model model, String keyword) {
        List<Employee> employees;
        employees = employeeservice.getByKeyword(keyword);
        model.addAttribute("employees", employees);
        model.addAttribute("user",currentUser());
        return "employees";
    }
/*
    @GetMapping("/admin")
    public String admin()
    {
        return "redirect:/";
    }

    @GetMapping("/adminModified")
    public String listNewEmployees(Model model){
        model.addAttribute("employees",employeeservice.getAllemployees());
        logger.trace("Home Page Accessed");
        return "employees";

    }
    @GetMapping("/user")
    public String user(){
        return "redirect:/";
    }
    @GetMapping("/userModified")
    public String listNewUserEmployees(Model model) {
        model.addAttribute("employees", employeeservice.getAllemployees());
        logger.trace("Home Page Accessed");
        return "User_employees";
    }
    @GetMapping("/403")
    public String error(){
        return "403";
    }
*/
}
