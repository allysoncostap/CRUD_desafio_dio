package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImp implements IAvaliacaoFisicaService {
   @Autowired
   private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

   @Autowired
   private AlunoRepository alunoRepository;



    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
            AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
            Aluno aluno = alunoRepository.findById(form.getAlunoId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Aluno nao encontrado") );
            avaliacaoFisica.setAluno(aluno);
            avaliacaoFisica.setPeso(form.getPeso());
            avaliacaoFisica.setAltura(form.getAltura());


            return  avaliacaoFisicaRepository.save(avaliacaoFisica);

        }



    @Override
    public AvaliacaoFisica get(Long id) {


        return avaliacaoFisicaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Avaliacao não encontrado! "));
    }

    @Override
    public List<AvaliacaoFisica> getAll(){
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public void update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        avaliacaoFisicaRepository.findById(id).map(avaliacaoExistente -> {
            avaliacaoExistente.setPeso(formUpdate.getPeso());
            avaliacaoExistente.setAltura(formUpdate.getAltura());
            avaliacaoFisicaRepository.save(avaliacaoExistente);
            return avaliacaoExistente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliaçao nao encontrado"));

    }

    @Override
    public void delete(Long id) {
        avaliacaoFisicaRepository.findById(id).map(avaliacaoFisica -> {
            avaliacaoFisicaRepository.delete(avaliacaoFisica);
            return avaliacaoFisica;

        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação nao encontrado"));
    }


}
