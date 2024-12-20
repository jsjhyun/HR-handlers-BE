package com.hr_handlers.vacation.repository;

import com.hr_handlers.vacation.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long>, VacationCustomRepository {
}
