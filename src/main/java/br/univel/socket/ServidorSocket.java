package br.univel.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe para instanciação de um socket de comunicação com os clientes.
 * O socket fica na escuta da porta e a cada novo cliente conectado,
 * direciona para a classe LerRequisicao via Thread
 * 
 * @author LucasMedeiros
 *
 */

public class ServidorSocket {
	
	private ExecutorService pool = Executors.newFixedThreadPool(4);
	private ServerSocket server;
	
	public Boolean IsClosed(){
		return server.isClosed();
	} 
	
	public void shutdown() throws IOException{
		if((this.server != null) && (!server.isClosed())){
			this.server.close();
		}		
	}
	
	public void startServer() throws IOException{
		server = null;
		server = new ServerSocket(1000);
		
		while (!server.isClosed()) {
			
			//aguardando cliente conectar
			Socket connection = server.accept();
			//recebeu uma requisição
			pool.submit(new LerRequisicao(connection));
		}
		pool.shutdown();		
	}

}
