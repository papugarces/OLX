package co.com.eam.avanzada.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.com.eam.avanzada.domain.Imgproducto;
import co.com.eam.avanzada.domain.Municipio;
import co.com.eam.avanzada.domain.Producto;
import co.com.eam.avanzada.domain.Usuario;
import co.com.eam.avanzada.repository.ICategoriaRepository;
import co.com.eam.avanzada.repository.IDepartamentoRepository;
import co.com.eam.avanzada.repository.IImgProductoRepository;
import co.com.eam.avanzada.repository.IMunicipioRepository;
import co.com.eam.avanzada.repository.IProductoRepository;
import co.com.eam.avanzada.repository.ISubcategoriaRepository;
import co.com.eam.avanzada.repository.IUsuarioRepository;

@Controller
public class ProductoController {
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	private final IImgProductoRepository iImagenRepository;
	private final IProductoRepository iProductoRepository;
	private final IDepartamentoRepository idepartamentoRepository;
	private final IMunicipioRepository imunicipioRepository;
	private final ICategoriaRepository icategoriaRepository;
	private final ISubcategoriaRepository isubcategoriaRepository;
	private final IUsuarioRepository iusuarioRepository;

	@Autowired
	public ProductoController(IImgProductoRepository iImagenRepository, IProductoRepository iProductoRepository,
			IDepartamentoRepository idepartamentoRepository, IMunicipioRepository imunicipioRepository, ICategoriaRepository icategoriaRepository, ISubcategoriaRepository isubcategoriaRepository,
			IUsuarioRepository iusuarioRepository) {
		super();
		this.iImagenRepository = iImagenRepository;
		this.iProductoRepository = iProductoRepository;
		this.idepartamentoRepository = idepartamentoRepository;
		this.imunicipioRepository = imunicipioRepository;
		this.icategoriaRepository = icategoriaRepository;
		this.isubcategoriaRepository = isubcategoriaRepository;
		this.iusuarioRepository = iusuarioRepository;
	}

	// metodo Agregar---------------------------------------------
	@GetMapping("/user/singproducto")
	public String showSignUpForm(Producto producto, Model model) {
		model.addAttribute("municipios", imunicipioRepository.findAll());
		model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
		model.addAttribute("usuarios", iusuarioRepository.findAll());
		return "add-producto";
	}

	@PostMapping("/user/addproducto")
	public String addProducto(@Valid Producto producto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-producto";
		}

		iProductoRepository.save(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";
	}
	
	@GetMapping("/user/crear-publicacion/{correo}")
	public String crearPublicacion(@PathVariable("correo") String correo, Producto producto, Model model) {
		model.addAttribute("correo", correo);
		model.addAttribute("departamentos", idepartamentoRepository.findAll());
		model.addAttribute("municipios", imunicipioRepository.findAll());
		model.addAttribute("categorias", icategoriaRepository.findAll());
		model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
		return "publicar";
	}
	
	@RequestMapping("/publicar/ajax/municipios")
	public String ajaxMunicipios(@RequestParam("idDepartamento") Integer idDepartamento, Model model) {
		List<Municipio> municipios = imunicipioRepository.findAllMunicipiosByIdDepartamento(idDepartamento);
		model.addAttribute("municipios", municipios);
		return "publicar :: municipios";
	}

	@PostMapping("/user/publicar/{correo}")
	public String publicarAnuncio(@PathVariable("correo") String correo, @Valid Producto producto, BindingResult result, Model model) {
						
		if (result.hasErrors()) {
			
			//producto.setUsuario(iusuarioRepository.findByCorreo(correo));
			return "publicar";
		}
		Usuario usuario = new Usuario();
		usuario.setCorreo(correo);
		producto.setEstado(true);
		producto.setUsuario(usuario);

		iProductoRepository.save(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		System.out.println(producto.toString());
		return "redirect:/user/productos-disponibles";
	}

	// metodo Actualizar---------------------------------------------
	@GetMapping("/user/editproducto/{idProducto}")
	public String showUpdateForm(@PathVariable("idProducto") Integer idProducto, Model model) {
		Producto producto = iProductoRepository.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
		model.addAttribute("producto", producto);
		model.addAttribute("municipios", imunicipioRepository.findAll());
		model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
		return "update-producto";
	}
	
	@GetMapping("/user/edit-producto/{idProducto}")
	public String showUpdateProducto(@PathVariable("idProducto") Integer idProducto, Model model) {
		Producto producto = iProductoRepository.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
		model.addAttribute("producto", producto);
		model.addAttribute("municipios", imunicipioRepository.findAll());
		model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
		return "edit-producto";
	}

	@PostMapping("/user/updateproducto/{idProducto}")
	public String updateProducto(@PathVariable("idProducto") Integer idProducto, @Valid Producto producto,
			BindingResult result, Model model, @RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			producto.setIdProducto(idProducto);
			return "update-producto";
		}

		Imgproducto img = new Imgproducto();

		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + " ");

			try {
				Files.write(fileNameAndPath, file.getBytes());
				img.setProducto(producto);
				img.setUrl(fileNameAndPath.toString());
				iImagenRepository.save(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		iProductoRepository.save(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";
	}
	
	@PostMapping("/user/update-producto/{idProducto}")
	public String updateProductoUser(@PathVariable("idProducto") Integer idProducto, @Valid Producto producto,
			BindingResult result, Model model, @RequestParam("files") MultipartFile[] files) {
		if (result.hasErrors()) {
			producto.setIdProducto(idProducto);
			return "edit-producto";
		}

		Imgproducto img = new Imgproducto();

		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + " ");

			try {
				Files.write(fileNameAndPath, file.getBytes());
				img.setProducto(producto);
				img.setUrl(fileNameAndPath.toString());
				iImagenRepository.save(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		iProductoRepository.save(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		return "redirect:/user/productos-disponibles";
	}

	// metodo Eliminar---------------------------------------------
	@GetMapping("/user/deleteproducto/{idProducto}")
	public String deleteProducto(@PathVariable("idProducto") Integer idProducto, Model model) {
		Producto producto = iProductoRepository.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
		iProductoRepository.delete(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";
	}

	// metodo productos disponibles ---------------------------------------------
	@GetMapping("/user/productos-disponibles")
	public String traerSoloDisponibles(Model model) {
		model.addAttribute("productos", iProductoRepository.cargarProductosActivos());
		return "index";
	}

	// metodo ver informacion del
	// producto---------------------------------------------
	@GetMapping("/user/verproducto/{idProducto}")
	public String verProducto(@PathVariable("idProducto") Integer idProducto, Model model) {
		Producto producto = iProductoRepository.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
		model.addAttribute("producto", producto);
		return "ver-publicacion";
	}

	// Listado de productos ---------------------------------------------
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/listapro")
	public String list(Producto producto, Model model) {
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";

	}

}
