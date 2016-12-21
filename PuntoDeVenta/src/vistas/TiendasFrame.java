package vistas;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.WindowFocusListener;
import java.util.List;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Tienda;
import principal.Utilidades;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TiendasFrame extends JFrame{

	private JPanel contentPane;
	private JTable tblTiendas;
	private JButton btnAgregar;
	private JButton btnDetalles;
	private JButton btnEliminar;
	private JButton btnEditar;
	private List<Tienda> tiendas;
	
	public TiendasFrame() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				tiendas = new Tienda().listar();
				
				tblTiendas.setModel(new DefaultTableModel(
						Utilidades.listToBidiArray(tiendas, new String[] { "nombre", "direccion"}),
						new String[] {
							"Tienda", "Direcci\u00F3n"
						}
					) {
						boolean[] columnEditables = new boolean[] {
							false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					tblTiendas.getColumnModel().getColumn(0).setResizable(false);
					tblTiendas.getColumnModel().getColumn(0).setPreferredWidth(275);
					tblTiendas.getColumnModel().getColumn(1).setResizable(false);
					tblTiendas.getColumnModel().getColumn(1).setPreferredWidth(342);
					btnDetalles.setEnabled(false);
					btnEditar.setEnabled(false);
					btnEliminar.setEnabled(false);
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		setTitle("Tiendas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAgregar = new JButton("Nueva Tienda");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MantenimientoTiendas mantenimiento = new MantenimientoTiendas();
				mantenimiento.agregarTienda();
			}
		});
		btnAgregar.setBounds(16, 18, 125, 46);
		contentPane.add(btnAgregar);
		
		btnDetalles = new JButton("Detalles");
		btnDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args) {
				Tienda temp = tiendas.get(tblTiendas.getSelectedRow());
				MantenimientoTiendas mantenimiento = new MantenimientoTiendas();
				mantenimiento.visualizarTienda(temp);
			}
		});
		btnDetalles.setEnabled(false);
		btnDetalles.setBounds(153, 18, 125, 46);
		contentPane.add(btnDetalles);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 76, 536, 289);
		contentPane.add(scrollPane);
		
		tblTiendas = new JTable();
		tblTiendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tblTiendas.getSelectedRow() != -1) {
					btnDetalles.setEnabled(true);
					btnEditar.setEnabled(true);
					btnEliminar.setEnabled(true);
				} else {
					btnDetalles.setEnabled(false);
					btnEditar.setEnabled(false);
					btnEliminar.setEnabled(false);
				}
			}
		});
	    tiendas = new Tienda().listar();
		tblTiendas.setModel(new DefaultTableModel(
				Utilidades.listToBidiArray(tiendas, new String[] { "nombre", "direccion"}),
			new String[] {
				"Tienda", "Direcci\u00F3n"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblTiendas.getColumnModel().getColumn(0).setResizable(false);
		tblTiendas.getColumnModel().getColumn(0).setPreferredWidth(275);
		tblTiendas.getColumnModel().getColumn(1).setResizable(false);
		tblTiendas.getColumnModel().getColumn(1).setPreferredWidth(342);
		scrollPane.setViewportView(tblTiendas);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tienda temp = tiendas.get(tblTiendas.getSelectedRow());
				MantenimientoTiendas mantenimiento = new MantenimientoTiendas();
				mantenimiento.editarTienda(temp);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(290, 18, 125, 46);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tienda temp = tiendas.get(tblTiendas.getSelectedRow());
				MantenimientoTiendas mantenimiento = new MantenimientoTiendas();
				mantenimiento.eliminarTienda(temp);
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(427, 18, 125, 46);
		contentPane.add(btnEliminar);
		
		
	}
}