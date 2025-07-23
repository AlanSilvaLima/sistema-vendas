package com.projeto.sistema.controller;

import ch.qos.logback.core.model.Model;
import com.projeto.sistema.model.Estado;
import com.projeto.sistema.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estado/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/listarEstado")
    public  ModelAndView listar() {
        ModelAndView mv = new ModelAndView("/administrativo/estados/lista");
        mv.addObject("listaEstados", estadoRepository.findAll());
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return cadastrar(estado.get());
    }

    @GetMapping("/removerEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        estadoRepository.delete(estado.get());
        return listar();
    }


    @PostMapping("/salvarEstado")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if (result.hasErrors()){
            return cadastrar(estado);
        }
        estadoRepository.saveAndFlush(estado);
        return cadastrar(new Estado());
    }



}
