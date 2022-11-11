package sevidor;
import mensagens.*;
public class DadosDaPosicao {
	int id;
	int[][] posicao;
	int profundidade;
	long tempo;	
	int[][] posicaoDeCalculo;
	
	DadosDaPosicao(Mensagem msg){
		this.id = msg.id;
		this.posicao=msg.posicao;
		tempo=System.currentTimeMillis();
		profundidade=0;
	}
	public Mensagem criaMsgE() {
		Mensagem msg = new Mensagem(posicao,this.id);
		return msg;
	}
}
