package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import principal.Utilidades;

public class Usuario implements IEntidadDatos<Usuario> {
	
	private int id;
	private String nombreUsuario;
	private String hashClave;
	private String salesClave;
	private String nombreCompleto;
	private TipoUsuario tipo;
	private Tienda tienda; 
	
	public Usuario(int id, String nombreUsuario, String hashClave, String salesClave, String nombreCompleto,
			TipoUsuario tipo, Tienda tienda) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.hashClave = hashClave;
		this.salesClave = salesClave;
		this.nombreCompleto = nombreCompleto;
		this.tipo = tipo;
		this.tienda = tienda;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getHashClave() {
		return hashClave;
	}

	public void setHashClave(String hashClave) {
		this.hashClave = hashClave;
	}

	public String getSalesClave() {
		return salesClave;
	}

	public void setSalesClave(String salesClave) {
		this.salesClave = salesClave;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	@Override
	public boolean insertar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_nombreUsuario", nombreUsuario);
		temp.put("_hashClave", hashClave);
		temp.put("_salesClave", salesClave);
		temp.put("_nombreCompleto", nombreCompleto);
		temp.put("_tipo", tipo);
		temp.put("_idTienda", tienda); 
		  
		try (Connection gate = Utilidades.newConnection();) {
			  return Utilidades.ejecutarCall("CALL AgregarUsuario(?,?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		  
		return false;
	}

	@Override
	public boolean actualizar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("idUsuario", id);
		temp.put("_nombreUsuario", nombreUsuario);
		temp.put("_hashClave", hashClave);
		temp.put("_salesClave", salesClave);
		temp.put("_nombreCompleto", nombreCompleto);
		temp.put("_tipo", tipo);
		temp.put("_idTienda", tienda); 
		  
		try (Connection gate = Utilidades.newConnection();) {
			  return Utilidades.ejecutarCall("CALL ModificarUsuario(?,?,?,?,?,?,?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean eliminar() {
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("_idUsuario", id);
		
		try (Connection gate = Utilidades.newConnection();) {
			return Utilidades.ejecutarCall("CALL EliminarUsuario(?)", temp, gate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Usuario buscar(int id) {
		List<Usuario> usuarios = listar(String.format("WHERE idusuario=%s", id));
		
		if(usuarios.size() > 0)
			return usuarios.get(0);
		return null;
	}

	@Override
	public List<Usuario> listar(String textoBusqueda) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ResultSet datos = Utilidades.ejecutarQuery("SELECT idusuario, nombreusuario, hashClave, salesClave, nombreCompleto, tipo, idTienda  FROM usuarios " + textoBusqueda, state);
			
			StringBuilder tiposSb = new StringBuilder ("(");
			StringBuilder tiendasSb = new StringBuilder("(");
			while(datos.next()){
				usuarios.add(new Usuario(datos.getInt("idusuario"), datos.getString("nombreusuario"), datos.getString("hashClave"), datos.getString("salesClave"), datos.getString("nombreCompleto"), tipo.values()[datos.getInt("tipo")], new Tienda(datos.getInt("idTienda"), null, null, null, null)));
				tiposSb.append(String.format("%s,", datos.getInt("tipo")));
				tiendasSb.append(String.format("%s,", datos.getInt("idTienda")));
			}
			
			if(tiposSb.charAt(tiposSb.length()-1) == ',')
				tiposSb.setCharAt(tiposSb.length()-1, ')');
			else
				tiposSb.append("0)");
			
			if(tiendasSb.charAt(tiendasSb.length()-1) == ',')
				tiendasSb.setCharAt(tiendasSb.length()-1, ')');
			else
				tiendasSb.append("0)");
			
			TipoUsuario[] tipos = tipo.values();
			
			List<Tienda> tiendas = new Tienda().listar(String.format("WHERE idtienda IN %s", tiendasSb.toString()));
			
			for(Usuario usuario : usuarios) {
				for(int  i = 0; i < 4; i++){
					if (usuario.getTipo() == tipos[i])
						usuario.setTipo(tipos[i]);
				}
				
				tiendas.forEach(t -> {
					if(usuario.getTienda().getId() == t.getId())
						usuario.setTienda(t);
				});
			}
			
			return usuarios;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return usuarios;
		}
	}

	public Usuario() {
		super();
	}
	
	public boolean iniciarSesion() {
		return true;
	}
	
	public boolean cerrarSesion() {
		return true;
	}
	
	public boolean estaAutorizado() {
		return true;
	}

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
