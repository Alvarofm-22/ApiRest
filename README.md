
# 🚀 Rick and Morty REST API (Spring Boot)


![Logo](https://saasradar.b-cdn.net/wp-content/uploads/2022/03/api_rest.png)


API desarrollada en Java con Spring Boot que consume datos de la Rick and Morty API
y agrega autenticación con JWT.

💡 Este proyecto fue creado con fines educativos para practicar:


- Consumo de servicios REST externos
- Creación de endpoints propios
- Seguridad con Spring Security y JWT
- Documentación con Swagger/OpenAPI


## 📦 Instalación y uso

Sigue estos pasos para levantar el proyecto en tu máquina local:

---

### 1️⃣ Clona el repositorio

```bash
git clone https://github.com/tu-usuario/ApiRest.git

cd ApiRest
```



## 📌 Caracteristicas

#### 🔑 Autenticación JWT:

-  Devuelve un token que debe enviarse en el encabezado Authorization: Bearer <token>.
- Post para iniciar sesión

```https
http://localhost:8080/api/auth/login
```

| Parameter  | Type       | Description                |
|:-----------|:-----------| :------------------------- |
| `username` | `string`   | **Required**. Your API key |
| `password` | `string`   | **Required**. Your API key |
| `roles`    | `[string]` | **Required**. Your API key |
| `exp`      | `int`      | **Required**. Your API key |


###### EJEMPLO
```json
{
  "username": "devuser",
  "password": "devpass",
  "roles": ["ROLE_USER"],
  "exp": 1737303600
}

```


#### 👽 Gestión de personajes:

- Descripción: Devuelve todos los personajes disponibles.
- GET → Lista de personajes (sin paginación).

```https
http://localhost:8080/api/characters
```


- Descripción: Devuelve los personajes correspondientes a una página específica.
- GET → Lista de personajes (con paginación).

```https
http://localhost:8080/api/characters?page=2
```


#### 📝 Documentación interactiva:

[Disponible en click aqui](http://localhost:8080/swagger-ui.html)


## 🛠️ Tecnologías utilizadas

![Java](https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Web](https://img.shields.io/badge/Spring%20Web-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Java%20JSON%20Web%20Token-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC322C?style=for-the-badge&logo=lombok&logoColor=white)
![OpenAPI (Swagger)](https://img.shields.io/badge/OpenAPI%20%2F%20Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-A42E2B?style=for-the-badge&logo=apachemaven&logoColor=white)





