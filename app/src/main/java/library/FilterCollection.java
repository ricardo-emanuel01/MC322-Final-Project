package library;

import java.util.ArrayList;
import java.util.Set;

public class FilterCollection {
    // classe que vai aplicar os filtros


    private FilterCollection() {}

    /**
     * Filtra um conjunto de usuários com base em um critério
     * @param users o conjunto de usuários a ser filtrado
     * @param criteria o critério de filtragem
     * @return Array com objetos filtrados
     */
    public static ArrayList<User> filtrarUser(Set<User> users, String criteria) {
        UserFilter filtro = new UserFilter();
        return filtro.aplica(users, criteria);
    }

    /**
     * Filtra um conjunto de empréstimos com base em um critério
     * @param emprestimos o conjunto de empréstimos a ser filtrado
     * @param criteria o critério de filtragem
     * @return Array com objetos filtrados
     */
    public static ArrayList<Emprestimo> filtrarEmprestimo(Set<Emprestimo> emprestimos, String criteria) {
        EmprestimoFilter filtro = new EmprestimoFilter();
        return filtro.aplica(emprestimos, criteria);
    }

    /**
     * Filtra um conjunto de itens emprestáveis com base em um critério
     * @param emprestaveis o conjunto de itens emprestáveis a ser filtrado
     * @param criteria o critério de filtragem
     * @return Array com objetos filtrados
     */
    public static ArrayList<Emprestavel> filtrarEmprestavel(Set<Emprestavel> emprestaveis, String criteria) {
        EmprestavelFilter filtro = new EmprestavelFilter();
        return filtro.aplica(emprestaveis, criteria);
    }
}
