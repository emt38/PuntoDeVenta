package modelos;

import java.util.List;

public class Pais implements IEntidadDatos<Pais> {
	private int id;
	private String nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pais() {
		super();
	}
	public Pais(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public Pais buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Pais> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}
