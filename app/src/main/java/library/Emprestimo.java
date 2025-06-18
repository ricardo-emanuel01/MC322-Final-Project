package library;

import java.time.LocalDate;
import java.util.UUID;

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
    UUID objEmprestado,
    LocalDate dataEmprestimo,
    LocalDate dataPrevisaoDevolucao,
    LocalDate dataUltimaRenovacao
) 
{ 
    public static Emprestimo renovacao(Emprestimo emprestimo, LocalDate dataRenovacao) {
        return new Emprestimo(
            emprestimo.usuario(),
            emprestimo.objEmprestado(),
            emprestimo.dataEmprestimo(),
            dataRenovacao.plusDays(7), 
            dataRenovacao
        );
    }
}