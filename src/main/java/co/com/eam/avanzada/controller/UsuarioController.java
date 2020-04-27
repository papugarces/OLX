package co.com.eam.avanzada.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.domain.UsuarioPK;
import co.com.eam.avanzada.repository.IUsuarioRepository;

@Controller
public class UsuarioController {

	private final IUsuarioRepository iUsuarioRepository;

	@Autowired
	public UsuarioController(IUsuarioRepository iUsuarioRepository) {
		this.iUsuarioRepository = iUsuarioRepository;
	}
	
	//controlador Agregar---------------------------------------------
    @GetMapping("/singusuario")
    public String showSignUpForm(Usuario usuario) {
        return "add-user";
    }
    
    @PostMapping("/addusuario")
    public String addUsuario(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        iUsuarioRepository.save(usuario);
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        return "lista-user";
    }
    
    //controlador Actualizar---------------------------------------------
    @GetMapping("/editusuario/{id}/{correo}")
    public String showUpdateForm(@PathVariable("id") String id,@PathVariable("correo") String correo, Model model) {
    	UsuarioPK primary = new UsuarioPK();
    	primary.setDni(id);
    	primary.setCorreo(correo);
    	Usuario usuario = iUsuarioRepository.findById(primary).orElseThrow(() -> new IllegalArgumentException("Invalido usuario id:" + id));
        model.addAttribute("usuario", usuario);
        return "update-user";
    }
    
    @PostMapping("/updateusuario/{id}/{correo}")
    public String updateUsuario(@PathVariable("id") String id,@PathVariable("correo") String correo, @Valid Usuario usuario, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		UsuarioPK primary = new UsuarioPK();
        	primary.setDni(id);
        	primary.setCorreo(correo);
        	usuario.setId(primary);;
            return "update-user";
        }
        
        iUsuarioRepository.save(usuario);
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        return "lista-user";
    }
    
    //controlador Eliminar---------------------------------------------
    @GetMapping("/deleteusuario/{id}/{correo}")
    public String deleteUsuario(@PathVariable("id") String id,@PathVariable("correo") String correo, Model model) {
    	UsuarioPK primary = new UsuarioPK();
    	primary.setDni(id);
    	primary.setCorreo(correo);
    	Usuario usuario = iUsuarioRepository.findById(primary).orElseThrow(() -> new IllegalArgumentException("Invalido usuario id:" + id));
    	iUsuarioRepository.delete(usuario);
        model.addAttribute("usuarios", iUsuarioRepository.findAll());
        return "lista-user";
    }

}