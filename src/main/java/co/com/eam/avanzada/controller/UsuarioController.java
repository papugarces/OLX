package co.com.eam.avanzada.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Authority;
import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.repository.AuthorityRepository;
import co.com.eam.avanzada.repository.IUsuarioRepository;
import co.com.eam.avanzada.util.Passgenerator;

@Controller
public class UsuarioController {

	private final IUsuarioRepository IusuarioRepository;
	
	@Autowired
   	private AuthorityRepository authorityRepository;
	
	@Autowired
    Passgenerator passgenerator;

	@Autowired
	public UsuarioController(IUsuarioRepository IusuarioRepository) {
		this.IusuarioRepository = IusuarioRepository;
	}
	
	//metodo Agregar---------------------------------------------
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/add-usuario")
    public String showSignUpFormAddUser(Usuario usuario) {
        return "add-user";
    }
    
    @PostMapping("/admin/addusuario")
    public String addUsuario(@Valid Usuario usuario, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        //String entrada = "03/12/2001";
        //DateFormat format = new SimpleDateFormat("DD/MM/YYYY");
        //Date fecha = (Date) format.parse(entrada);
        
        Authority autorizacion= authorityRepository.findByAuthority("ROLE_USER");
        
        Set<Authority> authority= new HashSet<Authority>();
        authority.add(autorizacion);
        
        usuario.setAuthority(authority);
		usuario.setPassword(passgenerator.enciptarPassword(usuario.getPassword()));

        IusuarioRepository.save(usuario);
        model.addAttribute("usuarios", IusuarioRepository.findAll());
        return "redirect:/admin/listauser";
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(Usuario usuario) {
        return "registrarse";
    }
    
    @PostMapping("/user-registration")
    public String userRegistration(@Valid Usuario usuario, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "registrarse";
        }
        
        Authority autorizacion= authorityRepository.findByAuthority("ROLE_USER");
           
        Set<Authority> authority= new HashSet<Authority>();
        authority.add(autorizacion);
        
        usuario.setAuthority(authority);
		usuario.setPassword(passgenerator.enciptarPassword(usuario.getPassword()));
                
        IusuarioRepository.save(usuario);
        return "redirect:/login";
    }
       
        
    //metodo Actualizar---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/editusuario/{correo}")
    public String showUpdateForm(@PathVariable("correo") String correo, Model model) {
    	Usuario usuario = IusuarioRepository.findById(correo).orElseThrow(() -> new IllegalArgumentException("Invalido usuario correo:" + correo));
        model.addAttribute("usuario", usuario);
        return "update-user";
    }
    
    @PostMapping("/admin/updateusuario/{correo}")
    public String updateUsuario(@PathVariable("correo") String correo, @Valid Usuario usuario, BindingResult result, Model model) {
    	if (result.hasErrors()) {
        	usuario.setCorreo(correo);
            return "update-user";
        }
        
    	IusuarioRepository.save(usuario);
        model.addAttribute("usuarios", IusuarioRepository.findAll());
        return "redirect:/admin/listauser";
    }
    
    //metodo Eliminar---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/deleteusuario/{id}/{correo}")
    public String deleteUsuario(@PathVariable("id") String id,@PathVariable("correo") String correo, Model model) {
    	Usuario usuario = IusuarioRepository.findById(correo).orElseThrow(() -> new IllegalArgumentException("Invalido usuario id:" + id));
    	IusuarioRepository.delete(usuario);
        model.addAttribute("usuarios", IusuarioRepository.findAll());
        return "redirect:/admin/listauser";
    }
    
 // Listado de Usuarios  ---------------------------------------------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
 	@GetMapping("/admin/listauser")
 	public String list(Usuario usuario, Model model) {
 		model.addAttribute("usuarios", IusuarioRepository.findAll());
 		return "lista-user";
 		
 	}

}