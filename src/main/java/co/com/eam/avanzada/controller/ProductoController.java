package co.com.eam.avanzada.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.com.eam.avanzada.domain.Imgproducto;
import co.com.eam.avanzada.domain.Producto;
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
	private final IMunicipioRepository imunicipioRepository;
	private final ISubcategoriaRepository isubcategoriaRepository;
	private final IUsuarioRepository iusuarioRepository;

	@Autowired
	public ProductoController(IImgProductoRepository iImagenRepository, IProductoRepository iProductoRepository,
			IMunicipioRepository imunicipioRepository, ISubcategoriaRepository isubcategoriaRepository,
			IUsuarioRepository iusuarioRepository) {
		super();
		this.iImagenRepository = iImagenRepository;
		this.iProductoRepository = iProductoRepository;
		this.imunicipioRepository = imunicipioRepository;
		this.isubcategoriaRepository = isubcategoriaRepository;
		this.iusuarioRepository = iusuarioRepository;
	}

	// metodo Agregar---------------------------------------------
	@GetMapping("/singproducto")
	public String showSignUpForm(Producto producto, Model model) {
		model.addAttribute("municipios", imunicipioRepository.findAll());
		model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
		model.addAttribute("usuarios", iusuarioRepository.findAll());
		return "add-producto";
	}

	@PostMapping("/addproducto")
	public String addProducto(@Valid Producto producto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-producto";
		}

		iProductoRepository.save(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";
	}

	// metodo Actualizar---------------------------------------------
	@GetMapping("/editproducto/{idProducto}")
	public String showUpdateForm(@PathVariable("idProducto") Integer idProducto, Model model) {
		Producto producto = iProductoRepository.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
		model.addAttribute("producto", producto);
		model.addAttribute("municipios", imunicipioRepository.findAll());
		model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
		model.addAttribute("usuarios", iusuarioRepository.findAll());
		return "update-producto";
	}

	@PostMapping("/updateproducto/{idProducto}")
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

	// metodo Eliminar---------------------------------------------
	@GetMapping("/deleteproducto/{idProducto}")
	public String deleteProducto(@PathVariable("idProducto") Integer idProducto, Model model) {
		Producto producto = iProductoRepository.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
		iProductoRepository.delete(producto);
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";
	}

	// metodo productos disponibles ---------------------------------------------
	@GetMapping("/productos-disponibles")
	public String traerSoloDisponibles(Model model) {
		model.addAttribute("productos", iProductoRepository.cargarProductosActivos());
		return "ejemplo";
	}
	
	
	// Listado de productos  ---------------------------------------------
	@GetMapping("/listapro")
	public String list(Producto producto, Model model) {
		model.addAttribute("productos", iProductoRepository.findAll());
		return "lista-productos";
		
	}

}
