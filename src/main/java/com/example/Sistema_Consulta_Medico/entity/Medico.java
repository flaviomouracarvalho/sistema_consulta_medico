package com.example.Sistema_Consulta_Medico.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//entidade medico

@Entity
@DiscriminatorValue("MEDICO")
public class Medico extends Pessoa implements Serializable {

    @NotBlank(message = "CRM é obrigatório")
    @Size(min = 5, message = "CRM deve conter ao menos 5 caracteres")
    private String crm;


//get e set
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

}
