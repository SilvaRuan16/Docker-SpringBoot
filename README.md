# Demonstração de Competência - Docker + Spring Boot
## Disciplina: Sistemas Distribuídos

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- **Spring Boot**
- **API REST**
- **Maven**
- **Docker**

### 📝 Descrição

Trata-se de uma API simples de CRUD para manipulação de dados de usuários, contendo campos como `userName` e `password`.  
Nesta primeira versão, o foco está nas funcionalidades básicas (CRUD com dados persistentes).  
Está planejado, em futuras versões, a implementação de criptografia de senhas, como demonstração de conformidade com a **LGPD (Lei Geral de Proteção de Dados)**.

---

## 🚀 Como Executar via Docker

### 1. Clonar o repositório
```bash
git clone https://github.com/SilvaRuan16/Docker-SpringBoot.git
cd Docker-SpringBoot
```

### 2. Buildar a imagem Docker
```bash
docker build -t spring-java .
```

### 3. Execute o container
```bash
docker run -p 8080:8080 spring-java
```

### 4. Abra o Navegador e cole na barra de pesquisa a seguinte url
```bash
http://localhost:8080/swagger-ui/index.html
```