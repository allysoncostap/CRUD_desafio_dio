package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoServiceImp implements IAlunoService {

    @Autowired
    private AlunoRepository repository;


    @Override
    public Aluno create(AlunoForm form) {
        Aluno alunoExistente = repository.findByCpf(form.getCpf());
        if (alunoExistente != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());


        return repository.save(aluno);
    }

    @Override
    public Aluno get(Long id) {
        return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Aluno não encontrado! "));
    }

    @Override
    public List<Aluno> getAll() {
        return repository.findAll();
    }




    @Override
    public void update( Long id, AlunoUpdateForm alunoUpdateForm) {
        repository.findById(id).map(alunoExistente -> {
            alunoExistente.setNome(alunoUpdateForm.getNome());
            alunoExistente.setCpf(alunoUpdateForm.getCpf());
            alunoExistente.setBairro((alunoUpdateForm.getBairro()));
            alunoExistente.setDataDeNascimento(alunoUpdateForm.getDataDeNascimento());
            repository.save(alunoExistente);
            return alunoExistente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado"));

    }

        @Override
        public void delete (@PathVariable Long id){
            repository.findById(id).map(aluno -> {
                repository.delete(aluno);
                return aluno;

            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno nao encontrado"));

        }

        @Override
        public List<AvaliacaoFisica> getAllAvaliacaoFisicaId (Long id){
            Aluno aluno = repository.findById(id).get();
            return aluno.getAvaliacoes();
        }


    }


