package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import control.InicioControl;
import model.Medicamento;
import persistencia.FarmaciaPersistencia;
import persistencia.MedicamentoContract;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

public class PPedido extends JPanel {
	public static final int ANCHO_PANEL = VPrincipal.ANCHO - 25;
	public static final int ALTO_PANEL = VPrincipal.ALTO - 100;
	public static final String BTN_FILTRAR = "Filtrar ";
	public static final String BTN_ANADIR = "Anadir Producto ";
	public static final String BTN_LIMPIAR = "Limpiar ";
	public static final String BTN_QUITAR = "Quitar Producto ";
	public static final String BTN_PEDIDO = "Realizar Pedido";
	private static final String COL_NOMBRE = "NOMBRE";
	private static final String COL_TIPO = "TIPO";
	private static final String COL_FARMACEUTICA = "FARMACEUTICA";
	private static final String COL_PRECIO = "PRECIO";
	private static final String COL_STOCK = "STOCK";
	private static final String COL_CANTIDAD = "CANTIDAD";
	private JComboBox cmbTipo;
	private JComboBox cmbFarma;
	private JSpinner spnMin;
	private JSpinner spnMax;
	private JRadioButton rdbtnStock;
	private JButton btnFiltrar;
	private JSpinner spnCantidad;
	private JButton btnAnadir;
	private JButton btnLimpiar;
	private JButton btnQuitar;
	private JButton btnPedido;
	private JTable tblProd;
	private JTable tblPed;
	private JTextField txtNom;
	private DefaultTableModel dtmMed;
	private DefaultTableModel dtmMed2;
	private JScrollPane scrpTblPr;
	private ArrayList<Medicamento> listMed;
	private JScrollPane scrpTblPe;
	
