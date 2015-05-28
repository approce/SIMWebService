CREATE DATABASE IF NOT EXISTS sim_web_service
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_bin;

USE sim_web_service;

CREATE TABLE users (
  user_id           LONG                            NOT NULL AUTO_INCREMENT,
  username          VARCHAR(20)                     NOT NULL,
  email             VARCHAR(60)                     NOT NULL,
  password          VARCHAR(100)                    NOT NULL,
  role              ENUM('ROLE_USER', 'ROLE_ADMIN') NOT NULL,
  balance           DOUBLE                          NOT NULL DEFAULT 0,
  registration_date TIMESTAMP                       NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (user_id)
);

CREATE TABLE offers (
  offer_id   VARCHAR(10)  NOT NULL,
  full_name  VARCHAR(100) NOT NULL,
  price      DOUBLE       NOT NULL,
  originator VARCHAR(30)  NOT NULL,
  PRIMARY KEY (offer_id)
);

CREATE TABLE requests (
  request_id LONG                               NOT NULL AUTO_INCREMENT,
  user_id    LONG                               NOT NULL,
  offer_id   VARCHAR(10)                        NOT NULL,
  status     ENUM('STOP', 'PREPARE', 'WAIT_NUMBER',
                  'NUMBER_SUBMIT', 'WAIT_CODE',
                  'COMPLETED', 'NUMBER_REJECT') NOT NULL,
  number     LONG                                        DEFAULT NULL,
  code       VARCHAR(50)                                 DEFAULT NULL,
  started    TIMESTAMP,
  finished   TIMESTAMP,
  expired    BOOLEAN                                     DEFAULT FALSE,
  PRIMARY KEY (request_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (offer_id) REFERENCES offers (offer_id)
);

CREATE TABLE transactions (
  transaction_id LONG   NOT NULL AUTO_INCREMENT,
  request_id     LONG   NOT NULL,
  change_value   DOUBLE NOT NULL,
  PRIMARY KEY (transaction_id),
  FOREIGN KEY (request_id) REFERENCES requests (request_id)
);


