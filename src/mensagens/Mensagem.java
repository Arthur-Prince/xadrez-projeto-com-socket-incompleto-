package mensagens;

public class Mensagem {
	public float[] avaliacao;
	public int[][] posicao;
	public int id;
	public int profundidade;
	public long tempo;
	public int[][][] ramificacao;
	// engine para
	public boolean pare=true;
	
	//mensagem do serv para a engine
	public Mensagem(int[][] posicao){
		this.posicao=posicao;
	}
	//mensagem da engine para o serv
	public Mensagem(int[][][] ramificacao,float[] avaliacao){
		this.ramificacao=ramificacao;
		this.avaliacao= avaliacao;
	}
	//mensagem servidor cliente
	public Mensagem(float[] avaliacao,int[][][] ramificacao,int profundidade,long tempo) {
		this.avaliacao=avaliacao;
		this.profundidade=profundidade;
		this.ramificacao=ramificacao;
		this.tempo=tempo;
	}
	//mensagem do cliente para o servidor
	public Mensagem(int[][] posicao,int id){
		this.posicao=posicao;
		this.id=id;
	}
	
	//pedido de conexao
	public Mensagem(int id) {
		this.id=id;
	}
	
	public Mensagem() {
		pare=false;
	}
	
	
}
