package modelos;

import java.util.List;

public class Ciudad implements IEntidadDatos<Ciudad> {
	private int id;
	private Provincia provincia;
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Ciudad(int id, Provincia provincia, String nombre) {
		super();
		this.id = id;
		this.provincia = provincia;
		this.nombre = nombre;
	}
	
	public Ciudad() {
		super();
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
	public Ciudad buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Ciudad> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}
