package pruebas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ArticuloTest.class, CiudadTest.class, ClienteTest.class, CompraTest.class, DevolucionCompraTest.class,
		DevolucionVentaTest.class, NotaCreditoTest.class, NotaDebitoTest.class, PaisTest.class, ProductoTest.class,
		ProvinciaTest.class, SuplidorTest.class, TiendaTest.class, UsuarioTest.class, VentaTest.class })
public class ModelsSuite {

}
