package rest.api

import rest.api.DepartamentoExclusaoException

class Departamento {
    String nome
    static hasMany = [empregados: Empregado]

    static constraints = {
        nome blank: false
    }

    def beforeDelete() {
        // Verificar se o departamento está associado a algum empregado
        if (empregados) {
            throw new DepartamentoExclusaoException("O departamento está associado a um ou mais empregados e não pode ser excluído.")
        }
    }
}
