package com.hr_handlers.vacation.entity;

import com.hr_handlers.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vacation")
public class Vacation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_num", nullable = false)
    private String doc_num;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VacationType type;

    @Column(name = "start_date", nullable = false)
    private Timestamp start_date;

    @Column(name = "end_date", nullable = false)
    private Timestamp end_date;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VacationStatus status;

    @Column(name = "approved_at")
    private Timestamp approved_at;

    @Column(name = "approver")
    private String approver;

    @Column(name = "employee_id", nullable = false)
    private Long employee_id;

//    @ManyToOne
//    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
//    private User user;
}