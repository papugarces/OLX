package co.com.eam.avanzada.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Categoria;
import co.com.eam.avanzada.domain.Chat;
import co.com.eam.avanzada.domain.Mensaje;
import co.com.eam.avanzada.domain.MensajePK;
import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.domain.UsuarioPK;
import co.com.eam.avanzada.repository.IChatRepository;
import co.com.eam.avanzada.repository.IMensajeRepository;
import co.com.eam.avanzada.repository.IUsuarioRepository;


@Controller
public class MensajeController {
	
	private final IMensajeRepository mensajeRepository;
	private final IUsuarioRepository usuarioRepository;
	private final IChatRepository chatRepository;

    @Autowired
    public MensajeController(IMensajeRepository mensajeRepository, IUsuarioRepository usuarioRepository,
			IChatRepository chatRepository) {
		super();
		this.mensajeRepository = mensajeRepository;
		this.usuarioRepository = usuarioRepository;
		this.chatRepository = chatRepository;
	}
    
     
    //metodo Agregar---------------------------------------------
    @GetMapping("/singnchat")
    public String showSignUpForm(UsuarioPK user1,UsuarioPK user2,Model model) {
    	model.addAttribute("usuarios", usuarioRepository.findAll());
        return "add-chat";
    }
    
    @PostMapping("/addchat")
    public String addChat(@Valid UsuarioPK user1,@Valid UsuarioPK user2, BindingResult result, Model model) {
    	Chat chat = new Chat();
    	Mensaje mensaje = new Mensaje();
    	if (result.hasErrors()) {
        	Usuario cliente = usuarioRepository.findById(user1).orElseThrow(() -> new IllegalArgumentException("Invalido Usuario id:" + user1));
        	Usuario ofertador = usuarioRepository.findById(user2).orElseThrow(() -> new IllegalArgumentException("Invalido Usuario id:" + user1));
        	
        	chat.setUsuario1(cliente);
        	chat.setUsuario2(ofertador);
        	MensajePK idMensaje = new MensajePK();
        	idMensaje.setChat(chat);
        	mensaje.setId(idMensaje);
            return "add-chat";
        }

        chatRepository.save(chat);
        mensajeRepository.save(mensaje);
        model.addAttribute("mensaje", mensajeRepository.findById(mensaje.getId()));
        model.addAttribute("envia", user1);
        return "add-mensaje";
    }
    
    //metodo Actualizar---------------------------------------------
    @GetMapping("/addmensaje/{mensaje}/{envia}")
    public String showUpdateForm(@PathVariable("mensaje") Mensaje mensaje,@PathVariable("envia") Usuario envia, Model model) {
        model.addAttribute("mensaje", mensaje);
        return "add-mensaje";
    }
    
    @PostMapping("/cargarmensaje")
    public String addMensaje(@Valid Mensaje msn, BindingResult result, Model model) {
    	Chat chat = new Chat();
    	Mensaje mensaje = new Mensaje();
    	if (result.hasErrors()) {
    		MensajePK idMensaje = new MensajePK();
    		idMensaje.setChat(msn.getId().getChat());
        	mensaje.setId(idMensaje);
        	mensaje.setFechaEnvio(msn.getFechaEnvio());
        	mensaje.setTexto(msn.getTexto());
        	mensaje.setUsuario(msn.getUsuario());
            return "add-mensaje";
        }

        mensajeRepository.save(mensaje);
        model.addAttribute("mensaje", mensajeRepository.cargarMensajes(mensaje.getUsuario().getId().getDni(),mensaje.getId().getChat().getUsuario2().getId().getDni()));
        return "add-mensaje";
    }
  

}
