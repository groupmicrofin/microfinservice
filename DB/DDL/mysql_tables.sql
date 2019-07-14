CREATE TABLE `group_masters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `login_id` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `audit_created_dttm` datetime DEFAULT NULL,
  `audit_updated_dttm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `group_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_master_id` int(11) NOT NULL,
  `group_start_date` date DEFAULT NULL,
  `meeting_frequency` int(11) DEFAULT NULL,
  `meeting_schedule` varchar(45) DEFAULT NULL,
  `share_face_value` int(11) DEFAULT NULL,
  `loan_interest_rate` int(11) DEFAULT NULL,
  `loan_interest_base` int(11) DEFAULT NULL,
  `loan_disb_amt_max_lim_percent` int(11) DEFAULT NULL,
  `loan_gauranters_count` int(11) DEFAULT NULL,
  `audit_created_date` date DEFAULT NULL,
  `audit_updated_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grpmst_1` (`group_master_id`),
  CONSTRAINT `FK_grpmst_1` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `meeting_calender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_master_id` int(11) NOT NULL,
  `cycle_no` int(11) NOT NULL,
  `share_amount` int(11) NOT NULL,
  `meeting_start_date` datetime DEFAULT NULL,
  `meeting_end_date` datetime DEFAULT NULL,
  `total_active_members` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_master_id_idx` (`group_master_id`),
  CONSTRAINT `FK_metclnd` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `group_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_master_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mobile` int(11) NOT NULL,
  `birth_date` date NOT NULL,
  `kyc_doc_type` varchar(45) DEFAULT NULL,
  `kyc_id` varchar(45) DEFAULT NULL,
  `member_status` varchar(45) NOT NULL DEFAULT 'active',
  `close_date` date DEFAULT NULL,
  `share_balance` int(11) DEFAULT NULL,
  `calender_id` int(11) DEFAULT NULL,
  `audit_created_dttm` datetime DEFAULT NULL,
  `audit_updated_dttm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calender_id_idx` (`calender_id`),
  KEY `group_master_id_idx` (`group_master_id`),
  CONSTRAINT `FK_gm` FOREIGN KEY (`calender_id`) REFERENCES `meeting_calender` (`id`),
  CONSTRAINT `FK_grpmst_2` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `group_balances` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_master_id` int(11) NOT NULL,
  `amt_share_fac_bal` int(11) DEFAULT NULL,
  `amt_share_fac_bal_others` int(11) DEFAULT NULL,
  `audit_created_dttm` datetime DEFAULT NULL,
  `amt_misc_dr` int(11) DEFAULT NULL,
  `dat_last_meeting` date DEFAULT NULL,
  `dat_next_meeting` date DEFAULT NULL,
  `last_activity_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gbal` (`group_master_id`),
  CONSTRAINT `FK_gbal` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `loan_masters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_member_id` int(11) NOT NULL,
  `amt_ln_disb` int(11) NOT NULL,
  `amt_ln_balance` double NOT NULL,
  `amt_int_accr` double NOT NULL,
  `amt_int_paid` double NOT NULL,
  `group_master_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grpmst_3` (`group_master_id`),
  KEY `FK_grpmembr1` (`group_member_id`),
  CONSTRAINT `FK_grpmembr1` FOREIGN KEY (`group_member_id`) REFERENCES `group_members` (`id`),
  CONSTRAINT `FK_grpmst_3` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`),
  CONSTRAINT `group_member_id` FOREIGN KEY (`id`) REFERENCES `group_members` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `ln_installments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_master_id` int(11) NOT NULL,
  `group_member_id` int(11) NOT NULL,
  `calender_id` int(11) NOT NULL,
  `amt_int_paid` double NOT NULL,
  `dat_installments` datetime DEFAULT NULL,
  `amt_princ_paid` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grpmembr2` (`group_member_id`),
  KEY `FK_grpmembr3` (`group_master_id`),
  CONSTRAINT `FK_grpmembr2` FOREIGN KEY (`group_member_id`) REFERENCES `group_members` (`id`),
  CONSTRAINT `FK_grpmembr3` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `group_event_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_master_id` int(11) NOT NULL,
  `group_member_id` int(11) NOT NULL,
  `txn_code` varchar(45) NOT NULL,
  `txn_amount` double DEFAULT NULL,
  `txn_date` datetime DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `calender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group_member_id_idx` (`group_member_id`),
  KEY `group_master_id_idx` (`group_master_id`),
  CONSTRAINT `FK_gel` FOREIGN KEY (`group_master_id`) REFERENCES `group_masters` (`id`),
  CONSTRAINT `FK_gel1` FOREIGN KEY (`group_member_id`) REFERENCES `group_members` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


