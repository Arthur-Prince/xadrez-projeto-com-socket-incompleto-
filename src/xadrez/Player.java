package xadrez;

public class Player {
	private String nome;
	private int cor;
	private boolean xeque;
	private Canvas inter;

	public Peca[] pecas = new Peca[17];
	public Peca rei;

	public Player(int cor, String nome, Canvas inter) {
		this.nome = nome;
		this.cor = cor;
		this.xeque = false;
		this.inter = inter;

		int c;
		for (c = 1; c < 17; c++) {
			this.pecas[c] = null;
		}
		// monta o tabuleiro padrao
		c = 1;
		if (this.cor == 1) {
			this.pecas[c] = (new Torre(this.cor, c++, 1));
			this.pecas[c] = (new Cavalo(this.cor, c++, 1));
			this.pecas[c] = (new Bispo(this.cor, c++, 1));
			this.pecas[c] = (new Rainha(this.cor, c++, 1));
			this.rei = new Rei(this.cor, c, 1);
			this.pecas[c++] = (this.rei);
			this.pecas[c] = (new Bispo(this.cor, c++, 1));
			this.pecas[c] = (new Cavalo(this.cor, c++, 1));
			this.pecas[c] = (new Torre(this.cor, c++, 1));

			for (c = 1; c < 9; c++) {
				this.pecas[c + 8] = new Peao(this.cor, c, 2, true, c + 8);
			}
		} else {
			this.pecas[c] = (new Torre(this.cor, c++, 8));
			this.pecas[c] = (new Cavalo(this.cor, c++, 8));
			this.pecas[c] = (new Bispo(this.cor, c++, 8));
			this.pecas[c] = (new Rainha(this.cor, c++, 8));
			this.rei = new Rei(this.cor, c, 8);
			this.pecas[c++] = (this.rei);
			this.pecas[c] = (new Bispo(this.cor, c++, 8));
			this.pecas[c] = (new Cavalo(this.cor, c++, 8));
			this.pecas[c] = (new Torre(this.cor, c++, 8));

			for (c = 1; c < 9; c++) {
				this.pecas[c + 8] = new Peao(this.cor, c, 7, false, c + 8);
			}
		}
	}

	Player(Peca p, int cor) {

		this.cor = cor;
		pecas[1] = p;
	}

	Player(Peca p1, Peca p2) {
		pecas[1] = p1;
		pecas[2] = p2;
	}

	public void setXeque(boolean b) {
		this.xeque = b;
	}

	public boolean isXeque() {
		return this.xeque;
	}

	public boolean isXequeMate() {
		return this.rei.isCapturada();
	}

	public int getCor() {
		return this.cor;
	}

	public String printNome() {
		return this.nome;
	}

	public Move gerarMov() {

		this.inter.setReady(true);
		while (this.inter.isReady()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}

		Move m = this.inter.getMove();
		return m;
	}

	public Canvas getInter() {
		return this.inter;
	}

	// ve se algum piao virou dama(lembrar de troca 1 por 9)
	public Peca houveTransformacao() {
		if (this.cor == 1) {
			for (int i = 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("P"))
						return pecas[i];
			}
		} else {
			for (int i = 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("p"))
						return pecas[i];
			}
		}
		return null;
	}

	public Peca houveTransformacaoDado(int j) {
		if (this.cor == 1) {
			for (int i = j + 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("P"))
						return pecas[i];
			}
		} else {
			for (int i = j + 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("p"))
						return pecas[i];
			}
		}
		return null;
	}

	// retorna o indice(lembrar de troca 1 por 9)
	public int buscaIndiceDaPecaTransformada() {
		if (this.cor == 1) {
			for (int i = 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("P"))
						return i;
			}
		} else {
			for (int i = 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("p"))
						return i;
			}
		}
		return 0;
	}

	public int buscaIndiceDaPecaTransformadaDado(int j) {
		if (this.cor == 1) {
			for (int i = j + 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("P"))
						return i;
			}
		} else {
			for (int i = j + 1; i < 16; i++) {
				if (pecas[i] != null)
					if (!pecas[i].printPeca().equals("p"))
						return i;
			}
		}
		return 0;
	}
}
