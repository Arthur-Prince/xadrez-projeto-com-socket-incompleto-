package sevidor;

import mensagens.*;

public class ThreadEngine extends Thread {
	Mensagem msg;
	
	ThreadEngine(Mensagem msg){
		this.msg = msg;
		
	}
	@Override
	public void run() {
		
	}
}
