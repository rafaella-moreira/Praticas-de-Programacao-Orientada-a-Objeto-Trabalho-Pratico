/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.text.Normalizer;

public class Ambiente 
{
    private String descricao;
    private List<Porta> portas;
    private List<Item> itens; // itens presentes neste ambiente (tesouro, chave mestra, dicas...)
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) 
    {
        this.descricao = descricao;
        this.portas = new ArrayList<Porta>();
        this.itens = new ArrayList<Item>();
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */

    //associar uma porta ao próprio ambiente
    public void ajustarPortas(Porta porta) {
        portas.add(porta);
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao(){
        return descricao;
    }

    // verifica se o ambiente tem uma porta que leva a outro ambiente
    public List<Ambiente> getAmbientesConectados() {
        List<Ambiente> ambientesConectados = new ArrayList<Ambiente>();
        for (Porta porta : portas) {
            ambientesConectados.add(porta.getAmbienteConectado(this));
        }
        return ambientesConectados;
    }

    // retorna uma lista das portas do ambiente
    public List<Porta> getPortas() {
        return Collections.unmodifiableList(portas);
    }

    /**
     * Procura a porta que leva ao ambiente com o nome informado
     * (ignora maiusculas/minusculas e acentos).
     * @return a porta, ou null se nao houver saida para esse ambiente.
     */
    public Porta getPortaPara(String nomeDestino) {
        if (nomeDestino == null) {
            return null;
        }
        String procurado = normalizar(nomeDestino);
        for (Porta porta : portas) {
            Ambiente destino = porta.getAmbienteConectado(this);
            if (destino != null && normalizar(destino.getDescricao()).equals(procurado)) {
                return porta;
            }
        }
        return null;
    }

    //' normaliza o texto removendo acentos e convertendo para minusculas'
    private static String normalizar(String texto) {
        return Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    // adiciona um item (qualquer subclasse de Item) ao ambiente
    public void adicionarItem(Item item) {
        if (item != null) {
            itens.add(item);
        }
    }

    // remove um item do ambiente
    public void removerItem(Item item) {
        itens.remove(item);
    }

    // retorna uma visao somente leitura dos itens do ambiente
    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    /**
     * Procura um item do ambiente pelo nome (ignora maiusculas/minusculas e acentos).
     * @return o item, ou null se nao houver item com esse nome.
     */
    public Item buscarItem(String nome) {
        if (nome == null) {
            return null;
        }
        String procurado = normalizar(nome);
        for (Item item : itens) {
            if (normalizar(item.getNome()).equals(procurado)) {
                return item;
            }
        }
        return null;
    }

}
