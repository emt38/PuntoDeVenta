package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import modelos.Articulo;
import modelos.DevolucionVenta;
import modelos.Producto;
import modelos.TipoUsuario;
import modelos.Usuario;
import modelos.Venta;
import principal.Program;
import principal.Utilidades;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingConstants;

public class DevolucionVentaFrame extends JFrame {
	private JTable tblVenta;
	private JTable tblDevolucion;

	private DevolucionVenta devolucion = new DevolucionVenta();
	private JButton btnDevolver;
	private JButton btnProcesarDevolucion;
	private JTextField txtTotal;
	private JTextField txtCantidad;
	private JButton btnRetornar;
	private Usuario usuario;
	
	@Override
	public void setVisible(boolean b) {
		if(usuario.getTipo() == TipoUsuario.Cajero) {
			JOptionPane.showMessageDialog(this, "Usted no está autorizado para continuar.");
			return;
		}
		super.setVisible(b);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		principal.Program.main(new String[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DevolucionVentaFrame frame = new DevolucionVentaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void recargarTablas() {
		String[] columnas = new String[] { "cantidad", "producto", "valor", "subtotal" };
		
		Object[][] articulosVenta = Utilidades.listToBidiArray(devolucion.getVenta().getArticulos(), columnas);
		Object[][] articulosDevolucion = Utilidades.listToBidiArray(devolucion.getArticulos(), columnas);
		
		tblVenta.setModel(new DefaultTableModel(
			articulosVenta,
			new String[] {
				"Cantidad", "Producto", "Valor", "SubTotal"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblVenta.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblVenta.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblVenta.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblVenta.getColumnModel().getColumn(3).setPreferredWidth(107);
		
		tblDevolucion.setModel(new DefaultTableModel(
				articulosDevolucion,
				new String[] {
					"Cantidad", "Producto", "Valor", "SubTotal"
				}
			) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		
		tblDevolucion.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblDevolucion.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblDevolucion.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblDevolucion.getColumnModel().getColumn(3).setPreferredWidth(107);
		
		txtTotal.setText(String.format("%.2f", devolucion.calcularTotal()));
		btnProcesarDevolucion.setEnabled(devolucion.getArticulos().size() > 0);
	}
	
	/**
	 * Create the frame.
	 */
	public DevolucionVentaFrame() {
		usuario = Program.getLoggedUser();
		setTitle("Devoluci\u00F3n de Ventas");
		setBounds(100, 100, 579, 513);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("#Venta");
		lblNewLabel.setBounds(21, 32, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JTextField txtVenta = new JTextField();
		txtVenta.setBounds(73, 25, 81, 28);
		txtVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtVenta);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = txtVenta.getText();
				if(txt.length() <= 0) {
					JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "Por favor introduzca un número de venta.");
					return;
				}
					
				
				int no = Integer.parseInt(txt);
				
				Venta venta = new Venta().buscar(no);
				
				if(venta != null) {
					Date now = new Date();
					long tiempoTranscurrido = now.getTime() - venta.getFecha().getTime();
					long days = TimeUnit.MILLISECONDS.toDays(tiempoTranscurrido);
					
					if(days >= 90) {
						JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "La venta especificada excede los 90 días de realizada y no puede ser devuelta.");
					} else {
						devolucion.setVenta(venta);
						btnCargar.setEnabled(false);
						txtVenta.setEditable(false);
						recargarTablas();
					}
				} else {
					JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "No se encontró la venta especificada.");
					txtVenta.setText("");
				}
			}
		});
		btnCargar.setBounds(157, 25, 66, 28);
		getContentPane().add(btnCargar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 64, 532, 129);
		getContentPane().add(scrollPane);
		
		tblVenta = new JTable();
		tblVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnDevolver.setEnabled(tblVenta.getSelectedRow() != -1);
			}
		});
		tblVenta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Producto", "Valor", "SubTotal"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblVenta.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblVenta.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblVenta.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblVenta.getColumnModel().getColumn(3).setPreferredWidth(107);
		scrollPane.setViewportView(tblVenta);
		
		btnDevolver = new JButton("Devolver Art\u00EDculo \u2193");
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Articulo ref = devolucion.getVenta().getArticulos().get(tblVenta.getSelectedRow());
				Articulo temp = new Articulo(ref.getProducto(), ref.getCantidad(), ref.getValor(), ref.getTasaImpuestos(), ref.getImpuestos(), ref.getSubTotal());
				
				if(!devolucion.devolverArticulo(temp, Float.parseFloat(txtCantidad.getText()))) {
					JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "No puede agregar un artículo dos veces (puede retirarlo y agregarlo con cantidades diferentes).\n La cantidad del artículo a devolver no debe exceder a la de la venta.", "Mensaje Devolución", JOptionPane.WARNING_MESSAGE);
					return;
				}
				recargarTablas();
			}
		});
		btnDevolver.setEnabled(false);
		btnDevolver.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnDevolver.setBounds(21, 198, 347, 36);
		getContentPane().add(btnDevolver);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 276, 532, 129);
		getContentPane().add(scrollPane_1);
		
		tblDevolucion = new JTable();
		tblDevolucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnRetornar.setEnabled(tblDevolucion.getSelectedRow() != -1);
			}
		});
		tblDevolucion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 13 && tblDevolucion.getSelectedRow() != -1) {
					devolucion.getArticulos().remove(tblDevolucion.getSelectedRow());
					recargarTablas();
				}
			}
		});
		tblDevolucion.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cantidad", "Producto", "Valor", "SubTotal"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblDevolucion.getColumnModel().getColumn(0).setPreferredWidth(97);
		tblDevolucion.getColumnModel().getColumn(1).setPreferredWidth(311);
		tblDevolucion.getColumnModel().getColumn(2).setPreferredWidth(103);
		tblDevolucion.getColumnModel().getColumn(3).setPreferredWidth(107);
		scrollPane.setViewportView(tblVenta);
		scrollPane_1.setViewportView(tblDevolucion);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
					e.consume();
				}
			}
		});
		txtCantidad.setText("1");
		txtCantidad.setBounds(447, 203, 106, 28);
		getContentPane().add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(380, 209, 55, 16);
		getContentPane().add(lblCantidad);
		
		btnProcesarDevolucion = new JButton("Procesar Devoluci\u00F3n");
		btnProcesarDevolucion.setEnabled(false);
		btnProcesarDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(DevolucionVentaFrame.this,"Por favor confirme que desea realizar la devolución.", "Realizar Devolución", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					devolucion.setSupervisor(usuario);
					devolucion.efectuar();
					JOptionPane.showMessageDialog(DevolucionVentaFrame.this, "La devolución se ha realizado exitosamente.");
					DevolucionVentaFrame.this.dispose();
				}
			}
		});
		btnProcesarDevolucion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnProcesarDevolucion.setBounds(21, 417, 532, 36);
		getContentPane().add(btnProcesarDevolucion);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		txtTotal.setText("0.00");
		txtTotal.setBounds(380, 25, 173, 28);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblMontoDevuelto = new JLabel("Monto Devuelto");
		lblMontoDevuelto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMontoDevuelto.setBounds(278, 31, 98, 16);
		getContentPane().add(lblMontoDevuelto);
		
		btnRetornar = new JButton("Retornar Art\u00EDculo \u2191");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblDevolucion.getSelectedRow() != -1) {
					devolucion.getArticulos().remove(tblDevolucion.getSelectedRow());
					recargarTablas();
				}
			}
		});
		btnRetornar.setEnabled(false);
		btnRetornar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnRetornar.setBounds(21, 235, 347, 36);
		getContentPane().add(btnRetornar);

	}
}
