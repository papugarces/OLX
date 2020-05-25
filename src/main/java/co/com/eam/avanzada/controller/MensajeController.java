package co.com.eam.avanzada.controller;

import java.util.ArrayList;
import java.util.List;

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
    public String showSignUpForm(String correoUser1,String correoUser2,Model model) {
    	model.addAttribute("usuarios", usuarioRepository.findAll());
        return "add-chat";
    }
    
    @GetMapping("/nuevo-chat")
    public String comenzarChat(Model model) {
    	
    	Chat chat = new Chat();
    	List<Usuario> listaUsuarios = (List<Usuario>) usuarioRepository.findAll();
    	
    	model.addAttribute("chat", chat);
    	model.addAttribute("usuarios",listaUsuarios);
    	
    	return "crear-chat";
    }
    
    @PostMapping("/crearchat")
    public String crearChat(@Valid Chat chat, BindingResult result, Model model) {
    	
    	System.out.println("idChat"+chat.getIdChat());
    	System.out.println("idChat"+chat.getUsuario1().getDni());
    	System.out.println("idChat"+chat.getUsuario1().getCorreo());
    	System.out.println("idChat"+chat.getUsuario2().getDni());
    	System.out.println("idChat"+chat.getUsuario2().getCorreo());
    	
    	if (result.hasErrors()) {
    		Usuario cliente = usuarioRepository.findById(chat.getUsuario1().getCorreo()).orElseThrow(() -> new IllegalArgumentException("Invalido Usuario correo:" + chat.getUsuario1().getCorreo()));
        	Usuario ofertador = usuarioRepository.findById(chat.getUsuario2().getCorreo()).orElseThrow(() -> new IllegalArgumentException("Invalido Usuario correo:" + chat.getUsuario2().getCorreo()));
        	
        	chat.setUsuario1(cliente);
        	chat.setUsuario2(ofertador);
        	return "crear-chat";
        	
    	}
    	
    	
    	chatRepository.save(chat);
    	model.addAttribute("chat", chat);
    	model.addAttribute("mensajes",mensajeRepository.cargarMensajes(chat.getUsuario1().getDni(), chat.getUsuario2().getDni()));
    	
    	return "chat";
    }
    /*
    @PostMapping("/enviarmensaje")
    public String enviarMensaje(@Valid Chat chat, @Valid String texto, @Valid Usuario remitente, BindingResult result, Model model) {
        
    	MensajePK idMensaje = new MensajePK();
    	idMensaje.setChat(chat);
    	
    	Mensaje mensaje = new Mensaje();
    	mensaje.setId(idMensaje);
    	mensaje.setTexto(texto);
    	mensaje.setUsuario(remitente);
    	model.addAttribute("mensaje", mensaje);
        return "chat";
    }
    */
    
    @PostMapping("/addchat")
    public String addChat(@Valid String user1,@Valid String user2, BindingResult result, Model model) {
    	Chat chat = new Chat();
    	Mensaje mensaje = new Mensaje();
    	if (result.hasErrors()) {
        	Usuario cliente = usuarioRepository.findById(user1).orElseThrow(() -> new IllegalArgumentException("Invalido Usuario correo:" + user1));
        	Usuario ofertador = usuarioRepository.findById(user2).orElseThrow(() -> new IllegalArgumentException("Invalido Usuario correo:" + user2));
        	
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
    
    @PostMapping("/enviarmensaje")
    public String addMensaje(@Valid Mensaje nuevomensaje, BindingResult result, Model model) {
    	
    	Mensaje mensaje = new Mensaje();
    	if (result.hasErrors()) {
    		MensajePK idMensaje = new MensajePK();
    		idMensaje.setChat(nuevomensaje.getId().getChat());
        	mensaje.setId(idMensaje);
        	mensaje.setFechaEnvio(nuevomensaje.getFechaEnvio());
        	mensaje.setTexto(nuevomensaje.getTexto());
        	mensaje.setUsuario(nuevomensaje.getUsuario());
            return "add-mensaje";
        }

        mensajeRepository.save(nuevomensaje);
        model.addAttribute("mensajes",mensajeRepository.cargarMensajes(mensaje.getId().getChat().getUsuario1().getDni(), mensaje.getId().getChat().getUsuario2().getDni()));
        //model.addAttribute("mensaje", mensajeRepository.cargarMensajes(mensaje.getUsuario().getId().getDni(),mensaje.getId().getChat().getUsuario2().getId().getDni()));
        return "add-mensaje";
    }
  

}
