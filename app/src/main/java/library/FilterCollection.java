package library;

import java.util.ArrayList;
import java.util.Map;

public class FilterCollection {
    // classe que vai aplicar os filtros


    private FilterCollection() {}

    /**
     * Filtra um conjunto de usuários com base em um critério
     * @param users o conjunto de usuários a ser filtrado
     * @param criteria o critério de filtragem
     * @return Array com objetos filtrados
     */
    public static ArrayList<User> filtrarUser(Map<String, User> users, String criteria) {
        UserNomeFilter filtro = new UserNomeFilter();
        return filtro.aplica(users, criteria);
    }

    /**
     * Filtra um conjunto de itens emprestáveis com base em um critério
     * @param emprestaveis o conjunto de itens emprestáveis a ser filtrado
     * @param criteria o critério de filtragem
     * @return Array com objetos filtrados
     */
    public static ArrayList<Emprestavel> filtrarEmprestavel(Map<String, Emprestavel> emprestaveis, String criteria) {
        EmprestavelTituloFilter filtro = new EmprestavelTituloFilter();
        return filtro.aplica(emprestaveis, criteria);
    }
}
