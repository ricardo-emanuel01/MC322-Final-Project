package library;


/**
 * Enum que representa o tipo de objeto emprestável
 */
public enum TipoEmprestavel {
    LIVRO("livro");

    private final String tipo;

    /**
     * Construtor do enum
     * @param tipo qual tipo de objeto
     */
    TipoEmprestavel(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna o tipo do objeto
     * @return o tipo do objeto em String
     */
    @Override
    public String toString() {
        return this.tipo;
    }
}
