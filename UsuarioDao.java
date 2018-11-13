package es.arelance.contenedores.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.hubiqus.inventario.model.Producto;
import es.hubiqus.inventario.model.dao.DaoException;


public class UsuarioDao {

	
private JdbcDataSource dataSource;
	
	public JdbcDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(JdbcDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//CRUD PARA USUARIO
	/**
	 * Listado el usuario 
	 * @param
	 * @return List<Usuario> 
	 * @throws SQLException
	 */
	
	public List<Usuario> listarUsuario() throws DaoException{
		List<Usuario> res = new ArrayList<Usuario>();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try{					
			String sql = "SELECT * FROM usuario ";
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);
			
			rs = statement.executeQuery();
		
			while (rs.next()){
				res.add(mapear(rs));
			}
		}catch (Exception ex){
			throw new DaoException(ex);
		}finally{
			try {
				if (rs != null){				
					rs.close();				
				}
				if (statement != null){				
					statement.close();				
				}
				if (connection != null){				
					connection.close();				
				}
			} catch (SQLException ex) {
				throw new DaoException(ex);
			}
		}
		
		return res;
	}
	
	/**
	 * Modificamos el usuario 
	 * @param usuario
	 * @return 
	 * @throws SQLException
	 */
	public void modificar(Usuario usuario) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			String sql = "UPDATE usuarios SET idusuarios = ?, dni = ?, nombre = ?, apellidos = ?, fechaalta = ?"
					+ "direccion = ?, telefono = ?, privilegios = ?, correoelectronico = ?";  

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);
			
			statement.getInt(1, usuario.getidusuarios);
			statement.setString(2, usuario.getdni());
			statement.setString(3, usuario.getnombre());
			statement.setString(4, usuario.getapellidos());
			statement.setDate(5, new java.sql.Date(usuario.getfechaalta().getTime()));
			statement.setString(6, usuario.getdireccion());
			statement.setInt(7, usuario.gettelefono());
			statement.setInt(8, usuario.getprivilegios);
			statement.setString(9, usuario.getcorreoelectronico);
			
			statement.executeUpdate();
			
		}catch (Exception ex){
			throw new DaoException(ex);
		}finally{
			try {
				if (statement != null){				
					statement.close();				
				}
				if (connection != null){				
					connection.close();				
				}
			} catch (SQLException ex) {
				throw new DaoException(ex);
			}
		}
	}//FIN MODIFICAR USUARIO
	
	/**
	 * Eliminarmos el usuario 
	 * @param id
	 * @return void
	 * @throws SQLException
	 */
	public void eliminar(int id) throws DaoException{
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			String sql = "DELETE FROM usuarios WHERE idusuarios = ?";

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id );
			
			statement.executeUpdate();
			
		}catch (Exception ex){
			throw new DaoException(ex);
		}finally{
			try {
				if (statement != null){				
					statement.close();				
				}
				if (connection != null){				
					connection.close();				
				}
			} catch (SQLException ex) {
				throw new DaoException(ex);
			}
		}
		
	}//FIN ELIMINAR
	
	//EXTENDS
	public void create(Usuario usuario)  throws DaoException{
		Connection connection = null;
		PreparedStatement statement = null;
		try{
			String sql = "INSERT INTO usuarios (dni, nombre, apellidos, fechaalta, direccion"
					+ "telefono, privilegios, correoelectronico) VALUES (?,?,?,?,?,?,?,?)";

			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getdni());
			statement.setString(2, usuario.getnombre());
			statement.setString(3, usuario.getapellidos());
			statement.setDate(4, new java.sql.Date(usuario.getfechaalta().getTime()));
			statement.setString(5, usuario.getdireccion());
			statement.setInt(6, usuario.gettelefono());
			statement.setInt(7, usuario.getprivilegios);
			statement.setString(8, usuario.getcorreoelectronico);
			
			statement.executeUpdate();
			
		}catch (Exception ex){
			throw new DaoException(ex);
		}finally{
			try {
				if (statement != null){				
					statement.close();				
				}
				if (connection != null){				
					connection.close();				
				}
			} catch (SQLException ex) {
				throw new DaoException(ex);
			}
		}
	}//FIN CREATE NEW USUARIO
	
	
	//PARA LOGIN CONSULTAR POR NOMBRE Y TELEFONO
	/**
	 * Obtener el tipo de usuario desde el login
	 * @param rs
	 * @return int 0 -> fallo al logearse; int 1 -> cliente; int 2 -> empleado; int 3 -> administrador
	 * @throws SQLException
	 */
	public int findByUserAndPass() {
		int res=0;
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try{					
			String sql = "SELECT dni, telefono, privilegios FROM usuarios " +
						"WHERE dni LIKE ? AND telefono LIKE ?";
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, usuario.getnombre());
			statement.setInt(2, usuario.gettelefono());
			
			rs = statement.executeQuery();
		
			while (rs.next()){
				res = rs.getInt("privilegios");
			}
		}catch (Exception ex){
			throw new DaoException(ex);
		}finally{
			try {
				if (rs != null){				
					rs.close();				
				}
				if (statement != null){				
					statement.close();				
				}
				if (connection != null){				
					connection.close();				
				}
			} catch (SQLException ex) {
				throw new DaoException(ex);
			}
		}
		
		return res;
	}//FIN findByUserAndPass
	
	
	/**
	 * Obtener un producto desde el ResultSet
	 * @param rs
	 * @return 
	 * @throws SQLException
	 */
	private Usuario mapear(ResultSet rs) throws SQLException {
		Usuario item = new Usuario();
		item.setidusuario(rs.getInt("idusuario"));
		item.setdni(rs.getString("dni"));
		item.setnombre(rs.getString("nombre"));
		item.setapellidos(rs.getString("apellidos"));
		item.setfechaalta(rs.getDate("fechaalta"));
		item.setdireccion(rs.getString("direccion"));
		item.settelefono(rs.getInt("telefono"));
		item.setprivilegios(rs.getInt("privilegios"));
		item.setcorreoelectronico(rs.getString("correoelectronico"));
		
		return item;
	}
	
	
}
