package com.projeto.sistema.controller;

import com.projeto.sistema.model.Funcionario;
import com.projeto.sistema.repository.FuncionarioRepository;
import com.projeto.sistema.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CidadeRepository cidadeRepository;



    @GetMapping("/cadastroFuncionario")
    public ModelAndView cadastrar(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("administrativo/Funcionario/cadastro");
        mv.addObject("Funcionario", funcionario);
        mv.addObject("listaCidade", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarFuncionario")
    public  ModelAndView listar() {
        ModelAndView mv = new ModelAndView("/administrativo/funcionarios/lista");
        mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
        return mv;
    }

    @GetMapping("/editarFuncionario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return cadastrar(funcionario.get());
    }

    @GetMapping("/removerFuncionario/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        funcionarioRepository.delete(funcionario.get());
        return listar();
    }


    @PostMapping("/salvarFuncionario")
    public ModelAndView salvar(Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()){
            return cadastrar(funcionario);
        }
        funcionarioRepository.saveAndFlush(funcionario);
        return cadastrar(new Funcionario());
    }



}
