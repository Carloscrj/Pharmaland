package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import control.InicioControl;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class VPrincipal extends JFrame {
	static final int ANCHO = 750;
	static final int ALTO = 550;
	public static final String MNTM_PEDIDO = "Pedido";
	public static final String MNTM_VENTA = "Venta";
	public static final String MNTM_CERRAR = "Cerrar Sesion";
	
	private JMenuItem mntmCerrar;
	private JMenuItem mntmVenta;
	private JMenuItem mntmPedido;
	private JScrollPane scrpContenedor;
	
	public VPrincipal() {
		setBackground(new Color(204, 255, 153));
		getContentPane().setBackground(new Color(204, 255, 153));
		init();
	}

	private void init() {
		setTitle("PharmaLand");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		scrpContenedor = new JScrollPane();
		scrpContenedor.setOpaque(false);
		scrpContenedor.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrpContenedor.setBackground(new Color(204, 255, 153));
		scrpContenedor.setBounds(12, 12, PPedido.ANCHO_PANEL, PPedido.ALTO_PANEL);
		getContentPane().add(scrpContenedor);
		
		setSize(ANCHO, ALTO);
		centrarVentana();
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO,ALTO));
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = this.getPreferredSize();
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(102, 102, 0));
		setJMenuBar(menuBar);
		
		mntmPedido = new JMenuItem(MNTM_PEDIDO);
		mntmPedido.setForeground(new Color(255, 255, 255));
		mntmPedido.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mntmPedido.setOpaque(true);
		mntmPedido.setBackground(new Color(102, 102, 0));
		menuBar.add(mntmPedido);
		
		mntmVenta = new JMenuItem(MNTM_VENTA);
		mntmVenta.setBackground(new Color(102, 102, 0));
		mntmVenta.setOpaque(true);
		mntmVenta.setForeground(new Color(255, 255, 255));
		mntmVenta.setFont(new Font("Times New Roman", Font.BOLD, 18));
		menuBar.add(mntmVenta);
		
		mntmCerrar = new JMenuItem(MNTM_CERRAR);
		mntmCerrar.setOpaque(true);
		mntmCerrar.setForeground(new Color(255, 255, 255));
		mntmCerrar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mntmCerrar.setBackground(new Color(102, 102, 0));
		mntmCerrar.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(mntmCerrar);
	}
	
	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
	
	public void setControlador(InicioControl control) {
		mntmPedido.addActionListener(control);
		mntmVenta.addActionListener(control);
		mntmCerrar.addActionListener(control);
	}
	
	public int mostrarConfirmacion(String mensaje, int tipo) {
		int resp = JOptionPane.showConfirmDialog(this, mensaje, "Confirmacion", JOptionPane.YES_NO_OPTION, tipo);
		
		return resp;
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

	public void borrarCont() {
		scrpContenedor.setViewportView(null);
	}
}