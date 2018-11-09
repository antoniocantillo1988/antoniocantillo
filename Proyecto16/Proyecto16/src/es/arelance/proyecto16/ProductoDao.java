package es.arelance.proyecto16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;



public class ProductoDao {

	SortedSet <Producto> conjunto = new TreeSet <Producto>();
	
	
	public void insertar (Producto p) throws SQLException, ClassNotFoundException{
		//CONECTAMOS A LA BASE DE DATOS
				Class.forName("com.mysql.jdbc.Driver");
			
				//Conectar con la base de datos
				Connection conn = (Connection) DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1/inventario", "root", "root");
				
				    
				    System.out.println("Conectado!");
				//ABRIR STATEMENT
				    PreparedStatement st = conn.prepareStatement("Insert into productos (nombre, cantidad, precio, comentario, fecha) Values(?, ?, ?, ?, ?)");
				    st.setString(1, p.getNombre());			    
				    st.setInt(2, p.getCantidad());
				    st.setInt(3, p.getPrecio());
				    st.setString(4, p.getComentario());
				    st.setDate(5, new java.sql.Date(p.getFecha().getTime()));
				    
				    //EJECUTA PREPARED STATEMENTE
				    st.executeUpdate();
				    
				//TRANSACCIONES    
//				   conn.setAutoCommit(false);
//				   conn.commit();
//				   conn.rollback();
			    
				    				
		
				//CERRAMOS T O D O    
				
				st.close();
				conn.close();
			}//FIN INSERTAR
		
	
	public void listar (String busqueda) throws SQLException, ClassNotFoundException{
				
		
		//CONECTAMOS A LA BASE DE DATOS
		Class.forName("com.mysql.jdbc.Driver");
	
		//Conectar con la base de datos
		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1/inventario", "root", "root");
		
		    
		    System.out.println("Conectado!");
		    //ABRIR STATEMENT
			//ABRIR STATEMENT
		    Statement st = conn.createStatement();
		    
		    ResultSet rs = st.executeQuery("select * from productos Where comentario like '%"+busqueda+"%'");
			
			while (rs.next()) {
				
				conjunto.add(new Producto(rs.getInt("id"),
				rs.getString("nombre"),
				rs.getInt("cantidad"),
				rs.getInt("precio"),
				rs.getString("comentario"),
				rs.getDate("fecha")));
		    
			}
			
		//TRANSACCIONES    
//		   conn.setAutoCommit(false);
//		   conn.commit();
//		   conn.rollback();
	    
		    				

		//CERRAMOS T O D O    
		rs.close();
		st.close();
		conn.close();
}
	
	
	public void listado() {
		
		Iterator <Producto> iterator = conjunto.iterator();
		
		
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}//FIN LISTADo	

	
	
}
