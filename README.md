# 📅 Backend de Gerenciamento de Eventos Tech

## 📌 Objetivo

**Backend de uma aplicação em Spring Boot** para gerenciar eventos de tecnologia, permitindo:

* Cadastro de eventos
* Listagem paginada de eventos futuros
* Filtros por critérios específicos
* Consulta detalhada de um evento
* Associação de cupons de desconto aos eventos

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* Spring Web
* Spring Data JPA
* Hibernate
* Banco de Dados Relacional (PostgreSQL / MySQL / H2)
* Maven ou Gradle

---

## 🧩 Domínio do Problema

### 📍 Evento

Um evento pode ser **remoto** ou **presencial** e possui os seguintes campos:

| Campo     | Descrição                            | Obrigatório     |
| --------- | ------------------------------------ | --------------- |
| titulo    | Título do evento                     | ✅               |
| descricao | Descrição detalhada                  | ❌               |
| data      | Data do evento                       | ✅               |
| local     | Local do evento (somente presencial) | ✅ se presencial |
| imagem    | Banner ou imagem do evento           | ❌               |
| urlEvento | URL do evento (somente remoto)       | ✅ se remoto     |
| tipo      | REMOTO ou PRESENCIAL                 | ✅               |

📌 **Regras**:

* Eventos remotos **não possuem local**
* Eventos presenciais **não possuem URL**
* Apenas eventos **futuros** devem ser retornados nas listagens

---

### 🎟️ Cupom de Desconto

Cada evento pode possuir **um ou mais cupons**, com os seguintes campos:

| Campo    | Descrição                 | Obrigatório |
| -------- | ------------------------- | ----------- |
| codigo   | Código do cupom           | ✅           |
| desconto | Percentual ou valor fixo  | ✅           |
| validade | Data de validade do cupom | ❌           |

📌 **Regras**:

* Apenas cupons **válidos** devem ser retornados
* Um cupom está ativo se:

  * Não possui validade **ou**
  * A data de validade é maior ou igual à data atual

---

## 🚀 Funcionalidades

### ✅ Cadastro de Evento

* Permite cadastrar eventos remotos ou presenciais
* Valida campos obrigatórios de acordo com o tipo do evento

### ✅ Associação de Cupons

* Permite associar um ou mais cupons a um evento
* Cada cupom pertence a um único evento

### 📄 Listagem de Eventos

* Retorna **apenas eventos futuros**
* Listagem paginada
* Campos retornados:

  * Título
  * Data
  * Local
  * Tipo (remoto/presencial)
  * Banner
  * Descrição

### 🔎 Filtros Disponíveis

A listagem de eventos pode ser filtrada por:

* Título
* Data
* Local

Os filtros podem ser utilizados de forma combinada

### 🔍 Detalhamento de Evento

Permite consultar todos os detalhes de um evento específico:

* Título
* Descrição
* Data
* Local
* Imagem
* URL do evento
* Lista de cupons ativos, contendo:

  * Código do cupom
  * Desconto
  * Data de validade

---

## 📐 Arquitetura Sugerida

```
com.seuprojeto.eventos
├── controller
├── service
├── repository
├── entity
├── dto
├── enums
└── exception
```

* **Controller**: expõe os endpoints REST
* **Service**: regras de negócio
* **Repository**: acesso ao banco de dados
* **Entity**: mapeamento JPA
* **DTO**: objetos de entrada e saída
* **Enums**: tipo do evento, tipo de desconto
* **Exception**: tratamento de erros

---

## 📡 Endpoints (Exemplo)

| Método | Endpoint             | Descrição                              |
| ------ | -------------------- | -------------------------------------- |
| POST   | /events              | Cadastrar evento                       |
| GET    | /events              | Listar eventos com paginação e filtros |
| GET    | /events/{id}         | Detalhar evento                        |
| POST   | /events/{id}/coupons | Associar cupom ao evento               |

---

## 🧪 Validações Importantes

* Data do evento deve ser futura
* Evento remoto exige URL
* Evento presencial exige local
* Código de cupom deve ser único por evento

---

## 📚 Observações Finais

Este projeto foi desenvolvido com foco em:

* Boas práticas de arquitetura
* Separação de responsabilidades
* Código limpo e escalável

💡 Ideal para evoluções futuras como autenticação, favoritos, inscrições e notificações.

---

## 🔄 Evolução da Arquitetura

Este projeto será **refatorado futuramente para utilizar Arquitetura Hexagonal (Ports and Adapters)**, com os seguintes objetivos:

* Melhor separação entre regras de negócio e frameworks
* Maior testabilidade do core da aplicação
* Facilidade para troca de tecnologias (ex: banco de dados, mensageria, APIs externas)
* Código mais desacoplado e sustentável a longo prazo

### 📐 Direcionamento da Refatoração

A refatoração irá introduzir:

* **Domínio isolado** (entidades e regras de negócio puras)
* **Ports** (interfaces que definem contratos de entrada e saída)
* **Adapters** (implementações para Web, Persistência, etc.)
* Framework Spring atuando apenas como **detalhe de infraestrutura**

Essa evolução garantirá que o projeto siga princípios de **Clean Architecture** e **DDD tático**, facilitando manutenção e evolução contínua.
