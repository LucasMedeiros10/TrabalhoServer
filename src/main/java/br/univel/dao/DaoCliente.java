package br.univel.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.db.Conexao;
import br.univel.dto.Cliente;

/**
 * Classe Dao de comunicação e persistência no banco de 
 * dados referente à classe Cliente
 * 
 * @author LucasMedeiros
 *
 */

public class DaoCliente implements Dao<Cliente, Integer>{

	@Override
	public Boolean insert(Cliente objeto) {
		Boolean resultado = false;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO CLIENTES (NOME, DT_NASC, RG, CPF) VALUES (?,?,?,?)");
			ps.setString(1, objeto.getNome());
			ps.setDate(2, new java.sql.Date(objeto.getDataNascimento().getTime()));
			ps.setString(3, objeto.getRg());
			ps.setString(4, objeto.getCpf());
			ps.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return resultado;
	}

	@Override
	public Cliente select(Integer chave) {
		Cliente cliente = null;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CLIENTES WHERE ID = ?");
			ps.setInt(1, chave);
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				cliente = new Cliente();
				cliente.setId(result.getInt("id"));
				cliente.setNome(result.getString("nome"));
				cliente.setCpf(result.getString("cpf"));				
				cliente.setRg(result.getString("rg"));
				cliente.setDataNascimento(result.getDate("dt_nasc"));				
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public Boolean update(Cliente objeto) {
		Boolean resultado = false;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("UPDATE CLIENTES SET NOME = ?, DT_NASC = ?, RG = ?, CPF = ? WHERE ID = ?");
			ps.setString(1, objeto.getNome());
			ps.setDate(2, new java.sql.Date(objeto.getDataNascimento().getTime()));
			ps.setString(3, objeto.getRg());
			ps.setString(4, objeto.getCpf());
			ps.setInt(5, objeto.getId());
			ps.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return resultado;
	}

	@Override
	public Boolean delete(Integer chave) {
		Boolean resultado = false;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("DELETE FROM CLIENTES WHERE ID = ?");
			ps.setInt(1, chave);
			ps.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return resultado;
	}

	@Override
	public List<Cliente> selectAll() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM CLIENTES");
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("id"));
				cliente.setNome(result.getString("nome"));
				cliente.setCpf(result.getString("cpf"));				
				cliente.setRg(result.getString("rg"));
				cliente.setDataNascimento(result.getDate("dt_nasc"));
				
				listaClientes.add(cliente);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaClientes;
	}

}
