package modelos;

import java.util.List;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
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
}
