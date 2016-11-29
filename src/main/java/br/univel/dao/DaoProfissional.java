package br.univel.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.univel.db.Conexao;
import br.univel.dto.Profissional;

/**
 * Classe Dao de comunicação e persistência no banco de 
 * dados referente à classe Profissional
 * 
 * @author LucasMedeiros
 *
 */

public class DaoProfissional implements Dao<Profissional, Integer>{

	@Override
	public Boolean insert(Profissional objeto) {
		Boolean resultado = false;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("INSERT INTO PROFISSIONAIS (NOME, DT_NASC, LOGIN, SENHA) VALUES (?,?,?,?)");
			ps.setString(1, objeto.getNome());
			ps.setDate(2, new java.sql.Date(objeto.getDataNascimento().getTime()));
			ps.setString(3, objeto.getLogin());
			ps.setString(4, objeto.getSenha());
			ps.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	@Override
	public Profissional select(Integer chave) {
		Profissional profissional = null;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PROFISSIONAIS WHERE ID = ?");
			ps.setInt(1, chave);
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				profissional = new Profissional();
				profissional.setId(result.getInt("id"));
				profissional.setNome(result.getString("nome"));
				profissional.setLogin(result.getString("login"));				
				profissional.setSenha(result.getString("senha"));
				profissional.setDataNascimento(result.getDate("dt_nasc"));				
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profissional;
	}

	@Override
	public Boolean update(Profissional objeto) {
		Boolean resultado = false;
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("UPDATE PROFISSIONAIS SET NOME = ?, DT_NASC = ?, LOGIN = ?, SENHA = ? WHERE ID = ?");
			ps.setString(1, objeto.getNome());
			ps.setDate(2, new java.sql.Date(objeto.getDataNascimento().getTime()));
			ps.setString(3, objeto.getLogin());
			ps.setString(4, objeto.getSenha());
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
									.clientPrepareStatement("DELETE FROM PROFISSIONAIS WHERE ID = ?");
			ps.setInt(1, chave);
			ps.executeUpdate();
			resultado = true;
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return resultado;
	}

	@Override
	public List<Profissional> selectAll() {
		List<Profissional> listaProfissionais = new ArrayList<Profissional>();
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PROFISSIONAIS");
			ResultSet result =  ps.executeQuery();
			
			while(result.next()){
				Profissional profissional = new Profissional();
				profissional.setId(result.getInt("id"));
				profissional.setNome(result.getString("nome"));
				profissional.setLogin(result.getString("login"));				
				profissional.setSenha(result.getString("senha"));
				profissional.setDataNascimento(result.getDate("dt_nasc"));
				listaProfissionais.add(profissional);
			}
			
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProfissionais;
	}

	public Boolean logar(String login, String senha) {
		Boolean resultado = false;
		
		try {
			PreparedStatement ps = (PreparedStatement) Conexao.getInstance().abrirConexao()
									.clientPrepareStatement("SELECT * FROM PROFISSIONAIS WHERE LOGIN = ? AND SENHA = ?");
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet result =  ps.executeQuery();
			
			resultado = result.next();
		
			ps.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return resultado;
	}


}
