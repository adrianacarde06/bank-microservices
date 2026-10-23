-- ==========================================
-- MICROSERVICIO: MS-PERSONAS
-- ==========================================
CREATE DATABASE IF NOT EXISTS bank_personas_bd;
USE bank_personas_bd;

CREATE TABLE persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(30), 
    edad INT,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    cliente_id BIGINT PRIMARY KEY, 
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_cliente_persona FOREIGN KEY (cliente_id) REFERENCES persona(id) ON DELETE CASCADE
);

CREATE INDEX idx_persona_identificacion ON persona(identificacion);


-- ==========================================
-- MICROSERVICIO: MS-CUENTAS
-- ==========================================
CREATE DATABASE IF NOT EXISTS bank_cuentas_bd;
USE bank_cuentas_bd;

CREATE TABLE cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DECIMAL(19,2) NOT NULL,
    saldo_actual DECIMAL(19,2) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    cliente_id BIGINT NOT NULL 
);

CREATE INDEX idx_cuenta_cliente ON cuenta(cliente_id);
CREATE INDEX idx_cuenta_numero ON cuenta(numero_cuenta);

CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cuenta_id BIGINT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento VARCHAR(20) NOT NULL, 
    valor DECIMAL(19,2) NOT NULL,
    saldo_resultante DECIMAL(19,2) NOT NULL,
    CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id) ON DELETE CASCADE
);

CREATE INDEX idx_movimiento_fecha ON movimiento(fecha);
CREATE INDEX idx_movimiento_cuenta ON movimiento(cuenta_id);
