package xadrez;

public class Rei extends Peca {

	public Rei(int cor, int x, int y) {
		super(cor, x, y);
		this.setValor(64000);
		this.numDeMov = 0;

	}

	@Override
	public boolean isMovValido(Tabuleiro t, int toX, int toY) {
		int xOffset = Math.abs(this.getX() - toX);
		int yOffset = Math.abs(this.getY() - toY);
		// roque

		int x = this.getX();
		int y = this.getY();
		if (this.getCor() == 0 && this.numDeMov == 0) {
			if (x == 5 && y == 8) {
				// roque pequeno(branco)
				if (toY == 8 && toX == 7) {
					Peca torre = t.lugares[8][8].getPeca();
					if (torre != null) {

						String testaTorre = torre.printPeca();

						if (testaTorre.equals("t") && torre.numDeMov == 0) {
							if (torre.isMovValido(t, 6, 8) && !t.lugares[6][8].isOcupado()) {
								Move m = new Move(8, 8, 6, 8);
								t.moverPeca(m);
								this.numDeMov++;
								return true;

							}
						}
					}
				}
				// roque grande(branco)
				if (toX == 3 && toY == 8) {
					Peca torre = t.lugares[1][8].getPeca();
					if (torre != null) {
						String testaTorre = torre.printPeca();

						if (testaTorre.equals("t") && torre.numDeMov == 0) {
							if (torre.isMovValido(t, 4, 8) && !t.lugares[4][8].isOcupado()) {
								Move m = new Move(1, 8, 4, 8);
								t.moverPeca(m);
								this.numDeMov++;
								return true;

							}
						}
					}
				}
			}
		} else {
			// pretas
			if (this.numDeMov == 0)
				if (x == 5 && y == 1) {
					// roque pequeno
					if (toX == 7 && toY == 1) {
						Peca torre = t.lugares[8][1].getPeca();
						if (torre != null) {
							String testaTorre = torre.printPeca();

							if (testaTorre.equals("T") && torre.numDeMov == 0) {
								if (torre.isMovValido(t, 6, 1) && !t.lugares[6][1].isOcupado()) {
									Move m = new Move(8, 1, 6, 1);
									t.moverPeca(m);
									this.numDeMov++;
									return true;

								}
							}
						}
					}
					// roque grande
					if (toX == 3 && toY == 1) {
						Peca torre = t.lugares[1][1].getPeca();
						if (torre != null) {
							String testaTorre = torre.printPeca();

							if (testaTorre.equals("T") && torre.numDeMov == 0) {
								if (torre.isMovValido(t, 4, 1) && !t.lugares[4][1].isOcupado()) {
									Move m = new Move(1, 1, 4, 1);
									t.moverPeca(m);
									this.numDeMov++;
									return true;

								}
							}
						}
					}
				}
		}

		// movimento comun do rei
		if (xOffset == 1 || yOffset == 1) {
			if (t.lugares[toX][toY].isOcupado() && t.lugares[toX][toY].getPeca().getCor() == this.getCor()) {
				return false;
			}
			this.numDeMov++;
			return true;
		}

		return false;
	}

	@Override
	public String printPeca() {
		if (this.getCor() == 1) {
			return "K";
		} else {
			return "k";
		}
	}

	@Override
	public Move[] movimentosPossiveis(Tabuleiro t) {
		Move[] movimentos = new Move[8];
		int contador = 0;
		int x = this.getX();
		int y = this.getY();
		if (this.getCor() == 0) {
			if (x == 5 && y == 8) {

				Peca torre = t.lugares[8][8].getPeca();
				if (torre != null) {
					String testaTorre = torre.printPeca();

					if (testaTorre.equals("t")) {
						// roque pequeno(branco)
						if (torre.isMovValido(t, 6, 8) && !t.lugares[6][8].isOcupado()) {
							Move m = new Move(5, 8, 7, 8);
							movimentos[contador] = m;
							contador++;

						}
					}
				}
				torre = t.lugares[1][8].getPeca();
				if (torre != null) {
					String testaTorre = torre.printPeca();
					// roque grande(branco)
					if (testaTorre.equals("t"))
						if (torre.isMovValido(t, 4, 8) && !t.lugares[4][8].isOcupado()) {
							Move m = new Move(5, 8, 3, 8);
							movimentos[contador] = m;
							contador++;

						}
				}

			}
		} else {
			// pretas
			if (x == 5 && y == 1) {

				Peca torre = t.lugares[8][1].getPeca();
				if (torre != null) {
					String testaTorre = torre.printPeca();

					if (testaTorre.equals("T")) {
						// roque pequeno
						if (torre.isMovValido(t, 6, 1) && !t.lugares[6][1].isOcupado()) {
							Move m = new Move(5, 1, 7, 1);
							movimentos[contador] = m;
							contador++;

						}
					}
				}
				torre = t.lugares[1][1].getPeca();
				if (torre != null) {
					String testaTorre = torre.printPeca();

					if (testaTorre.equals("T")) {
						// roque grande
						if (torre.isMovValido(t, 4, 1) && !t.lugares[4][1].isOcupado()) {
							Move m = new Move(3, 1, 7, 1);
							movimentos[contador] = m;
							contador++;
						}
					}
				}

			}
		}

		int Ox = x;
		int Oy = y;
		int[] v = { 0, 1, -1 };

		// movimentos normais
		for (int i = 0; i < 3; i++) {
			x += v[i];
			for (int j = 0; j < 3; j++) {
				y += v[j];
				if (x <= 8 && x >= 1 && y <= 8 && y >= 1) {
					if (t.lugares[x][y].isOcupado()) {
						if (t.lugares[x][y].getPeca().getCor() != this.getCor()) {
							movimentos[contador] = new Move(Ox, Oy, x, y);
							contador++;

						}
					} else {
						movimentos[contador] = new Move(Ox, Oy, x, y);
						contador++;

					}
				}
				y = Oy;

			}
			x = Ox;
		}

		return movimentos;
	}

}
