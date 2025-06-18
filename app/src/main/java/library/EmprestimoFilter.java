package library;

import java.util.Set;

public class EmprestimoFilter implements Filter<Emprestimo> {

    /**
     * Aplica um filtro a um conjunto de itens Emprestimo com base num critério fornecido
     * @param items    O set Emprestimo de items para o filter
     * @param criteria O criterio a ser aplicado para filtrar os items
     * @return O item Emprestimo que corresponde ao critério, ou null se nenhum item corresponder
     */
    @Override
    public Emprestimo aplica(Set<Emprestimo> items, String criteria) {
        for (Emprestimo emprestimo : items) {
            if (emprestimo.getObjEmprestado().toString().equals(criteria)) { 
                return emprestimo;
            }
        }
        return null;
    }
    
}
