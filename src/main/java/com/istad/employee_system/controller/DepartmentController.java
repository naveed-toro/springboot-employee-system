package com.istad.employee_system.controller;

import com.istad.employee_system.dto.DepartmentDto;
import com.istad.employee_system.model.Department;
import com.istad.employee_system.payload.ApiResponse;
import com.istad.employee_system.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*") // تاکہ React/Next.js وغیرہ اس سے بات کر سکیں
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // 1. نیا ڈیپارٹمنٹ بنانا (POST)
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.createDepartment(department), HttpStatus.CREATED);
    }

    // 2. تمام ڈیپارٹمنٹس لانا (GET)
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // 3. کسی ایک کو ڈھونڈنا (GET by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    // 4. اپ ڈیٹ کرنا (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDetails));
    }

    // 5. ڈیلیٹ کرنا (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("ڈیپارٹمنٹ کامیابی سے ڈیلیٹ کر دیا گیا ہے!");
    }

    // --- 6. نیا فیچر: پیجینیشن، سارٹنگ اور سرچ (اب DTO اور ApiResponse کی پلیٹ کے ساتھ) ---
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<DepartmentDto>>> getDepartmentsWithPaginationAndSearch(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false) String keyword) {

        // سروس سے پیجینیشن والا ڈیٹا لائیں (پلیٹ میں سجا ہوا DTO)
        Page<DepartmentDto> pageData = departmentService.getDepartmentsByPaginationAndSearch(pageNo, pageSize, sortField, sortDirection, keyword);
        
        // ڈیٹا کو ApiResponse (بل/رسید) کے ڈبے میں پیک کریں
        ApiResponse<Page<DepartmentDto>> response = new ApiResponse<>(
                "200 OK",
                "ڈیپارٹمنٹس کا ڈیٹا کامیابی سے حاصل کر لیا گیا ہے",
                pageData
        );
        
        return ResponseEntity.ok(response);
    }
}