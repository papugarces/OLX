package co.com.eam.avanzada.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

/**
 * The primary key class for the mensajes database table.
 * 
 */
@Embeddable
@Data
public class MensajePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMensaje;

	//bi-directional many-to-one association to Chat
	
	@ManyToOne
	@JoinColumn(name="idChat")
	private Chat chat;

	
}