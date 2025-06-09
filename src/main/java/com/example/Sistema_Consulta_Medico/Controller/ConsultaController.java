package com.example.Sistema_Consulta_Medico.Controller;


import com.example.Sistema_Consulta_Medico.Enum.StatusAgenda;
import com.example.Sistema_Consulta_Medico.Repository.AgendaRepository;
import com.example.Sistema_Consulta_Medico.Repository.ConsultaRepository;
import com.example.Sistema_Consulta_Medico.Repository.MedicoRepository;
import com.example.Sistema_Consulta_Medico.Repository.PacienteRepository;
import com.example.Sistema_Consulta_Medico.entity.Agenda;
import com.example.Sistema_Consulta_Medico.entity.Medico;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import com.example.Sistema_Consulta_Medico.entity.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//controler consulta
@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository consultaRepo;
    @Autowired
    private PacienteRepository pacienteRepo;
    @Autowired
    private MedicoRepository medicoRepo;
    @Autowired
    private AgendaRepository agendaRepo;

    @GetMapping
    public String listar(@RequestParam(required = false)
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate keyword,
                         Model model) {

        List<Consulta> consultas;

        if (keyword != null) {
            // cria intervalo [00:00, 23:59)
            LocalDateTime inicio = keyword.atStartOfDay();
            LocalDateTime fim = inicio.plusDays(1);
            consultas = consultaRepo.buscarPorData(inicio, fim);
        } else {
            consultas = consultaRepo.findAll();
        }

        model.addAttribute("consultas", consultas);
        return "consulta/listar";
    }



    @GetMapping("/nova")
    public String novaConsulta(@RequestParam(value = "medicoId", required = false) Long medicoId, Model model) {
        List<Medico> medicos = medicoRepo.buscarMedicosDisponiveis();

        List<Agenda> agendas = new ArrayList<>();
        if (medicoId != null) {
            agendas = agendaRepo.findByStatusAndMedico_Id(StatusAgenda.DISPONIVEL, medicoId);
        }

        model.addAttribute("consulta", new Consulta());
        model.addAttribute("medicos", medicos);
        model.addAttribute("agendas", agendas);
        model.addAttribute("medicoId", medicoId); // para manter a seleção no combo

        return "consulta/form";
    }


    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Consulta consulta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Se houver erros, recarrega os selects para não dar erro no form
            model.addAttribute("pacientes", pacienteRepo.findAll());
            model.addAttribute("medicos", medicoRepo.findAll());
            return "consulta/form";
        }
        Agenda agenda= consulta.getAgenda();
        agenda.setStatus(StatusAgenda.AGENDADO);
        agendaRepo.save(agenda);
        consultaRepo.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada para o ID: " + id));
        model.addAttribute("consulta", consulta);
        model.addAttribute("pacientes", pacienteRepo.findAll());
        model.addAttribute("medicos", medicoRepo.findAll());
        return "consulta/form";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        Consulta consulta = consultaRepo.findById(id).orElseThrow();
        Agenda agenda = consulta.getAgenda();
        agenda.setStatus(StatusAgenda.DISPONIVEL);
        agenda.setConsulta(null);
        agendaRepo.save(agenda);
        consultaRepo.deleteById(id);
        return "redirect:/consultas";
    }
}
