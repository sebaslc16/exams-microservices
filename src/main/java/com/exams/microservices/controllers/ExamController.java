package com.exams.microservices.controllers;

import com.commons.microservices.controllers.CommonController;
import com.commonsexamns.entity.Exam;
import com.commonsexamns.entity.Question;
import com.exams.microservices.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editExam(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Exam> examOptional = service.findById(id);

        if(examOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Exam examDB = examOptional.get();
        examDB.setNombre(exam.getNombre());

        // Lista de preguntas que no estan en el nuevo json para
        // actualizar, estas se eliminaran del examen
        List<Question> eliminadas = new ArrayList<>();

        // Primera forma de eliminar las preguntas que no estan
        // en el nuevo json para la actualizacion del examen
        /*
        examDB.getPreguntas().forEach(question -> {
            if(!exam.getPreguntas().contains(question)) {
                eliminadas.add(question);
            }
        });

        eliminadas.forEach(question -> {
            examDB.removeQuestion(question);
        });
         */

        // Otra forma mas simple de eliminar las preguntas
        examDB.getPreguntas()
                .stream().filter( question -> !exam.getPreguntas().contains(question))
                .forEach(examDB::removeQuestion);

        // Se actualizan las nuevas preguntas al examen
        examDB.setPreguntas(exam.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDB));
    }

    @GetMapping("/filter/{term}")
    public ResponseEntity<?> filterByName(@PathVariable String term){
        return ResponseEntity.ok(service.findByNombre(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listAsignaturas() {
        return ResponseEntity.ok(service.findAllAsignaturas());
    }

}
