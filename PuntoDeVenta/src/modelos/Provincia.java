package modelos;

import java.util.List;

public class Provincia implements IEntidadDatos<Provincia> {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private Pais pais;
	private String nombre;
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
	public Provincia buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Provincia> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}
}
