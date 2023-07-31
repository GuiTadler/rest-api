package rest.api

import grails.validation.ValidationException

import java.time.format.DateTimeFormatter

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class EmpregadoController {

    EmpregadoService empregadoService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static dateFormats = ['dd/MM/yyyy']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def empregados = empregadoService.list(params)

        def listarEmpregado = empregados.collect { empregado ->
            [ id:empregado.id,
              nome:empregado.nome,
              dataNascimento:empregado.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
              matricula:empregado.matricula,
              departamento:empregado.departamento.id]
        }
        respond listarEmpregado, model: [empregadoCount: empregadoService.count()]
    }

    def show(Long id) {
        def idEmpregado =  empregadoService.get(id)

        def listarEmpregadoId = idEmpregado.collect { empregado ->
            [ id:empregado.id,
              nome:empregado.nome,
              dataNascimento:empregado.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
              matricula:empregado.matricula,
              departamento:empregado.departamento.id]
        }
        respond listarEmpregadoId, model: [empregadoCount: empregadoService.count()]
    }

    @Transactional
    def save(Empregado empregado) {
        if (empregado == null) {
            render status: NOT_FOUND
            return
        }
        if (empregado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond empregado.errors
            return
        }

        try {
            empregadoService.save(empregado)
        } catch (ValidationException e) {
            respond empregado.errors
            return
        }

        respond empregado, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Empregado empregado) {
        if (empregado == null) {
            render status: NOT_FOUND
            return
        }
        if (empregado.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond empregado.errors
            return
        }

        try {
            empregadoService.save(empregado)
        } catch (ValidationException e) {
            respond empregado.errors
            return
        }

        respond empregado, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || empregadoService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}