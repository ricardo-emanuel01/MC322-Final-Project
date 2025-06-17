package library;


import java.time.LocalDate;
import java.util.List;

public class Livro extends Emprestavel {
    public Livro(
        String id, String titulo, String subtitulo, List<String> autores
    ) {
        super(id, titulo, subtitulo, autores, TipoEmprestavel.LIVRO);
    }


    public Livro(
        String id,
        String titulo,
        String subtitulo,
        List<String> autores,
        LocalDate previsaoDevolucao,
        String IDUser
    ) {
        super(
            id,
            titulo,
            subtitulo,
            autores,
            TipoEmprestavel.LIVRO,
            previsaoDevolucao,
            IDUser
        );
    }
}
