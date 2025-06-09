package com.example.Sistema_Consulta_Medico.Controller;




import com.example.Sistema_Consulta_Medico.Repository.MedicoRepository;
import com.example.Sistema_Consulta_Medico.entity.Medico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controler médico
@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepo;

    @GetMapping
    public String listar(@RequestParam(required = false) String keyword, Model model) {
        List<Medico> medicos = (keyword != null && !keyword.isBlank())
                ? medicoRepo.buscarPorNome(keyword)
                : medicoRepo.findAll();

        model.addAttribute("medicos", medicos);
        return "medico/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("medico", new Medico());
        return "medico/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Medico medico, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "medico/form";
        }
        medicoRepo.save(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("medico", medicoRepo.findById(id).get());
        return "medico/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        medicoRepo.deleteById(id);
        return "redirect:/medicos";
    }
}
