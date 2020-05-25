package co.com.eam.avanzada.repository;


import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.avanzada.domain.Producto;

@Repository
 
public interface IProductoRepository extends CrudRepository<Producto, Integer> {

	//----------Consulta para traer los productos a mostrar -----------

		@Query("SELECT p FROM Producto p " + 
				" JOIN p.municipio m  JOIN m.departamento d WHERE p.estado = true")
		public Iterable<Producto> cargarProductosActivos();
		
	//--------------------Consulta al abrir la publicacion -----------
		@Query("SELECT p " 
				+"FROM Producto p JOIN p.municipio m JOIN m.departamento d JOIN p.usuario us " 
				+"WHERE p.estado = true and p.idProducto= ?1 ")
		public Producto mostrarProductoSeleccionado(Integer id);

	//-------------------------- buscar por título -------------------
		@Query("SELECT p FROM Producto p " + 
				"JOIN p.municipio m JOIN m.departamento d " + 
				"WHERE p.estado= true AND p.titulo= ?1 ")
		public Iterable<Producto> mostrarProductoBusquedaTitulo(String busqueda);

	//------------------------- filtrar por subcategoría ---------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb "
				+ "WHERE p.estado= true AND sb.nombre= ?1 ")
		public Iterable<Producto> mostrarProductoFiltroSubacategoria(String busqueda);
	
		
	//------------------------- filtrar por categoría ---------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.subcategoria sb "
				+ "JOIN sb.categoria c WHERE p.estado= true AND c.nombre= ?1")
		public Iterable<Producto> mostrarProductoFiltroCategoria(String busqueda);
		
		
		//------------------------- filtrar por departamento ---------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb " 
				+ "WHERE p.estado= true AND d.nombre=?1")
		public Iterable<Producto> mostrarProductoFiltroDepartamento(String busqueda);
/*	
	//------------------------- filtrar por precio ---------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb " 
				+ "WHERE p.estado= true AND p.precio BETWEEN ?1 AND ?2 ")	
		public Iterable<Producto>mostrarProductoFiltroPrecio(Double valor1, Double valor2);
		
	//------------------------- filtrar por precio (Descendente) ---------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb " 
				+ "WHERE p.estado= true AND p.precio BETWEEN ?1 AND ?2 order by p.precio DESC")
		public Iterable<Producto>mostrarProductoFiltroPrecioDescendente(Double valor1, Double valor2);	

	//------------------------- filtrar por precio (Ascendente) ---------
		
		@Query("SELECT p FROM Producto p " 
				+"JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb " 
				+"WHERE p.estado= true AND p.precio BETWEEN ?1 AND ?2 order by p.precio ASC")
		public Iterable<Producto>mostrarProductoFiltroPrecioAscendente(Double valor1, Double valor2);
*/	
		
		
		//------------------------- filtrar por precio (Descendente) ---------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb " 
				+ "WHERE p.estado= true order by p.precio DESC")
		public Iterable<Producto>mostrarProductoFiltroGeneralPrecioDescendente();
		
		
		
		//------------------------- filtrar por precio (Ascendente) ---------
		
		@Query("SELECT p FROM Producto p " 
				+"JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb " 
				+"WHERE p.estado= true  order by p.precio ASC")
		public Iterable<Producto>mostrarProductoFiltroGeneralPrecioAscendente();
		
		
		
		
	//------------------- consultar por palabra clave del producto ---------------			
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb "
				+ "WHERE p.estado = true AND p.titulo LIKE %?1%")
		public Iterable<Producto> mostrarProductoPalabraClave(String busqueda);
		
	//---------consultar por fecha de publicacion ----------------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb "
				+ "WHERE p.estado = true AND p.fechaPublicacion BETWEEN ?1 AND ?2")				
		public Iterable<Producto> mostrarProductoFecha(Date fecha1, Date fecha2) ;
				
		
	//------------------------- Consultar Mis Publicaciones ------------------
		@Query("SELECT p FROM Producto p "
				+ "JOIN p.municipio m JOIN m.departamento d JOIN p.subcategoria sb "
				+ "WHERE p.estado= true and p.usuario.correo = ?1")
		public Iterable<Producto> misPublicaciones(String correo);
				
	
}
