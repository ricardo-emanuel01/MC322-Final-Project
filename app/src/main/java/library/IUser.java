package library;

import java.util.UUID;

public interface IUser {
    public Emprestimo emprestar(UUID IDObjeto);
    public Emprestimo renovar(UUID IDObjeto);
    public Emprestimo devolver(UUID IDObjeto);
}
