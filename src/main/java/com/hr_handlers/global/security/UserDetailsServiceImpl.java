package com.hr_handlers.global.security;

import com.hr_handlers.employee.entity.Employee;
import com.hr_handlers.employee.repository.EmpRepository;
import com.hr_handlers.global.exception.ErrorCode;
import com.hr_handlers.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmpRepository empRepository;

    // 사원은 empNo를 id로 로그인
    @Override
    public UserDetails loadUserByUsername(String empNo) throws UsernameNotFoundException {
            Employee employee = empRepository.findByEmpNo(empNo)
                    .orElseThrow(() -> new GlobalException(ErrorCode.EMPLOYEE_NOT_FOUND));

            return new UserDetailsImpl(employee);
    }
}