package com.example.Sistema_Consulta_Medico.Repository;

import com.example.Sistema_Consulta_Medico.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Paciente> buscarPorNome(@Param("nome") String nome);
}

