package library;

import java.util.Set;
import java.util.ArrayList;


public class EmprestavelFilter implements Filter<Emprestavel> {

    /**
     * Aplica um filtro a um conjunto de itens Emprestavel com base num critério fornecido
     * @param items    O set Emprestavel de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return Array com itens filtrados
     */
    @Override
    public ArrayList<Emprestavel> aplica(Set<Emprestavel> items, String criteria) {
        ArrayList<Emprestavel> emprestavelArray = new ArrayList<>();
        for (Emprestavel emprestavel : items) {
            if (emprestavel.getID().equals(criteria)) {
                emprestavelArray.add(emprestavel);
            }
        }
        return emprestavelArray;
    }
    
}
