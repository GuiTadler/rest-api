package rest.api

import grails.databinding.BindingFormat
import java.time.LocalDate

class Empregado {
    Long id
    String nome
    @BindingFormat("dd/MM/yyyy")
    LocalDate dataNascimento

    Integer matricula

    static belongsTo = [departamento: Departamento]

    static constraints = {
        id unique: true
        dataNascimento nullable: false
        matricula nullable: false, unique: true
    }
}


