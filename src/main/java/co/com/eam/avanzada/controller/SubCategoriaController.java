package co.com.eam.avanzada.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import co.com.eam.avanzada.domain.Subcategoria;
import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.domain.Categoria;
import co.com.eam.avanzada.repository.ICategoriaRepository;
import co.com.eam.avanzada.repository.ISubcategoriaRepository;

@Controller
public class SubCategoriaController {
	private final ISubcategoriaRepository subCategoriaRepository;
	
	private final ICategoriaRepository categoriaRepository;

    @Autowired
    public SubCategoriaController(ISubcategoriaRepository subCategoriaRepository,ICategoriaRepository categoriaRepository) {
        this.subCategoriaRepository = subCategoriaRepository;
        this.categoriaRepository = categoriaRepository;
    }
    
    //metodo Agregar---------------------------------------------
    @GetMapping("/singsubcategoria")
    public String showSignUpForm(Subcategoria subcategoria,Model model) {
    	model.addAttribute("categorias", categoriaRepository.findAll());
        return "add-subCategoria";
    }
    
    @PostMapping("/addsubcategoria")
    public String addSubCategoria(@Valid Subcategoria subcategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-subCategoria";
        }
        
        subCategoriaRepository.save(subcategoria);
        model.addAttribute("subcategorias", subCategoriaRepository.findAll());
        return "lista-subCategoria";
    }
    
    //metodo Actualizar---------------------------------------------
    @GetMapping("/editSubCategoria/{idSubCategoria}")
    public String showUpdateForm(@PathVariable("idSubCategoria") int idSubCategoria, Model model) {
    	Subcategoria subcategoria = subCategoriaRepository.findById(idSubCategoria).orElseThrow(() -> new IllegalArgumentException("Invalido SubCategoria idSubCategoria:" + idSubCategoria));
        model.addAttribute("subcategoria", subcategoria);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "update-subCategoria";
    }
    
    @PostMapping("/updateSubCategoria/{idSubCategoria}")
    public String updateSubCategoria(@PathVariable("idSubCategoria") int idSubCategoria, @Valid Subcategoria subcategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	subcategoria.setIdSubCategoria(idSubCategoria);
            return "update-subCategoria";
        }
        
        subCategoriaRepository.save(subcategoria);
        model.addAttribute("subcategorias", subCategoriaRepository.findAll());
        return "lista-subCategoria";
    }
    
    //metodo Eliminar---------------------------------------------
    @GetMapping("/deleteSubCategoria/{idSubCategoria}")
    public String deleteSubCategoria(@PathVariable("idSubCategoria") int idSubCategoria, Model model) {
    	Subcategoria subcategoria = subCategoriaRepository.findById(idSubCategoria).orElseThrow(() -> new IllegalArgumentException("Invalido SubCategoria idSubCategoria:" + idSubCategoria));
    	subCategoriaRepository.delete(subcategoria);
        model.addAttribute("subcategorias", subCategoriaRepository.findAll());
        return "lista-subCategoria";
    }
    
    
    // Listado de subCategorias  ---------------------------------------------
 	@GetMapping("/listasubca")
 	public String list(Subcategoria subcategoria, Model model) {
 		model.addAttribute("subcategorias", subCategoriaRepository.findAll());
 		return "lista-subCategoria";
 		
 	}
}
