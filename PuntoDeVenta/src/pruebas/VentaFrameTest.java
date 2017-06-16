package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.Test;

import java.awt.Point;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Producto;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.AgregarProductoDialog;
import vistas.VentaFrame;

public class VentaFrameTest {
	
	private int velocity = 4;
	private int lag = 500;
	
	Producto producto;
	VentaFrame frmVenta;
	List<Component> componentesVentaFrame;
	AgregarProductoDialog frmAgregarProducto;
	List<Component> componentesAgregarProductoDialog;
	
	//Componentes de VentaFrame//
	Component btnBuscarArticulos;
	Component tablaVenta;
	Component btnAgregar_frmVenta;
	Component txtAgregar;
	Component btnVaciarArticulos;
	Component txtEfectivoRecibido;
	Component txtTotal;
	Component btnRealizarVenta;
	
	//Componentes de AgregarProductoDialog//
	Component tablaAgrProct;
	Component btnAgregar_frmAgergarProducto;
	Component btnCerrar;
	
	public VentaFrameTest(){
		producto = new Producto(0, "QWE123", "Producto de prueba para ventaFrame", 10, 0f, 18f, 1f);
		frmVenta = new VentaFrame();
		
		componentesVentaFrame = new ArrayList<Component>();
		componentesAgregarProductoDialog = new ArrayList<Component>();
		
		Utilidades.getAllComponents(frmVenta, componentesVentaFrame);
		this.btnBuscarArticulos = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("btnBuscarArticulos"));
		this.tablaVenta = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("tabla"));
		this.btnAgregar_frmVenta = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("btnAgregar"));
		this.txtAgregar = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("txtAgregar"));
		this.btnVaciarArticulos = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("btnVaciarArticulos"));
		this.txtEfectivoRecibido = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("txtEfectivoRecibido"));
		this.txtTotal = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("txtTotal"));
		this.btnRealizarVenta = Utilidades.buscarElemento(componentesVentaFrame, c -> c.getName() != null && c.getName().equals("btnRealizarVenta"));
		
		frmAgregarProducto = frmVenta.getAgregarProductoDialog();
		Utilidades.getAllComponents(frmAgregarProducto, componentesAgregarProductoDialog);
		this.tablaAgrProct = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("tabla"));
		this.btnAgregar_frmAgergarProducto = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnAgregar"));
		this.btnCerrar = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnCerrar"));
	}
	
	@Test
	public void testBuscarArticulos() {
		
		producto.insertar();
		
		RobotFingers rob = new RobotFingers();
		
		frmVenta.setVisible(true);
		
		//Clic a btnBuscarArticulos//
		rob.moveToComponentAnimated(btnBuscarArticulos, velocity);
		rob.leftClickComponent(btnBuscarArticulos); 
		rob.delay(lag);

		
		//Clic a la primera fila de la tabla de AgregarProductoDialog//
		Point location = tablaAgrProct.getLocationOnScreen();
		Point primerafila = new Point(((int)location.getX() + 10), ((int)location.getY() + 5));
		rob.moveToAnimated(primerafila, velocity);
		rob.leftClick(primerafila);
		rob.delay(lag);
		
		//Clic en agregar//
		rob.moveToComponentAnimated(btnAgregar_frmAgergarProducto, velocity);
		rob.leftClickComponent(btnAgregar_frmAgergarProducto);
		rob.delay(lag);
		
		//Clic en cerrar//
		rob.moveToComponentAnimated(btnCerrar, velocity);
		rob.delay(lag);
		rob.leftClickComponent(btnCerrar);
		rob.delay(lag);
		
		//Si la tabla tiene mas de una fila, entonces lo pudo agregar.//
		assertTrue(((JTable)tablaVenta).getModel().getRowCount()>0);
		
		//Para borrar el producto de prueba//
		producto.setId(new Producto().listar("ORDER BY idproducto DESC LIMIT 1").get(0).getId());
		producto.eliminar();
		
		frmVenta.dispose();
	}
	
	@Test
	public void testAgregacionPorCodigo() {
		frmVenta.setVisible(true);
		final String codigo = "COD-858";
		
		RobotFingers rob = new RobotFingers();
		
		rob.moveToComponentAnimated(btnVaciarArticulos, velocity);
		rob.leftClickComponent(btnVaciarArticulos);
		rob.delay(lag);
		rob.moveToComponentAnimated(txtAgregar, velocity);
		rob.leftClickComponent(txtAgregar);
		rob.writeString(codigo);
		rob.delay(lag);
		rob.moveToComponentAnimated(btnAgregar_frmVenta, velocity);
		rob.leftClickComponent(btnAgregar_frmVenta);
		rob.delay(lag*2);
		
		Producto tempProduct = new Producto().listar("Where p.codigo = '" + codigo + "'").get(0);
		
		int rowCount = ((JTable)tablaVenta).getRowCount();
		boolean sonIguales = false;
		for(int i =0; i<rowCount; i++){
				if(((JTable)tablaVenta).getValueAt(i, 1).equals(tempProduct.getDescripcion()))
					sonIguales = true;
				else
					sonIguales = false;
		}
		assertTrue(sonIguales);
		
		frmVenta.dispose();
	}

	@Test
	public void testRealizarCompra() {	
		
		final String codigo = "QWE123";
		
		try{
			Connection gate = Utilidades.newConnection();
			Statement state = gate.createStatement();
			
			//insertar el producto de prueba//
			ejecutarQuery("insert into productos(codigo, descripcion, costo, precio, tasaImpuesto) values('" + producto.getCodigo() + "', '" + producto.getDescripcion() + "', " + producto.getCosto() + ", " + producto.getPrecio() + ", " + producto.getTasaImpuesto() + ");", state);
			
			
			frmVenta.setVisible(true);
			
			RobotFingers rob = new RobotFingers();
			
			//Clicl en vaciar articulos//
			rob.moveToComponentAnimated(btnVaciarArticulos, velocity);
			rob.leftClickComponent(btnVaciarArticulos);
			rob.delay(lag);
			rob.moveToComponentAnimated(txtAgregar, velocity);
			rob.leftClickComponent(txtAgregar);
			rob.writeString(codigo);
			rob.delay(lag);
			rob.moveToComponentAnimated(btnAgregar_frmVenta, velocity);
			rob.leftClickComponent(btnAgregar_frmVenta);
			rob.delay(lag*2);
			
			//Para aumentar +1 el precio del producto para poder pagarlo//
			float efectivorecibido = Float.parseFloat(((JTextField)txtTotal).getText().replaceAll(",", "."));
			efectivorecibido++;
			
			//Clic y escribir en el campo Efectivo recibido//
			rob.moveToComponentAnimated(txtEfectivoRecibido, velocity);
			rob.leftClickComponent(txtEfectivoRecibido);
			rob.delay(lag);
			rob.writeString(Float.toString(efectivorecibido));
			rob.writeString("\n");
			
			//Clic en Realizar venta//
			rob.moveToComponentAnimated(btnRealizarVenta, velocity);
			rob.leftClickComponent(btnRealizarVenta);
			rob.delay(lag);
			
			//Clic en Si//
			Point location = frmVenta.getLocationOnScreen();
			Point btnYesPosition = new Point(((int)location.getX()+300), ((int)location.getY()+300));
			rob.moveToAnimated(btnYesPosition, velocity);
			rob.leftClick(btnYesPosition);
			rob.delay(lag);
			
			//Clic en Acceptar//
			int ancho = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
			int alto = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) + 30;
			rob.moveTo(ancho, alto);
			rob.delay(lag);
			rob.leftClick(ancho, alto);

			//Verificar si existe una venta realizada con el producto de prueba//
			ResultSet resultado = Utilidades.ejecutarQuery("SELECT COUNT(*) AS numFilas FROM (SELECT * FROM ventasdetalle WHERE idventa = (SELECT idventa FROM posdb.ventasencabezado ORDER BY fecha DESC LIMIT 1) AND idproducto = (select idproducto FROM productos WHERE codigo = 'QWE123' LIMIT 1)) AS venta;", state);
			resultado.first();
			int numeroDeFilas = resultado.getInt("numFilas");
			
			//Si existe al menos una fila con el producto de prueba, entonces si se realizo la venta//
			assertTrue(numeroDeFilas>0);
		}
		catch(SQLException e){
			
		}
		
		//Para borrar el producto de prueba//
		producto.setId(new Producto().listar("WHERE codigo = '" + codigo + "'").get(0).getId());
		producto.eliminar();
		
		frmVenta.dispose();
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
}

