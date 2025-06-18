package library;

import java.util.Set;
/**
 * Interface Filter (Criteria Pattern)
 * @param <T> o tipo de objeto a ser filtrado
 */
public interface Filter<T> {
    /**
     * @param item o objeto a ser testado
     * @return T o objeto encontrado ou seja pode ser T ou null
     */
    T aplica(Set<T> item, String criteria);

}