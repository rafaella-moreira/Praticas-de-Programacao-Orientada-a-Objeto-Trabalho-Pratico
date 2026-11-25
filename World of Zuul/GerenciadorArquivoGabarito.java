import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GerenciadorArquivoGabarito {
    private Gabarito gabarito;

    public GerenciadorArquivoGabarito(Gabarito gabarito) {
        this.gabarito = gabarito;
    }

    //escrever o gabarito em um arquivo
    public void escreverGabarito(String nomeArquivo) throws ArquivoInvalidoException {
        try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(nomeArquivo), StandardCharsets.UTF_8)) {
            escritor.write("# Gabarito do jogo (gerado automaticamente)");
            escritor.newLine();
            escritor.write("[TESOURO]");
            escritor.newLine();
            escritor.write(gabarito.getTesouro().getLocalizacaoTesouro().getDescricao());
            escritor.newLine();
            escritor.write("[CHAVE_MESTRA]");
            escritor.newLine();
            escritor.write(gabarito.getChaveMestra().getLocalizacaoChaveMestra().getDescricao());
            escritor.newLine();
            escritor.write("# ambiente indicado (onde o tesouro NAO esta);texto da dica");
            escritor.newLine();
            escritor.write("[DICAS]");
            escritor.newLine();
            for (Dica dica : gabarito.getDicas()) {
                escritor.write(dica.getAmbienteIndicado().getDescricao() + ";" + dica.getTexto());
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new ArquivoInvalidoException("Nao foi possivel gravar o gabarito em '" + nomeArquivo + "'.", e);
        }
    }

}
