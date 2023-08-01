package rest.api

import grails.validation.ValidationException
import java.time.format.DateTimeFormatter
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class EmpregadoController {

    def empregadoService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static dateFormats = ['dd/MM/yyyy']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def empregados = empregadoService.list(params)

        def listarEmpregado = empregados.collect { empregado ->
            [ id: empregado.id,
              departamento: [
                      id: empregado.departamento ? empregado.departamento.id : null,
                      nome: empregado.departamento ? empregado.departamento.nome : null
              ],
              matricula: empregado.matricula,
              nome: empregado.nome,
              dataNascimento: empregado.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))]
        }
        respond listarEmpregado, model: [empregadoCount: empregadoService.count()]
    }

    def show(Long id) {
        def empregado = empregadoService.get(id)
        if (!empregado) {
            render text: "Registro inexistente."
            return
        }

        def listarEmpregadoId = [id: empregado.id,
                                 departamento: [
                                         id: empregado.departamento ? empregado.departamento.id : null,
                                         nome: empregado.departamento ? empregado.departamento.nome : null
                                 ],
                                 matricula: empregado.matricula,
                                 nome: empregado.nome,
                                 dataNascimento: empregado.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))]

        respond listarEmpregadoId, model: [empregadoCount: empregadoService.count()]
    }

    @Transactional
    def save(Empregado empregado) {
        if (!empregado) {
            render text: "Erro ao criar o registro."
            return
        }

        if (empregado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond empregado.errors
            return
        }

        try {
            empregadoService.save(empregado)
            render text: "Registro criado com sucesso."
        } catch (ValidationException e) {
            respond empregado.errors
        }
    }

    @Transactional
    def update(Empregado empregado) {
        if (!empregado) {
            render text: "Registro não encontrado."
            return
        }

        if (empregado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond empregado.errors
            return
        }

        try {
            empregadoService.save(empregado)
            render text: "Registro atualizado com sucesso."
        } catch (ValidationException e) {
            respond empregado.errors
        }
    }

    @Transactional
    def delete(Long id) {
        if (id == null || empregadoService.delete(id) == null) {
            render text: "Registro não encontrado."
            return
        }
        render text: "Registro excluído com sucesso."
    }
}


