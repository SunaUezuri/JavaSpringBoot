# 🎬 ScreenMatch

ScreenMatch é uma aplicação desenvolvida com **Spring Boot** que consome a API do **OMDB** para obter informações sobre séries e permite armazená-las em um banco de dados **PostgreSQL**. Além disso, conta com um sistema de tradução de inglês para português.

---

## 🚀 Tecnologias Utilizadas

📌 **Java 21**  
📌 **Spring Boot 3.3.1**  
📌 **Spring Data JPA**  
📌 **PostgreSQL**  
📌 **Jackson Databind** (Manipulação de JSON)  
📌 **Spring Boot DevTools**  
📌 **Maven**  

---

## 📂 Estrutura do Projeto

### 🔹 1. Config
- Contém a configuração do **CORS Filter** para permitir requisições de diferentes origens.

### 🔹 2. Controller
- Responsável por tratar as requisições HTTP da entidade **Série**.

### 🔹 3. DTO (Data Transfer Object)
- Possui dois **Records**:
  - **EpisodioDTO**
  - **SerieDTO**
- Esses records definem os dados que serão transmitidos nas requisições.

### 🔹 4. Main
- Contém um sistema para uma aplicação de terminal.

### 🔹 5. Model
- Armazena os **Records** que recebem os dados da API **OMDB**, além das classes principais:
  - **Serie** e **Episodio**, cujos atributos são baseados nos records.
  - **Enum Genero**, que define os gêneros das séries.

### 🔹 6. Repository
- Possui a interface **SerieRepository**, que é responsável por realizar as operações **JPA** no banco de dados.

### 🔹 7. Service
- Contém classes responsáveis pelo consumo da **API OMDB** e um subpacote **traducao**, que trata a integração com a **API de tradução**.

---

## 🛠️ Como Rodar o Projeto no IntelliJ IDEA

### ✅ Pré-requisitos
- **Java 21**
- **Maven**
- **PostgreSQL** instalado e configurado
- **IntelliJ IDEA**

### ▶️ Passos
1️⃣ Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/screenmatch.git
   ```
2️⃣ Abra o IntelliJ IDEA e importe o projeto:
   - Selecione **File** > **Open**.
   - Escolha o diretório do projeto **screenmatch** e clique em **OK**.
3️⃣ Configure o banco de dados PostgreSQL:
   - Crie um banco de dados chamado `screenmatch`.
   - Defina as credenciais no **application.properties**:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/screenmatch
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     ```
4️⃣ Aguarde o IntelliJ baixar as dependências do **Maven**.
5️⃣ Execute a aplicação:
   - No IntelliJ, abra a classe principal (`ScreenmatchApplication.java`).
   - Clique com o botão direito sobre a classe e selecione **Run 'ScreenmatchApplication'**.

---

## 📡 API Endpoints

| Método | Endpoint                           | Descrição |
|--------|------------------------------------|-----------|
| 🟢 GET    | `/series`                           | Lista todas as séries |
| 🟢 GET    | `/series/top5`                      | Retorna as 5 melhores séries |
| 🟢 GET    | `/series/lancamentos`               | Lista as séries mais recentes |
| 🟢 GET    | `/series/{id}`                       | Busca uma série por ID |
| 🟢 GET    | `/series/{id}/temporadas/todas`      | Lista todas as temporadas de uma série |
| 🟢 GET    | `/series/{id}/temporadas/{numero}`   | Lista os episódios de uma temporada específica |
| 🟢 GET    | `/series/categoria/{genero}`        | Lista séries por gênero |

---

## 📧 Contato
📩 Caso tenha dúvidas ou sugestões, entre em contato pelo e-mail: **wesley.jane.santos@gmail.com**

