# 🥘 LetsCook

**LetsCook** é uma API desenvolvida por [Stefanie](https://github.com/StefanieReetz) que permite ao usuário cadastrar ingredientes disponíveis, buscar receitas com base nesses ingredientes utilizando a API externa **Spoonacular**, salvar receitas favoritas e visualizar o que está faltando para prepará-las.

> Projeto pessoal criado para praticar back-end com Java e Spring Boot, utilizando autenticação JWT e integração com APIs externas.

---

## :door: Conteúdo

- [Sobre](#💡-sobre-o-projeto)
- [Funcionalidades](#✨-funcionalidades)
- [Tecnologias](#🛠️-tecnologias-utilizadas)
- [Como executar o projeto](#▶️-como-executar)
- [Variáveis de Ambiente](#⚙️-variáveis-de-ambiente)
- [Autenticação](#🔐-autenticação-jwt)
- [Endpoints](#pushpin-endpoints)
- [Insomnia Collection](#🔗-insomnia-collection)

---

## 💡 Sobre o projeto

A ideia surgiu da vontade de criar algo útil, bonito e diferente: uma API onde o usuário informa os ingredientes que tem e recebe sugestões de receitas que pode preparar com o que já possui.

Além disso, é possível favoritar receitas e ver os ingredientes que ainda faltam para completar alguma.

> Um projeto feito com foco no aprendizado de autenticação, consumo de API externa, e organização de uma API REST com boas práticas.

---

## ✨ Funcionalidades

- 🔐 Autenticação via JWT
- 👩‍🍳 Cadastro e login de usuários
- 🧂 Cadastro e listagem dos ingredientes do usuário
- 🍝 Busca de receitas com base nos ingredientes informados (Spoonacular)
- 💾 Favoritar receitas
- 📝 Ver detalhes da receita, modo de preparo e o que está faltando

---

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

## ▶️ Como executar

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL rodando
- IDE de sua preferência

### Rodando a aplicação

```bash
git clone https://github.com/seu-usuario-aqui/lets-cook.git
cd lets-cook
./mvnw spring-boot:run
