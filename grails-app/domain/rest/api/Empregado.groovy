package rest.api

import java.time.LocalDate

class Empregado {

    Long id
    String nome
    LocalDate dataNascimento
    Integer matricula

    static belongsTo = [departamento: Departamento] // Empregado pertence a um departamento

    static constraints = {
    }
}
