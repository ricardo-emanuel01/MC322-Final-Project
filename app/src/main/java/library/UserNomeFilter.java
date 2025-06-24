package library;


import java.util.Map;
import java.util.ArrayList;

public class UserNomeFilter implements Filter<User> {

    /**
     * Aplica um filtro a um conjunto de itens User com base num critério fornecido
     * @param items    O set User de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return Array com itens filtrados
     */
    @Override
    public ArrayList<User> aplica(Map<String, User> items, String criteria) {
        ArrayList<User> userArray = new ArrayList<>();
        for (Map.Entry<String, User> entry : items.entrySet()) {
            if (entry.getValue().getPrimeiroNome().equals(criteria)) {
                userArray.add(entry.getValue());
            }
        }
        return userArray;
    }

}
