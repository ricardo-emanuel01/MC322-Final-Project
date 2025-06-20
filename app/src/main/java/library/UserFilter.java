package library;

import java.util.Set;
import java.util.ArrayList;

public class UserFilter implements Filter<User> {

    /**
     * Aplica um filtro a um conjunto de itens User com base num critério fornecido
     * @param items    O set User de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return Array com itens filtrados
     */
    @Override
    public ArrayList<User> aplica(Set<User> items, String criteria) {
        ArrayList<User> userArray = new ArrayList<>();
        for (User user : items) {
            if (user.getEmail().equals(criteria)) {
                userArray.add(user);
            }
        }
        return userArray;
    }

}
