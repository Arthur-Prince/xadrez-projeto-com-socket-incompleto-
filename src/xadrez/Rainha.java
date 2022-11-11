package xadrez;

public class Rainha extends Peca {

	public Rainha(int cor, int x, int y) {
		super(cor, x, y);
		this.setValor(1000);
		this.numDeMov=0;;
	}

	@Override
	public boolean isMovValido(Tabuleiro t, int toX, int toY) {
		int xOffset = toX - this.getX();
		int yOffset = toY - this.getY();
		/*
		 * 0-nao pode mover 1-para sul 5-para norte 3-para oeste 7-para leste 4-noroeste
		 * 2-suldoeste 6-nordeste 8-suldeste
		 */
		int dir = 0;
		// dir=direcao
		if (xOffset == 0 && yOffset == 0) {
			return false;
		}

		if ((xOffset == 0 && yOffset != 0) || (xOffset != 0 && yOffset == 0)) {
			if (xOffset == 0) {
				if (yOffset < 0) {
					dir = 1;
				} else if (yOffset > 0) {
					dir = 5;
				}
			}
			if (yOffset == 0) {

				if (xOffset > 0) {
					dir = 3;
				} else if (xOffset < 0) {
					dir = 7;
				}

			}

		} else if (Math.abs(xOffset) == Math.abs(yOffset)) {
			if (xOffset > 0 && yOffset > 0) {
				dir = 4;
			} else if (xOffset > 0 && yOffset < 0) {
				dir = 2;
			} else if (xOffset < 0 && yOffset > 0) {
				dir = 6;
			} else {
				dir = 8;
			}
		} else {
			return false;
		}

		int x = this.getX();
		int y = this.getY();

		// marca o caminho q a rainha vai ter q fazer
		// se encontrar alguma peca pare nela
		while (!(x == toX && y == toY)) {
			switch (dir) {
			case 1:
				y--;
				break;
			case 3:
				x++;
				break;
			case 5:
				y++;
				break;
			case 7:
				x--;
				break;
			case 2:
				y--;
				x++;
				break;
			case 4:
				x++;
				y++;
				break;
			case 6:
				x--;
				y++;
				break;
			case 8:
				x--;
				y--;
				break;
			}
			if (t.lugares[x][y].isOcupado()) {
				break;
			}
		}
		// retorna true so se tiver uma peca da cor oposta no destino ou se n tiver peca
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
			return "Q";
		} else {
			return "q";
		}
	}

	@Override
	public Move[] movimentosPossiveis(Tabuleiro t) {
		int Ox = this.getX();
		int Oy = this.getY();
		int x = Ox;
		int y = Oy;
		Move[] movimentos = new Move[27];
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
		x = Ox;
		// pega os movimentos nordeste
		while (x < 8 && y < 8) {
			x++;
			y++;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
			contador++;

		}

		x = Ox;
		y = Oy;

		// pega os movimentos suldeste
		while (x < 8 && y > 1) {
			x++;
			y--;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
			contador++;

		}
		x = Ox;
		y = Oy;

		// pega os movimentos noroeste
		while (x > 1 && y < 8) {
			x--;
			y++;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
			contador++;

		}
		x = Ox;
		y = Oy;

		// pega os movimentos suldoeste
		while (x > 1 && y > 1) {
			x--;
			y--;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
					contador++;

				}
				break;
			}
			movimentos[contador] = new Move(this.getX(), this.getY(), x, y);
			contador++;

		}
		return movimentos;
	}

}