package engine;

import xadrez.*;

public class Avaliador {
	private Tabuleiro tab;
	private Move[][] moves;
	public float[][] avaliacoes;

	Avaliador(Tabuleiro t) {
		this.tab = t;

	}

	// gera todas as variantes possiveis
	void getMoves() {
		moves = new Move[18][];
		for (int i = 0; i < 17; i++) {
			Peca p = tab.atual.pecas[i];
			moves[i] = p.movimentosPossiveis(tab);
		}
		Peca p = tab.atual.rei;
		moves[17] = p.movimentosPossiveis(tab);

	}

	// pega o valor das pecas dos players e soma
	public int getValores(Tabuleiro t) {
		int rtn = 0;
		Peca p = t.atual.rei;
		if (!p.isCapturada()) {
			rtn += p.getValor();
		}
		for (int i = 0; i < 17; i++) {

			p = t.atual.pecas[i];
			if (!p.isCapturada()) {
				rtn = p.getValor();
			}
		}

		t.trocarVez();

		p = t.atual.rei;
		if (!p.isCapturada()) {
			rtn -= p.getValor();
		}
		for (int i = 0; i < 17; i++) {
			p = t.atual.pecas[i];
			if (!p.isCapturada()) {
				rtn -= p.getValor();
			}

		}
		t.trocarVez();
		return rtn;
	}

	// olha a posicao depois do movimento e avalia dps refaz o movimento
	float avaliaPosicao(Move m) {
		Peca p = tab.lugares[m.toX][m.toY].getPeca();
		tab.moverPeca(m);
		int v = getValores(tab);
		int valor;
		if (tab.atual.getCor() == 0) {// soma v com um numero aleatorio
			valor = (int) (v + Math.round(Math.random() * 100));
		} else {// inverte o valor de v pq esta de pretas e a avaliacao deve beneficiar as
				// brancas
			valor = (int) (Math.round(Math.random() * 100) - v);
		}

		tab.desFazMove(m, p);
		float rtn = valor / 100;

		return rtn;
	}

	public void geraAvaliacoes() {
		// inicializa avaliacoes
		int contador = 0;
		this.avaliacoes = new float[moves.length][];
		for (int i = 0; i < this.moves.length; i++) {
			for (int j = 0; i < moves[i].length; j++) {
				Move m = moves[i][j];
				if (m == null) {
					break;
				}
				contador++;
			}
			this.avaliacoes = new float[i][contador];
		}

		// adiciona o valor na variavel avaliacoes
		for (int i = 0; i < this.moves.length; i++) {
			for (int j = 0; i < avaliacoes.length; j++) {
				Move m = moves[i][j];
				this.avaliacoes[i][j] = avaliaPosicao(m);
			}
		}
	}

	// recebe o numero de lances que vc quer que retorne
	public Move[] melhoresLances(int num, int cor) {
		int[] px = new int[num];
		int[] py = new int[num];
		Move[] melhores = new Move[num];

		// seleciona os melhores lances
		if (cor == 0) {
			for (int i = 0; i < this.moves.length; i++) {
				for (int j = 0; i < avaliacoes.length; j++) {
					int contador = 0;
					float valor = this.avaliacoes[i][j];
					while (contador < num && melhores[contador] != null) {
						if (avaliacoes[px[contador]][py[contador]] >= valor)
							break;

					}
					if (contador < num) {
						movDireita(px, contador);
						movDireita(py, contador);
						movDireita(melhores, contador);
						px[contador] = i;
						py[contador] = j;
						melhores[contador] = moves[i][j];

					}
				}
			}
		} else {
			for (int i = 0; i < this.moves.length; i++) {
				for (int j = 0; i < avaliacoes.length; j++) {
					int contador = 0;
					float valor = this.avaliacoes[i][j];
					while (contador < num && melhores[contador] != null) {
						if (avaliacoes[px[contador]][py[contador]] <= valor)
							break;

					}
					if (contador < num) {
						movDireita(px, contador);
						movDireita(py, contador);
						movDireita(melhores, contador);
						px[contador] = i;
						py[contador] = j;
						melhores[contador] = moves[i][j];

					}
				}
			}
		}

		return melhores;
	}

	// move o q tem no arrajo para direita a partir da posicao i
	private void movDireita(int[] a, int i) {
		for (int j = a.length - 1; j > i + 1; j--) {
			a[j] = a[j - 1];
		}
	}

	private void movDireita(Move[] a, int i) {
		for (int j = a.length - 1; j > i + 1; j--) {
			a[j] = a[j - 1];
		}
	}
	
	/*
	 * execucao:
	 * 
	 * getMoves();
	 * geraAvaliacoes();
	 * melhoresLances(int num, int cor)
	 * cria msg envia msg
	 */
	
}
