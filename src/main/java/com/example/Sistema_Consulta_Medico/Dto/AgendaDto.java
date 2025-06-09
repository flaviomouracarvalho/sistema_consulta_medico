package com.example.Sistema_Consulta_Medico.Dto;

public class AgendaDto {
    private Long medicoId;
    private String dataHoraInicio; // Ex: "2025-06-04T14:30"
    private String dataHoraFim; // Ex: "2025-06-04T14:30"

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(String dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(String dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
