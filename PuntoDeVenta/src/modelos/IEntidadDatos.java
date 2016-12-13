package modelos;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public interface IEntidadDatos<T> {
	boolean insertar();
	boolean actualizar();
	boolean eliminar();
	T buscar(int id);
	default List<T> listar() {
		return listar("");

	}
	
	static <T> Object[][] ListToBidiArray(List<T> lista) {
		return ListToBidiArray(lista, new String[]{});
	}
	
    static <T> Object[][] ListToBidiArray(List<T> lista, String[] camposObviados) {
		if(lista.size() <= 0)
			return new Object[0][0];
		
		T evaluar = lista.get(0);
		Class<?> clase = evaluar.getClass();
		
		Method[] metodos = clase.getMethods();
		List<Method> metodosGet = new ArrayList<Method>();
		
		List<Object> valores;
		List<Object[]> bidi = new ArrayList<Object[]>();
		
		String methodName;
		
		for(Method metodo : metodos) {
			methodName = metodo.getName();
			
			if(methodName.startsWith("get"))
				metodosGet.add(metodo);		
			
		}
		
		List<Method> eval = new ArrayList<Method>(metodosGet);
		
		for(Method metodo : eval) {
			methodName = metodo.getName().substring(3);
			for(String o : camposObviados) {
				if(o.equals(methodName)) {
					metodosGet.remove(metodo);
				}
				
			}
		}
		metodosGet.removeIf(m -> m.getName() == "getClass");
		Object retorno;
		
		for(T elemento : lista) {
			valores = new ArrayList<Object>();
			for(Method metodo : metodosGet) {
				
				try {
					retorno = metodo.invoke(elemento, new Object[0]);
				} catch (Exception e) {
					retorno = null;
				}
				valores.add(retorno);
			}
			bidi.add(valores.toArray());
		}
		
		return bidi.toArray(new Object[0][0]);
	}
	
	List<T> listar(String textoBusqueda);
}
