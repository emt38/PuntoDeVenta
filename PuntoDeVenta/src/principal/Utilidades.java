package principal;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.Crypt;

import modelos.Ciudad;
import modelos.Pais;
import modelos.Provincia;

public final class Utilidades {
	private Utilidades() {
		// Esta clase es estática
	}
	
	// Métodos Para Hashing
	
	public static String generarSales() {
		byte[] salt = new byte[13];
		Random random = new SecureRandom();
		random.nextBytes(salt);
		return "$5$f" + new String(salt, StandardCharsets.UTF_8);
	}
	
	public static boolean verificarHash(String clear, String hashed, String sales) {
		return MessageDigest.isEqual( // constant time comparison
		      hashed.getBytes( StandardCharsets.UTF_8 ),
		      Crypt.crypt( clear.getBytes( StandardCharsets.UTF_8 ), hashed ).getBytes( StandardCharsets.UTF_8 )
		  );
	}
	
	public static String generarHash(String dato, String sales) {
		return org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(dato.getBytes(), sales);
	}
	
	// Fin Hashing
	
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
	
	public static <T> Object[][] listToBidiArray(List<T> lista, String[] camposSeleccionados) {
		if(lista.size() <= 0)
			return new Object[0][0];
		
		T evaluar = lista.get(0);
		Class<?> clase = evaluar.getClass();
		
		Method[] metodos = clase.getMethods();
		List<Method> metodosGet = new ArrayList<>();
		
		List<Object> valores;
		List<Object[]> bidi = new ArrayList<>();
		
		String methodName;
		
		for(String c : camposSeleccionados) {
			for(Method metodo : metodos) {
				methodName = metodo.getName();
				
				if(methodName.startsWith("get") && methodName.substring(3).toLowerCase().equals(c.toLowerCase()))
					metodosGet.add(metodo);
			}
		}

		metodosGet.removeIf(m -> m.getName().equals("getClass"));
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
	
	public static boolean loadUbicaciones(List<Pais> paises, List<Provincia> provincias, List<Ciudad> ciudades) {
		try {

			ciudades.addAll(new Ciudad().listar());
			
			ciudades.forEach(c -> {
				if(!provincias.contains(c.getProvincia())) {
					provincias.add(c.getProvincia());
				}
			});
			
			provincias.forEach(c -> {
				if(!paises.contains(c.getPais())) {
					paises.add(c.getPais());
				}
			});
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static List<Provincia> provinciasDePais(Pais pais, List<Provincia> provincias) {
		List<Provincia> provinciasDelPais = new ArrayList<>();
		try {
			provincias.forEach(p -> {
				if(p.getPais().getId() == pais.getId())
					provinciasDelPais.add(p);
			});
			
			return provinciasDelPais;
		} catch (Exception e) {
			return provinciasDelPais;
		}
	}
	
	public static List<Ciudad> ciudadesDeProvincia(Provincia provincia, List<Ciudad> ciudades) {
		List<Ciudad> ciudadesDeLaProvincia = new ArrayList<>();
		try {
			ciudades.forEach(c -> {
				if(c.getProvincia().getId() == provincia.getId())
					ciudadesDeLaProvincia.add(c);
			});
			
			return ciudadesDeLaProvincia;
		} catch (Exception e) {
			return ciudadesDeLaProvincia;
		}
	}
}
