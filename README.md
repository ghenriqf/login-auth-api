# API REST de Autenticação e Autorização com Spring Boot e JWT

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API REST desenvolvida com **Spring Boot** para autenticação e autorização de usuários com **Spring Security** e **JWT (JSON Web Token)**.  
O sistema implementa cadastro, login e controle de acesso baseado em **roles (USER e ADMIN)**, com segurança stateless e criptografia de senhas com **BCrypt**.

-------------------------------------------------------------------

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3+
- Spring Security
- Spring Web
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Maven

-------------------------------------------------------------------

## Estrutura do Projeto

```
src/
└── main/java/com/ghenriqf/login_auth_api
    ├── controller/        # Controladores REST (Auth, Admin, User, Teste)
    ├── domain/            # Entidades de domínio (User, UserRole)
    ├── dto/               # Objetos de transferência de dados (Request e Response)
    ├── infra/security/    # Configuração de segurança e geração/validação de tokens
    ├── repository/        # Acesso ao banco de dados (UserRepository)
    └── service/           # Camada de autenticação
```

-------------------------------------------------------------------

## Endpoints Principais

| Método | Endpoint | Descrição | Acesso |
|--------|-----------|-----------|--------|
| POST | /auth/register | Cadastra um novo usuário | Público |
| POST | /auth/login | Realiza o login e retorna um token JWT | Público |
| GET | /user | Acesso permitido a usuários e administradores | USER, ADMIN |
| GET | /admin | Acesso exclusivo para administradores | ADMIN |
| GET | /teste | Endpoint público para teste de segurança | Público |

-------------------------------------------------------------------

## Exemplo de Login

### Requisição
```json
POST /auth/login
{
  "email": "user@example.com",
  "password": "123456"
}
```

### Resposta
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

-------------------------------------------------------------------

## Exemplo de Registro

### Requisição
```json
POST /auth/register
{
  "name": "Gabriel Henrique",
  "email": "gabriel@email.com",
  "password": "123456",
  "role": "ROLE_ADMIN"
}
```

### Resposta (201 Created)
```json
{
  "name": "Gabriel Henrique",
  "email": "gabriel@email.com"
}
```

-------------------------------------------------------------------

## Autenticação JWT

Após o login, o token JWT deve ser incluído no cabeçalho das requisições protegidas:

```
Authorization: Bearer <seu_token_jwt>
```

-------------------------------------------------------------------

## Regras de Acesso

| Endpoint | Papel Necessário |
|-----------|------------------|
| /auth/login | Público |
| /auth/register | Público |
| /user | USER, ADMIN |
| /admin | ADMIN |
| /teste | Público |

-------------------------------------------------------------------

## Configuração do Banco de Dados

Edite o arquivo `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/login_auth_api
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
api.security.token.secret=sua_chave_secreta
```

-------------------------------------------------------------------

## Execução

### Clonar o repositório
```bash
git clone https://github.com/ghenriqf/login-auth-api.git
cd login-auth-api
```

### Rodar com Maven
```bash
mvn spring-boot:run
```

A API estará disponível em:  
**http://localhost:8080**
