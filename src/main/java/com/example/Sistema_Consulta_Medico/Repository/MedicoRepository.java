package com.example.Sistema_Consulta_Medico.Repository;

import com.example.Sistema_Consulta_Medico.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("FROM Medico m WHERE LOWER(m.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Medico> buscarPorNome(@Param("nome") String nome);

    @Query(value = "SELECT p.* " +
            "FROM PESSOA p " +
            "INNER JOIN TB_AGENDA agenda ON p.id = agenda.MEDICO_ID " +
            "WHERE agenda.status = 'DISPONIVEL'", nativeQuery = true)
    List<Medico> buscarMedicosDisponiveis();

}
