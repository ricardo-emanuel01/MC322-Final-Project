package library;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Record utilizado para representar um emprestimo
 * @param usuario um UUID que representa o usuario responsavel pelo emprestimo
 * @param objEmprestado um UUID que representa o objeto emprestado
 * @param dataEmprestimo a data do emprestimo
 * @param dataPrevisaoDevolucao a data maxima para devolucao do objeto caso nao haja renovacao
 * @param dataUltimaRenovacao a data da ultima renovacao feita pelo responsavel pelo emprestimo
 */
public record Emprestimo(
    UUID usuario,
    UUID objEmprestado,
    LocalDate dataEmprestimo,
    LocalDate dataPrevisaoDevolucao,
    LocalDate dataUltimaRenovacao
) {}
