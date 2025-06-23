package library;

import java.util.Set;
import java.util.ArrayList;

public class EmprestimoFilter implements Filter<Emprestimo> {

    /**
     * Aplica um filtro a um conjunto de itens Emprestimo com base num critério fornecido
     * @param items    O set Emprestimo de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return Array com itens filtrados
     */
    @Override
    public ArrayList<Emprestimo> aplica(Set<Emprestimo> items, String criteria) {
        ArrayList<Emprestimo> emprestimoArray = new ArrayList<>();
        for (Emprestimo emprestimo : items) {
            if (emprestimo.objEmprestado().equals(criteria)) {
                emprestimoArray.add(emprestimo);
            }
        }
        return emprestimoArray;
    }

}
