package pruebas;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.junit.Test;

import modelos.Producto;
import principal.RobotFingers;
import principal.Utilidades;
import vistas.AgregarProductoDialog;

public class AgregarProductoDialogTest {
	
	final String codigo = "QWE123";
	
	private int velocity = 4;
	private int lag = 500;
	
	Producto producto;
	AgregarProductoDialog frmAgregarProducto;
	List<Component> componentesAgregarProductoDialog;
	
	//Componentes de AgregarProductoDialog//
	Component tablaAgrProct;
	Component btnAgregar_frmAgergarProducto;
	Component btnCerrar;
	Component cbbxMetodoBusqueda;
	Component txtBusqueda;
	Component btnFiltrar;
	Component tabla;
	
	public AgregarProductoDialogTest(){
		producto = new Producto(0, codigo, "Producto de prueba para ventaFrame", 10, 0f, 18f, 1f);
		frmAgregarProducto = new AgregarProductoDialog(new JTable());
		componentesAgregarProductoDialog = new ArrayList<Component>();
		
		Utilidades.getAllComponents(frmAgregarProducto, componentesAgregarProductoDialog);
		this.tablaAgrProct = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("tabla"));
		this.btnAgregar_frmAgergarProducto = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnAgregar"));
		this.btnCerrar = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnCerrar"));
		this.cbbxMetodoBusqueda = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("cbbxMetodoBusqueda"));
		this.txtBusqueda = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("txtBusqueda"));		
		this.btnFiltrar = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("btnFiltrar"));
		this.tabla = Utilidades.buscarElemento(componentesAgregarProductoDialog, c -> c.getName() != null && c.getName().equals("tabla"));
	}
	
	@Test
	public void testFiltrar() {
		producto.insertar();
		
		frmAgregarProducto.setVisible(true);
		
		RobotFingers rob = new RobotFingers();
		
		//Clic en el cbb//
		rob.moveToComponentCenterAnimated(cbbxMetodoBusqueda, velocity);
		rob.delay(lag);
		rob.leftClickComponentCenter(cbbxMetodoBusqueda);
		rob.delay(lag);
		
		//Clic en el elemento "Codigo" del cbb//
		Point location = cbbxMetodoBusqueda.getLocationOnScreen();
		Point elementoCodigo = new Point(((int)location.getX() + 23), ((int)location.getY() + 65));
		rob.moveToAnimated(elementoCodigo, velocity);
		rob.delay(lag);
		rob.leftClick(elementoCodigo);
		rob.delay(lag);
		
		//Clic y escribir en el elemento txtBusqueda//
		rob.moveToComponentCenterAnimated(txtBusqueda, velocity);
		rob.delay(lag);
		rob.leftClickComponentCenter(txtBusqueda);
		rob.writeString(codigo);
		rob.delay(lag);
		
		//Clic en btnFiltrar//
		rob.moveToComponentCenterAnimated(btnFiltrar, velocity);
		rob.delay(velocity);
		rob.leftClickComponentCenter(btnFiltrar);
		rob.delay(lag);
		
		//Verificar si mostro el producto de prueba en la tabla//
		int rowCount = ((JTable)tabla).getRowCount();
		boolean existeCodigo = false;
		for(int i = 0; i<rowCount; i++){
				if(((JTable)tabla).getValueAt(i, 1).equals(codigo))
					existeCodigo = true;
				else
					existeCodigo = false;
		}
		//Si existe el producto del codigo, entonces si filtro//
		assertTrue(existeCodigo);
		
		frmAgregarProducto.dispose();
		
		//Para borrar el producto de prueba//
		producto.setId(new Producto().listar("WHERE codigo = '" + codigo + "'").get(0).getId());
		producto.eliminar();
	}

}
