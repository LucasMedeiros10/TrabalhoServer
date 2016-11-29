package br.univel.facade;

import java.util.List;

import br.univel.dao.DaoCliente;
import br.univel.dto.Cliente;

/**
 * Facade para controle das ações realizadas e 
 * comunicação com o {@link}DaoCliente
 * 
 * @author LucasMedeiros
 *
 */

public class ClienteFacade implements OperacoesFacade<Cliente>{

	private Cliente cliente;
	private DaoCliente dao;
	
	
	public ClienteFacade(Cliente cliente){
		this.cliente = cliente;
		dao = new DaoCliente();
	}
	
	@Override
	public String validate() {
		String mensagens = "";

		if(cliente.getNome().trim().isEmpty()){
			mensagens = mensagens.concat("Nome não informado.\n");
		}
		
		if(cliente.getDataNascimento() == null){
			mensagens = mensagens.concat("Data de nascimento inválida.\n");
		}
		
		if(cliente.getCpf().trim().isEmpty()){
			mensagens = mensagens.concat("CPF não informado.\n");
		}
		
		if(cliente.getRg().trim().isEmpty()){
			mensagens = mensagens.concat("RG não informado.\n");
		}
		return mensagens;
	}

	@Override
	public Boolean inserir() {
		return dao.insert(cliente);
	}

	@Override
	public Boolean editar() {
		return dao.update(cliente);
	}

	@Override
	public Boolean excluir() {			
		return dao.delete(cliente.getId());
	}

	@Override
	public Cliente listar() {
		return dao.select(cliente.getId());
	}

	@Override
	public List<Cliente> listarTodos() {
		return dao.selectAll();
	}

	@Override
	public Object execute(){
		Object resultado = null;
		
		switch (cliente.getTipoOperacao()) {
		
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
				
			default:
				break;
		}
		return resultado;
	}

}
