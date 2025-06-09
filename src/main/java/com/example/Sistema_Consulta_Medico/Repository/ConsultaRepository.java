package com.example.Sistema_Consulta_Medico.Repository;


import com.example.Sistema_Consulta_Medico.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteId(Long pacienteId);
    @Query("FROM Consulta c WHERE c.agenda.dataHoraInicio >= :inicio AND c.agenda.dataHoraFim < :fim")
    List<Consulta> buscarPorData(@Param("inicio") LocalDateTime inicio,
                                 @Param("fim") LocalDateTime fim);


}