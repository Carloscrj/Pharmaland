package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import control.InicioControl;
import persistencia.FarmaciaPersistencia;
import persistencia.MedicamentoContract;
import model.Medicamento;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class PVenta extends JPanel {
	public static final int ANCHO_PANEL = VPrincipal.ANCHO - 25;
	public static final int ALTO_PANEL = VPrincipal.ALTO - 100;
	public static final String BTN_FILTRAR = "Filtrar";
	public static final String BTN_ANADIR = "Anadir Producto";
	public static final String BTN_LIMPIAR_VENTA = "Limpiar";
	public static final String BTN_QUITAR = "Quitar Producto";
	public static final String BTN_VENTA = "Realizar Venta";
	private static final String COL_NOMBRE = "NOMBRE";
	private static final String COL_TIPO = "TIPO";
	private static final String COL_CANTIDAD = "CANTIDAD";
	private static final String COL_PRECIO = "PRECIO";
	private static final String COL_FARMACEUTICA = "FARMACEUTICA";
	private static final String COL_STOCK = "STOCK";
	private JTable tblProd;
	private JTable tblVenta;
	private JButton btnQuitar;
	private JButton btnVenta;
	private JComboBox cmbTipo;
	private JButton btnFiltrar;
	private JSpinner spnCantidad;
	private JButton btnAnadir;
	private JButton btnLimpiar;
	private JTextField txtNom;
	private DefaultTableModel dtmMed;
	private DefaultTableModel dtmMed2;
	private JScrollPane scrpTblPr;
	private ArrayList<Medicamento> listMed;
	private JScrollPane scrpTblPe;
	private JComboBox cmbPago;
	
	public PVenta() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		JLabel lblVenta = new JLabel("VENTA PRODUCTOS");
		lblVenta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblVenta.setBounds(289, 24, 197, 22);
		add(lblVenta);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(54, 67, 46, 14);
		add(lblTipo);
		
		cmbTipo = new JComboBox();
		cmbTipo.setBackground(Color.WHITE);
		cmbTipo.addItem("Todos");
		cmbTipo.setBounds(117, 63, 78, 22);
		add(cmbTipo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(49, 95, 73, 14);
		add(lblNombre);
		
		btnFiltrar = new JButton(BTN_FILTRAR);
		btnFiltrar.setForeground(new Color(255, 255, 255));
		btnFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnFiltrar.setBorderPainted(false);
		btnFiltrar.setBackground(new Color(102, 102, 0));
		btnFiltrar.setBounds(254, 63, 89, 23);
		add(btnFiltrar);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(100, 273, 95, 14);
		add(lblCantidad);
		
		spnCantidad = new JSpinner();
		spnCantidad.setBorder(new EmptyBorder(0, 0, 0, 0));
		spnCantidad.setBackground(new Color(255, 255, 255));
		spnCantidad.setModel(new SpinnerNumberModel(1, 1, null, 1));
		spnCantidad.setBounds(162, 270, 56, 20);
		add(spnCantidad);
		
		btnAnadir = new JButton(BTN_ANADIR);
		btnAnadir.setBorderPainted(false);
		btnAnadir.setBackground(new Color(102, 102, 0));
		btnAnadir.setForeground(new Color(255, 255, 255));
		btnAnadir.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAnadir.setBounds(49, 303, 140, 23);
		add(btnAnadir);
		
		btnLimpiar = new JButton(BTN_LIMPIAR_VENTA);
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setBackground(new Color(102, 102, 0));
		btnLimpiar.setBounds(201, 303, 89, 23);
		add(btnLimpiar);
		
		JSeparator sprt = new JSeparator();
		sprt.setOrientation(SwingConstants.VERTICAL);
		sprt.setForeground(Color.BLACK);
		sprt.setToolTipText("");
		sprt.setBounds(381, 69, 11, 257);
		add(sprt);
		
		btnQuitar = new JButton(BTN_QUITAR);
		btnQuitar.setForeground(new Color(255, 255, 255));
		btnQuitar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnQuitar.setBorderPainted(false);
		btnQuitar.setBackground(new Color(102, 102, 0));
		btnQuitar.setBounds(413, 303, 135, 23);
		add(btnQuitar);
		
		btnVenta = new JButton(BTN_VENTA);
		btnVenta.setBorderPainted(false);
		btnVenta.setBackground(new Color(102, 102, 0));
		btnVenta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnVenta.setForeground(new Color(255, 255, 255));
		btnVenta.setBounds(556, 303, 146, 23);
		add(btnVenta);
		
		setSize(ANCHO_PANEL, ALTO_PANEL);
		
		txtNom = new JTextField();
		txtNom.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNom.setBackground(new Color(204, 255, 153));
		txtNom.setBounds(117, 92, 114, 21);
		add(txtNom);
		txtNom.setColumns(10);
		
		JScrollPane scrpTblPr_1 = new JScrollPane();
		scrpTblPr_1.setBackground(Color.WHITE);
		scrpTblPr_1.setBounds(10, 125, 361, 135);
		add(scrpTblPr_1);
		
		tblProd = new JTable();
		scrpTblPr_1.setViewportView(tblProd);
		
		JScrollPane scrpTblPe_1 = new JScrollPane();
		scrpTblPe_1.setBounds(390, 66, 325, 197);
		add(scrpTblPe_1);
		
		tblVenta = new JTable();
		scrpTblPe_1.setViewportView(tblVenta);
		
		JLabel lblTipoPago = new JLabel("T.Pago:");
		lblTipoPago.setBounds(470, 274, 45, 13);
		add(lblTipoPago);
		
		cmbPago = new JComboBox();
		cmbPago.setBackground(Color.WHITE);
		cmbPago.setModel(new DefaultComboBoxModel(new String[] {"TARJETA", "EFECTIVO"}));
		cmbPago.setBounds(525, 269, 125, 22);
		add(cmbPago);
		
		configurarTabla();
		configurarTabla2();
	}
	
	private void configurarTabla() {
		dtmMed = new DefaultTableModel() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblProd.setModel(dtmMed);
		
		dtmMed.addColumn(COL_NOMBRE);
		dtmMed.addColumn(COL_TIPO);
		dtmMed.addColumn(COL_FARMACEUTICA);
		dtmMed.addColumn(COL_PRECIO);
		dtmMed.addColumn(COL_STOCK);
		
		tblProd.getColumn(COL_NOMBRE).setPreferredWidth(75);
		tblProd.getColumn(COL_TIPO).setPreferredWidth(75);
		tblProd.getColumn(COL_FARMACEUTICA).setPreferredWidth(125);
		tblProd.getColumn(COL_PRECIO).setPreferredWidth(75);
		tblProd.getColumn(COL_STOCK).setPreferredWidth(75);
       
		
	}
	
	private void configurarTabla2() {
		dtmMed2 = new DefaultTableModel() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblVenta.setModel(dtmMed2);
		
		dtmMed2.addColumn(COL_NOMBRE);
		dtmMed2.addColumn(COL_TIPO);
		dtmMed2.addColumn(COL_FARMACEUTICA);
		dtmMed2.addColumn(COL_PRECIO);
		dtmMed2.addColumn(COL_STOCK);
		dtmMed2.addColumn(COL_CANTIDAD);
		
		tblVenta.getColumn(COL_NOMBRE).setPreferredWidth(75);
		tblVenta.getColumn(COL_TIPO).setPreferredWidth(75);
		tblVenta.getColumn(COL_FARMACEUTICA).setPreferredWidth(75);
		tblVenta.getColumn(COL_PRECIO).setPreferredWidth(75);
		tblVenta.getColumn(COL_STOCK).setPreferredWidth(75);
		tblVenta.getColumn(COL_CANTIDAD).setPreferredWidth(125);
	}
	
	
	public void setControladorBotones(InicioControl  c) {
		btnAnadir.addActionListener(c);
		btnFiltrar.addActionListener(c);
		btnVenta.addActionListener(c);
		btnQuitar.addActionListener(c);
		btnLimpiar.addActionListener(c);
		asignarTipos(c);
	}
	
	public void resetearValores(boolean menu) {
		tblProd.setSelectionMode(0);
		txtNom.setText(null);
		cmbTipo.setSelectedIndex(0);
		spnCantidad.setValue(1);
		
		if (menu) {
			dtmMed2.getDataVector().clear();
		}
	}
	
	public void filtrarVenta(FarmaciaPersistencia fP) {
		dtmMed.getDataVector().clear();
    	tblVenta.setSelectionMode(0);
    	
    	String nom = txtNom.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        
        boolean esValido = true;
        
        String where = "";
        
        if (!nom.isBlank()) {
			where += MedicamentoContract.NOMBRE + " LIKE ? ";
		}
        
        if (!tipo.equals("Todos")) {			
			if (!where.isBlank()) {
				where += " AND ";
			}
			
			where += MedicamentoContract.TIPO + " = ? ";
		}
        
        if (esValido) {
        	listMed = fP.seleccionarMedVenta(nom, tipo, where);
            
            if (!listMed.isEmpty()) {
            	for (Medicamento med : listMed) {
    				dtmMed.addRow(med.getRowData());
    			}
			}
            else {
				mostrarMensaje("No hay medicamentos con esos filtros", "Error de busqueda", 1);
			}
		}
        
	}
	
	private void mostrarMensaje(String mensaje, String titulo, int tipo) {
		JOptionPane.showMessageDialog(this,mensaje, titulo, tipo);
	}
	
	public void asignarTipos(InicioControl c) {
        FarmaciaPersistencia rP = c.getfPersistencia();
        
        DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) cmbTipo.getModel();
            for (String region : rP.getTipos()) {
                modelo.addElement(region);
            }
    }
	
	public void anadir() {		
		Medicamento med;
		String nombre= null;
		int cantidad= 0;
		int stock= 0;
		
		try {
			med = (Medicamento) listMed.toArray()[tblProd.getSelectedRow()];
			nombre= med.getNombre();
			stock= med.getStock();
			cantidad= (int) spnCantidad.getValue();
			
			if (stock == 0) {
				
				mostrarMensaje("No se puede incluir " + nombre + " porque no hay stock", "Error de Stock", 0);
				
			}else {
				dtmMed2.addRow(med.getRowData2((int) spnCantidad.getValue()));
			}
			
			
		}
		catch (ArrayIndexOutOfBoundsException e) {
			mostrarMensaje("No hay medicamento seleccionado", "Error", 0);
		}
		catch (NullPointerException e) {
			mostrarMensaje("No hay medicamento seleccionado", "Error", 0);
		}
	}
	
	public void quitar() {
		try {
			dtmMed2.removeRow(tblVenta.getSelectedRow());
		}
			catch (ArrayIndexOutOfBoundsException e) {
			mostrarMensaje("No hay medicamento seleccionado", "Error al Quitar", 0);
		}
	}
	
	public String tipoDePago() {
		String tPago= (String) cmbPago.getSelectedItem();
		return tPago;
	}
	
	public void venta(FarmaciaPersistencia fP) {
		String nombre= null;
		int cantidad= 0;
		int stock= 0;
		boolean ventaPosible= true;
		
		if (dtmMed2.getRowCount() != 0) {
			for (int i = 0; i < dtmMed2.getRowCount(); i++) {
				nombre = (String) tblVenta.getModel().getValueAt(i, 0);
				cantidad = (int) tblVenta.getModel().getValueAt(i, 5);
				stock= (int) tblVenta.getModel().getValueAt(i, 4);
				
				if (cantidad > stock) {
					
					mostrarMensaje("No se puede vender " + nombre + " porque la cantidad pedida es mayor que el stock", "Error de Cantidad", 0);
					
					ventaPosible= false;
					
				}else {
					
					fP.hacerVenta(cantidad, nombre);
					fP.modificarTblVenta(fP.obtenerIdMed(nombre, cantidad));
				}
				
			}
			
			if (ventaPosible) {
				dtmMed2.getDataVector().clear();
				mostrarMensaje("Venta realizada con exito", "Resultado de operacion", 1);
			}
			
		}
		else {
			mostrarMensaje("No se puede hacer la venta sin medicamentos", "Error al Realizar Venta", 0);
		}
	}
}