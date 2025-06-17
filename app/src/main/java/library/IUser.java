package library;

import java.util.UUID;

public interface IUser {
    public Emprestimo emprestar(UUID IDObjeto, int duracaoEmprestimo);
    public Emprestimo renovar(UUID IDObjeto, int duracaoEmprestimo);
    public Emprestimo devolver(UUID IDObjeto);
}
