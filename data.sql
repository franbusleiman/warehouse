-- Crear tabla de roles
CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Insertar roles
INSERT INTO roles (nombre) VALUES ('OPERARIO'), ('SUPERVISOR');

-- Crear tabla de usuarios
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Crear tabla de productos
CREATE TABLE productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    stock INT NOT NULL
);

-- Crear tabla de órdenes
CREATE TABLE ordenes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla de fases
CREATE TABLE fases (
    id INT PRIMARY KEY AUTO_INCREMENT,
    orden_id INT NOT NULL,
    tipo ENUM('PICKING', 'CONTROL_CALIDAD', 'EMBALAJE', 'DESPACHO') NOT NULL,
    estado ENUM('BLOQUEADA', 'DISPONIBLE', 'EN_PROCESO', 'COMPLETADA') DEFAULT 'BLOQUEADA',
    asignada_a INT DEFAULT NULL,
    fecha_inicio DATETIME,
    fecha_fin DATETIME,
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (asignada_a) REFERENCES usuarios(id)
);

-- Crear tabla intermedia producto_orden
CREATE TABLE producto_orden (
    id INT PRIMARY KEY AUTO_INCREMENT,
    orden_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- Consultar cuántas fases completó cada operario y su tiempo promedio
SELECT
    u.usuario,
    COUNT(f.id) AS fases_completadas,
    ROUND(AVG(TIMESTAMPDIFF(MINUTE, f.fecha_inicio, f.fecha_fin)), 2) AS tiempo_promedio_minutos
FROM fases f
JOIN usuarios u ON f.asignada_a = u.id
WHERE f.estado = 'COMPLETADA'
GROUP BY u.usuario
ORDER BY fases_completadas DESC;

-- Niveles de stock actual
SELECT
    id AS producto_id,
    nombre,
    stock
FROM productos
ORDER BY stock ASC;

-- Tiempo promedio por tipo de fase
SELECT
    tipo,
    ROUND(AVG(TIMESTAMPDIFF(MINUTE, fecha_inicio, fecha_fin)), 2) AS tiempo_promedio_minutos
FROM fases
WHERE estado = 'COMPLETADA'
GROUP BY tipo;
