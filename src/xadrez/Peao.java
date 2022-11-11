package xadrez;


public class Peao extends Peca {
	private boolean paraBaixo;
	public int marca;

	public Peao(int cor, int x, int y, boolean paraBaixo, int marca) {
		super(cor, x, y);
		this.paraBaixo = paraBaixo;
		this.marca = marca;
		this.setValor(100);
		this.numDeMov=0;
	}

	@Override
	public boolean isMovValido(Tabuleiro t, int toX, int toY) {

		if (paraBaixo) {
			if (toY <= this.getY()) {
				return false;
			}

			if (toX == this.getX()) {

				if (this.getY() == 2) {
					if ((toY - this.getY()) == 1 && !t.lugares[toX][toY].isOcupado()) {
						this.numDeMov++;
						return true;
					} else if ((toY - this.getY()) == 2 && !t.lugares[toX][toY].isOcupado()
							&& !t.lugares[toX][toY - 1].isOcupado()) {
						this.numDeMov++;
						return true;
					}
				} else if ((toY - this.getY()) == 1 && !t.lugares[toX][toY].isOcupado()) {
					if (toY == 8) {
						transformacao(t);
					}
					this.numDeMov++;
					return true;
				}

			} else {

				if ((toY - this.getY() == 1) && Math.abs(this.getX() - toX) == 1) {
					if (t.lugares[toX][toY].isOcupado() && t.lugares[toX][toY].getPeca().getCor() != this.getCor()) {
						if (toY == 8) {
							transformacao(t);
						}
						this.numDeMov++;
						return true;
					}
				}
			}

		} else {
			if (toY >= this.getY()) {
				return false;
			}

			if (toX == this.getX()) {
				if (this.getY() == 7) {
					if ((toY - this.getY()) == -1 && !t.lugares[toX][toY].isOcupado()) {
						this.numDeMov++;
						return true;
					} else if ((toY - this.getY()) == -2 && !t.lugares[toX][toY].isOcupado()
							&& !t.lugares[toX][toY + 1].isOcupado()) {
						this.numDeMov++;
						return true;
					}
				} else if ((toY - this.getY()) == -1 && !t.lugares[toX][toY].isOcupado()) {
					if (toY == 1) {
						transformacao(t);
					}
					this.numDeMov++;
					return true;
				}

			} else {
				if ((toY - this.getY() == -1) && Math.abs(this.getX() - toX) == 1) {
					if (t.lugares[toX][toY].isOcupado() && t.lugares[toX][toY].getPeca().getCor() != this.getCor()) {
						if (toY == 1) {
							transformacao(t);
						}
						this.numDeMov++;
						return true;
					}
				}
			}

		}

		return false;

	}

	@Override
	public String printPeca() {
		if (this.getCor() == 1) {
			return "P";
		} else {
			return "p";
		}
	}

	@Override
	public Move[] movimentosPossiveis(Tabuleiro t) {
		int Ox = this.getX();
		int Oy = this.getY();
		int x = Ox;
		int y = Oy;
		Move[] movimentos = new Move[4];

		int contador = 0;

		if (paraBaixo) {// pretas
			// anda para frente(x nao muda)
			y++;
			if (!t.lugares[x][y].isOcupado()) {
				movimentos[contador] = new Move(Ox, Oy, x, y);
				contador++;
				if (Oy == 2) {
					y++;
					if (!t.lugares[x][y].isOcupado()) {
						movimentos[contador] = new Move(Ox, Oy, x, y);
						contador++;
					}
					y--;
				}

			}
			// anda na diagonal
			x++;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, x, y);
					contador++;
				}
			}
			x = Ox - 1;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, x, y);
					contador++;
				}
			}

		} else {// brancas
			y--;
			if (!t.lugares[x][y].isOcupado()) {
				movimentos[contador] = new Move(Ox, Oy, x, y);
				contador++;
				if (Oy == 7) {
					y--;
					if (!t.lugares[x][y].isOcupado()) {
						movimentos[contador] = new Move(Ox, Oy, x, y);
						contador++;
					}
					y++;
				}

			}
			// anda na diagonal
			x++;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, x, y);
					contador++;
				}
			}
			x = Ox - 1;
			if (t.lugares[x][y].isOcupado()) {
				if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
					movimentos[contador] = new Move(Ox, Oy, x, y);
					contador++;
				}
			}

		}

		return movimentos;
	}
	//muda a peca para uma dama
	private void transformacao(Tabuleiro t) {
		

		int x = this.getX();
		int y = this.getY();
		System.out.println(y);
		
		Peca d = new Rainha(this.getCor(), x, y);
		t.lugares[x][y].setPeca(d);
		t.atual.pecas[this.marca] = d;
		

	}
	

}