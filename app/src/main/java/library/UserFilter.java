package library;

import java.util.Set;

public class UserFilter implements Filter<User> {

    /**
     * Aplica um filtro a um conjunto de itens User com base num critério fornecido
     * @param items    O set User de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return O item User que corresponde ao critério, ou null se nenhum item corresponder
     */
    @Override
    public User aplica(Set<User> items, String criteria) {
        for (User user : items) {
            if (user.getEmail().equals(criteria)) {
                return user;
            }
        }
        return null;
    }

}
