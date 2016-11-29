package br.univel.facade;

import java.util.List;

/**
 * Interface para contrato e definição dos métodos que os 
 * façades vão implementar em suas classes
 * 
 * @author LucasMedeiros
 *
 * @param <T>
 */

public interface OperacoesFacade<T> {

	String validate();
	
	Boolean inserir();
	
	Boolean editar();
	
	Boolean excluir();
	
	public T listar();
	
	public List<T> listarTodos();
	
	public Object execute();
	
}
