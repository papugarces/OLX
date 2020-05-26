package co.com.eam.avanzada.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.repository.IUsuarioRepository;

@Controller
public class UsuarioController {

	private final IUsuarioRepository IusuarioRepository;

	@Autowired
	public UsuarioController(IUsuarioRepository IusuarioRepository) {
		this.IusuarioRepository = IusuarioRepository;
	}
	
	//metodo Agregar---------------------------------------------
    @GetMapping("/singusuario")
    public String showSignUpForm(Usuario usuario) {
        return "add-user";
    }
    
    @PostMapping("/addusuario")
    public String addUsuario(@Valid Usuario usuario, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        String entrada = "03/12/2001";
        DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
        Date fecha = (Date) format.parse(entrada);

        IusuarioRepository.save(usuario);
        model.addAttribute("usuarios", IusuarioRepository.findAll());
        return "lista-user";
    }
    
    //metodo Actualizar---------------------------------------------
    @GetMapping("/editusuario/{id}/{correo}")
    public String showUpdateForm(@PathVariable("id") String id,@PathVariable("correo") String correo, Model model) {
    	Usuario usuario = IusuarioRepository.findById(correo).orElseThrow(() -> new IllegalArgumentException("Invalido usuario id:" + id));
        model.addAttribute("usuario", usuario);
        return "update-user";
    }
    
    @PostMapping("/updateusuario/{id}/{correo}")
    public String updateUsuario(@PathVariable("id") String id,@PathVariable("correo") String correo, @Valid Usuario usuario, BindingResult result, Model model) {
    	if (result.hasErrors()) {
        	usuario.setDni(id);
        	usuario.setCorreo(correo);
            return "update-user";
        }
        
    	IusuarioRepository.save(usuario);
        model.addAttribute("usuarios", IusuarioRepository.findAll());
        return "lista-user";
    }
    
    //metodo Eliminar---------------------------------------------
    @GetMapping("/deleteusuario/{id}/{correo}")
    public String deleteUsuario(@PathVariable("id") String id,@PathVariable("correo") String correo, Model model) {
    	Usuario usuario = IusuarioRepository.findById(correo).orElseThrow(() -> new IllegalArgumentException("Invalido usuario id:" + id));
    	IusuarioRepository.delete(usuario);
        model.addAttribute("usuarios", IusuarioRepository.findAll());
        return "lista-user";
    }
    
 // Listado de Usuarios  ---------------------------------------------
 	@GetMapping("/listauser")
 	public String list(Usuario usuario, Model model) {
 		model.addAttribute("usuarios", IusuarioRepository.findAll());
 		return "lista-user";
 		
 	}

}