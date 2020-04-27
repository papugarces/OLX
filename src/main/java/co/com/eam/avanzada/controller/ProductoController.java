package co.com.eam.avanzada.controller;

import java.sql.Date;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.avanzada.domain.Producto;
import co.com.eam.avanzada.repository.IProductoRepository;


@Controller
public class ProductoController {

    private final IProductoRepository productoRepository;
    
///////////////// Producto Quemado ///////////////////
    
    Producto producto1 = new Producto(null, //id
    		"Este es el ejemplo agregar", //descripción
    		"dirección de ejemplo", //dirección
    		true, //estado
    		null, //fecha  (Date) Calendar.getInstance().getTime()
    		"7894563", //telefono de contacto
    		120000, //precio
    		"Título de Agregar", //título
    		null, //imágenes
    		null, //municipio
    		null, //usuario
    		null); //subcategoría

    @Autowired
    public ProductoController(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    @GetMapping("/signupProducto")
    public String showSignUpForm(Producto producto) {
        return "add-producto";
    }
    
    
///////////////// Método por consola ///////////////////   
    @GetMapping("/publicar-producto")
    public String meterProducto() {
    	productoRepository.save(producto1);
        return "ejemplo";
    }
    
    @GetMapping("/addproducto")
    public String addProducto(@Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-producto";
        }
        
        productoRepository.save(producto);
        model.addAttribute("productos", productoRepository.findAll());
        return "lista-producto";
    }
    
    @GetMapping("/editP/{idProducto}")
    public String showUpdateForm(@PathVariable("idProducto") Integer idProducto, Model model) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalido producto idProducto:" + idProducto));
        model.addAttribute("producto", producto);
        return "ejemplo";
    }
    
///////////////// Método por consola ///////////////////
    @GetMapping("/buscar-publicacion/{idProducto}")
	public String buscarProducto(@PathVariable("idProducto") Integer idProducto) {
		Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalido Producto idProducto:" + idProducto));
		String mensaje = "Título: "+producto.getTitulo().toString()
				+" -  Descripción: "+producto.getDescripcion().toString()
				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
				+" -  Apellido: "+producto.getUsuario().getApellido().toString()
				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
				+" -  Dirección: "+producto.getDireccion().toString();
		System.out.println(mensaje);
		return "ejemplo";
	}
    
    @PostMapping("/updateP/{idProducto}")
    public String updateProducto(@PathVariable("idProducto") Integer idProducto, @Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            producto.setIdProducto(idProducto);
            return "update-producto";
        }
        
        productoRepository.save(producto);
        model.addAttribute("productos", productoRepository.findAll());
        return "lista-producto";
    }
    
///////////////// Método por consola ///////////////////   
    @GetMapping("/modificar-publicacion")
    public String modificarProducto() {
    	productoRepository.save(producto1);
        return "ejemplo";
    }
    
    @GetMapping("/deleteP/{idProducto}")
    public String deleteProducto(@PathVariable("idProducto") Integer idProducto, Model model) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalido Producto idProducto:" + idProducto));
        productoRepository.delete(producto);
        model.addAttribute("users", productoRepository.findAll());
        return "lista-producto";
    }
    
///////////////// Método por consola ///////////////////
    @GetMapping("/eliminar-publicacion/{idProducto}")
    public String eliminarProducto(@PathVariable("idProducto") Integer idProducto) {
    	Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalido Producto idProducto:" + idProducto));
        productoRepository.delete(producto);
        return "ejemplo";
    }
    
///////////////// Método por consola ///////////////////
    
    @GetMapping("/todos-los-productos")
    public String traerTodos() {
    	Iterable<Producto> lista = productoRepository.findAll();
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    @GetMapping("/productos-disponibles")
    public String traerSoloDisponibles() {
    	Iterable<Producto> lista = productoRepository.cargarProductosActivos();
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    @GetMapping("/detalle-publicacion/{id}")
    public String  detallePublicacion(@PathVariable("id") int busqueda) {
    	Producto publicacion = productoRepository.mostrarProductoSeleccionado(busqueda);
    	String mensaje = "\nTítulo: "+publicacion.getTitulo().toString()
				+"\n\n -  Descripción: "+publicacion.getDescripcion().toString()
				+"\n -  Nombre: "+publicacion.getUsuario().getNombre().toString()
				+"\n -  Apellido: "+publicacion.getUsuario().getApellido().toString()
				+"\n -  Categoría: "+publicacion.getSubcategoria().getCategoria().getNombre().toString()
				+"\n -  SubCategoría: "+publicacion.getSubcategoria().getNombre().toString()
				+"\n -  Dirección: "+publicacion.getDireccion().toString()
				+"\n -  Ciudad: "+publicacion.getMunicipio().getNombre().toString()
				+"\n -  Departamento: "+publicacion.getMunicipio().getDepartamento().getNombre().toString();
		System.out.println(mensaje);
    	return "ver-publicacion";
    }
    
    @GetMapping("/busqueda-titulo/{busqueda}")
    public String filtrarTitulo(@PathVariable("busqueda") String busqueda) {
    	Iterable<Producto> lista = productoRepository.mostrarProductoBusquedaTitulo(busqueda);
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    @GetMapping("/busqueda-subcategoria/{busqueda}")
    public String filtrarSubcategoria(@PathVariable("busqueda") String busqueda) {
    	Iterable<Producto> lista = productoRepository.mostrarProductoFiltroSubacategoria(busqueda);
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    @GetMapping("/busqueda-categoria/{busqueda}")
    public String filtrarCategoria(@PathVariable("busqueda") String busqueda) {
    	Iterable<Producto> lista = productoRepository.mostrarProductoFiltroCategoria(busqueda);
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    @GetMapping("/busqueda-departamento/{busqueda}")
    public String filtrarDepartamento(@PathVariable("busqueda") String busqueda) {
    	Iterable<Producto> lista = productoRepository.mostrarProductoFiltroDepartamento(busqueda);
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    

    
    
    
    @GetMapping("/busqueda-precioDES")
    public String filtrarPrecioDesc() {
    	
    	Iterable<Producto> lista = productoRepository.mostrarProductoFiltroGeneralPrecioDescendente();
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Precio: "+producto.getPrecio()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    @GetMapping("/busqueda-precioASC")
    public String filtrarPrecioAsc() {
    	Iterable<Producto> lista = productoRepository.mostrarProductoFiltroGeneralPrecioAscendente();
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Precio: "+producto.getPrecio()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
    
    
    
    
    @GetMapping("/busqueda-palabra-clave/{busqueda}")
    public String filtrarPalabraClave(@PathVariable("busqueda") String busqueda) {
    	Iterable<Producto> lista = productoRepository.mostrarProductoPalabraClave(busqueda);
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
       
    
    @GetMapping("/mis-publicaciones/{dni}")
    public String filtrarPorMiId(@PathVariable("dni") String dni) {
    	Iterable<Producto> lista = productoRepository.misPublicaciones(dni);
    	for (Producto producto : lista) {

    		String mensaje = "Título: "+producto.getTitulo().toString()
    				+" -  Descripción: "+producto.getDescripcion().toString()
    				+" -  Categoría: "+producto.getSubcategoria().getCategoria().getNombre().toString()
    				+" -  SubCategoría: "+producto.getSubcategoria().getNombre().toString()
    				+" -  Nombre: "+producto.getUsuario().getNombre().toString()
    				+" -  Apellido: "+producto.getUsuario().getApellido().toString();
    		
    		System.out.println(mensaje);
		}
    	return "lista-productos";
    }
    
}