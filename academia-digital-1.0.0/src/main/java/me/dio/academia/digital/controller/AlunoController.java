package me.dio.academia.digital.controller;

import com.sun.xml.bind.v2.TODO;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImp service;


    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping
    public Aluno create(@RequestBody AlunoForm form){
        return service.create(form);

        // criar  exception ao  criar aluno com cpf existente
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id , @RequestBody AlunoUpdateForm alunoUpdateForm) {
        service.update( id, alunoUpdateForm);
    }


    @GetMapping("/avalicacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(@PathVariable Long id){
        return service.getAllAvaliacaoFisicaId(id);
    }

    @GetMapping
    public List<Aluno> getall(){

        return service.getAll();
    }



}
