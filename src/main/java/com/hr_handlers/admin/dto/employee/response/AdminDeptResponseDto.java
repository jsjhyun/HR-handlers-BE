package com.hr_handlers.admin.dto.employee.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AdminDeptResponseDto {
    private Long id;
    private String deptName;
}
