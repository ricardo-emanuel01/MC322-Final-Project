package library;

/**
 * Enum que representa as permissões de cada usuário
 */
public enum Permissoes {
    /**
     * Usuário com permissões de administrador
     */
    ADMIN("admin"),
    /**
     * Usuário com permissões comuns
     */
    LEITOR("leitor");

    private final String permissao;

    /**
     * Construtor do enum
     * @param permissao o tipo de permissao do usuario
     */
    Permissoes(String permissao) {
        this.permissao = permissao;
    }

    /**
     * Retorna o tipo da permissao em texto
     * @return o tipo da permissao
     */
    @Override
    public String toString() {
        return this.permissao;
    }
}
