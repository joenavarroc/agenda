# 🗓️ Agenda App

Aplicación web desarrollada en **Java** con **Spring Boot**, que permite a los usuarios **registrarse, iniciar sesión (JWT y OAuth2)**, **agendar contactos y eventos**, y recuperar credenciales por correo electrónico. Incluye frontend en **HTML, CSS, JavaScript** y despliegue con **Docker** y **Docker Compose** usando **MySQL** como base de datos. Documentación disponible con **Swagger**.

---

## 🚀 Funcionalidades Principales

- ✅ Registro y login de usuarios
- ✅ Autenticación con **JWT**
- ✅ Login social con **OAuth2 (Google)**
- ✅ Agendamiento de:
  - 📇 Contactos
  - 📆 Eventos con calendario
- ✅ Recuperación de contraseña vía email
- ✅ Seguridad con **Spring Security**
- ✅ API documentada con **Swagger**
- ✅ Base de datos MySQL
- ✅ Contenedores con **Docker y Docker Compose**

---

## 🧰 Tecnologías Usadas

| Backend | Frontend | Infraestructura |
|--------|----------|------------------|
| Java 17 | HTML5 | Docker |
| Spring Boot | CSS3 | Docker Compose |
| Spring Security | JavaScript | MySQL |
| JWT | — | Mail Sender |
| OAuth2 (Google) | — | Swagger UI |

---

## 📂 Estructura del Proyecto
agenda/
├── src/
│ ├── main/
│ │ ├── java/com/agenda/
│ │ └── resources/
│ └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md


---

## 🔐 Autenticación

- **JWT**: Para sesiones estándar.
- **OAuth2 con Google, Github, Facebook**: Para login con cuentas externas.
- **Recuperación por email**: Envío automático de token para restablecer contraseña.

---

## 🧪 Swagger

> Disponible en:  
`http://localhost:8080/swagger-ui/index.html`

Permite explorar y probar todos los endpoints disponibles (login, registro, contactos, eventos, recuperación, etc.).

---

## 🐬 Docker y MySQL

### ✅ Levantar el proyecto con un solo comando:

```bash
docker-compose up --build

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu-email@gmail.com
spring.mail.password=tu-contraseña-generada
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

🧑‍💻 Desarrollador
👨‍💻 Joe Navarro

🌐 GitHub: @joenavarroc
