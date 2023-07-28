-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `a3Database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `a3Database` ;

-- -----------------------------------------------------
-- Table `a3Database`.`X-clients568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`X-clients568` (
  `clientId568` INT NOT NULL AUTO_INCREMENT,
  `clientCity568` VARCHAR(45) NULL DEFAULT NULL,
  `dollarsOnOrder568` INT NULL DEFAULT NULL,
  `clientStatus568` VARCHAR(45) NULL DEFAULT NULL,
  `clientName568` VARCHAR(45) NULL DEFAULT NULL,
  `clientCompPassword568` VARCHAR(45) NULL DEFAULT NULL,
  `moneyOwed568` INT NULL DEFAULT NULL,
  PRIMARY KEY (`clientId568`),
  UNIQUE INDEX `clientId_UNIQUE` (`clientId568` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`X-parts568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`X-parts568` (
  `partNo568` INT NOT NULL AUTO_INCREMENT,
  `partDescription568` VARCHAR(45) NULL DEFAULT NULL,
  `QoH568` VARCHAR(45) NULL DEFAULT NULL,
  `partName568` VARCHAR(45) NULL DEFAULT NULL,
  `currentPrice568` INT NULL DEFAULT NULL,
  PRIMARY KEY (`partNo568`),
  UNIQUE INDEX `partNo_UNIQUE` (`partNo568` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`X-pos568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`X-pos568` (
  `poNo568` INT NOT NULL AUTO_INCREMENT,
  `datePO568` DATETIME NULL DEFAULT NULL,
  `status568` VARCHAR(45) NULL DEFAULT NULL,
  `clientId568` INT NOT NULL,
  PRIMARY KEY (`poNo568`, `clientId568`),
  UNIQUE INDEX `poNo_UNIQUE` (`poNo568` ASC) VISIBLE,
  INDEX `fk_POs568_Clients568_idx` (`clientId568` ASC) VISIBLE,
  CONSTRAINT `fk_POs568_Clients568`
    FOREIGN KEY (`clientId568`)
    REFERENCES `a3Database`.`X-clients568` (`clientId568`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`X-line568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`X-line568` (
  `poNo568` INT NOT NULL,
  `lineNo568` INT NOT NULL,
  `partNo568` INT NOT NULL,
  `partPrice568` INT NOT NULL,
  `partQuantity568` INT NOT NULL,
  INDEX `fk_Line568_POs5681_idx` (`poNo568` ASC, `lineNo568` ASC) VISIBLE,
  INDEX `fk_Line568_Parts5681_idx` (`partNo568` ASC) VISIBLE,
  CONSTRAINT `fk_Line568_Parts5681`
    FOREIGN KEY (`partNo568`)
    REFERENCES `a3Database`.`X-parts568` (`partNo568`),
  CONSTRAINT `fk_Line568_POs5681`
    FOREIGN KEY (`poNo568`)
    REFERENCES `a3Database`.`X-pos568` (`poNo568`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- --------------------------------------------------------------------------------------------------------------
-- -----------------------------------------------------
-- Table `a3Database`.`Y-clients568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Y-clients568` (
  `clientId568` INT NOT NULL AUTO_INCREMENT,
  `clientCity568` VARCHAR(45) NULL DEFAULT NULL,
  `dollarsOnOrder568` INT NULL DEFAULT NULL,
  `clientStatus568` VARCHAR(45) NULL DEFAULT NULL,
  `clientName568` VARCHAR(45) NULL DEFAULT NULL,
  `clientCompPassword568` VARCHAR(45) NULL DEFAULT NULL,
  `moneyOwed568` INT NULL DEFAULT NULL,
  PRIMARY KEY (`clientId568`),
  UNIQUE INDEX `clientId_UNIQUE` (`clientId568` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`Y-parts568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Y-parts568` (
  `partNo568` INT NOT NULL AUTO_INCREMENT,
  `partDescription568` VARCHAR(45) NULL DEFAULT NULL,
  `QoH568` VARCHAR(45) NULL DEFAULT NULL,
  `partName568` VARCHAR(45) NULL DEFAULT NULL,
  `currentPrice568` INT NULL DEFAULT NULL,
  PRIMARY KEY (`partNo568`),
  UNIQUE INDEX `partNo_UNIQUE` (`partNo568` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`Y-pos568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Y-pos568` (
  `poNo568` INT NOT NULL AUTO_INCREMENT,
  `datePO568` DATETIME NULL DEFAULT NULL,
  `status568` VARCHAR(45) NULL DEFAULT NULL,
  `clientId568` INT NOT NULL,
  PRIMARY KEY (`poNo568`, `clientId568`),
  UNIQUE INDEX `poNo_UNIQUE` (`poNo568` ASC) VISIBLE,
  INDEX `fk_Y-POs568_Clients568_idx` (`clientId568` ASC) VISIBLE,
  CONSTRAINT `fk_Y-POs568_Clients568`
    FOREIGN KEY (`clientId568`)
    REFERENCES `a3Database`.`Y-clients568` (`clientId568`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`Y-line568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Y-line568` (
  `poNo568` INT NOT NULL,
  `lineNo568` INT NOT NULL,
  `partNo568` INT NOT NULL,
  `partPrice568` INT NOT NULL,
  `partQuantity568` INT NOT NULL,
  INDEX `fk_Y-Line568_POs5681_idx` (`poNo568` ASC, `lineNo568` ASC) VISIBLE,
  INDEX `fk_Y-Line568_Parts5681_idx` (`partNo568` ASC) VISIBLE,
  CONSTRAINT `fk_Y-Line568_Parts5681`
    FOREIGN KEY (`partNo568`)
    REFERENCES `a3Database`.`Y-parts568` (`partNo568`),
  CONSTRAINT `fk_Y-Line568_POs5681`
    FOREIGN KEY (`poNo568`)
    REFERENCES `a3Database`.`Y-pos568` (`poNo568`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- --------------------------------------------------------------------------------------------------------------
-- -----------------------------------------------------
-- Table `a3Database`.`Z-clients568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Z-clients568` (
  `clientId568` INT NOT NULL AUTO_INCREMENT,
  `clientCity568` VARCHAR(45) NULL DEFAULT NULL,
  `dollarsOnOrder568` INT NULL DEFAULT NULL,
  `clientStatus568` VARCHAR(45) NULL DEFAULT NULL,
  `clientName568` VARCHAR(45) NULL DEFAULT NULL,
  `clientCompPassword568` VARCHAR(45) NULL DEFAULT NULL,
  `moneyOwed568` INT NULL DEFAULT NULL,
  PRIMARY KEY (`clientId568`),
  UNIQUE INDEX `clientId_UNIQUE` (`clientId568` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`Z-pos568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Z-pos568` (
  `poNo568` INT NOT NULL AUTO_INCREMENT,
  `datePO568` DATETIME NULL DEFAULT NULL,
  `status568` VARCHAR(45) NULL DEFAULT NULL,
  `clientId568` INT NOT NULL,
  PRIMARY KEY (`poNo568`, `clientId568`),
  UNIQUE INDEX `poNo_UNIQUE` (`poNo568` ASC) VISIBLE,
  INDEX `fk_Z-POs568_Clients568_idx` (`clientId568` ASC) VISIBLE,
  CONSTRAINT `fk_Z-POs568_Clients568`
    FOREIGN KEY (`clientId568`)
    REFERENCES `a3Database`.`Z-clients568` (`clientId568`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `a3Database`.`Z-line568`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `a3Database`.`Z-line568` (
  `poNo568` INT NOT NULL,
  `lineNo568` INT NOT NULL,
  `company568` VARCHAR(45) NOT NULL,
  `partNo568` INT NOT NULL,
  `partPrice568` INT NOT NULL,
  `partQuantity568` INT NOT NULL,
  INDEX `fk_Z-Line568_POs5681_idx` (`poNo568` ASC, `lineNo568` ASC) VISIBLE,
  INDEX `fk_Z-Line568_Parts5681_idx` (`partNo568` ASC) VISIBLE,
  CONSTRAINT `fk_Z-Line568_POs5681`
    FOREIGN KEY (`poNo568`)
    REFERENCES `a3Database`.`Z-pos568` (`poNo568`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Creating X-Parts for Company X
-- -----------------------------------------------------
INSERT INTO `x-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Wall mount Screw', 200, 'Screw', 200);

INSERT INTO `x-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Standard sink washer', 100, 'Washer', 100);

INSERT INTO `x-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Light oak flooring', 1400, 'Tiles', 25);

INSERT INTO `x-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('10 inch nails', 135, 'Nails', 10);


-- -----------------------------------------------------
-- Creating X-Clients for Company X
-- -----------------------------------------------------
INSERT INTO `x-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Halifax', 100, 'Active', 'Dan', 'Dan123', 2000);

INSERT INTO `x-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Victoria', 0, 'Inactive', 'Bobby', 'sugar23!', 350);

INSERT INTO `x-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Tennessee', 15, 'Active', 'Lynda', 'pASWod1003', 0);

INSERT INTO `x-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Toronto', 15, 'Active', 'Melissa', 'happyDays', 124);

-- -----------------------------------------------------
-- Creating X-Pos for Purchase order of Company X
-- -----------------------------------------------------
INSERT INTO `x-pos568` (datePO568, status568, clientId568)
VALUES ('2022-12-25', 'Complete', 1);

INSERT INTO `x-pos568` (datePO568, status568, clientId568)
VALUES ('2022-01-05', 'Incomplete', 1);

INSERT INTO `x-pos568` (datePO568, status568, clientId568)
VALUES ('2021-05-11', 'Incomplete', 3);

INSERT INTO `x-pos568` (datePO568, status568, clientId568)
VALUES ('2020-11-11', 'Cancelled', 4);

INSERT INTO `x-pos568` (datePO568, status568, clientId568)
VALUES ('2020-01-23', 'Incomplete', 4);

-- -----------------------------------------------------
-- Creating X-Lines for Company X
-- -----------------------------------------------------
INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (1, 1, 1, 10, 2);

INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (1, 2, 2, 24, 3);

INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (2, 1, 3, 3, 4);

INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (3, 1, 2, 25, 5);

INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (4, 1, 3, 4, 200);

INSERT INTO `x-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (5, 1, 3, 6, 200);


-- -----------------------------------------------------
-- Creating y-Parts for Company Y
-- -----------------------------------------------------
INSERT INTO `y-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Black cabinets', 12, 'Cabinet', 5);

INSERT INTO `y-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Bright Green Hammer', 150, 'Hammer', 15);

INSERT INTO `y-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Screwdriver with adjusting head', 45, 'Screwdriver', 3);

INSERT INTO `y-parts568` (partDescription568, QoH568, partName568, currentPrice568)
VALUES ('Purple nail gun', 50, 'Nail Gun', 3);

-- -----------------------------------------------------
-- Creating y-Clients for Company Y
-- -----------------------------------------------------
INSERT INTO `y-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Vancouver', 180, 'Active', 'Michael', 'Micha3l123', 22000);

INSERT INTO `y-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Chicago', 0, 'Inactive', 'Taylor', 'Tgirly2012!', 0);

INSERT INTO `y-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Lexington', 90, 'Active', 'Liv', 'livvy2002', 3300);

INSERT INTO `y-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Fredericksburg', 0, 'Active', 'Company Z', '!128923!', 0);

-- -----------------------------------------------------
-- Creating y-Pos for Company Y
-- -----------------------------------------------------
INSERT INTO `y-pos568` (datePO568, status568, clientId568)
VALUES ('2008-01-01', 'Complete', 1);

INSERT INTO `y-pos568` (datePO568, status568, clientId568)
VALUES ('2021-09-26', 'Waiting', 3);

INSERT INTO `y-pos568` (datePO568, status568, clientId568)
VALUES ('2022-11-11', 'Complete', 3);

INSERT INTO `y-pos568` (datePO568, status568, clientId568)
VALUES ('2020-12-11', 'Pending', 4);

INSERT INTO `y-pos568` (datePO568, status568, clientId568)
VALUES ('2022-12-11', 'Incomplete', 4);

-- -----------------------------------------------------
-- Creating y-Lines for Company Y
-- -----------------------------------------------------
INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (1, 1, 1, 5, 10);

INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (1, 2, 2, 26, 10);

INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (2, 1, 3, 3, 50);

INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (3, 1, 2, 26, 1);

INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (4, 1, 3, 3, 20);

INSERT INTO `y-line568` (poNo568, lineNo568, partNo568, partPrice568, partQuantity568)
VALUES (5, 1, 1, 5, 10);

-- -----------------------------------------------------
-- Creating z-Clients for Company Z
-- -----------------------------------------------------
INSERT INTO `z-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Wolfville', 0, 'Active', 'Neil', 'NeiltheThri!!', 20);

INSERT INTO `z-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Sydney', 0, 'InActive', 'Morgan', 'Morgan201223', 0);

INSERT INTO `z-clients568` (clientCity568, dollarsOnOrder568, clientStatus568, clientName568, clientCompPassword568, moneyOwed568)
VALUES ('Bedford', 0, 'Active', 'Patrick', 'karson321', 1004);

-- -----------------------------------------------------
-- Creating Pos for Company Z
-- -----------------------------------------------------
INSERT INTO `z-pos568` (datePO568, status568, clientId568)
VALUES ('2014-12-11', 'Complete', 3);

INSERT INTO `z-pos568` (datePO568, status568, clientId568)
VALUES ('2021-11-16', 'Incomplete', 3);

INSERT INTO `z-pos568` (datePO568, status568, clientId568)
VALUES ('2021-09-27', 'Pending', 3);

-- -----------------------------------------------------
-- Creating Lines for Company Z
-- -----------------------------------------------------
INSERT INTO `z-line568` (poNo568, lineNo568, company568, partNo568, partPrice568, partQuantity568)
VALUES (1, 1, "X", 3, 4, 200);

INSERT INTO `z-line568` (poNo568, lineNo568, company568, partNo568, partPrice568, partQuantity568)
VALUES (1, 2, "Y", 3, 2, 20);

INSERT INTO `z-line568` (poNo568, lineNo568, company568, partNo568, partPrice568, partQuantity568)
VALUES (2, 1, "Y", 1, 3, 10);

INSERT INTO `z-line568` (poNo568, lineNo568, company568, partNo568, partPrice568, partQuantity568)
VALUES (2, 1, "X", 1, 4, 56);

