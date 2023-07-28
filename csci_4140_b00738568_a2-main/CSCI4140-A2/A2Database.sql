-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pO
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pO
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pO` DEFAULT CHARACTER SET utf8 ;
USE `pO` ;

-- -----------------------------------------------------
-- Table `pO`.`Parts568`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pO`.`Parts568` ;

CREATE TABLE IF NOT EXISTS `pO`.`Parts568` (
  `partNo568` INT NOT NULL,
  `qoh568` INT NULL,
  `partName568` VARCHAR(45) NULL,
  `currentPrice568` DECIMAL(7,2) NULL,
  `partDescription568` VARCHAR(45) NULL,
  PRIMARY KEY (`partNo568`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pO`.`Clients568`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pO`.`Clients568` ;

CREATE TABLE IF NOT EXISTS `pO`.`Clients568` (
  `clientId568` INT NOT NULL,
  `clientCity568` VARCHAR(45) NULL,
  `moneyOwed568` DECIMAL(18) NULL,
  `clientName568` VARCHAR(45) NULL,
  `clientPassword568` VARCHAR(45) NULL,
  `dollarsOnOrder568` DECIMAL(18) NULL,
  `clientStatus568` VARCHAR(45) NULL,
  PRIMARY KEY (`clientId568`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pO`.`PO568`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pO`.`PO568` ;

CREATE TABLE IF NOT EXISTS `pO`.`PO568` (
  `poNo568` INT NOT NULL,
  `datePO568` DATETIME NULL,
  `status568` VARCHAR(45) NULL,
  `clientID568` INT NULL,
  PRIMARY KEY (`poNo568`),
  INDEX `clientId_568_idx` (`clientID568` ASC) VISIBLE,
  CONSTRAINT `clientId_568`
    FOREIGN KEY (`clientID568`)
    REFERENCES `pO`.`Clients568` (`clientId568`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pO`.`Lines568`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pO`.`Lines568` ;

CREATE TABLE IF NOT EXISTS `pO`.`Lines568` (
  `poNo568` INT NOT NULL,
  `partNo568` INT NOT NULL,
  `priceOrdered568` DECIMAL(8,2) NULL,
  `lineNo568` INT NULL,
  `qty568` INT NULL,
  PRIMARY KEY (`poNo568`, `partNo568`),
  INDEX `partNo_568_idx` (`partNo568` ASC) VISIBLE,
  CONSTRAINT `poNo_568`
    FOREIGN KEY (`poNo568`)
    REFERENCES `pO`.`PO568` (`poNo568`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `partNo_568`
    FOREIGN KEY (`partNo568`)
    REFERENCES `pO`.`Parts568` (`partNo568`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Table content insertion
-- -----------------------------------------------------
INSERT INTO Parts568 
VALUES (00001, 12, 'Screw', '2.45', 'Black'),
(00002, 3, 'Pipe', '5.45', 'Steel'),
(00003, 24, 'Hammer', '9.45', 'Orange');

INSERT INTO Clients568
VALUES (5, 'Winnipeg', 500, 'Richard', 'qwerty', '250', 'done');

INSERT INTO PO568
VALUES (70003, '2022-05-23 12:00:00', 'In progress', 5);

INSERT INTO Lines568
VALUES (70004, 2, 25.00, 2, 3);

Drop table po.po;