package co.com.eam.avanzada.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

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
    
    @GetMapping("/signupmensaje")
    public String showSignUpForm(Mensaje mensaje) {
        return "add-mensaje";
    }
    

	@GetMapping("/addmensaje")
    public String addMensaje(@Valid Mensaje mensajes, BindingResult result, Model model) {
		UsuarioPK primaria = new UsuarioPK();
		UsuarioPK primaria2 = new UsuarioPK();
        primaria.setCorreo("ejemplo3@awd.com");
        primaria.setDni("1097");
        
        primaria2.setCorreo("ejemplo4@awd.cp");
        primaria2.setDni("1098");
        
        Usuario cliente = usuarioRepository.findById(primaria).get();
        Usuario Ofertador = usuarioRepository.findById(primaria2).get();

		Chat chat = new Chat();
        
        //chat.setUsuario1(cliente);
        //chat.setUsuario2(Ofertador);
        //chatRepository.save(chat);
        
        chat = chatRepository.findById(1).get();
        Mensaje mensaje = new  Mensaje();
        MensajePK idMensaje = new MensajePK();
        idMensaje.setChat(chat);
        idMensaje.setIdMensaje(1);//este se debe cambiar
        mensaje.setId(idMensaje);
        mensaje.setFechaEnvio(null);
        mensaje.setUsuario(Ofertador);
       // mensaje.setUsuario(cliente);
        mensaje.setTexto("");
        
        
        mensajeRepository.save(mensaje);
        
        return "add-mensaje";
    }
	
	
	@GetMapping("/verMensajes")
    public String verChat(@Valid Mensaje mensajes, BindingResult result, Model model) {
		Iterable<Mensaje> lista = mensajeRepository.cargarMensajes("1097", "1098");
    	for (Mensaje mensaje : lista) {
    		
    		System.out.println(mensaje.getUsuario().getNombre()+" : "+mensaje.getTexto());
		}
        
        return "add-mensaje";
    }
    
    

}
