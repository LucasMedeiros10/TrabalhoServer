package br.univel.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import br.univel.dto.Cliente;
import br.univel.dto.Profissional;
import br.univel.facade.ClienteFacade;
import br.univel.facade.ProfissionalFacade;

/**
 * Classe de leitura do objeto recebido na requisição recebida
 * do client. É verificado qual o tipo da classe para definir qual Facade
 * executará o tratamento.
 * 
 * @author LucasMedeiros
 *
 */

public class LerRequisicao implements Runnable{
	
	private final Socket connection;

	public LerRequisicao(Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		try (InputStream input = this.connection.getInputStream();
				ObjectInputStream objInput = new ObjectInputStream(input);
				OutputStream output = this.connection.getOutputStream();
				ObjectOutputStream objOutput = new ObjectOutputStream(output)) {

			//recebe objeto da comunicação
			Object object = objInput.readObject();
			
			if(object.getClass().equals(Cliente.class)){
				
				Object ObjectRetorno = new ClienteFacade((Cliente) object).execute();
				objOutput.writeObject(ObjectRetorno);
				// Termina a escrita no OutputStream, liberando o cliente para prosseguir
				objOutput.flush();
				
				
			}else if(object.getClass().equals(Profissional.class)){

				Object ObjectRetorno = new ProfissionalFacade((Profissional) object).execute();
				objOutput.writeObject(ObjectRetorno);
				// Termina a escrita no OutputStream, liberando o cliente para prosseguir
				objOutput.flush();
				
			}else{
				System.out.println("Objeto não identificado: ".concat(object.getClass().getName()));
			}
									
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (!this.connection.isClosed()) {
				try {
					this.connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}

}
