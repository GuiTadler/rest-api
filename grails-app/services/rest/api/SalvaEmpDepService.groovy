package rest.api

import org.springframework.transaction.annotation.Transactional

@Transactional
class SalvaEmpDepService {

    static void init() {

        Departamento departamento1 = new Departamento(nome: "Rh")
        Departamento departamento2 = new Departamento(nome: "Desenvolvimento")

        departamento1.save()
        departamento2.save()

        Empregado empregado1 = new Empregado (nome: "Guilherme", matricula: 1, dataNascimento: "06/12/1993", departamento: 2)
        Empregado empregado2 = new Empregado (nome: "Kelly", matricula: 2, dataNascimento: "24/07/1974", departamento: 1)
        Empregado empregado3 = new Empregado (nome: "Matheus", matricula: 3, dataNascimento: "25/10/1983", departamento: 2)
        Empregado empregado4 = new Empregado (nome: "Eduarda", matricula: 4, dataNascimento: "25/05/2002", departamento: 1)
        Empregado empregado5 = new Empregado (nome: "Abelão"   , matricula: 5, dataNascimento: "21/10/1945", departamento: 1)

        empregado1.save()
        empregado2.save()
        empregado3.save()
        empregado4.save()
        empregado5.save()

    }
}
