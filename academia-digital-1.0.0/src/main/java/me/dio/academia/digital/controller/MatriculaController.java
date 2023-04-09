package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.impl.MatriculaServicelmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServicelmp matriculaServicelmp;

    @PostMapping
    public Matricula create(@RequestBody MatriculaForm form){
        return  matriculaServicelmp.create(form);

    }

    @GetMapping
    public List<Matricula> allMatriculas() {
        return matriculaServicelmp.getAll();
    }


        @GetMapping("/{id}")
    public Matricula getAvaliacaoFisicaById(@PathVariable Long id){
        return  matriculaServicelmp.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        matriculaServicelmp.delete(id);
    }



}
