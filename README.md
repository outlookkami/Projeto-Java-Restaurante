# Meson - Sistema de gestão de atendimento para restaurantes

> O Meson é um sistema de organização dos fluxos de um restaurante, que conta com cadastros e outros fluxos para controle do atendimento, e utiliza o modelo SaaS. O sistema foi desenvolvido com Spring Boot e PostgreSQL, e conteinerizado com Docker.

---

## Funcionalidades do sistema
O sistema conta com 4 perfis de usuário, sendo eles: administrador geral (responsável por aprovar os cadastros de restaurantes), administrador (responsável pela gestão de um restaurante), garçom (encarregado dos atendimentos) e cozinha (responsável pela preparação e alteração de status dos pedidos).

Administrador: 
  - Cadastro de categorias
  - Cadastro de produtos
  - Cadastro de mesas
  - Cadastro de funcionários
  - Visualização de relatórios

Garçom:
  - Cadastro de comandas nas mesas
  - Registro de pedidos nas comandas

Cozinha: 
  - Altera status dos pedidos

---

## Tecnologias utilizadas
• Spring Boot
• PostgreSQL
• PgAdmin
• Docker
• Jasper Report 

---

## Como executar o projeto
É necessário ter instalado Git e Docker Desktop.
- Clone o repositório
- Execute o comando: docker compose up --build
- Acesse os caminhos: http://localhost:8080 e http://localhost:5050
- Caso a porta 8080 esteja ocupada, basta alterar a porta no arquivo docker-compose.yaml
