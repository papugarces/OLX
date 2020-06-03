package co.com.eam.avanzada.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Categoria;
import co.com.eam.avanzada.repository.ICategoriaRepository;



@Controller
public class CategoriaController {
	private final ICategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    //metodo Agregar---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/signcategoria")
    public String showSignUpForm(Categoria categoria) {
        return "add-categoria";
    }
    
    @PostMapping("/addcategoria")
    public String addCategoria(@Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-categoria";
        }
        
        categoriaRepository.save(categoria);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "redirect:/listacat";
    }
    
    //metodo Actualizar---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/editCategoria/{idCategoria}")
    public String showUpdateForm(@PathVariable("idCategoria") int idCategoria, Model model) {
    	Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalido categoria idCategoria:" + idCategoria));
        model.addAttribute("categoria", categoria);
        return "update-categoria";
    }
    
    @PostMapping("/updateCategoria/{idCategoria}")
    public String updateCategoria(@PathVariable("idCategoria") int idCategoria, @Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	categoria.setIdCategoria(idCategoria);
            return "update-categoria";
        }
        
        categoriaRepository.save(categoria);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "redirect:/listacat";
    }
    
    //metodo Eliminar---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteCategoria/{idCategoria}")
    public String deleteCategoria(@PathVariable("idCategoria") int idCategoria, Model model) {
    	Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalido categoria idCategoria:" + idCategoria));
        categoriaRepository.delete(categoria);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "redirect:/listacat";
    }
    
 // Listado de Categorias  ---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
  	@GetMapping("/listacat")
  	public String list(Categoria categoria, Model model) {
  		model.addAttribute("categorias", categoriaRepository.findAll());
        return "lista-categoria";
  	}
}
