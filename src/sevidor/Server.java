package sevidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import mensagens.*;

import java.util.LinkedList;

public class Server {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// cria o server socket
		ServerSocket ss = new ServerSocket(12347);
		//protocolos de comunicacao
		LinkedList<DadosDaPosicao> dp=new LinkedList<DadosDaPosicao>();
		int numeroDeEngine=0;
		int numeroDeCliente=0;
		int id=0;
		//thread
		ThreadEngine thread;
		
		
		//roda o server
		while(true) {
			
			//estabelece conexao
			Socket cliente = ss.accept();
			System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
			
			
			
			// le a mensagem
			ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
			ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
			Mensagem msg= (Mensagem)input.readObject();
			
			//identifica se e engine ou cliente
			if(msg.id==0) {//cliente
				numeroDeCliente++;
				id++;
				//retorna a msg com o id do cliente
				output.writeObject(new Mensagem(id));
			}
			if(msg.id==-1) {//engine
				numeroDeEngine++;
				id++;
				//envia uma msg com o id da engine
			}
			//responde cliente ou servidor cas ja tenha sido identificado
			if(msg.id>0) {//cliente com id ja conectado
				
			}else {//engine com id ja conectado
				dp.add(new DadosDaPosicao(msg));
				
			}
			if(numeroDeEngine>0&&numeroDeCliente>0) {
				thread = new ThreadEngine(msg);
				thread.start();
			}
		}

	}

}
