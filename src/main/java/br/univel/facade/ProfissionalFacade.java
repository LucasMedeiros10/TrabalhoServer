package br.univel.facade;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.univel.dao.DaoProfissional;
import br.univel.dto.Profissional;
import br.univel.function.Hash;

/**
 * Facade para controle das ações realizadas e 
 * comunicação com o {@link}DaoProfissional
 * 
 */

public class ProfissionalFacade implements OperacoesFacade<Profissional>{

	private Profissional profissional;
	private DaoProfissional dao;
	
	public ProfissionalFacade(Profissional profissional) {
		this.profissional = profissional;
		dao = new DaoProfissional();
	}
	
	@Override
	public String validate() {
		String mensagens = "";
		
		if(profissional.getNome().trim().isEmpty()){
			mensagens = mensagens.concat("Nome não informado.\n");
		}
		
		if(profissional.getDataNascimento() == null){
			mensagens = mensagens.concat("Data de nascimento inválida.\n");
		}
		
		if(profissional.getLogin().trim().isEmpty()){
			mensagens = mensagens.concat("Login não informado.\n");
		}
		
		if(profissional.getSenha().trim().isEmpty()){
			mensagens = mensagens.concat("Senha não informada.\n");
		}
						
		return mensagens;
	}

	@Override
	public Boolean inserir() {
		
		Hash hash = new Hash();
		try {
			profissional.setSenha(hash.hashSHA256(profissional.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return dao.insert(profissional);
	}

	@Override
	public Boolean editar() {
		try {
			profissional.setSenha(new Hash().hashSHA256(profissional.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return dao.update(profissional);
	}

	@Override
	public Boolean excluir() {
		return dao.delete(profissional.getId());
	}

	@Override
	public Profissional listar() {
		return dao.select(profissional.getId());
	}

	@Override
	public List<Profissional> listarTodos() {
		return dao.selectAll();
	}
	
	public Boolean logar(){
		Boolean resultado = false;
		try {
			resultado = dao.logar(profissional.getLogin(), new Hash().hashSHA256(profissional.getSenha()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return resultado;
	}

	@Override
	public Object execute() {
		Object resultado = null;
		switch (profissional.getTipoOperacao()) {
		
		case EDICAO:
			if(validate().equals("")){
				resultado = editar();
			}else{
				resultado = validate();
			}
			break;
			
		case EXCLUSAO:
			if(validate().equals("")){
				resultado = excluir();
			}else{
				resultado = validate();
			}
			break;
			
		case INSERCAO:
			if(validate().equals("")){
				resultado = inserir();
			}else{
				resultado = validate();
			}
			break;
			
		case LISTAR:
			resultado = listar();
			break;				
			
		case LISTARTODOS:
			resultado = listarTodos();
			break;
			
		case LOGIN:
			resultado = logar();
			break;	
			
		default:
			break;
		}
		return resultado;
	}

}
