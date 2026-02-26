package com.istad.employee_system.controller;

import com.istad.employee_system.dto.EmployeeDto;
import com.istad.employee_system.payload.ApiResponse;
import com.istad.employee_system.model.Employee;
import com.istad.employee_system.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*") // تاکہ React/Next.js وغیرہ اس سے بات کر سکیں
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 1. Create Employee API (ایرر 1 یہاں فکس ہو گیا)
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    // 2. Get All Employees API
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // 3. Get Employee By ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    // 4. Update Employee (ایرر 2 یہاں فکس ہو گیا)
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    // 5. Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

    // --- نیا فیچر: پیجینیشن، سارٹنگ اور سرچ (اب DTO اور ApiResponse کی پلیٹ کے ساتھ) ---
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<EmployeeDto>>> getEmployeesWithPaginationAndSearch(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false) String keyword) {

        // 1. سروس سے اب Employee کی بجائے EmployeeDto (پلیٹ) ملے گی
        Page<EmployeeDto> pageData = employeeService.getEmployeesByPaginationAndSearch(pageNo, pageSize, sortField, sortDirection, keyword);
        
        // 2. ڈیٹا کو ApiResponse (بل/رسید) کے ڈبے میں پیک کریں
        ApiResponse<Page<EmployeeDto>> response = new ApiResponse<>(
                "200 OK",
                "Get Employees Successfully",
                pageData
        );
        
        return ResponseEntity.ok(response);
    }
}