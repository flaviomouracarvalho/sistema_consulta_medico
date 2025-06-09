package com.example.Sistema_Consulta_Medico.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
//entidade consulta
@Entity
@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Min(value = 0, message = "O valor da consulta não pode ser negativo")
    private double valor;

    @NotBlank(message = "É obrigatorio preencher o campo")
    @Size(max = 255, message = "A observação não pode passar de 255 caracteres")
    private String observacao;


    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;


    @OneToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;


    public String dados() {
        return "Consulta em " + agenda.getDataHoraInicio() + " até " + agenda.getDataHoraFim() + " R$" + valor;
    }
    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}