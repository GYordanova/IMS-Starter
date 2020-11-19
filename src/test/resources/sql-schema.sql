drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `surname` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `Itemid` INT(11) NOT NULL AUTO_INCREMENT,
    `Itemname` VARCHAR(40) NULL DEFAULT NULL,
    `Itemmake` VARCHAR(40) NULL DEFAULT NULL,
    `Itemprice` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`Itemid`)
);
CREATE TABLE IF NOT EXISTS `ims`.`order` (
    `Orderid` INT(11) NOT NULL AUTO_INCREMENT,
    `Customerid` INT(11) NOT NULL,
    `price` DOUBLE NULL DEFAULT NULL,
    PRIMARY KEY (`Orderid`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orderline` (
	`Orderlineid` INT(11) NOT NULL AUTO_INCREMENT,
    `Orderid` INT(11) NOT NULL,
    `Itemid` INT(11) NOT NULL,
    PRIMARY KEY (`Orderlineid`)
);