import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//classe que representa a casa assombrada contendo os ambientes e as portas entre eles

public class CasaAssombrada {

    private enum Secao { NENHUMA, INICIO, AMBIENTES, PORTAS }

    // linkedHashMap para manter a ordem de inserção dos ambientes
    private final Map<String, Ambiente> ambientes = new LinkedHashMap<String, Ambiente>();
    // colecao centralizada de portas: cada porta compartilhada aparece uma unica vez
    private final List<Porta> portas = new ArrayList<Porta>();
    private Ambiente ambienteInicial;

    
    public CasaAssombrada(String nomeArquivo) throws ArquivoInvalidoException {
        carregar(nomeArquivo);
        atualizarEstadosPortas(); // estado inicial aleatorio das portas
    }

    // retorna o ambiente inicial da casa assombrada
    public Ambiente getAmbienteInicial() {
        return ambienteInicial;
    }

    // retorna uma lista de todos os ambientes da casa assombrada
    public List<Ambiente> getAmbientes() {
        return new ArrayList<Ambiente>(ambientes.values());
    }

    // retorna uma visao somente leitura de todas as portas da casa (cada uma uma unica vez)
    public List<Porta> getPortas() {
        return Collections.unmodifiableList(portas);
    }

    // redefine aleatoriamente o estado de todas as portas (cada porta eh atualizada uma unica vez)
    public final void atualizarEstadosPortas() {
        for (Porta porta : portas) {
            porta.modificarEstadoPorta();
        }
    }

    // carrega os ambientes e portas a partir do arquivo de mapa
    private void carregar(String nomeArquivo) throws ArquivoInvalidoException {
        String nomeInicio = null;
        List<String> nomesAmbientes = new ArrayList<String>();
        List<String[]> paresPortas = new ArrayList<String[]>();

        try (BufferedReader leitor = Files.newBufferedReader(Paths.get(nomeArquivo), StandardCharsets.UTF_8)) {
            Secao secao = Secao.NENHUMA;
            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;
                if (numeroLinha == 1 && linha.startsWith("\uFEFF")) {
                    linha = linha.substring(1); // remove BOM, se existir
                }
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                if (linha.startsWith("[") && linha.endsWith("]")) {
                    secao = lerSecao(linha, numeroLinha);
                    continue;
                }

                switch (secao) {
                    case INICIO:
                        if (nomeInicio != null) {
                            throw erro(numeroLinha, "ambiente inicial definido mais de uma vez");
                        }
                        nomeInicio = linha;
                        break;
                    case AMBIENTES:
                        nomesAmbientes.add(linha);
                        break;
                    case PORTAS:
                        String[] partes = linha.split(";");
                        if (partes.length != 2 || partes[0].trim().isEmpty() || partes[1].trim().isEmpty()) {
                            throw erro(numeroLinha, "porta deve ter o formato 'ambiente1;ambiente2'");
                        }
                        paresPortas.add(new String[] { partes[0].trim(), partes[1].trim() });
                        break;
                    default:
                        throw erro(numeroLinha, "conteudo fora de uma secao");
                }
            }
        } catch (IOException e) {
            throw new ArquivoInvalidoException("Nao foi possivel ler o arquivo de mapa '" + nomeArquivo + "'.", e);
        }

        montar(nomeInicio, nomesAmbientes, paresPortas);
    }

    // le a sessao presente no arquivo
    private Secao lerSecao(String linha, int numeroLinha) throws ArquivoInvalidoException {
        String nome = linha.substring(1, linha.length() - 1).trim().toUpperCase();
        try {
            return Secao.valueOf(nome);
        } catch (IllegalArgumentException e) {
            throw erro(numeroLinha, "secao desconhecida " + linha);
        }
    }

    // Cria ambientes e portas a partir dos dados lidos 
    private void montar(String nomeInicio, List<String> nomesAmbientes, List<String[]> paresPortas)
            throws ArquivoInvalidoException {
        for (String nome : nomesAmbientes) {
            if (ambientes.containsKey(nome)) {
                throw new ArquivoInvalidoException("Mapa invalido: ambiente repetido '" + nome + "'.");
            }
            ambientes.put(nome, new Ambiente(nome));
        }
        if (ambientes.size() < 2) {
            throw new ArquivoInvalidoException("Mapa invalido: sao necessarios ao menos 2 ambientes.");
        }

        if (nomeInicio == null) {
            throw new ArquivoInvalidoException("Mapa invalido: secao [INICIO] nao definida.");
        }
        ambienteInicial = ambientes.get(nomeInicio);
        if (ambienteInicial == null) {
            throw new ArquivoInvalidoException("Mapa invalido: ambiente inicial '" + nomeInicio + "' nao existe.");
        }

        Set<String> portasCriadas = new HashSet<String>();
        for (String[] par : paresPortas) {
            Ambiente a = ambientes.get(par[0]);
            Ambiente b = ambientes.get(par[1]);
            if (a == null || b == null) {
                throw new ArquivoInvalidoException("Mapa invalido: porta '" + par[0] + ";" + par[1]
                        + "' usa um ambiente nao declarado.");
            }
            if (a == b) {
                throw new ArquivoInvalidoException("Mapa invalido: porta liga '" + par[0] + "' a ele mesmo.");
            }
            String chave = par[0].compareTo(par[1]) < 0 ? par[0] + ";" + par[1] : par[1] + ";" + par[0];
            if (!portasCriadas.add(chave)) {
                throw new ArquivoInvalidoException("Mapa invalido: porta repetida '" + par[0] + ";" + par[1] + "'.");
            }
            Porta porta = new Porta(a, b);
            a.ajustarPortas(porta);
            b.ajustarPortas(porta);
            portas.add(porta);
        }

        verificarAlcancabilidade();
    }

    // garante que todos os ambientes podem ser alcancados a partir do ambiente inicial
    private void verificarAlcancabilidade() throws ArquivoInvalidoException {
        Set<Ambiente> visitados = new HashSet<Ambiente>();
        LinkedList<Ambiente> fila = new LinkedList<Ambiente>();
        visitados.add(ambienteInicial);
        fila.add(ambienteInicial);

        while (!fila.isEmpty()) {
            Ambiente atual = fila.removeFirst();
            for (Ambiente vizinho : atual.getAmbientesConectados()) {
                if (visitados.add(vizinho)) {
                    fila.add(vizinho);
                }
            }
        }

        if (visitados.size() != ambientes.size()) {
            for (Ambiente ambiente : ambientes.values()) {
                if (!visitados.contains(ambiente)) {
                    throw new ArquivoInvalidoException("Mapa invalido: o ambiente '" + ambiente.getDescricao()
                            + "' nao pode ser alcancado a partir do ambiente inicial.");
                }
            }
        }
    }

    private ArquivoInvalidoException erro(int numeroLinha, String motivo) {
        return new ArquivoInvalidoException("Mapa invalido (linha " + numeroLinha + "): " + motivo + ".");
    }
}
