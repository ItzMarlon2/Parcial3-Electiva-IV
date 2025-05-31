# 📦 Parcial 3 - Electiva V

Este proyecto consiste en la implementación de un sistema CRUD distribuido en microservicios independientes para gestionar productos. Cada microservicio se encarga de una operación específica (`get`, `post`, `patch`, `delete`) y está desarrollado con Spring Boot y desplegado en Kubernetes.

## 📁 Estructura del Proyecto

El repositorio principal incluye los siguientes submódulos:

- [`get`](https://github.com/ItzMarlon2/get-ms): Obtención de productos.
- [`post`](https://github.com/ItzMarlon2/post-ms): Creación de productos.
- [`patch`](https://github.com/ItzMarlon2/patch-ms): Actualización de productos.
- [`delete`](https://github.com/ItzMarlon2/delete-ms): Eliminación de productos.

Otros archivos importantes:
- `docker-compose.yml`: Orquestación de servicios para desarrollo local.
- `backup-2025-05-31.sql`: Script de inicialización para la base de datos MariaDB.
- `Parcial3-ElectivaIV.postman_collection`: Colección de pruebas en Postman.

## ⚙️ Tecnologías

- Java 17
- Spring Boot 3.5
- MariaDB
- Docker & Docker Compose
- Kubernetes (kubectl, minikube)
- JaCoCo (Cobertura de pruebas)
- JUnit & Mockito

## 🚀 Despliegue

### Docker Compose (para desarrollo local)


docker-compose up --build

# Kubernetes (para producción/local avanzado)

## Aplicar servicios y deployments
kubectl apply -f kubernetes/mariadb
kubectl apply -f kubernetes/get
kubectl apply -f kubernetes/post
kubectl apply -f kubernetes/patch
kubectl apply -f kubernetes/delete

## Redireccionar puertos
kubectl port-forward svc/get-products-ms 8081:80
kubectl port-forward svc/post-products-ms 8082:80
kubectl port-forward svc/patch-products-ms 8083:80
kubectl port-forward svc/delete-products-ms 8084:80

# 📦 Endpoints
GET: GET /products

POST: POST /products

PATCH: PATCH /products/{id}

DELETE: DELETE /products/{id}

Consulta la colección de Postman incluida para probar todos los casos.

👨‍💻 Autor
Marlon (@ItzMarlon2)

```bash
