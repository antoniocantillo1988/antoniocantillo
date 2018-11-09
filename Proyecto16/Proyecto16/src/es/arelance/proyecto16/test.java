package es.arelance.proyecto16;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		
		ProductoDao varProductoDao = new ProductoDao();
		
		try {
			Date myDate = new Date();
			new SimpleDateFormat("yyyy-dd-MM").format(myDate);
			
			//INSERTAR PRORUDCTO NUEVO
			Producto varProductoNuevo = new Producto("MANZANA", 2, 32, "Manzana mala barata", myDate);
			varProductoDao.insertar(varProductoNuevo);
			
			
			//LISTAR 
			varProductoDao.listar("ric");
			
			//MOSTRAMOS EL SORTEDSET CON UN ITERATOR
			varProductoDao.listado();
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Fallo de conexión");
			e.printStackTrace();
		}

	}

}
