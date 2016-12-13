package modelos;

import java.util.List;

public interface IEntidadDatos<T> {
	boolean insertar();
	boolean actualizar();
	boolean eliminar();
	T buscar(int id);
	default List<T> listar() {
		return listar("");

	}
	
	List<T> listar(String textoBusqueda);
}
