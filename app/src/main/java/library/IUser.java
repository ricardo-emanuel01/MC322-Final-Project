package library;

import java.util.UUID;

public interface IUser {
    public void emprestar(UUID IDObjeto);
    public void renovar(UUID IDObjeto);
    public void devolver(UUID IDObjeto);
}
