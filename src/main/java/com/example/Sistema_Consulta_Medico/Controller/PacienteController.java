package com.example.Sistema_Consulta_Medico.Controller;


import com.example.Sistema_Consulta_Medico.Repository.PacienteRepository;
import com.example.Sistema_Consulta_Medico.entity.Paciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controler paciente
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    @GetMapping
    public String listar(@RequestParam(required = false) String keyword, Model model) {
        List<Paciente> pacientes = (keyword != null && !keyword.isBlank())
                ? pacienteRepo.buscarPorNome(keyword)
                : pacienteRepo.findAll();

        model.addAttribute("pacientes", pacientes);
        return "paciente/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Paciente paciente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("paciente", paciente);
            return "paciente/form";
        }

        pacienteRepo.save(paciente);
        return "redirect:/pacientes";
    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteRepo.findById(id).orElseThrow());
        return "paciente/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        pacienteRepo.deleteById(id);
        return "redirect:/pacientes";
    }
}
