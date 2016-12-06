package modelos;

import java.util.List;

public interface IEntidadDatos<T> {
	boolean insertar();
	boolean actualizar();
	boolean eliminar();
	T buscar(int id);
	List<T> listar();
}
