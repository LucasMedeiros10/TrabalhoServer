package br.univel.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import br.univel.socket.ServidorSocket;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe visual Swing de controle da aplicação servidor
 * 
 * @author LucasMedeiros
 *
 */

public class TelaPrincipal extends JFrame{
	
	private JTextField txtSegundos;
	private ServidorSocket socket = new ServidorSocket();
	private Circulo circulo;	
	
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(430, 205);
		setLocationRelativeTo(null);
		
		JLabel lblOnline = new JLabel("Server on-line?");
		
		JLabel lblVerificarACada = new JLabel("Verificar a cada (segundos)");
		
		txtSegundos = new JTextField();
		txtSegundos.setText("10");
		txtSegundos.setColumns(10);
		
		JButton btnReiniciarServer = new JButton("Reiniciar Server");
		btnReiniciarServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fecharConexao();				
				//iniciarConexao();
			}
		});
		
		circulo = new Circulo();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOnline)
							.addGap(18)
							.addComponent(circulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblVerificarACada))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtSegundos, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(btnReiniciarServer)
					.addContainerGap(80, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblOnline)
							.addGap(24))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(circulo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVerificarACada)
						.addComponent(btnReiniciarServer)
						.addComponent(txtSegundos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);		
		
		// $hide>>$
		iniciarConexao();
		// $hide<<$				
	}
	
	public void iniciarConexao(){
		try {
			circulo.setOnline(true);
			socket.startServer();
		} catch (IOException e) {
			e.printStackTrace();
			circulo.setOnline(false);
		}		
	}
	
	public void fecharConexao(){
		circulo.setOnline(false);
		try {
			socket.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
