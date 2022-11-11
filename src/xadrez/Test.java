package xadrez;

public class Test {

	public static void main(String[] args) {
		// testa se gera os movimentos certos
		Peca c1 = new Rei(0, 5, 8);
		Peca t = new Torre(0,8,8);
		Player p1 = new Player(c1,t);
		Peca c2 = new Bispo(0, 4, 7);
		Player p2 = new Player(c2,1);

		Tabuleiro tab = new Tabuleiro(p1, p2);
		tab.trocarVez();
		Move[] moves1 = c1.movimentosPossiveis(tab);
		int i = 0;
		tab.imprimeTab();
		System.out.println(moves1.length);
		Peca retorna=null;
		
		for(int j=0;j<moves1.length;j++ ) {
			if(moves1[j]!=null) {	
				System.out.print(j+"   ");
				moves1[j].printMove();
			}
		}
		//cavalo ok
		//bispo ok
		//rainha ok
		//torre ok
		
		while (i<moves1.length) {
			
			if(moves1[i]==null)
				break;
			moves1[i].printMove();
			System.out.println();
			tab.moverPeca(moves1[i]);
			if(c2.isCapturada()) {
				retorna=c2;
			}
			tab.imprimeTab();
			System.out.println();
			System.out.println("oooooohhhhh");
			tab.desFazMove(moves1[i], retorna);
			retorna=null;
			tab.imprimeTab();
			System.out.println();
			i++;
			
		}
		
		

	}

}
