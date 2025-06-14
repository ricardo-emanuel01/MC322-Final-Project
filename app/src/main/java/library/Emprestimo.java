package library;

import java.time.LocalDate;
import java.util.UUID;

public record Emprestimo(
    UUID usuario,
    UUID objEmprestado,
    LocalDate dataEmprestimo,
    LocalDate dataPrevisaoDevolucao,
    LocalDate dataUltimaRenovacao
) {}
