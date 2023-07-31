# 👨‍💼📁 REST-API 📁👩‍💼
## **Descrição 📝**
Este é um projeto de uma API desenvolvida em Groovy on Grails, que fornece endpoints para gerenciar empregados e departamentos. A API é projetada para aceitar e retornar dados no formato JSON e utiliza o banco de dados H2 como armazenamento.

## **Pré-requisitos** ⚙️

### Antes de executar a API, certifique-se de ter o seguinte instalado em sua máquina 🔧:

* Grails (versão 5.3.2 ou superior) 📦
* Java Development Kit (JDK) (versão 8 ou 11) ☕️

### Configuração de variáveis de ambiente 🛠️:

Defina a variável de ambiente `GRAILS_HOME` apontando para o diretório de instalação do Grails.
Adicione `%GRAILS_HOME%\bin;%JAVA_HOME%\bin` ao final da variável PATH.

## Execução ▶️

### Para executar a API localmente, abra o Prompt de Comando no diretório do projeto e utilize o seguinte comando 💻:

* `grails run-app`

#### **A API estará disponível em http://localhost:8080/rest-api.**

## Endpoints 🌐

### A API possui dois domínios: 

#### Empregado e Departamento. Ambos possuem os mesmos endpoints configurados conforme abaixo 🛣️:

* `GET rest-api/empregado:` Retorna a lista de todos os empregados.

* `GET rest-api/empregado/{id}:` Retorna os detalhes de um empregado específico.

* `POST rest-api/empregado:` Cria um novo empregado.

* `PUT rest-api/empregado/{id}:` Atualiza os detalhes de um empregado existente.

* `DELETE rest-api/empregado/{id}:` Remove um empregado.

## Armazenamento 💾

### Os dados são salvos em bancos de dados diferentes dependendo do ambiente em que a aplicação é executada ⌨️:

### Banco de Testes (testDb):

* `JDBC URL: jdbc:h2:mem:testDb`
* `User name: sa`
* `Password: 123`

### Banco de Desenvolvimento (devDb):

* `JDBC URL: jdbc:h2:mem:devDb`
* `User name: sa`
* `Password: 123`

### Banco de Produção (prodDb):

* `JDBC URL: jdbc:h2:./prodDb`
* `User name: sa`
* `Password: 123`

### H2 Console 🗄️

* **Acesso ao console: http://localhost:8080/rest-api/h2-console/**

## Divirta-se utilizando a API! 🚀