	public PPedido() {
		setBackground(new Color(204, 255, 153));
		setLayout(null);
		
		JLabel lblPedido = new JLabel("REALIZAR PEDIDO");
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPedido.setBounds(289, 23, 162, 14);
		add(lblPedido);
		
		cmbTipo = new JComboBox();
		cmbTipo.addItem("Todos");
		cmbTipo.setBounds(75, 51, 114, 23);
		add(cmbTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(28, 55, 46, 14);
		add(lblTipo);
		
		cmbFarma = new JComboBox();
		cmbFarma.addItem("Todas");
		cmbFarma.setBounds(122, 81, 138, 23);
		add(cmbFarma);
		
		JLabel lblFarma = new JLabel("Farmaceutica:");
		lblFarma.setBounds(28, 81, 109, 14);
		add(lblFarma);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 114, 60, 14);
		add(lblNombre);
		
		spnMin = new JSpinner();
		spnMin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnMin.setBounds(128, 151, 46, 20);
		add(spnMin);
		
		JLabel lblMin = new JLabel("Precio Min:");
		lblMin.setBounds(28, 154, 97, 14);
		add(lblMin);
		
		JLabel lblMax = new JLabel("Max:");
		lblMax.setBounds(192, 154, 33, 14);
		add(lblMax);
		
		spnMax = new JSpinner();
		spnMax.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnMax.setBounds(243, 151, 40, 20);
		add(spnMax);
		
		rdbtnStock = new JRadioButton("Stock");
		rdbtnStock.setBackground(new Color(204, 255, 153));
		rdbtnStock.setBounds(221, 51, 77, 23);
		add(rdbtnStock);
		
		btnFiltrar = new JButton(BTN_FILTRAR);
		btnFiltrar.setForeground(new Color(255, 255, 255));
		btnFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnFiltrar.setBorderPainted(false);
		btnFiltrar.setBackground(new Color(102, 102, 0));
		btnFiltrar.setBounds(270, 81, 97, 27);
		add(btnFiltrar);
		
		spnCantidad = new JSpinner();
		spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantidad.setBounds(168, 301, 53, 20);
		add(spnCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(115, 304, 89, 14);
		add(lblCantidad);
		
		btnAnadir = new JButton(BTN_ANADIR);
		btnAnadir.setForeground(new Color(255, 255, 255));
		btnAnadir.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAnadir.setBorderPainted(false);
		btnAnadir.setBackground(new Color(102, 102, 0));
		btnAnadir.setBounds(10, 335, 154, 23);
		add(btnAnadir);
		
		btnLimpiar = new JButton(BTN_LIMPIAR);
		btnLimpiar.setBackground(new Color(102, 102, 0));
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBounds(192, 333, 89, 23);
		add(btnLimpiar);
		
		JSeparator sprt = new JSeparator();
		sprt.setForeground(Color.BLACK);
		sprt.setOrientation(SwingConstants.VERTICAL);
		sprt.setBounds(377, 55, 2, 303);
		add(sprt);
		
		btnQuitar = new JButton(BTN_QUITAR);
		btnQuitar.setForeground(new Color(255, 255, 255));
		btnQuitar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnQuitar.setBorderPainted(false);
		btnQuitar.setBackground(new Color(102, 102, 0));
		btnQuitar.setBounds(409, 298, 141, 23);
		add(btnQuitar);
		
		btnPedido = new JButton(BTN_PEDIDO);
		btnPedido.setBorderPainted(false);
		btnPedido.setBackground(new Color(102, 102, 0));
		btnPedido.setForeground(new Color(255, 255, 255));
		btnPedido.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPedido.setBounds(560, 296, 136, 23);
		add(btnPedido);
		
		setSize(ANCHO_PANEL, ALTO_PANEL);
		
		txtNom = new JTextField();
		txtNom.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtNom.setBackground(new Color(204, 255, 153));
		txtNom.setBounds(111, 111, 114, 21);
		add(txtNom);
		txtNom.setColumns(10);
		
		scrpTblPr = new JScrollPane();
		scrpTblPr.setBounds(10, 199, 357, 92);
		add(scrpTblPr);
		
		tblProd = new JTable();
		scrpTblPr.setViewportView(tblProd);
		
		scrpTblPe = new JScrollPane();
		scrpTblPe.setBounds(389, 68, 326, 197);
		add(scrpTblPe);
		
		tblPed = new JTable();
		tblPed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrpTblPe.setViewportView(tblPed);
		
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
		
		tblPed.setModel(dtmMed2);
		
		dtmMed2.addColumn(COL_NOMBRE);
		dtmMed2.addColumn(COL_TIPO);
		dtmMed2.addColumn(COL_FARMACEUTICA);
		dtmMed2.addColumn(COL_PRECIO);
		dtmMed2.addColumn(COL_STOCK);
		dtmMed2.addColumn(COL_CANTIDAD);
		
		tblPed.getColumn(COL_NOMBRE).setPreferredWidth(75);
		tblPed.getColumn(COL_TIPO).setPreferredWidth(75);
		tblPed.getColumn(COL_FARMACEUTICA).setPreferredWidth(75);
		tblPed.getColumn(COL_PRECIO).setPreferredWidth(75);
		tblPed.getColumn(COL_STOCK).setPreferredWidth(75);
		tblPed.getColumn(COL_CANTIDAD).setPreferredWidth(125);
	}
	
	public void setControladorBotones(InicioControl  c) {
		btnAnadir.addActionListener(c);
		btnFiltrar.addActionListener(c);
		btnPedido.addActionListener(c);
		btnQuitar.addActionListener(c);
		btnLimpiar.addActionListener(c);
		asignarTipos(c);
		asignarFarma(c);
	}
	
	public void resetearValores(boolean menu) {
		dtmMed.getDataVector().clear();
		tblProd.setSelectionMode(0);
		txtNom.setText(null);
		cmbTipo.setSelectedIndex(0);
		cmbFarma.setSelectedIndex(0);
		spnCantidad.setValue(1);
		spnMax.setValue(1);
		spnMin.setValue(0);
		rdbtnStock.setSelected(false);
		
		if (menu) {
			dtmMed2.getDataVector().clear();
		}
	}
	
	public void filtrarPedido(FarmaciaPersistencia fP){
    	dtmMed.getDataVector().clear();
    	tblProd.setSelectionMode(0);
    	
        String nom = txtNom.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        String farma = (String) cmbFarma.getSelectedItem();
        int max = (int) spnMax.getValue();
        int min = (int) spnMin.getValue();
        boolean stock = rdbtnStock.isSelected();
        
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
        
        if (!farma.equals("Todas")) {			
			if (!where.isBlank()) {
				where += " AND ";
			}
			
			where += MedicamentoContract.FARMACEUTICA + " = ? ";
		}
        
        if (max < min) {
			mostrarMensaje("El precio min no puede ser mayor que el max", "Error de datos", 0);
			esValido = false;
		}
        else {			
			if (!where.isBlank()) {
				where += " AND ";
			}
			
			where += "ROUND(" + MedicamentoContract.PRECIO + ") BETWEEN " + min + " AND " + (max-1);
		}
		
		if (stock) {
			if (!where.isBlank()) {
				where += " AND ";
			}
			
			where += MedicamentoContract.STOCK + " > 0 ";
		}
		else {
			if (!where.isBlank()) {
				where += " AND ";
			}
			
			where += MedicamentoContract.STOCK + " = 0 ";
		}
		
        if (esValido) {
        	listMed = fP.seleccionarMed(nom, tipo, farma, where);
            
            if (!listMed.isEmpty()) {
            	for (Medicamento med : listMed) {
    				dtmMed.addRow(med.getRowData());
    			}
			}
            else {
				mostrarMensaje("No hay ningun medicamento con esos filtros", "Error de busqueda", 1);
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
	
	public void asignarFarma(InicioControl c) {
        FarmaciaPersistencia rP = c.getfPersistencia();
        
        DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) cmbFarma.getModel();
            for (String region : rP.getFarma()) {
                modelo.addElement(region);
            }
    }
	
	public void anadir() {		
		Medicamento med;
		try {
			med = (Medicamento) listMed.toArray()[tblProd.getSelectedRow()];
			
			dtmMed2.addRow(med.getRowData2((int) spnCantidad.getValue()));
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
			dtmMed2.removeRow(tblPed.getSelectedRow());
		}
			catch (ArrayIndexOutOfBoundsException e) {
			mostrarMensaje("No hay medicamento seleccionado", "Error al Quitar", 0);
		}
	}
	
	public void pedir(FarmaciaPersistencia fP) {
		String nombre = null;
		int cantidad = 0;
		
		if (dtmMed2.getRowCount() != 0) {
			for (int i = 0; i < dtmMed2.getRowCount(); i++) {
				nombre = (String) tblPed.getModel().getValueAt(i, 0);
				cantidad = (int) tblPed.getModel().getValueAt(i, 5);
				
				fP.hacerPedido(cantidad, nombre);
				
				fP.modificarTblPedido(fP.obtenerIdMed(nombre, cantidad));
			}
			dtmMed2.getDataVector().clear();
			mostrarMensaje("Pedido realizado con exito", "Resultado de operacion", 1);
		}
		else {
			mostrarMensaje("No se puede hacer el pedido sin medicamentos", "Error al Hacer Pedido", 0);
		}
	}
}
