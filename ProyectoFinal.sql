-- Crear DB
CREATE DATABASE PARQUE;

USE PARQUE;

-- Crear la tabla Juego
CREATE TABLE Juego (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  NombreJuego VARCHAR(50) NOT NULL,
  Precio DECIMAL(10,2) NOT NULL
);

-- Crear la tabla Cliente
CREATE TABLE Cliente (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Nit VARCHAR(20) NOT NULL,
  Nombre VARCHAR(50) NOT NULL,
  Direccion VARCHAR(255)
);
-- Agregar indice
ALTER TABLE `Juego` ADD INDEX(Nombre);
ALTER TABLE `Cliente` ADD INDEX(Nit);

-- Crear la tabla Boleto
CREATE TABLE Boleto (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Serie INT NOT NULL,
  Adulto BIT NOT NULL,
  Nino BIT NOT NULL,
  Cantidad INT NOT NULL,
  Fecha DATETIME NOT NULL,
  NombreJuego VARCHAR(50) NOT NULL,
  NitCliente VARCHAR(20) NOT NULL,
  FOREIGN KEY (NombreJuego) REFERENCES Juego(Nombre),
  FOREIGN KEY (NitCliente) REFERENCES Cliente(Nit)
);
-- Agregar indice
ALTER TABLE `Boleto` ADD INDEX(Cantidad);
ALTER TABLE `Boleto` ADD INDEX(Fecha);
ALTER TABLE `Juego` ADD INDEX(Precio);

-- Crear la tabla Caja
CREATE TABLE Caja (
  Id INT AUTO_INCREMENT PRIMARY KEY,
  Apertura BIT NOT NULL,
  Cantidad INT NOT NULL,
  Precio DECIMAL(10,2) NOT NULL,
  FechaTiempo DATETIME NOT NULL,
  FOREIGN KEY (Cantidad) REFERENCES Boleto(Cantidad),
  FOREIGN KEY (Precio) REFERENCES Juego(Precio),
  FOREIGN KEY (FechaTiempo) REFERENCES Boleto(Fecha)
);