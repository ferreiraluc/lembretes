---

# API Spring Boot - Gerenciamento de Pessoas e Lembretes

Essa API foi desenvolvida com o objetivo de fornecer um sistema para gerenciar pessoas e seus respectivos lembretes. A aplicação permite a criação, atualização, exclusão e listagem de pessoas, bem como a manipulação de lembretes associados a cada pessoa.

## Funcionalidades

1. **CRUD de Pessoa:**
    - Criar pessoa
    - Atualizar pessoa
    - Deletar pessoa
    - Listar todas as pessoas

2. **CRUD de Lembretes:**
    - Criar lembrete para uma pessoa
    - Atualizar lembrete
    - Deletar lembrete
    - Listar todos os lembretes de uma pessoa específica

3. **Buscar Lembretes de uma Pessoa pelo Nome:**
    - A busca ocorre utilizando o nome da pessoa (e não pelo ID)
    - O resultado da busca retorna o nome da pessoa e a lista de seus lembretes

## Instalação

### Pré-requisitos

- Java 8 ou superior
- Maven
- Banco de dados (configurar no `application.properties`)

### Passos

1. Clone o repositório:
   ```
   git clone https://github.com/ferreiraluc/lembretes
   ```

2. Navegue até o diretório do projeto:
   ```
   cd lembretes
   ```

3. Construa e execute o projeto com o Maven:
   ```
   mvn clean install
   mvn spring-boot:run
   ```

## Uso

**Base URL**: `http://localhost:8080`

### Endpoints:

- **Lembretes**:
  - Buscar um lembrete pelo ID: `GET /lembrete/{id}`
  - Cadastrar um novo lembrete: `POST /lembrete`
  - Atualizar um lembrete pelo ID: `PUT /lembrete/{id}`
  - Excluir um lembrete pelo ID: `DELETE /lembrete/{id}`

- **Pessoas**:
  - Buscar uma pessoa pelo ID: `GET /lembrete/pessoa/{id}`
  - Listar todas as pessoas: `GET /lembrete/pessoa`
  - Listar pessoas por status (ativo/inativo): `GET /lembrete/pessoa/ativo/{ativo}`
  - Cadastrar uma nova pessoa: `POST /lembrete/pessoa`
  - Atualizar uma pessoa pelo ID: `PUT /lembrete/pessoa/{id}`
  - Excluir uma pessoa pelo ID: `DELETE /lembrete/pessoa/{id}`


## Contribuição

Sinta-se à vontade para criar um fork e submeter Pull Requests!

## Licença

MIT

---
