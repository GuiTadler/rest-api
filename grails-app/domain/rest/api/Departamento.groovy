package rest.api

class Departamento {

    Long id
    String nome
    static hasMany = [empregados: Empregado] // Um departamento pode ter muitos empregados

    static constraints = {
    }
}
