package xadrez;

public class Torre extends Peca {

	public Torre(int cor, int x, int y) {
		super(cor, x, y);
		this.setValor(500);
		this.numDeMov=0;

	}

	@Override
	public boolean isMovValido(Tabuleiro t, int toX, int toY) {
		int xOffset = this.getX() - toX;
		int yOffset = this.getY() - toY;
		int dir = 0;

		if ((xOffset != 0 && yOffset != 0) || (xOffset == 0 && yOffset == 0)) {

			return false;
		}

		if (xOffset == 0) {
			if (yOffset > 0) {
				dir = 1;
			} else if (yOffset < 0) {
				dir = 3;
			}
		}

		if (yOffset == 0) {

			if (xOffset > 0) {
				dir = 4;
			} else if (xOffset < 0) {
				dir = 2;
			}

		}

		int x = this.getX(), y = this.getY();

		while (!(x == toX && y == toY)) {
			switch (dir) {
			case 1:
				y--;
				break;
			case 2:
				x++;
				break;
			case 3:
				y++;
				break;
			case 4:
				x--;
				break;
			}
			if (t.lugares[x][y].isOcupado()) {
				break;
			}
		}

		if (x == toX && y == toY) {
			if (!t.lugares[x][y].isOcupado()) {
				this.numDeMov++;
				return true;
			}

			if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
				this.numDeMov++;
				return true;
			}
		}
		return false;

	}

	@Override
	public String printPeca() {
		if (this.getCor() == 1) {
			return "T";
		} else {
			return "t";
		}
	}

	@Override
	public Move[] movimentosPossiveis(Tabuleiro t) {
		int Ox = this.getX();
		int Oy = this.getY();
		int x = Ox;
		int y = Oy;
		Move[] movimentos = new Move[14];

		int contador = 0;
		// pega os movimentos norte
		while (y < 8) {

			y++;
			if (t.lugares[Ox][y].isOcupado()) {
				if (t.lugares[Ox][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(this.getX(), this.getY(), Ox, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(Ox, Oy, Ox, y);
			contador++;

		}
		y = Oy;

		// pega os movimentos sul
		while (y > 1) {

			y--;
			if (t.lugares[Ox][y].isOcupado()) {
				if (t.lugares[Ox][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, Ox, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(this.getX(), this.getY(), Ox, y);
			contador++;

		}
		y = Oy;

		// pega os movimentos oeste
		while (x > 1) {
			x--;
			if (t.lugares[x][Oy].isOcupado()) {
				if (t.lugares[x][Oy].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, x, Oy);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(Ox, Oy, x, y);
			contador++;

		}
		x = Ox;

		// pega os movimentos leste
		while (x < 8) {
			x++;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, x, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(Ox, Oy, x, y);
			contador++;

		}

		
		return movimentos;
	}

}