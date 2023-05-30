SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema concerthallDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema concerthallDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `concerthallDB` DEFAULT CHARACTER SET UTF8MB4 ;
USE `concerthallDB` ;

-- -----------------------------------------------------
-- Table `concerthallDB`.`Venue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Venue` (
  `venue_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `capacity` INT NOT NULL,
  PRIMARY KEY (`venue_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `venue_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `start_date` DATETIME NOT NULL,
  `end_date` DATETIME NOT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_Event_Venue_idx` (`venue_id` ASC) VISIBLE,
  CONSTRAINT `fk_Event_Venue`
    FOREIGN KEY (`venue_id`)
    REFERENCES `concerthallDB`.`Venue` (`venue_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Artist` (
  `artist_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `genre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`artist_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Concert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Concert` (
  `concert_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `artist_id` INT NOT NULL,
  PRIMARY KEY (`concert_id`),
  INDEX `fk_Concert_Event1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Concert_Artist1_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `fk_Concert_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Concert_Artist1`
    FOREIGN KEY (`artist_id`)
    REFERENCES `concerthallDB`.`Artist` (`artist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Audience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Audience` (
  `audience_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `age` INT NOT NULL,
  `gender` ENUM('Male', 'Female', 'Other') NOT NULL,
  PRIMARY KEY (`audience_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Ticket` (
  `ticket_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `audience_id` INT NOT NULL,
  `seat_number` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  INDEX `fk_Ticket_Event1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Ticket_Audience1_idx` (`audience_id` ASC) VISIBLE,
  CONSTRAINT `fk_Ticket_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ticket_Audience1`
    FOREIGN KEY (`audience_id`)
    REFERENCES `concerthallDB`.`Audience` (`audience_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Staff` (
  `staff_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `role` VARCHAR(100) NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`staff_id`),
  INDEX `fk_Staff_Event1_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_Staff_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Booking` (
  `booking_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `audience_id` INT NOT NULL,
  `staff_id` INT NOT NULL,
  `booking_date` DATETIME NOT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `fk_Booking_Event1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Booking_Audience1_idx` (`audience_id` ASC) VISIBLE,
  INDEX `fk_Booking_Staff1_idx` (`staff_id` ASC) VISIBLE,
  CONSTRAINT `fk_Booking_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Audience1`
    FOREIGN KEY (`audience_id`)
    REFERENCES `concerthallDB`.`Audience` (`audience_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Staff1`
    FOREIGN KEY (`staff_id`)
    REFERENCES `concerthallDB`.`Staff` (`staff_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Equipment` (
  `equipment_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`equipment_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Event_Equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Event_Equipment` (
  `event_id` INT NOT NULL,
  `equipment_id` INT NOT NULL,
  INDEX `fk_Event_Equipment_Event1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Event_Equipment_Equipment1_idx` (`equipment_id` ASC) VISIBLE,
  PRIMARY KEY (`event_id`, `equipment_id`),
  CONSTRAINT `fk_Event_Equipment_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_Equipment_Equipment1`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `concerthallDB`.`Equipment` (`equipment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Sponsor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Sponsor` (
  `sponsor_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`sponsor_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Event_Sponsor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Event_Sponsor` (
  `event_id` INT NOT NULL,
  `sponsor_id` INT NOT NULL,
  INDEX `fk_Event_Sponsor_Event1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Event_Sponsor_Sponsor1_idx` (`sponsor_id` ASC) VISIBLE,
  PRIMARY KEY (`event_id`, `sponsor_id`),
  CONSTRAINT `fk_Event_Sponsor_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_Sponsor_Sponsor1`
    FOREIGN KEY (`sponsor_id`)
    REFERENCES `concerthallDB`.`Sponsor` (`sponsor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concerthallDB`.`Feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `concerthallDB`.`Feedback` (
  `feedback_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `audience_id` INT NOT NULL,
  `rating` INT NOT NULL,
  PRIMARY KEY (`feedback_id`),
  INDEX `fk_Feedback_Event1_idx` (`event_id` ASC) VISIBLE,
  INDEX `fk_Feedback_Audience1_idx` (`audience_id` ASC) VISIBLE,
  CONSTRAINT `fk_Feedback_Event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `concerthallDB`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Audience1`
    FOREIGN KEY (`audience_id`)
    REFERENCES `concerthallDB`.`Audience` (`audience_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
