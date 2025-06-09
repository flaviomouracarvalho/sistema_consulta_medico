package com.example.Sistema_Consulta_Medico.Controller;
import com.example.Sistema_Consulta_Medico.Dto.AgendaDto;
import com.example.Sistema_Consulta_Medico.Enum.StatusAgenda;
import com.example.Sistema_Consulta_Medico.Repository.AgendaRepository;
import com.example.Sistema_Consulta_Medico.Repository.ConsultaRepository;
import com.example.Sistema_Consulta_Medico.Repository.MedicoRepository;
import com.example.Sistema_Consulta_Medico.Repository.PacienteRepository;
import com.example.Sistema_Consulta_Medico.entity.Agenda;
import com.example.Sistema_Consulta_Medico.entity.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepo;
    @Autowired
    private MedicoRepository medicoRepo;
    @Autowired
    private PacienteRepository pacienteRepo;
    @Autowired
    private ConsultaRepository consultaRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendas", agendaRepo.findAll());
        return "agenda/listar";
    }

    @GetMapping("/nova")
    public String novaAgenda(Model model) {
        model.addAttribute("agenda", new AgendaDto()); // <- ESSENCIAL
        model.addAttribute("medicos", medicoRepo.findAll());
        return "agenda/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute AgendaDto dto, Model model) {
        if ((dto.getDataHoraInicio() == null || dto.getDataHoraInicio().isBlank()) && (dto.getDataHoraFim() == null || dto.getDataHoraFim().isBlank())) {
            model.addAttribute("erro", "A data/hora início ou fim é obrigatória.");
            model.addAttribute("agenda", dto);
            model.addAttribute("medicos", medicoRepo.findAll());
            return "agenda/form";
        }


        LocalDateTime inicio = LocalDateTime.parse(dto.getDataHoraInicio());
        LocalDateTime fim = LocalDateTime.parse(dto.getDataHoraFim());
        Long medicoId = dto.getMedicoId();

        List<Agenda> conflitos = agendaRepo.findAgendasConflitantes(
                medicoRepo.findById(medicoId).orElseThrow().getId(),
                inicio,
                fim
        );

        if (!conflitos.isEmpty()) {
            model.addAttribute("erro", "Já existe uma agenda que conflita com este horário.");
            model.addAttribute("agenda", dto);
            model.addAttribute("medicos", medicoRepo.findAll());
            return "agenda/form";
        }

        DayOfWeek diaSemana = inicio.getDayOfWeek();
        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            model.addAttribute("erro", "Não é possivel cadastrar agenda para o fins de semana");
            model.addAttribute("agenda", dto);
            model.addAttribute("medicos", medicoRepo.findAll());
            return "agenda/form";
        }

        if (inicio.isAfter(fim)){
            model.addAttribute("erro", "A data/hora início não pode ser superior a data/hora final");
            model.addAttribute("agenda", dto);
            model.addAttribute("medicos", medicoRepo.findAll());
            return "agenda/form";
        }

        Agenda agenda = new Agenda();
        agenda.setDataHoraInicio(inicio);
        agenda.setDataHoraFim(fim);
        agenda.setStatus(StatusAgenda.DISPONIVEL);
        agenda.setMedico(medicoRepo.findById(medicoId).orElseThrow());
        agendaRepo.save(agenda);


        return "redirect:/agenda";
    }



    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Agenda agenda = agendaRepo.findById(id).orElseThrow();
        agenda.setStatus(StatusAgenda.CANCELADO);
        if (agenda.getConsulta() != null) {
            Consulta consulta = consultaRepo.findById(agenda.getConsulta().getId()).orElseThrow();
            agenda.setConsulta(null);
            consultaRepo.delete(consulta);
        }
        agendaRepo.save(agenda);
        redirectAttributes.addFlashAttribute("mensagem", "Horário cancelado com sucesso!");
        return "redirect:/agenda";
    }
}
