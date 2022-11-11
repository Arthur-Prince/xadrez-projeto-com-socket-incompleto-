package xadrez;

public class Tabuleiro {
	public Lugar[][] lugares = new Lugar[9][9];
	private boolean xequeMate;
	private int vez;
	public Player p1, p0;
	public Player atual;

	public Tabuleiro(Player p1, Player p0) {

		this.p1 = p1;
		this.p0 = p0;
		this.vez = 0;
		this.atual = p0;
		this.xequeMate = false;
		this.inicializarTabuleiro();
	}

	public void inicializarTabuleiro() {
		for (int c = 1; c < 9; c++) {
			for (int d = 1; d < 9; d++) {
				this.lugares[c][d] = new Lugar();
			}
		}
		Lugar l = null;

		Player p = this.p1;

		for (int c = 1; c < p.pecas.length; c++) {
			Peca peca = p.pecas[c];
			if (peca != null) {
				l = this.lugares[peca.getX()][peca.getY()];
				l.setPeca(peca);
			}
		}

		p = this.p0;

		for (int c = 1; c < p.pecas.length; c++) {
			Peca peca = p.pecas[c];
			if (peca != null) {
				l = this.lugares[peca.getX()][peca.getY()];
				l.setPeca(peca);
			}
		}

	}

	public boolean nextMov() {
		Move m = this.atual.gerarMov();
		Lugar l = this.lugares[m.fromX][m.fromY];
		if (l.isOcupado()) {
			Peca p = l.getPeca();
			if (p.getCor() == this.vez) {

				if (this.moverPeca(m)) {

					this.testaXeque();

					this.testaXequeMate();

					return true;
				}
			}
		}
		return false;
	}

	public boolean moverPeca(Move m) {
		Lugar l1 = this.lugares[m.fromX][m.fromY];
		Peca p = l1.getPeca();
		if (p.isMovValido(this, m.toX, m.toY)) {
			Peca p1 = l1.getPeca();
			p1.setX(m.toX);
			p1.setY(m.toY);
			l1.setPeca(null);
			l1.setOcupado(false);
			if (this.lugares[m.toX][m.toY].isOcupado()) {
				Peca p2 = this.lugares[m.toX][m.toY].getPeca();
				p2.setCapturada(true);
			}
			this.lugares[m.toX][m.toY].setPeca(p1);

			return true;
		}
		return false;
	}

	public void trocarVez() {
		if (this.vez == 1) {
			this.vez = 0;
			this.atual = this.p0;
		} else {
			this.vez = 1;
			this.atual = this.p1;
		}
	}

	public void testaXeque() {
		Player inimigo = null;
		Player atual = null;
		boolean xeque = false;

		atual = this.p1;
		inimigo = this.p0;

		for (int c = 1; c < 17; c++) {
			Peca pa = atual.pecas[c];
			if (pa != null && !pa.isCapturada() && pa.isMovValido(this, inimigo.rei.getX(), inimigo.rei.getY())) {
				xeque = true;
				break;
			}
		}
		if (xeque) {
			inimigo.setXeque(true);
		} else {
			inimigo.setXeque(false);
		}

		atual = this.p0;
		inimigo = this.p1;
		xeque = false;

		for (int c = 1; c < 17; c++) {
			Peca pa = atual.pecas[c];
			if (pa != null && !pa.isCapturada() && pa.isMovValido(this, inimigo.rei.getX(), inimigo.rei.getY())) {
				xeque = true;
				break;
			}
		}
		if (xeque) {
			inimigo.setXeque(true);
		} else {
			inimigo.setXeque(false);
		}
	}

	public void testaXequeMate() {
		this.xequeMate = (this.p0.rei.isCapturada() || this.p1.rei.isCapturada());
	}

	public boolean isXequeMate() {
		return this.xequeMate;
	}

	public Player getPlayerAtual() {
		if (vez == 1) {
			return this.p1;
		} else {
			return this.p0;
		}
	}

	public void desenharTabuleiro(Canvas can) {
		can.draw(this);
	}

