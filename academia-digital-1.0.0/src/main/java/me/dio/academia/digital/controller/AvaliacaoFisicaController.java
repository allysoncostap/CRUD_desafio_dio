package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImp service;


    @PostMapping
    public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm form){
        return  service.create(form);

    }

    @GetMapping("/{id}")
    public AvaliacaoFisica getAvaliacaoFisicaById(@PathVariable Long id){
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id , @RequestBody AvaliacaoFisicaUpdateForm avaliacaoFisicaUpdateForm) {
        service.update( id, avaliacaoFisicaUpdateForm);
    }

    @GetMapping
    public List<AvaliacaoFisica> allMatriculas() {
        return service.getAll();
    }





}
