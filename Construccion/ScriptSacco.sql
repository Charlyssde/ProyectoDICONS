-- MySQL Script generated by MySQL Workbench
-- Wed Dec  5 18:18:27 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema sacco
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sacco` ;

-- -----------------------------------------------------
-- Schema sacco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sacco` DEFAULT CHARACTER SET latin1 ;
SHOW WARNINGS;
USE `sacco` ;

-- -----------------------------------------------------
-- Table `sacco`.`dictamen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`dictamen` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`dictamen` (
  `iddictamen` INT(11) NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `observaciones` TINYTEXT NOT NULL,
  `descripcion` TINYTEXT NOT NULL,
  `idTecnico` VARCHAR(45) NOT NULL,
  `idSolicitante` VARCHAR(45) NOT NULL,
  `numEquipo` INT(11) NOT NULL,
  PRIMARY KEY (`iddictamen`),
  CONSTRAINT `idSolicitante`
    FOREIGN KEY (`idSolicitante`)
    REFERENCES `sacco`.`responsable` (`numPersonal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idTecnico`
    FOREIGN KEY (`idTecnico`)
    REFERENCES `sacco`.`tecnicoacademico` (`numPersonal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `numEquipo`
    FOREIGN KEY (`numEquipo`)
    REFERENCES `sacco`.`hardware` (`numInventario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `idTecnico_idx` ON `sacco`.`dictamen` (`idTecnico` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `idSolicitante_idx` ON `sacco`.`dictamen` (`idSolicitante` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `numEquipo_idx` ON `sacco`.`dictamen` (`numEquipo` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sacco`.`hardware`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`hardware` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`hardware` (
  `numInventario` INT(11) NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NOT NULL,
  `modelo` VARCHAR(45) NOT NULL,
  `numSerie` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `idubicacion` INT(11) NOT NULL,
  PRIMARY KEY (`numInventario`),
  CONSTRAINT `idubicacion`
    FOREIGN KEY (`idubicacion`)
    REFERENCES `sacco`.`ubicacion` (`idubicacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `idubicacion_idx` ON `sacco`.`hardware` (`idubicacion` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sacco`.`jefecentrocomputo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`jefecentrocomputo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`jefecentrocomputo` (
  `numPersonal` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`numPersonal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sacco`.`responsable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`responsable` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`responsable` (
  `numPersonal` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `extension` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`numPersonal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sacco`.`software`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`software` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`software` (
  `numInventario` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `numLicencias` INT(11) NOT NULL,
  `fechaAdq` DATE NOT NULL,
  `observaciones` VARCHAR(45) NOT NULL,
  `version` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`numInventario`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sacco`.`tecnicoacademico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`tecnicoacademico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`tecnicoacademico` (
  `numPersonal` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `telefono` VARCHAR(10) NOT NULL,
  `extension` VARCHAR(10) NULL DEFAULT NULL,
  `correoInstitucional` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`numPersonal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sacco`.`ubicacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sacco`.`ubicacion` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sacco`.`ubicacion` (
  `idubicacion` INT(11) NOT NULL AUTO_INCREMENT,
  `edificio` VARCHAR(45) NOT NULL,
  `uso` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idubicacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
