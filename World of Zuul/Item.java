public abstract class Item {
    private final String nome;

    public Item(String nome) {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }

    /**
     * Define o que acontece quando o jogador explora o ambiente onde este item esta.
     * Cada subclasse decide seu proprio comportamento (polimorfismo por sobrescrita).
     * @param jogador O jogador que explorou o ambiente.
     * @param ambiente O ambiente que contem o item.
     * @return mensagem a ser exibida ao jogador, ou null se nada deve ser exibido.
     */
    public abstract String aoExplorar(Jogador jogador, Ambiente ambiente);
}
