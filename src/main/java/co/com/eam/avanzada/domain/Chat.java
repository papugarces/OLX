package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the chat database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idChat;

	//bi-directional many-to-one association to Usuario
	@ManyToOne()
	@JoinColumns({
		@JoinColumn(name="correoCliente", referencedColumnName="correo"),
		@JoinColumn(name="dniCliente", referencedColumnName="dni")
		})
	
	// Usuario 1 Hace Referencia al Cliente
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario 
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="correoOfertador", referencedColumnName="correo"),
		@JoinColumn(name="dniOfertador", referencedColumnName="dni")
		})
	// Usuario 2 Hace Referencia al Ofertador
	private Usuario usuario2;

}