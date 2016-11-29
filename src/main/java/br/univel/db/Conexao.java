package br.univel.db;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * Classe de conexão com o banco de dados MySQL
 * 
 * @author LucasMedeiros
 *
 */

public class Conexao {
	
	private static Conexao conBD;
	private Connection con = null;
	
	private Conexao(){
		
	}

	public Connection abrirConexao() throws SQLException {		
		
		String url = "jdbc:mysql://localhost/trabalhothread";
		String user = "root";
		String pass = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conBD.con = (Connection) DriverManager.getConnection(url, user, pass);
	
		return conBD.con;
	}	
	
	public void fecharConexao() throws SQLException {
		con.close();
	}
	
	public static Conexao getInstance(){
		if (conBD == null){
			conBD = new Conexao();
		}
				
		return conBD;
	}
}
