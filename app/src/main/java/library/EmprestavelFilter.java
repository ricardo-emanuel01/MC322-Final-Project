package library;

import java.util.Set;


public class EmprestavelFilter implements Filter<Emprestavel> {

    /**
     * Aplica um filtro a um conjunto de itens Emprestavel com base num critério fornecido
     * @param items    O set Emprestavel de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return O item Emprestavel que corresponde ao critério, ou null se nenhum item corresponder
     */
    @Override
    public Emprestavel aplica(Set<Emprestavel> items, String criteria) {
        for (Emprestavel emprestavel : items) {
            if (emprestavel.getID().equals(criteria)) {
                return emprestavel;
            }
        }
        return null;
    }
    
}
