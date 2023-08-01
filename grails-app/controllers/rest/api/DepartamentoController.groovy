package rest.api

import grails.validation.ValidationException
import rest.api.DepartamentoExclusaoException
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class DepartamentoController {

    DepartamentoService departamentoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond departamentoService.list(params), model:[departamentoCount: departamentoService.count()]
    }

    def show(Long id) {
        def departamento = departamentoService.get(id)
        if (!departamento) {
            render text: "Departamento não encontrado."
            return
        }

        respond departamento
    }

    @Transactional
    def save(Departamento departamento) {
        if (!departamento) {
            render text: "Erro ao criar o departamento."
            return
        }
        if (departamento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond departamento.errors
            return
        }

        try {
            departamentoService.save(departamento)
            render text: "Registro criado com sucesso."
        } catch (ValidationException e) {
            respond departamento.errors
        }
    }

    @Transactional
    def update(Departamento departamento) {
        if (!departamento) {
            render text: "Departamento não encontrado."
            return
        }
        if (departamento.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond departamento.errors
            return
        }

        try {
            departamentoService.save(departamento)
            render text: "Registro atualizado com sucesso."
        } catch (ValidationException e) {
            respond departamento.errors
        }
    }

    @Transactional
    def delete(Long id) {
        Departamento departamento = Departamento.get(id)
        if (!departamento) {
            response.sendError(404, "Departamento não encontrado.")
            return
        }
        try {
            departamento.delete(flush: true)
            response.status = 200
            render "Departamento excluído com sucesso."
        } catch (DepartamentoExclusaoException e) {
            response.sendError(409, e.message)
        } catch (Exception e) {
            response.sendError(500, "Ocorreu um erro ao excluir o departamento.")
        }
    }
}
