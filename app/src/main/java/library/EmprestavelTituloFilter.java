package library;

import java.util.ArrayList;
import java.util.Map;


public class EmprestavelTituloFilter implements Filter<Emprestavel> {
    /**
     * Aplica um filtro a um conjunto de itens Emprestavel com base num critério fornecido
     * @param items    O set Emprestavel de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return Array com itens filtrados
     */
    @Override
    public ArrayList<Emprestavel> aplica(Map<String, Emprestavel> items, String criteria) {
        ArrayList<Emprestavel> emprestavelArray = new ArrayList<>();
        for (Map.Entry<String, Emprestavel> entry : items.entrySet()) {
            if (entry.getValue().getTitulo().equals(criteria)) {
                emprestavelArray.add(entry.getValue());
            }
        }
        return emprestavelArray;
    }
    
}
