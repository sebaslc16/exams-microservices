package com.exams.microservices.services;

import com.commons.microservices.services.CommonService;
import com.commonsexamns.entity.Asignatura;
import com.commonsexamns.entity.Exam;


import java.util.List;

public interface ExamService extends CommonService<Exam> {
    public List<Exam> findByNombre(String term);

    public Iterable<Asignatura> findAllAsignaturas();
}
