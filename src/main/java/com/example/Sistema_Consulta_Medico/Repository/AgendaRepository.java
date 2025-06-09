package com.example.Sistema_Consulta_Medico.Repository;


import com.example.Sistema_Consulta_Medico.Enum.StatusAgenda;
import com.example.Sistema_Consulta_Medico.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByStatusAndMedico_Id(StatusAgenda status, Long id);

    @Query("""
    SELECT a FROM Agenda a
    WHERE a.medico.id = :medicoId
    AND (
        :dataHoraInicio BETWEEN a.dataHoraInicio AND a.dataHoraFim
        OR :dataHoraFim BETWEEN a.dataHoraInicio AND a.dataHoraFim
        OR a.dataHoraInicio BETWEEN :dataHoraInicio AND :dataHoraFim
    )
""")
    List<Agenda> findAgendasConflitantes(@Param("medicoId") Long medicoId,
                                         @Param("dataHoraInicio") LocalDateTime dataHoraInicio,
                                         @Param("dataHoraFim") LocalDateTime dataHoraFim);

}

