package principal;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Utilidades {
	private Utilidades() {
		// Esta clase es estática
	}
	
	public static Connection newConnection() {
		try {
			return DriverManager.getConnection(Program.getDBServer(), Program.getDBUser(), Program.getDBPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static ResultSet ejecutarQuery(String query, Statement st) {
		try {
			ResultSet result = st.executeQuery(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean ejecutarCallByIndex(String query, Map<Integer, Object> parametros, Connection conexion) {
		try (CallableStatement st = conexion.prepareCall(query);) {
			parametros.forEach((Integer key, Object value) -> {
				try {
					st.setObject(key, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			st.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ejecutarCallByIndex(CallableStatement st, Map<Integer, Object> parametros) {
		try {
			parametros.forEach((Integer key, Object value) -> {
				try {
					st.setObject(key, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			st.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ejecutarCall(String query, Map<String, Object> parametros, Connection conexion) {
		try (CallableStatement st = conexion.prepareCall(query);) {
			parametros.forEach((String key, Object value) -> {
				try {
					st.setObject(key, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			st.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ejecutarCall(CallableStatement st, Map<String, Object> parametros) {
		try {
			parametros.forEach((String key, Object value) -> {
				try {
					st.setObject(key, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			st.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ejecutarDML(String query) {
		try (Connection conexion = DriverManager.getConnection(Program.getDBServer(), Program.getDBUser(), Program.getDBPassword());
			Statement st = conexion.createStatement();) {
			return st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ejecutarDML(String query, Connection conexion) {
		try (Statement st = conexion.createStatement();) {
			return st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean ejecutarDML(String query, Statement st) {
		try {
			return st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static <T> Object[][] listToBidiArray(List<T> lista) {
		return listToBidiArray(lista, new String[]{});
	}
	
	public static <T> Object[][] listToBidiArray(List<T> lista, String[] camposObviados) {
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
}