	public void desFazMove(Move m, Peca p) {
		desfazRoque(m);
		if (this.atual.getCor() == 1) {
			if (m.fromY == 7 && m.toY == 8)
				desTransforma(m);
		} else {
			if (m.fromY == 2 && m.toY == 1)
				desTransforma(m);
		}

		Lugar l1 = this.lugares[m.toX][m.toY];
		Peca p1 = l1.getPeca();
		if (p1 != null) {
			p1.setX(m.fromX);
			p1.setY(m.fromY);
			this.lugares[m.fromX][m.fromY].setPeca(p1);
		}
		if (p == null) {
			l1.setPeca(null);
			l1.setOcupado(false);
		} else {
			p.setCapturada(false);
			l1.setPeca(p);
		}

	}

	private void desTransforma(Move m) {
		Peca p = this.atual.houveTransformacao();
		int i = this.atual.buscaIndiceDaPecaTransformada();
		while (p != null) {

			if (p.getX() == m.toX && p.getY() == m.toY) {
				if (p.numDeMov == 0) {

					boolean paraBaixo;
					if (p.getCor() == 1)
						paraBaixo = true;
					else
						paraBaixo = false;
					Peca piao = new Peao(p.getCor(), m.fromX, m.fromY, paraBaixo, i);
					this.lugares[m.fromX][m.fromY].setPeca(piao);
					this.lugares[m.toX][m.toY].setPeca(null);
					this.lugares[m.toX][m.toY].setOcupado(false);
					this.atual.pecas[i] = piao;
				}
			}
			p = this.atual.houveTransformacaoDado(i);
			i = this.atual.buscaIndiceDaPecaTransformadaDado(i);
		}

	}

	public void imprimeTab() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				Peca p = this.lugares[i][j].getPeca();
				if (p != null)
					System.out.print(p.printPeca() + "|");
				else {
					System.out.print(0 + "|");
				}
			}
			System.out.println("\n------------------");
		}
	}

	public Peca getPecaDestino(Move m) {
		return this.lugares[m.toX][m.toY].getPeca();
	}

	public Peca getPecaorigem(Move m) {
		return this.lugares[m.fromX][m.fromY].getPeca();
	}

	public boolean houveTransformacao(Tabuleiro copia, Move m) {

		return true;
	}

	private void desfazRoque(Move m) {
		Peca rei = this.lugares[m.toX][m.toY].getPeca();
		if (m.toY == 1 && m.fromX == 5 && rei.getCor() == 1 && m.fromY == 1) {
			if (rei.printPeca().equals("K")) {
				if(m.toX==7) {//roque pequeno
					Peca p1 = this.lugares[1][6].getPeca();
					p1.setX(1);
					p1.setY(8);
					this.lugares[1][6].setPeca(null);
					this.lugares[1][6].setOcupado(false);
					this.lugares[1][8].setPeca(p1);
				}
				if(m.toX==3) {//roque grande
					Peca p1 = this.lugares[1][4].getPeca();
					p1.setX(1);
					p1.setY(1);
					this.lugares[1][4].setPeca(null);
					this.lugares[1][4].setOcupado(false);
					this.lugares[1][1].setPeca(p1);
				}
			}
		}
		if (m.toY == 8 && m.fromX == 5 && rei.getCor() == 0 && m.fromY == 8) {
			if (rei.printPeca().equals("k")) {
				if(m.toX==7) {//roque pequeno
					Peca p1 = this.lugares[1][6].getPeca();
					p1.setX(8);
					p1.setY(8);
					this.lugares[8][6].setPeca(null);
					this.lugares[8][6].setOcupado(false);
					this.lugares[8][8].setPeca(p1);
				}
				if(m.toX==3) {//roque grande
					Peca p1 = this.lugares[1][4].getPeca();
					p1.setX(8);
					p1.setY(1);
					this.lugares[8][4].setPeca(null);
					this.lugares[8][4].setOcupado(false);
					this.lugares[8][1].setPeca(p1);
				}
			}
		}
	}
}
