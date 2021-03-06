package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Origem;
import com.algaworks.brewer.model.Sabor;
import com.algaworks.brewer.repository.Estilos;
import com.algaworks.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {	
	
	@Autowired
	Estilos estilos;
	
	@Autowired
	CadastroCervejaService cadastroCervejaService;
	
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());	
		return mv;
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cerveja);
		}
		
		System.out.println(">>>> SKU: " + cerveja.getSku());
		System.out.println(">>>> NOME: " + cerveja.getNome());
//		System.out.println(">>>> SABOR: " + cerveja.getSabor());
//		System.out.println(">>>> ORIGEM: " + cerveja.getOrigem());
//		System.out.println(">>>> DESCRIÇÃO: " + cerveja.getDescricao());
//		System.out.println(">>>> ESTILO: " + cerveja.getEstilo());
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!!!"); //Redirect precisa de atributo 'flash' para não peder valores
		return new ModelAndView("redirect:/cervejas/novo");
	}	
	
}
