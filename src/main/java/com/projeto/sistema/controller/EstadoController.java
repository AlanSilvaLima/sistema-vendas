package com.projeto.sistema.controller;

import ch.qos.logback.core.model.Model;
import com.projeto.sistema.model.Estado;
import com.projeto.sistema.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado){
        ModelAndView mv = new ModelAndView("administrativo/estado/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(estado);
        }
        estadoRepository.saveAndFlush(estado);
        return cadastrar(new Estado());
    }

}
