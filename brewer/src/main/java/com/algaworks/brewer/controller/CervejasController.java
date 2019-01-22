package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;

@Controller
public class CervejasController {

	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja) {
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cerveja);
		}
		
		System.out.println(">>>> SKU: " + cerveja.getSku());
		System.out.println(">>>> NOME: " + cerveja.getNome());
		System.out.println(">>>> DESCRIÇÃO: " + cerveja.getDescricao());
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!!!"); //Redirect precisa de atributo 'flash' para não peder valores
		return "redirect:/cervejas/novo";
	}
	
	@RequestMapping("/cervejas/cadastro")
	public String cadastro() {
		return "cerveja/cadastro-produto";
	}
	
}
