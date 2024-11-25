package com.hr_handlers.admin.controller;

import com.hr_handlers.admin.dto.employee.EmpRegisterDto;
import com.hr_handlers.admin.dto.employee.request.AdminEmpUpdateRequestDto;
import com.hr_handlers.admin.service.AdminEmpService;
import com.hr_handlers.global.dto.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/emp")
public class AdminEmpController {

    private final AdminEmpService adminEmpService;

    // 사원 등록
    @PostMapping
    public SuccessResponse<String> registerEmp(@Valid @RequestBody EmpRegisterDto registerDto){
        return adminEmpService.register(registerDto);
    }

    // 사원 조회


    // 사원 전체 조회


    // 사원 수정
    @PatchMapping("/{empNo}")
    public SuccessResponse<Void> updateEmpDetail(@PathVariable("empNo") String empNo,
                                                 @RequestBody AdminEmpUpdateRequestDto updateRequest){
        return adminEmpService.updateEmpDetail(empNo, updateRequest);
    }

    // 사원 삭제
    @DeleteMapping("/{empNo}")
    public SuccessResponse<Void> deleteEmp(@PathVariable("empNo") String empNo){
        return adminEmpService.delete(empNo);
    }
}
