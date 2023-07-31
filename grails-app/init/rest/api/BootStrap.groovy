package rest.api

import javax.transaction.Transactional

@Transactional
class BootStrap {

    def init = { servletContext ->
        SalvaEmpDepService.init()
    }

    def destroy = {
        Empregado.deleteAll()
        Departamento.deleteAll()
    }
}
