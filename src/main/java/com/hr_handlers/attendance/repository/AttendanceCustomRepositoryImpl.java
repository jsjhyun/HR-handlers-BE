package com.hr_handlers.attendance.repository;

import com.hr_handlers.attendance.dto.AttendanceHistoryResponseDto;
import com.hr_handlers.attendance.dto.AttendanceHistorySearchDto;
import com.hr_handlers.attendance.dto.EmployeeAttendanceResponseDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.hr_handlers.attendance.entity.QAttendance.attendance;
import static com.hr_handlers.employee.entity.QEmployee.employee;

@Repository
public class AttendanceCustomRepositoryImpl implements AttendanceCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public AttendanceCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public EmployeeAttendanceResponseDto findAttendance(String empNo) {
        return jpaQueryFactory
                .select(Projections.constructor(EmployeeAttendanceResponseDto.class,
                attendance.id,
                attendance.status,
                attendance.checkInTime,
                attendance.checkOutTime,
                employee.id))
                .from(attendance)
                .join(attendance.employee, employee)
                .where(employee.empNo.eq(empNo)
                        .and(attendance.checkInTime.between(
                                DateUtils.getStartOfDay(LocalDateTime.now()),
                                DateUtils.getEndOfDay(LocalDateTime.now()))))
                .fetchOne();
    }

    @Override
    public Page<AttendanceHistoryResponseDto> findAttendanceHistory(
            String empNo,
            AttendanceHistorySearchDto searchDto,
            Pageable pageable
    ) {

        NumberExpression<Integer> totalHours = attendance.checkOutTime.hour().subtract(attendance.checkInTime.hour());
        NumberExpression<Integer> totalMinutes = attendance.checkOutTime.minute().subtract(attendance.checkInTime.minute());

        List<AttendanceHistoryResponseDto> content = jpaQueryFactory
                .select(Projections.constructor(AttendanceHistoryResponseDto.class,
                        attendance.checkInTime.stringValue(),
                        attendance.checkInTime.stringValue(),
                        attendance.checkOutTime.stringValue(),

                        ExpressionUtils.as(
                                totalHours.multiply(60).add(totalMinutes),
                                "workingTime"
                        )
                ))
                .from(attendance)
                .join(attendance.employee, employee)
                .where(
                        employee.empNo.eq(empNo),
                        dateBetween(searchDto.getCheckInTime(), searchDto.getCheckOutTime())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(attendance.count())
                .from(attendance)
                .join(attendance.employee, employee)
                .where(
                        employee.empNo.eq(empNo),
                        dateBetween(searchDto.getCheckInTime(), searchDto.getCheckOutTime())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression dateBetween(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return checkInTime != null && checkOutTime != null ?
                attendance.checkInTime.between(
                        Timestamp.valueOf(checkInTime),
                        Timestamp.valueOf(checkOutTime)
                ) : null;
    }

    private static class DateUtils {
        public static Timestamp getStartOfDay(LocalDateTime dateTime) {
            return Timestamp.valueOf(dateTime.toLocalDate().atStartOfDay());
        }

        public static Timestamp getEndOfDay(LocalDateTime dateTime) {
            return Timestamp.valueOf(dateTime.toLocalDate().atTime(23, 59, 59));
        }
    }
}
