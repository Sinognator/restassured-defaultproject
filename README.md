# RestAssured Default Project
![Java CI with Maven](https://github.com/Sinognator/restassured-defaultproject/actions/workflows/maven.yml/badge.svg)

Projeto de automação de testes de API usando Java + RestAssured, com boas práticas, organização por camadas e dados dinâmicos via JavaFaker.  
A API testada é a [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html), usada amplamente em testes de automação.

---

## ✅ Funcionalidades implementadas

Este projeto cobre todas as operações do CRUD na API:

| Método | Endpoint                  | Status       |
|--------|---------------------------|--------------|
| POST   | `/booking`                | ✅ Criar reserva com dados dinâmicos |
| GET    | `/booking/{id}`           | ✅ Buscar reserva por ID |
| PUT    | `/booking/{id}`           | ✅ Atualizar reserva com token |
| DELETE | `/booking/{id}`           | ✅ Excluir reserva com autenticação |
| POST   | `/auth`                   | ✅ Gerar token JWT para uso nas requisições protegidas |

---

## 📦 Tecnologias utilizadas

- Java 17
- RestAssured 5.3.0
- JUnit 4
- Maven
- Jackson (serialização JSON)
- JavaFaker (geração de dados dinâmicos)

---

## 📁 Estrutura de pastas

```
src/
├── main/
│   └── java/
│       └── br/com/api/
│           ├── model/
│           └── utils/
└── test/
    └── java/
        └── br/com/api/test/
```