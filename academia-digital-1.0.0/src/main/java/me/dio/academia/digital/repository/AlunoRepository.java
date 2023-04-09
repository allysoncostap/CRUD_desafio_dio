package me.dio.academia.digital.repository;

import me.dio.academia.digital.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    @Query("SELECT a FROM Aluno a WHERE a.cpf = :cpf")
    Aluno findByCpf(@Param("cpf") String cpf);
}
