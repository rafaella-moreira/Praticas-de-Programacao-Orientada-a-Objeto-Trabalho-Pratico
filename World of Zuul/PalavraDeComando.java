public enum PalavraDeComando
{
    IR("ir", "ir <ambiente>      - tenta abrir a porta e ir para o ambiente (gasta 1 tentativa)"),
    CHAVE("chave", "chave <ambiente>   - usa a chave mestra para abrir a porta (não gasta tentativa)"),
    EXPLODIR("explodir", "explodir           - usa a carga explosiva no ambiente atual"),
    AJUDA("ajuda", "ajuda              - mostra esta ajuda"),
    SAIR("sair", "sair               - encerra o jogo"),
    DESCONHECIDO("?", "");

    private final String texto;
    private final String descricao;

    /**
     * Cria uma palavra de comando.
     *
     * @param texto     texto que o jogador digita.
     * @param descricao descrição exibida na ajuda.
     */
    PalavraDeComando(String texto, String descricao)
    {
        this.texto = texto;
        this.descricao = descricao;
    }

    /** @return o texto digitado pelo jogador. */
    public String getTexto()
    {
        return texto;
    }

    /** @return a descrição usada na ajuda. */
    public String getDescricao()
    {
        return descricao;
    }
}