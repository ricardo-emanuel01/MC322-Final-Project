package library;

import java.util.ArrayList;
import java.util.Set;


/**
 * Interface Filter (Criteria Pattern)
 * @param <T> o tipo de objeto a ser filtrado
 * @param criteria string que contem o tipo de criterio usado (email, id e etc)
 * @return Array com os objetos filtrados
 */
public interface Filter<T> {
    ArrayList<T> aplica(Set<T> item, String criteria);

}
