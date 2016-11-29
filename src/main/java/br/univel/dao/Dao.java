package br.univel.dao;

import java.util.List;

/**
 * Interface de implementação dos métodos de comunicação e
 * persistência no banco de dados
 * 
 * @author LucasMedeiros
 *
 * @param <T>
 * @param <K>
 */

public interface Dao<T,K> {
	 
	public Boolean insert(T objeto);
	public T select(K chave);
	public Boolean update(T objeto);
	public Boolean delete(K chave);
	public List<T> selectAll();
}

