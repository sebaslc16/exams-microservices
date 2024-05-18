package com.exams.microservices.services;

import com.commons.microservices.services.CommonServiceImpl;
import com.commonsexamns.entity.Asignatura;
import com.commonsexamns.entity.Exam;
import com.exams.microservices.models.repository.AsignaturaRepository;
import com.exams.microservices.models.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService{

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Exam> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }
}
