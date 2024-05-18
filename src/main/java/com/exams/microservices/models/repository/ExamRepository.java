package com.exams.microservices.models.repository;

import com.commonsexamns.entity.Exam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExamRepository extends PagingAndSortingRepository<Exam,Long> {

    @Query("select e from Exam e where e.nombre like %?1%")
    public List<Exam> findByNombre(String term);
}
