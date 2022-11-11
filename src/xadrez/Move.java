package xadrez;

public class Move{
	public int fromX,fromY,toX,toY;
	
	public Move(int fromX,int fromY,int toX,int toY){
		this.fromX=fromX;
		this.fromY=fromY;
		this.toX=toX;
		this.toY=toY;
	}
	public void printMove() {
		System.out.println("de ("+this.fromX+","+this.fromY+") para ("+this.toX+","+this.toY+")");
	}
}