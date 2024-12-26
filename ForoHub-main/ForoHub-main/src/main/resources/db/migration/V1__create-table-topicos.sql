CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    mensaje TEXT,
    fecha_creacion DATETIME,
    status VARCHAR(255),
    autor VARCHAR(255),
    curso VARCHAR(255)
);
