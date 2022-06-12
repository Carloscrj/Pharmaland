package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.AccesoDB;
import model.Medicamento;
import model.Usuario;

public class FarmaciaPersistencia {
	private AccesoDB acceso;
	
	public FarmaciaPersistencia() {
		acceso = new AccesoDB();
	}

	public Usuario validarUsuario(String nombreUsuario) {
		Usuario user = null;
		
		String query = "SELECT " + FarmaciaContract.EMP_APENOM + ", " + FarmaciaContract.EMP_CONTRASENA + " FROM " + FarmaciaContract.NOM_TAB_EMP + 
				" WHERE " + FarmaciaContract.EMP_APENOM  + " = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		String nomUser = "";
		String pwd = "";
		
		try {
			con = acceso.getConexion();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombreUsuario);
			
			rslt = pstmt.executeQuery();
			
			if (rslt.next()) {
				nomUser = rslt.getString(1);
				pwd = rslt.getString(2);
				
				user = new Usuario(pwd, nomUser);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public int crearUsuarioNuevo(String usuario, String pwd) {
		
		int num = 0;
		
		String query = "INSERT INTO " + FarmaciaContract.NOM_TAB_EMP + " (" + FarmaciaContract.EMP_CONTRASENA + ", " + FarmaciaContract.EMP_APENOM + 
				") VALUES (?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, pwd);
			pstmt.setString(2, usuario);
			
			num = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return num;
	}
	
	public ArrayList<String> getTipos(){
		ArrayList<String> listTipos = new ArrayList<String>();
		
		String query = "SELECT DISTINCT " + MedicamentoContract.TIPO + " FROM " + MedicamentoContract.NOM_TAB + " ORDER BY " + MedicamentoContract.TIPO;
		
		String tipo;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				tipo = rslt.getString(MedicamentoContract.TIPO);
				
				listTipos.add(tipo);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listTipos;
	}

	public ArrayList<String> getFarma() {
		ArrayList<String> listFarma = new ArrayList<String>();
		
		String query = "SELECT DISTINCT " + MedicamentoContract.FARMACEUTICA + " FROM " + MedicamentoContract.NOM_TAB + " ORDER BY " + MedicamentoContract.FARMACEUTICA;
		
		String farma;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				farma = rslt.getString(MedicamentoContract.FARMACEUTICA);
				
				listFarma.add(farma);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listFarma;
	}

	public ArrayList<Medicamento> seleccionarMed(String nom, String tipo, String farma, String where) {
		ArrayList<Medicamento> listMed = new ArrayList<Medicamento>();
		
		String query = "SELECT " + MedicamentoContract.ID + ", "  + MedicamentoContract.NOMBRE + ", "  + MedicamentoContract.TIPO + ", "  + MedicamentoContract.FARMACEUTICA + ", " 
				 + MedicamentoContract.PRECIO + ", "  + MedicamentoContract.STOCK + " FROM " + MedicamentoContract.NOM_TAB + " WHERE " + where + " ORDER BY " + MedicamentoContract.NOMBRE;
				
		Medicamento med;
		int id;
        double precio;
        int stock;
		int var = 1;
        
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			if (!nom.isBlank()) {
				pstmt.setString(var, nom + "%");
				var++;
			}
			if (!tipo.equals("Todos")) {
				pstmt.setString(var, tipo);
				var++;
			}
			if (!farma.equals("Todas")) {
				pstmt.setString(var, farma);
			}
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				id = rslt.getInt(MedicamentoContract.ID);
				nom = rslt.getString(MedicamentoContract.NOMBRE);
				tipo = rslt.getString(MedicamentoContract.TIPO);
				farma = rslt.getString(MedicamentoContract.FARMACEUTICA);
				precio = rslt.getDouble(MedicamentoContract.PRECIO);
				stock = rslt.getInt(MedicamentoContract.STOCK);
				
				med = new Medicamento(id, nom, tipo, farma, precio, stock);
				
				listMed.add(med);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listMed;
	}
	
	public ArrayList<Medicamento> seleccionarMedVenta(String nom, String tipo, String where) {
		ArrayList<Medicamento> listMed = new ArrayList<Medicamento>();
		
		String query = "SELECT " + MedicamentoContract.ID + ", "  + MedicamentoContract.NOMBRE + ", "  + MedicamentoContract.TIPO + ", "  + MedicamentoContract.FARMACEUTICA + ", " 
				 + MedicamentoContract.PRECIO + ", "  + MedicamentoContract.STOCK + " FROM " + MedicamentoContract.NOM_TAB ;
		
		if (!tipo.equals("Todos") || !nom.isBlank()) {
			query +=  " WHERE " + where ;
		}
		
		query +=  " ORDER BY " + MedicamentoContract.NOMBRE;
		
				
		Medicamento med;
		int id;
		String farma;
        double precio;
        int stock;
		int var = 1;
        
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			if (!nom.isBlank()) {
				pstmt.setString(var, nom + "%");
				var++;
			}
			if (!tipo.equals("Todos")) {
				pstmt.setString(var, tipo);
				var++;
			}
			
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				id = rslt.getInt(MedicamentoContract.ID);
				nom = rslt.getString(MedicamentoContract.NOMBRE);
				tipo = rslt.getString(MedicamentoContract.TIPO);
				farma= rslt.getString(MedicamentoContract.FARMACEUTICA);
				precio = rslt.getDouble(MedicamentoContract.PRECIO);
				stock = rslt.getInt(MedicamentoContract.STOCK);
				
				med = new Medicamento(id, nom, tipo, farma, precio, stock);
				
				listMed.add(med);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listMed;
	}
	
	public int obtenerIdEmple(String nombre) {
		String query = "SELECT " + FarmaciaContract.EMP_ID + " FROM " + FarmaciaContract.NOM_TAB_EMP + " WHERE " + FarmaciaContract.EMP_APENOM + " = ?";
		
		int id = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombre);
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				id = rslt.getInt(FarmaciaContract.EMP_ID);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}	
	
	public void insertarId(int id) {
		String query = "INSERT INTO " + PedidoContract.NOM_TAB + " (" + PedidoContract.ID_EMP + ") VALUES (?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modificarTblPedido(ArrayList<Object> idMedCant) {
		int idP = obtenerIdP();
		
		if (idP != 0) {
			String query = "INSERT INTO " + Medicamento_PedidoContract.NOM_TAB + " (" + Medicamento_PedidoContract.ID_MED + ", " + Medicamento_PedidoContract.ID_PED + 
					", " + Medicamento_PedidoContract.CANTIDAD + ") VALUES (?, ?, ?)";
			
			Connection con = null;
			PreparedStatement pstmt = null;
					
			try {
				con = acceso.getConexion();
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, (int) idMedCant.toArray()[0]);
				pstmt.setInt(2, idP);
				pstmt.setInt(3, (int) idMedCant.toArray()[1]);
				pstmt.executeUpdate();
			}
			catch (ClassNotFoundException e) {
				System.out.println("El driver indicado no es correcto");
				e.printStackTrace();
			}
			catch (SQLException e) {
				System.out.println("Error en la base de datos");
				e.printStackTrace();
				
			}
			finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private int obtenerIdP() {
		String query = "SELECT " + PedidoContract.ID + " FROM " + PedidoContract.NOM_TAB;
		
		int idP = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				idP = rslt.getInt(PedidoContract.ID);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return idP;
	}

	public void hacerPedido(int cantidad, String nombre) {

		String query = "UPDATE " + MedicamentoContract.NOM_TAB + " SET " + MedicamentoContract.STOCK + " = " + MedicamentoContract.STOCK + 
				" + ? WHERE " + MedicamentoContract.NOMBRE + " = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cantidad);
			pstmt.setString(2, nombre);
			pstmt.executeUpdate();
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
		
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Object> obtenerIdMed(String nombre, int cantidad) {
		ArrayList<Object> idMedCant = new ArrayList<Object>();
		
		String query = "SELECT " + MedicamentoContract.ID + " FROM " + MedicamentoContract.NOM_TAB + " WHERE " + MedicamentoContract.NOMBRE + " = ?";
		
		int id;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombre);
			rslt = pstmt.executeQuery();
			
			while (rslt.next()) {
				id = rslt.getInt(MedicamentoContract.ID);
				
				idMedCant.add(id);
				idMedCant.add(cantidad);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(rslt != null) rslt.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return idMedCant;		
	}

	public void insertarTablaVenta(int id, String tPago) {
		String query ="INSERT INTO " + VentaContract.NOM_TAB + " (" + VentaContract.ID_EMP + ", " + VentaContract.TIP_PAGO + 
				", " + VentaContract.FECH + ") VALUES (?, ?, DATETIME('NOW'))";

		
		Connection con = null;
		PreparedStatement pstmt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, tPago);
			pstmt.executeUpdate();
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void modificarTblVenta(ArrayList<Object> idMedCant) {
	int idV = obtenerIdV();
	
	if (idV != 0) {
		String query = "INSERT INTO " + Medicamento_VentaContract.NOM_TAB + " (" + Medicamento_VentaContract.ID_MED + ", " + Medicamento_VentaContract.ID_VENTA + 
				", " + Medicamento_VentaContract.CANTIDAD + ") VALUES (?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
				
		try {
			con = acceso.getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, (int) idMedCant.toArray()[0]);
			pstmt.setInt(2, idV);
			pstmt.setInt(3, (int) idMedCant.toArray()[1]);
			pstmt.executeUpdate();
		}
		catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
			
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

 
 private int obtenerIdV() {
	String query = "SELECT " + VentaContract.ID + " FROM " + VentaContract.NOM_TAB;
	
	int idV = 0;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rslt = null;
			
	try {
		con = acceso.getConexion();
		pstmt = con.prepareStatement(query);
		rslt = pstmt.executeQuery();
		
		while (rslt.next()) {
			idV = rslt.getInt(VentaContract.ID);
		}
	}
	catch (ClassNotFoundException e) {
		System.out.println("El driver indicado no es correcto");
		e.printStackTrace();
	}
	catch (SQLException e) {
		System.out.println("Error en la base de datos");
		e.printStackTrace();
		
	}
	finally {
		try {
			if(rslt != null) rslt.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
			
	return idV;
}

 public void hacerVenta(int cantidad, String nombre) {

	String query = "UPDATE " + MedicamentoContract.NOM_TAB + " SET " + MedicamentoContract.STOCK + " = " + MedicamentoContract.STOCK + 
			" - ? WHERE " + MedicamentoContract.NOMBRE + " = ?";
	
	Connection con = null;
	PreparedStatement pstmt = null;
			
	try {
		con = acceso.getConexion();
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, cantidad);
		pstmt.setString(2, nombre);
		pstmt.executeUpdate();
	}
	catch (ClassNotFoundException e) {
		System.out.println("El driver indicado no es correcto");
		e.printStackTrace();
	}
	catch (SQLException e) {
		System.out.println("Error en la base de datos");
		e.printStackTrace();
		
	}
	finally {
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


}
