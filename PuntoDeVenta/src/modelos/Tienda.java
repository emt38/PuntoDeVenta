package modelos;

import java.util.List;

public class Tienda implements IEntidadDatos<Tienda> {
	
	private int id;
	private String nombre;
	private String direccion;
	private String slogan;
	private String ciudad;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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
	public Tienda buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tienda> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda(int id, String nombre, String direccion, String slogan, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.slogan = slogan;
		this.ciudad = ciudad;
	}

	public Tienda() {
		super();
	}

}
