public class Programa {

	public static void main(String[] args) {
		try {
			Jogo jogo = new Jogo();
			jogo.jogar();
		} catch (ArquivoInvalidoException e) {
			System.err.println("Erro ao iniciar o jogo: " + e.getMessage());
		}
	}

}
