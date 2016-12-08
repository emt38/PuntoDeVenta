package modelos;

import java.util.List;

public class Suplidor extends Persona implements IEntidadDatos<Suplidor> {
	
	private int id;
	
	public Suplidor() {
		super();
	}

	public Suplidor(int id, String rnc, String empresa) {
		super();
		this.id = id;
		this.rnc = rnc;
		this.empresa = empresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	private String rnc;
	private String empresa;
	
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
	public Suplidor buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Suplidor> listar(String textoBusqueda) {
		// TODO Auto-generated method stub
		return null;
	}

}
