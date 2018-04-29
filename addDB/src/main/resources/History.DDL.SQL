CREATE TABLE `history` (
	`id` BIGINT(20) NOT NULL,
	`description` VARCHAR(250) NOT NULL,
	`lat` FLOAT NOT NULL,
	`lng` FLOAT NOT NULL,
	`zoom` SMALLINT(6) NOT NULL,
	`creation_time` BIGINT(20) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
