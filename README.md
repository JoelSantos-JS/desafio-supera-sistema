Claro! Aqui está o conteúdo completo do `README.md` dentro do bloco Markdown:

```markdown
# Sistema de Gerenciamento de Tarefas

Um sistema de gerenciamento de tarefas desenvolvido com Spring Boot, que permite a criação, leitura, atualização e exclusão de listas e itens de tarefas. O sistema também possui endpoints para gerenciar tarefas com funcionalidades específicas.

## Sumário

- [Visão Geral](#visão-geral)
- [Instalação e Execução](#instalação-e-execução)
- [Endpoints da API](#endpoints-da-api)
- [Testes](#testes)
- [Docker](#docker)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Visão Geral

Este projeto é uma aplicação de gerenciamento de tarefas com os seguintes recursos:
- **Listas**: CRUD (Criar, Ler, Atualizar, Deletar) para listas de tarefas.
- **Itens**: CRUD para itens de tarefas dentro das listas.
- **Estado**: Atualização do estado dos itens de tarefas.

## Instalação e Execução

### Requisitos

- JDK 17
- Maven
- Docker (opcional, para execução em contêiner)

### Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/sistema-gerenciamento-tarefas.git
cd sistema-gerenciamento-tarefas
```

### Construir o Projeto

```bash
mvn clean install
```

### Executar a Aplicação Localmente

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

### Listas

- **Criar Lista**
  - `POST /listas`
  - Corpo da Requisição: `{"nome": "Nome da Lista"}`
  - Resposta: `201 Created`

- **Obter Lista por ID**
  - `GET /listas/{id}`
  - Resposta: `200 OK`

- **Listar Todas as Listas**
  - `GET /listas`
  - Resposta: `200 OK`

- **Atualizar Lista**
  - `PUT /listas/{id}`
  - Corpo da Requisição: `{"nome": "Novo Nome da Lista"}`
  - Resposta: `200 OK`

- **Deletar Lista**
  - `DELETE /listas/{id}`
  - Resposta: `204 No Content`

### Itens

- **Criar Item**
  - `POST /listas/{listaId}/itens`
  - Corpo da Requisição: `{"nome": "Nome do Item", "estado": "Novo"}`
  - Resposta: `200 OK`

- **Obter Item por ID**
  - `GET /listas/{listaId}/itens/{itemId}`
  - Resposta: `200 OK`

- **Listar Itens por Lista**
  - `GET /listas/{listaId}/itens`
  - Resposta: `200 OK`

- **Atualizar Item**
  - `PUT /listas/{listaId}/itens/{itemId}`
  - Corpo da Requisição: `{"nome": "Novo Nome do Item", "estado": "Atualizado"}`
  - Resposta: `200 OK`

- **Deletar Item**
  - `DELETE /listas/{listaId}/itens/{itemId}`
  - Resposta: `204 No Content`

- **Atualizar Estado do Item**
  - `PATCH /listas/{listaId}/itens/{itemId}/estado`
  - Parâmetro de Consulta: `estado=NovoEstado`
  - Resposta: `200 OK`

## Testes

Os testes do controlador foram escritos usando JUnit e Mockito. Para executar os testes, use o comando:

```bash
mvn test
```

Os testes cobrem operações CRUD para listas e itens, garantindo que o comportamento esperado da API seja mantido.

## Docker

### Construir a Imagem Docker

```bash
docker build -t sistema-gerenciamento-tarefas .
```

### Executar o Contêiner Docker

```bash
docker run -d -p 8080:8080 sistema-gerenciamento-tarefas
```

A aplicação estará disponível em `http://localhost:8080` dentro do contêiner Docker.

## Contribuição

Contribuições são bem-vindas! Se você deseja contribuir para este projeto, por favor, siga estas etapas:

1. Fork o repositório.
2. Crie uma nova branch (`git checkout -b feature/MinhaNovaFuncionalidade`).
3. Faça suas alterações e commit (`git commit -am 'Adiciona nova funcionalidade'`).
4. Envie para o repositório remoto (`git push origin feature/MinhaNovaFuncionalidade`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.
```

Sinta-se à vontade para ajustar qualquer seção conforme necessário para melhor refletir seu projeto e suas necessidades.
