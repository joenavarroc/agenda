-- Active: 1742437067050@@127.0.0.1@3306@agenda
DROP DATABASE IF EXISTS agenda;
CREATE DATABASE agenda;

USE agenda;
CREATE TABLE rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE  -- Ejemplo: "ROLE_USER", "ROLE_ADMIN"
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios_roles (
    usuario_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (rol_id) REFERENCES rol(id) ON DELETE CASCADE
);

-- Tabla de contactos
CREATE TABLE contacto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    usuario_id BIGINT,  -- Clave foránea que se refiere a la tabla de usuarios
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)  -- Relación con la tabla de usuario
);

-- Tabla de eventos con relación a usuarioCREATE TABLE evento (CREATE TABLE evento (
CREATE TABLE evento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),  -- Columna descripcion agregada
    fecha DATE NOT NULL,
    hora TIME NOT NULL,  -- Hora del evento
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

SHOW TABLES;
 SELECT * FROM contacto;