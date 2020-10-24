# spring-webflux-example
Um exemplo de API RESTful utilizando o Spring Boot com Webflux, JWT e banco de dados PostgreSQL.

## Requisitos

- Java 11
- Ferramenta para acessar os endpoints (Postman, Insomnia ...)

Pode ser executado com o gradlew ou uma instalação local do gradle.

## Rotas

O servidor é **http://localhost:8080**

- GET /           --> Mensagem de boas vindas
- POST /login     --> Realizar login na API. Retorna o JWT no header do response.
- GET /food       --> Lista todas as comidas cadastradas
- POST /food      --> Cadastra uma nova comida, apenas se estiver autenticado com o JWT.
- GET /food/{id}  --> Busca por uma comida dado o seu id

Request body para login:
`
{
  "username": "user",
  "password": "pass"
}
`

Request body para cadastro de comida:
`
{
  "name": "Calzone",
  "calories": 400
}
`

Em desenvolvimento...

## Melhorias

- [ ] Swagger
- [ ] Controllers passadas para código funcional
- [ ] Tratamento de erros e exceções
