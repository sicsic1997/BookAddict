/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_USERS  (
    ID_USER  INT auto_increment,
    USER_NAME  VARCHAR(150) NOT NULL,
    FIRST_NAME VARCHAR(100) NOT NULL,
    LAST_NAME VARCHAR(50),
    PASSWORD VARCHAR(50) NOT NULL,
    ID_TYPE INT,
    PHONE_NUMBER VARCHAR(20),
    ADDRESS VARCHAR(100),
    CONSTRAINT  PK_BA_USERS  PRIMARY KEY ( ID_USER )
);

