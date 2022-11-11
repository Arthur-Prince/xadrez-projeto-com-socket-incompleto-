package xadrez;

public class Bispo extends Peca{
	
	
	public Bispo(int cor,int x,int y){
		super(cor,x,y);
		this.setValor(300);
		this.numDeMov=0;
	}
	
	@Override
	public boolean isMovValido(Tabuleiro t,int toX, int toY){
		int xOffset=toX-this.getX();
		int yOffset=toY-this.getY();
		int dir=0;
		
		if((Math.abs(xOffset)!=Math.abs(yOffset)) || (xOffset==0 && yOffset==0)){
			return false;
		}
		
		if(xOffset>0 && yOffset>0 ){
			dir =1;
		}else if(xOffset>0 && yOffset<0){
			dir=4;
		}else if(xOffset<0 && yOffset>0){
			dir=2;
		}else{
			dir=3;
		}
		
		
		int x=this.getX();
		int y=this.getY();
		
		
		
		
		while(!(x==toX && y==toY)){
			switch(dir){
				case 1:
					y++;
					x++;
				break;
				case 2:
					x--;
					y++;
				break;
				case 3:
					x--;
					y--;
				break;
				case 4:
					x++;
					y--;
				break;
			}
			if(t.lugares[x][y].isOcupado()){
				break;
			}			
		}
		
		if(x==toX && y==toY){
			if(!t.lugares[x][y].isOcupado()){
				this.numDeMov++;
				return true;
			}

			if(t.lugares[x][y].getPeca().getCor()!=this.getCor()){
				this.numDeMov++;
				return true;
			}
		}
		return false;
		
	}
	
	
	@Override
	public String printPeca(){
		if(this.getCor()==1){
			return "B";
		}else{
			return "b";
		}
	}

	@Override
	public Move[] movimentosPossiveis(Tabuleiro t) {
		int Ox=this.getX();
		int Oy=this.getY();
		int x=Ox;
		int y=Oy;
		Move[] movimentos = new Move[13];
		
		int contador=0;
		//pega os movimentos nordeste
		while(x<8&&y<8) {
			x++;
			y++;
			if(t.lugares[x][y].isOcupado()) {
				if(t.lugares[x][y].getPeca().getCor()!=this.getCor()){
					movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
					contador++;
					
				}
				break;
			}
			movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
			contador++;
			
		}
		
		x=Ox;
		y=Oy;
		
		//pega os movimentos suldeste
		while(x<8&&y>1) {
			x++;
			y--;
			if(t.lugares[x][y].isOcupado()) {
				if(t.lugares[x][y].getPeca().getCor()!=this.getCor()){
					movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
					contador++;
					
				}
				break;
			}
			movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
			contador++;
					
		}
		x=Ox;
		y=Oy;
		
		//pega os movimentos noroeste
		while(x>1&&y<8) {
			x--;
			y++;
			if(t.lugares[x][y].isOcupado()) {
				if(t.lugares[x][y].getPeca().getCor()!=this.getCor()){
					movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
					contador++;
				}
				break;
			}
			movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
			contador++;
					
		}
		x=Ox;
		y=Oy;
		
		//pega os movimentos suldoeste
		while(x>1&&y>1) {
			x--;
			y--;
			if(t.lugares[x][y].isOcupado()) {
				if(t.lugares[x][y].getPeca().getCor()!=this.getCor()){
					movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
					contador++;
				}
				break;
			}
			movimentos[contador]=new Move(this.getX(),this.getY(),x,y);
			contador++;
					
		}
		
		
		return movimentos;
	}
	
}