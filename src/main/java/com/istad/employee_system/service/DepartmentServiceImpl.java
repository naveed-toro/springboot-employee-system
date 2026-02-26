package com.istad.employee_system.service;

import com.istad.employee_system.dto.DepartmentDto;
import com.istad.employee_system.exception.ResourceNotFoundException;
import com.istad.employee_system.model.Department;
import com.istad.employee_system.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Constructor Injection (بہترین طریقہ)
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        // اگر ڈیپارٹمنٹ نہ ملے تو سیکیورٹی گارڈ بلائیں
        return departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اس آئی ڈی کے ساتھ کوئی ڈیپارٹمنٹ موجود نہیں ہے: " + id));
    }

    @Override
    public Department updateDepartment(Long id, Department departmentDetails) {
        // پہلے چیک کریں کہ ڈیپارٹمنٹ موجود ہے یا نہیں
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! اپ ڈیٹ کے لیے اس آئی ڈی کا ڈیپارٹمنٹ نہیں ملا: " + id));
        
        // نیا ڈیٹا سیٹ کریں
        existingDepartment.setDepartmentName(departmentDetails.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentDetails.getDepartmentDescription());
        
        // سیو کریں
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        // چیک کریں کہ موجود ہے یا نہیں
        departmentRepository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("معذرت! ڈیلیٹ کرنے کے لیے مطلوبہ ڈیپارٹمنٹ نہیں مل سکا: " + id));
            
        departmentRepository.deleteById(id);
    }

    // --- یہ ہمارا نیا میپر (Mapper) ہے جو دیگچی (Entity) سے ڈیٹا نکال کر پلیٹ (DTO) میں ڈالے گا ---
    private DepartmentDto mapToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentDescription(department.getDepartmentDescription());
        return dto;
    }

    // --- 6. نیا فیچر: پیجینیشن اور سرچ کا اصل لاجک (اب DTO کے ساتھ) ---
    @Override
    public Page<DepartmentDto> getDepartmentsByPaginationAndSearch(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        
        // 1. ترتیب (Sorting) سیٹ کریں: سیدھی (ASC) یا الٹی (DESC)
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        // 2. پیج (Pageable) سیٹ کریں (سپرنگ بوٹ میں پیج 0 سے شروع ہوتا ہے، اس لیے ہم نے pageNo - 1 کیا ہے)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Department> departments;
        
        // 3. اگر کوئی کی ورڈ (keyword) دیا گیا ہے تو سرچ کریں، ورنہ سارے لائیں
        if (keyword != null && !keyword.trim().isEmpty()) {
            departments = departmentRepository.searchDepartments(keyword, pageable);
        } else {
            departments = departmentRepository.findAll(pageable);
        }
        
        // 4. یہ لائن ہر ڈیپارٹمنٹ کو باری باری mapToDto والے فنکشن سے گزار کر پلیٹ میں سجا دے گی
        return departments.map(this::mapToDto);
    }
}