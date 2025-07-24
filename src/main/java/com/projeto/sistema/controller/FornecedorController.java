package com.projeto.sistema.controller;

import com.projeto.sistema.model.Fornecedor;
import com.projeto.sistema.repository.CidadeRepository;
import com.projeto.sistema.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private CidadeRepository cidadeRepository;



    @GetMapping("/cadastroFornecedor")
    public ModelAndView cadastrar(Fornecedor fornecedor) {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/cadastro");
        mv.addObject("Fornecedor", fornecedor);
        mv.addObject("listaCidade", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/listarFornecedor")
    public  ModelAndView listar() {
        ModelAndView mv = new ModelAndView("/administrativo/fornecedores/lista");
        mv.addObject("listaFornecedors", fornecedorRepository.findAll());
        return mv;
    }

    @GetMapping("/editarFornecedor/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        return cadastrar(fornecedor.get());
    }

    @GetMapping("/removerFornecedor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        fornecedorRepository.delete(fornecedor.get());
        return listar();
    }


    @PostMapping("/salvarFornecedor")
    public ModelAndView salvar(Fornecedor fornecedor, BindingResult result) {
        if (result.hasErrors()){
            return cadastrar(fornecedor);
        }
        fornecedorRepository.saveAndFlush(fornecedor);
        return cadastrar(new Fornecedor());
    }



}
