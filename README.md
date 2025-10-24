# Spring Boot API Login & Auth

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

API REST desenvolvida com **Spring Boot** para autenticação e controle de acesso de usuários, utilizando **Spring Security** e **JWT**.  

---

## Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3**  
- **Spring Security**  
- **JWT (JSON Web Token)**  
- **Maven**  
- **Banco de Dados Relacional** PostgreSQL

---

## Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/ghenriqf/login-auth-api.git
```

### 2. Instalar dependências com Maven

### 3. Rodar o projeto

1. Inicie a aplicação com Maven
2. A API estará acessível em http://localhost:8080

---

## Funcionalidades

- Registro de novos usuários (`/api/auth/register`)  
- Login e autenticação de usuários (`/api/auth/login`)  
- Geração e validação de tokens JWT  
- Proteção de rotas com autenticação  

---

## API Endpoints

```markdown
GET /teste - Endpoint para teste de segurança

POST /auth/login - Login para User

POST /auth/register - Registro de um novo User
```
