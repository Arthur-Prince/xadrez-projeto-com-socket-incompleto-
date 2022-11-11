package xadrez;

public class Cavalo extends Peca {

	public Cavalo(int cor, int x, int y) {
		super(cor, x, y);
		this.setValor(300);
		this.numDeMov=0;

	}

	@Override
	public boolean isMovValido(Tabuleiro t, int toX, int toY) {

		int xOffset = Math.abs(this.getX() - toX);
		int yOffset = Math.abs(this.getY() - toY);

		if ((xOffset == 1 && yOffset == 2) || (xOffset == 2 && yOffset == 1)) {
			if (t.lugares[toX][toY].isOcupado()) {
				if (t.lugares[toX][toY].getPeca().getCor() != this.getCor()) {
					this.numDeMov++;
					return true;
				}
			} else {
				this.numDeMov++;
				return true;
			}
		}

		return false;

	}

	@Override
	public String printPeca() {
		if (this.getCor() == 1) {
			return "H";
		} else {
			return "h";
		}
	}

	@Override
	public Move[] movimentosPossiveis(Tabuleiro t) {
		int Ox = this.getX();
		int Oy = this.getY();
		Move[] movimentos = new Move[8];
		int contador = 0;
		int[] dif = { 1, -1, 2, -2 };

		for (int i = 0; i < 2; i++) {
			int[] xy = { Ox, Oy };
			// soma 1 ao x ou y
			xy[i] = dif[0] + xy[i];

			for (int j = 0; j < 2; j++) {
				// soma 2 ou menos 2 ao y ou x
				xy[(i + 1) % 2] = dif[j + 2] + xy[(i + 1) % 2];

				if (xy[0] < 9 && xy[0] > 0 && xy[1] < 9 && xy[1] > 0) {
					if (t.lugares[xy[0]][xy[1]].isOcupado()) {
						if (t.lugares[xy[0]][xy[1]].getPeca().getCor() != this.getCor()) {
							movimentos[contador] = new Move(Ox, Oy, xy[0], xy[1]);
							contador++;
							
						}
					} else {

						movimentos[contador] = new Move(Ox, Oy, xy[0], xy[1]);
						contador++;
						
					}
				}
				xy[(i + 1) % 2] = -dif[j + 2] + xy[(i + 1) % 2];
			}
			xy[0] = Ox;
			xy[1] = Oy;
			// subtrai 1 ao x ou y
			xy[i] += dif[1];

			for (int j = 0; j < 2; j++) {
				// soma 2 ou menos 2 ao y ou x
				xy[(i + 1) % 2] = dif[j + 2] + xy[(i + 1) % 2];

				if (xy[0] < 9 && xy[0] > 0 && xy[1] < 9 && xy[1] > 0) {
					if (t.lugares[xy[0]][xy[1]].isOcupado()) {
						if (t.lugares[xy[0]][xy[1]].getPeca().getCor() != this.getCor()) {
							movimentos[contador] = new Move(Ox, Oy, xy[0], xy[1]);
							contador++;
							
						}
					} else {
						movimentos[contador] = new Move(Ox, Oy, xy[0], xy[1]);
						contador++;
						
					}
				}
				xy[(i + 1) % 2] = -dif[j + 2] + xy[(i + 1) % 2];
			}

		}

		
		return movimentos;
	}

}
