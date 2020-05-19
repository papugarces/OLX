package co.com.eam.avanzada.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Producto;
import co.com.eam.avanzada.domain.UsuarioPK;
import co.com.eam.avanzada.repository.IMunicipioRepository;
import co.com.eam.avanzada.repository.IProductoRepository;
import co.com.eam.avanzada.repository.ISubcategoriaRepository;
import co.com.eam.avanzada.repository.IUsuarioRepository;

@Controller
public class ProductoController {

	private final IProductoRepository iProductoRepository;
	private final IMunicipioRepository imunicipioRepository;
	private final ISubcategoriaRepository isubcategoriaRepository;
	private final IUsuarioRepository iusuarioRepository;
	
	@Autowired
	public ProductoController(IProductoRepository iProductoRepository,IMunicipioRepository imunicipioRepository,
			ISubcategoriaRepository isubcategoriaRepository, IUsuarioRepository iusuarioRepository) {
		this.iProductoRepository = iProductoRepository;
		this.imunicipioRepository = imunicipioRepository;
		this.isubcategoriaRepository = isubcategoriaRepository;
		this.iusuarioRepository = iusuarioRepository;
	}
	
	//metodo Agregar---------------------------------------------
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
    
  //metodo Actualizar---------------------------------------------
    @GetMapping("/editproducto/{idProducto}")
    public String showUpdateForm(@PathVariable("idProducto") Integer idProducto, Model model) {
    	Producto producto = iProductoRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
        model.addAttribute("producto", producto);
        model.addAttribute("municipios", imunicipioRepository.findAll());
        model.addAttribute("subcategorias", isubcategoriaRepository.findAll());
        model.addAttribute("usuarios", iusuarioRepository.findAll());
        return "update-producto";
    }
    
    @PostMapping("/updateproducto/{idProducto}")
    public String updateProducto(@PathVariable("idProducto") Integer idProducto, @Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	producto.setIdProducto(idProducto);
            return "update-producto";
        }
        
        iProductoRepository.save(producto);
        model.addAttribute("productos", iProductoRepository.findAll());
        return "lista-productos";
    }
    
    //metodo Eliminar---------------------------------------------
    @GetMapping("/deleteproducto/{idProducto}")
    public String deleteProducto(@PathVariable("idProducto") Integer idProducto, Model model) {
    	Producto producto = iProductoRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalido El Producto id:" + idProducto));
    	iProductoRepository.delete(producto);
        model.addAttribute("productos", iProductoRepository.findAll());
        return "lista-productos";
    }
    
    //metodo productos disponibles ---------------------------------------------
    @GetMapping("/productos-disponibles")
    public String traerSoloDisponibles(Model model) {
    	model.addAttribute("productos",iProductoRepository.cargarProductosActivos());
    	return "productos-dis";
    }
}
