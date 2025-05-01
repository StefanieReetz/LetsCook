# 🥘 LetsCook

**LetsCook** é uma API desenvolvida por [Stefanie](https://github.com/StefanieReetz) que permite ao usuário cadastrar ingredientes disponíveis, buscar receitas com base nesses ingredientes utilizando a API externa **Spoonacular**, salvar receitas favoritas e visualizar o que está faltando para prepará-las.

> Projeto pessoal criado para praticar back-end com Java e Spring Boot, utilizando autenticação JWT e integração com APIs externas.

---

## :door: Conteúdo

- [Sobre](#Sobre)
- [Funcionalidades](#Funcionalidades)
- [Tecnologias](#Tecnologias)
- [Como executar o projeto](#executar)
- [Variáveis de Ambiente](#variaveis-de-ambiente)
- [Endpoints](#Endpoints)

---

<a name="Sobre"></a>
## 💡 Sobre o projeto

A **LetsCook API** é uma API responsável por sugerir receitas com base nos ingredientes que o usuário tem em casa. Ela se conecta com a [API Spoonacular](https://spoonacular.com/), consome os dados de receitas e retorna sugestões de pratos que podem ser preparados com os ingredientes disponíveis.

Além disso, também permite favoritar receitas e visualizar quais ingredientes ainda estão faltando para prepará-las. Para acessar a maioria das funcionalidades, é necessário estar autenticado.

---
<a name="Funcionalidades"></a>
## ✨ Funcionalidades

- 🔐 Autenticação via JWT
- 👩‍🍳 Cadastro e login de usuários
- 🧂 Cadastro e listagem dos ingredientes do usuário
- 🍝 Busca de receitas com base nos ingredientes informados (Spoonacular)
- 💾 Favoritar receitas
- 📝 Ver detalhes da receita, modo de preparo e o que está faltando

---

<a name="Tecnologias"></a>
## 🛠️ Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- API Spoonacular  
- PostgreSQL  
- JWT  
- Maven  

---

<a name="executar"></a>
## ▶️ Como executar

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL rodando

### Rodando a aplicação

```bash
git clone https://github.com/StefanieReetz/LetsCook.git
cd LetsCook
./mvnw spring-boot:run
```
---

<a name="variaveis-de-ambiente"></a>
## ⚙️ Variáveis de Ambiente

#### Para executar o projeto corretamente, é necessário configurar as seguintes variáveis de ambiente:
<details>

<summary><strong>🗂️ Banco de Dados</strong></summary>

| Variável | Descrição |
|---------|-----------|
| `DB_USER` | Usuário do banco de dados PostgreSQL |
| `DB_PASSWORD` | Senha do banco de dados PostgreSQL |
</details>

<details><summary><strong>🔐 JWT</strong></summary>

| Variável | Descrição |
|---------|-----------|
| `JWT_SECRET` | Chave secreta usada para assinar e verificar os tokens JWT. Pode ser qualquer string segura. |
</details>

<details>
<summary><strong>🍅 Integração com o Spoonacular</strong></summary> 

| Variável | Descrição                                                                              |
|---------|----------------------------------------------------------------------------------------|
| `SPOONCULAR_API_KEY` | API Key do spoonacular ([Spotify Developer](https://spoonacular.com/food-api/pricing)) |
</details>


<a name="Endpoints"></a>
## 📎 Endpoints
<details>
    <summary>
        <strong>Autenticação</strong>
    </summary>

Para acessar os endpoints protegidos da API LetsCook, é necessário estar autenticado. Você pode criar uma conta e depois fazer login para receber o token JWT.
#### 📝 Cadastra um usuario
```http
POST http://localhost:8080/auth/signup
```

##### Corpo da requisição:
| Chave      | Tipo       | Descrição                         |
|:-----------| :--------- |:----------------------------------|
| `username`     | `string` | **Obrigatório**. Nome do usuário  |
| `password` | `string` | **Obrigatório**. Senha do usuário |


##### Exemplo de criação de um usuário
```json
{
  "username": "usuario",
  "password": "senha123"
}
```
---
### 🔑 Login
```http
POST http://localhost:8080/auth/login
```

##### Corpo da requisição:
| Chave      | Tipo       | Descrição                         |
|:-----------| :--------- |:----------------------------------|
| `username`     | `string` | **Obrigatório**. Nome do usuário  |
| `password` | `string` | **Obrigatório**. Senha do usuário |


#### Exemplo de login em um usuário:
``` json
{
  "username": "usuario",
  "password": "senha123"
}
```

#### Exemplo de resposta:
```json
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

 Use esse token no cabeçalho Authorization de todas as requisições protegidas:
```makefile
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
</details>

<details>
    <summary>
        <strong>🍎 Ingredientes</strong>
    </summary>

#### Listar ingredientes
Você conseguirá ver os ingredientes do usuário cadastrado
```http
GET http://localhost:8080/ingredients
```
---
#### Adiciona um ingrediente ao usuário cadastrado
```http
POST http://localhost:8080/ingredients
```

##### Corpo da requisição:
| Chave      | Tipo       | Descrição                                        |
|:-----------| :--------- |:-------------------------------------------------|
| `name`     | `string` | **Obrigatório**. Nome do ingrediente (em ingles) |
| `quantity` | `Integer` | **Obrigatório**. Quantidade de ingredientes      |


##### Exemplo de requisição:
```json
{
  "name" : "Strawberry",
  "quantity" : "9"
}
```
##### Exemplo de resposta:
```json
{
  "id": 1,
  "name": "Strawberry",
  "quantity": 9
}
```
---

#### Atualiza ingredientes pelo Id
```http
POST http://localhost:8080/ingredients/{id}
```
| Parâmetro   | Tipo   | Descrição                                     |
| :---------- |:-------|:----------------------------------------------|
| `id` | `Long` | **Obrigatório**. Identificação do ingrediente |

##### Exemplo de requisição:
```http
  PUT http://localhost:8080/ingredients/1
```

##### Corpo da requisição:
| Chave      | Tipo       | Descrição                                        |
|:-----------| :--------- |:-------------------------------------------------|
| `name`     | `string` | **Obrigatório**. Nome do ingrediente (em inglês) |
| `quantity` | `Integer` | **Obrigatório**. Quantidade de ingredientes      |


##### Exemplo de requisição:
```json
{
  "name" : "Onion",
  "quantity" : "5"
}
```
##### Exemplo de resposta:
```json
{
  "id": 1,
  "name": "Onion",
  "quantity": 5
}
```
---
#### Excluir ingredientes
```http
DELETE http://localhost:8080/ingredients/id
```
| Parâmetro   | Tipo   | Descrição                                     |
| :---------- |:-------|:----------------------------------------------|
| `id` | `Long` | **Obrigatório**. Identificação do ingrediente |

##### Exemplo de requisição:
```http
  PUT http://localhost:8080/ingredients/1
```

</details>