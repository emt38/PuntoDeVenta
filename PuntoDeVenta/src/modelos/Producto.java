package modelos;

import java.util.ArrayList;
import java.util.List;

public class Producto implements IEntidadDatos<Producto> {
	//test Commit  to server
	private int id;
	private String codigo;
	private String descripcion;
	private float precio;
	private float costo;
	private float tasaImpuesto;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getTasaImpuesto() {
		return tasaImpuesto;
	}

	public void setTasaImpuesto(float tasaImpuesto) {
		this.tasaImpuesto = tasaImpuesto;
	}

	@Override
	public boolean insertar() {
		return true;
	}

	@Override
	public boolean actualizar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean eliminar() {
		// TODO Auto-generated method stub
		return true;
	}

	public Producto(int id, String codigo, String descripcion, float precio, float costo, float tasaImpuesto) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.costo = costo;
		this.tasaImpuesto = tasaImpuesto;
	}
	
	public Producto() {
		super();
	}

	@Override
	public Producto buscar(int id) {
		return 	listar().get(id-1);
	}

	@Override
	public List<Producto> listar(String textoBusqueda) {
		ArrayList<Producto> productos = new ArrayList<>();
		productos.add(new Producto(1, "05905935303", "Berenjena", 50.0f, 25.0f, 18f));
		productos.add(new Producto(2, "05905935304", "Pan", 100.0f, 50f, 18f));
		productos.add(new Producto(3, "05905935305", "Bistec", 200.0f, 100f, 18f));
		productos.add(new Producto(4, "05905935306", "Naranja", 250.0f, 125f, 18f));
		productos.add(new Producto(5, "05905935307", "Riki Taqui", 300.0f, 150f, 18f));
		productos.add(new Producto(6, "05905935308", "Batata", 6.5f, 4.5f, 18f));
		productos.add(new Producto(7, "05905935309", "Patata", 5.5f, 4.0f, 18f));
		
		return productos;
	}

	@Override
	public List<Cliente> getAllTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
