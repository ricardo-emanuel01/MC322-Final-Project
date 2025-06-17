package library;


import java.util.List;
import java.util.UUID;
import java.time.LocalDate;


/**
 * Classe que será utilizada como base para os objetos emprestaveis
 */
public class Emprestavel implements Comparable<Emprestavel> {
    private final UUID id;
    private String titulo;
    private String subtitulo;
    private List<String> autores;
    private TipoEmprestavel tipo;
    private boolean disponivel;
    private LocalDate dataPrevisaoDevolucao;
    private String IDUser;


    /**
     * Construtor da classe Emprestável
     * @param id para quando o livro já era cadastrado
     * @param titulo o título da obra
     * @param subtitulo o subtítulo da obra caso exista
     * @param autores a lista de autores
     * @param tipo o tipo da obra (Livro, Tese, ...)
     */
    protected Emprestavel(
        String id,
        String titulo,
        String subtitulo,
        List<String> autores,
        TipoEmprestavel tipo
    ) {
        if (id != null) this.id = UUID.fromString(id);
        else this.id = UUID.randomUUID();
        this.titulo = titulo;
        // Pode ser null
        this.subtitulo = subtitulo;
        this.autores = autores;
        this.disponivel = true;
        this.dataPrevisaoDevolucao = null;
        this.tipo = tipo;
        this.IDUser = null;
    }


    /**
     * Construtor utilizado na deserializacao do objeto
     * @param id para quando o livro já era cadastrado
     * @param titulo o título da obra
     * @param subtitulo o subtítulo da obra caso exista
     * @param autores a lista de autores
     * @param tipo o tipo da obra (Livro, Tese, ...)
     * @param previsaoDevolucao a data prevista para devolucao
     * @param IDUser ID do usuario que emprestou
     */
    protected Emprestavel(
        String id,
        String titulo,
        String subtitulo,
        List<String> autores,
        TipoEmprestavel tipo,
        LocalDate previsaoDevolucao,
        String IDUser
    ) {
        if (id != null) this.id = UUID.fromString(id);
        else this.id = UUID.randomUUID();
        this.titulo = titulo;
        // Pode ser null
        this.subtitulo = subtitulo;
        this.autores = autores;
        this.disponivel = false;
        this.dataPrevisaoDevolucao = previsaoDevolucao;
        this.tipo = tipo;
        this.IDUser = IDUser;
    }


    // Getters utilizados pelo Gson
    protected String getID() {return this.id.toString();}
    protected String getTitulo() {return this.titulo;}
    protected String getSubtitulo() {return this.subtitulo;}
    protected List<String> getAutores() {return this.autores;}
    protected String getTipo() {return this.tipo.toString();}
    protected String getIDUSer() {return this.IDUser;}
    protected boolean getDisponibilidade() {return this.disponivel;}
    protected String getPrevisaoDevolucao() {
        if (this.dataPrevisaoDevolucao != null) {
            return this.dataPrevisaoDevolucao.toString();
        }
        return null;
    }


    /**
     * Utilizado para controlar a disponibilidade
     * e a previsao de devolucao de uma obra
     * @param IDUser o ID do usuario que deseja emprestar
     * @param dataPrevisaoDevolucao a previsao de devolucao
     */
    protected void emprestar(
        String IDUser, LocalDate dataPrevisaoDevolucao
    ) {
        if (this.disponivel) {
            this.IDUser = IDUser;
            this.dataPrevisaoDevolucao = dataPrevisaoDevolucao;
            this.disponivel = false;
        } else {
            // TODO: jogar alguma exceção
        }
    }


    /**
     * Utilizado para controlar a nova data de devolucao do objeto
     * @param IDUser ID do usuário que emprestou
     * @param novaDataPrevisaoDevolucao a nova data de previsao
     */
    protected void renovar(
        String IDUser,
        LocalDate novaDataPrevisaoDevolucao
    ) {
        if (this.IDUser.equals(IDUser)) {
            this.dataPrevisaoDevolucao = novaDataPrevisaoDevolucao;
        } else {
            // TODO: jogar alguma exceção
        }
    }


    /**
     * Utilizado para controlar a disponibilidade do objeto
     * @param IDUser ID do usuário que está devolvendo
     */
    protected void devolver(String IDUser) {
        if (!this.disponivel && this.IDUser.equals(IDUser)) {
            this.IDUser = null;
            this.dataPrevisaoDevolucao = null;
            this.disponivel = true;
        } else {
            // TODO: jogar alguma exceção
        }
    }


    /**
     * Utilizado para que o treeSet utilize o ID como key
     * @param outro o objeto a ser comparado
     * @return
     */
    @Override
    public int compareTo(Emprestavel outro) {
        return this.getID().compareTo(outro.getID());
    }
}
