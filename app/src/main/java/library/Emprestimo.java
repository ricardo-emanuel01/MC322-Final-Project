package library;

import java.time.LocalDate;


/**
 * Record utilizado para representar um emprestimo
 * @param usuario o email do usuario que sera unico
 * @param objEmprestado um UUID que representa o objeto emprestado
 * @param dataEmprestimo a data do emprestimo
 * @param dataPrevisaoDevolucao a data maxima para devolucao do objeto caso nao haja renovacao
 * @param dataUltimaRenovacao a data da ultima renovacao feita pelo responsavel pelo emprestimo
 */
public record Emprestimo(
    String usuario,
    String objEmprestado,
    LocalDate dataEmprestimo,
    LocalDate dataPrevisaoDevolucao,
    LocalDate dataUltimaRenovacao
) implements Comparable<Emprestimo> {

    @Override
    public int compareTo(Emprestimo outro) {
        return this.objEmprestado().compareTo(outro.objEmprestado());
    }
}