-- Active: 1742437067050@@127.0.0.1@3306@agenda
-- Insertar registros de ejemplo
INSERT INTO usuario (username, password) VALUES
('juan', '$2a$10$nQxiYTFgFjc9tpSmgZnUau31LDEv8v6Dz.LCPzHw9j5J9Sff.60ii'),  -- 1234
('ana',  '$2a$10$h5jUyrHkRHPCjGOg7H9U6OpyQp7wBQFuHrN0FvMnRnlU4RXjaVDVC');  -- abcd

INSERT INTO rol (nombre) VALUES 
('ROLE_USER'),
('ROLE_ADMIN');

-- Asignar roles a usuarios
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES
(1, 1),  -- juan tiene ROLE_USER
(2, 2);  -- ana tiene ROLE_ADMIN

-- Contactos de juan
INSERT INTO contacto (nombre, telefono, email, usuario_id) VALUES
('Carlos Rivera', '555-1111', 'carlos.rivera@example.com', 1),
('Laura Méndez', '555-2222', 'laura.mendez@example.com', 1),
('Pedro Sánchez', '555-3333', 'pedro.sanchez@example.com', 1);

-- Contactos de ana
INSERT INTO contacto (nombre, telefono, email, usuario_id) VALUES
('Lucía Torres', '555-4444', 'lucia.torres@example.com', 2),
('Miguel Rojas', '555-5555', 'miguel.rojas@example.com', 2),
('Elena Vidal', '555-6666', 'elena.vidal@example.com', 2);

INSERT INTO contacto (nombre, telefono, email, usuario_id) VALUES
('Tomás Díaz', '555-7777', 'tomas.diaz@example.com', 3),
('Valeria Silva', '555-8888', 'valeria.silva@example.com', 3),
('Bruno Acosta', '555-9999', 'bruno.acosta@example.com', 3);

-- Eventos de juan (usuario_id = 1)
INSERT INTO evento (titulo, descripcion, fecha, hora, usuario_id) VALUES
('Reunión con cliente', 'Presentación del producto', '2025-05-15', '10:00:00', 1),
('Cita médica', 'Chequeo anual', '2025-05-16', '08:30:00', 1);

-- Eventos de ana (usuario_id = 2)
INSERT INTO evento (titulo, descripcion, fecha, hora, usuario_id) VALUES
('Clases de yoga', 'Sesión semanal', '2025-05-17', '18:00:00', 2),
('Entrevista de trabajo', 'Posición en marketing', '2025-05-18', '09:00:00', 2);

-- Eventos de ? (usuario_id = 3)
INSERT INTO evento (titulo, descripcion, fecha, hora, usuario_id) VALUES
('Taller de programación', 'Curso de Java y Spring Boot', '2025-05-19', '14:00:00', 3),
('Cena familiar', 'Cumpleaños de mamá', '2025-05-20', '20:30:00', 3);


SELECT id FROM usuario WHERE username = 'joe';
SELECT * FROM evento WHERE usuario_id = (SELECT id FROM usuario WHERE username = 'joe');

SELECT username, password FROM usuario WHERE username = 'joe';
SELECT * FROM evento WHERE fecha = '2025-05-17';
