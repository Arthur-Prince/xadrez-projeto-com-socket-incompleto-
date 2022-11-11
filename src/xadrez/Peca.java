package xadrez;

public abstract class Peca{
	private int cor;  //0 branca 1 preta
	private int x,y;
	private boolean capturada;
	private int valor;
	public int numDeMov;
	
	public Peca(int cor,int x,int y){
		this.setCor(cor);
		this.setX(x);
		this.setY(y);
		this.setCapturada(false);
	}
	
	public void setCor(int cor){
		this.cor=cor;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	
	public int getCor(){
		return this.cor;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
	public void setCapturada(boolean b){
		this.capturada=b;
	}
	public boolean isCapturada(){
		return this.capturada;
	}
	
	public abstract String printPeca();
	
	public abstract boolean isMovValido(Tabuleiro t,int toX, int toY);
	
	public abstract Move[] movimentosPossiveis(Tabuleiro t);

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	public void decNumMov() {
		this.numDeMov--;
	}

}