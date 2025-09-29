# 🏦 Devsu Transacciones - Microservicios con Spring Boot y Docker

Este proyecto implementa una arquitectura de **microservicios** utilizando **Spring Boot**, **MySQL** y **Docker Compose**, como parte de una prueba técnica.

El sistema gestiona **Clientes** y **Cuentas**, donde el microservicio de **Cuentas** consume al microservicio de **Clientes** mediante llamadas REST internas en la red de Docker.

---

## 📌 Arquitectura del sistema

La arquitectura consta de **tres servicios principales**:

1. **Base de datos MySQL (`db`)**
    - Contenedor que levanta la base de datos `devsu`.
    - Usuario: `root` | Password: `root`.
    - Expuesto en el puerto `3307` de tu máquina.

2. **Microservicio Clientes (`micro-clientes`)**
    - Administra los datos de los clientes.
    - Expone su API en: `http://localhost:8081/clientes`.
    - Se conecta a la base de datos `devsu`.

3. **Microservicio Cuentas (`micro-cuentas`)**
    - Gestiona cuentas bancarias y operaciones.
    - Expone su API en: `http://localhost:8082/cuentas`.
    - Se conecta a la base de datos `devsu`.
    - Consume al micro de clientes mediante `http://micro-clientes:8080/clientes` dentro de la red Docker.

---

## ⚙️ Requisitos previos

Asegúrate de tener instalados:

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/)

---

## 🚀 Cómo levantar el proyecto con Docker Compose

### 1. Clonar el repositorio

```bash
git clone https://github.com/Ninjajava/Devsu_Transacciones.git
cd Devsu_Transacciones

### 2. En la raiz del proyectoo transacciones ejecuta

```bash
docker-compose up --build -d

Este comando:

Descarga la imagen de MySQL si no la tienes.

Construye las imágenes de micro-clientes y micro-cuentas.

Levanta todos los servicios en segundo plano.




