package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.Test;

import modelos.Producto;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.AgregarProductoDialog;
import vistas.CompraFrame;

public class CompraFrameTest {
	
	final String codigo = "QWE123";
	
	private int velocity = 4;
	private int lag = 500;
	
	Producto producto;
	CompraFrame frmCompra;
	List<Component> componentesCompraFrame;
	AgregarProductoDialog frmAgregarProducto;
	List<Component> componentesAgregarProductoDialog;
	
	//Componentes de VentaFrame//
	Component tablaCompra;
	Component btnBuscarArticulos;
	Component btnRealizarCompra;
	Component btnCambiarDesc;
	Component txtCambiardesc;
	Component txtTotalDescuento;
	
	
	//Componentes de AgregarProductoDialog//
	Component tablaAgrProct;
	Component btnAgregar_frmAgergarProducto;
	Component btnCerrar;
	
	public CompraFrameTest(){
		producto = new Producto(0, codigo, "Producto de prueba para ventaFrame", 10, 0f, 18f, 1f);
		//insertar el producto de prueba. Se insertara cada vez que se llame a un @Test, el constructor siempre se llama cada vez que se llama a un @Test//
		InsertSQL("insert into productos(codigo, descripcion, costo, precio, tasaImpuesto) values('" + producto.getCodigo() + "', '" + producto.getDescripcion() + "', " + producto.getCosto() + ", " + producto.getPrecio() + ", " + producto.getTasaImpuesto() + ");");
		frmCompra = new CompraFrame();
		
		componentesCompraFrame = new ArrayList<Component>();
		componentesAgregarProductoDialog = new ArrayList<Component>();
		
		Utilidades.getAllComponents(frmCompra, componentesCompraFrame);
		this.tablaCompra = Utilidades.buscarElemento(componentesCompraFrame, c -> c.getName() != null && c.getName().equals("tabla"));
		this.btnBuscarArticulos = Utilidades.buscarElemento(componentesCompraFrame, c -> c.getName() != null && c.getName().equals("btnBuscarArticulos"));
		this.btnRealizarCompra = Utilidades.buscarElemento(componentesCompraFrame, c -> c.getName() != null && c.getName().equals("btnRealizarCompra"));
		this.btnCambiarDesc = Utilidades.buscarElemento(componentesCompraFrame, c -> c.getName() != null && c.getName().equals("btnCambiarDesc"));
		this.txtCambiardesc = Utilidades.buscarElemento(componentesCompraFrame, c -> c.getName() != null && c.getName().equals("txtCambiardesc"));
		this.txtTotalDescuento = Utilidades.buscarElemento(componentesCompraFrame, c -> c.getName() != null && c.getName().equals("txtTotalDescuento"));
		
		frmAgregarProducto = frmCompra.getAgregarProductoDialog();
		Utilidades.getAllComponents(frmAgregarProducto, componentesAgregarProductoDialog);
		this.tablaAgrProct = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("tabla"));
		this.btnAgregar_frmAgergarProducto = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnAgregar"));
		this.btnCerrar = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnCerrar"));
	}

	@Test
	public void testCambiarDescuento() {
		frmCompra.setVisible(true);
		
		RobotFingers rob = new RobotFingers();
		
		//Clic y escribir en txtCambiardesc//
		rob.moveToComponentCenterAnimated(txtCambiardesc, velocity);
		rob.delay(lag);
		rob.leftClickComponentCenter(txtCambiardesc);
		rob.writeString("7");
		rob.delay(lag);
		
		//Clilc en btnCambiarDesc//
		rob.moveToComponentCenterAnimated(btnCambiarDesc, velocity);
		rob.delay(lag);
		rob.leftClickComponentCenter(btnCambiarDesc);
		rob.delay(lag);
		
		float cambiarDescuento = Float.parseFloat(((JTextField)txtCambiardesc).getText().replaceAll(",", "."));
		float totalDescuento = Float.parseFloat(((JTextField)txtTotalDescuento).getText().replaceAll(",", "."));
		
		//Si los campos txtCambiardesc y txtTotalDescuento, entonces lo pudo cambiar//
		assertEquals(cambiarDescuento, totalDescuento, 2);
		
		//Para borrar el producto de prueba//
		producto.setId(new Producto().listar("WHERE codigo = '" + codigo + "'").get(0).getId());
		producto.eliminar();
		
		frmCompra.dispose();
	}
	
	@Test
	public void testBuscarArticulos() {
		
		BuscarProducto_Robot();
		
		//Si la tabla tiene mas de una fila, entonces lo pudo agregar.//
		assertTrue(((JTable)tablaCompra).getModel().getRowCount()>0);
		
		//Para borrar el producto de prueba//
		producto.setId(new Producto().listar("WHERE codigo = '" + codigo + "'").get(0).getId());
		producto.eliminar();
				
		frmCompra.dispose();
	}
	

	@Test
	public void testRealizarCompra() {
		
		BuscarProducto_Robot();
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentCenterAnimated(btnRealizarCompra, velocity);
		rob.delay(lag);
		rob.leftClickComponentCenter(btnRealizarCompra);
		rob.delay(lag);
		
		Point location = frmCompra.getLocationOnScreen();
		Point btnYesPosition = new Point(((int)location.getX()+550), ((int)location.getY()+300));
		rob.moveToAnimated(btnYesPosition, velocity);
		rob.delay(lag);
		rob.leftClick(btnYesPosition);
		rob.delay(lag);
		
		int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
		int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
		rob.moveTo(ancho, alto);
		rob.delay(lag*3);
		rob.leftClick(ancho, alto);
		
		//Verificar si existe una compra realizada con el producto de prueba//
		try{
			ResultSet resultado = ConsultaSQL("SELECT COUNT(*) AS numFilas FROM (SELECT * FROM comprasdetalle WHERE idcompra = (SELECT idcompra FROM posdb.comprasencabezado ORDER BY fecha DESC LIMIT 1) AND idproducto = (select idproducto FROM productos WHERE codigo = 'QWE123' LIMIT 1)) AS compra;");
			resultado.first();
			int numeroDeFilas = resultado.getInt("numFilas");
			
			//Si existe al menos una fila con el producto de prueba, entonces si se realizo la compra//
			assertTrue(numeroDeFilas>0);
		}
		catch(SQLException e){
			assertTrue(false);
		}
		
		
		//Para borrar el producto de prueba//
		producto.setId(new Producto().listar("WHERE codigo = '" + codigo + "'").get(0).getId());
		producto.eliminar();	
		
		frmCompra.dispose();
	}

	private void BuscarProducto_Robot() {
			RobotFingers rob = new RobotFingers();
			rob.delay(lag);
			
			frmCompra.setVisible(true);
			
			//Clic a btnBuscarArticulos//
			rob.moveToComponentCenterAnimated(btnBuscarArticulos, velocity);
			rob.delay(lag);
			rob.leftClickComponentCenter(btnBuscarArticulos);
			rob.delay(lag);
			
			//Autoseleccionar el producto de prueba//
			for(int i =0; i<((JTable)tablaAgrProct).getRowCount(); i++){
				String tablaDescripcion = (String)((JTable)tablaAgrProct).getValueAt(i, 1);
				String productoDescripcion = producto.getCodigo();
				if(tablaDescripcion.equals(productoDescripcion)){
					((JTable)tablaAgrProct).setRowSelectionInterval(i,i);
					break;
				}
			}
			rob.delay(lag);
			
			//Clic en agregar//
			rob.moveToComponentCenterAnimated(btnAgregar_frmAgergarProducto, velocity);
			rob.delay(lag);
			rob.leftClickComponentCenter(btnAgregar_frmAgergarProducto);
			rob.delay(lag);
			
			//Clic en cerrar//
			rob.moveToComponentCenterAnimated(btnCerrar, velocity);
			rob.delay(lag);
			rob.leftClickComponentCenter(btnCerrar);
			rob.delay(lag);
	}
	
	public static int ejecutarQuery(String query, Statement st) {
		try {
			int result = st.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void InsertSQL(String consulta){
		try{
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			ejecutarQuery(consulta, state);
		}
		catch(SQLException e){
			
		}
	}
	
	public ResultSet ConsultaSQL(String consulta){
		try{
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			return Utilidades.ejecutarQuery(consulta, state);
		}
		catch(SQLException e){
			return null;
		}
	}
	
}
