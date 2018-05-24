/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_USER_TYPES  (
    ID_TYPE  INT,
    DE_TYPE  VARCHAR(90),
    CONSTRAINT  PK_ID_TYPE  PRIMARY KEY ( ID_TYPE )
);

DELETE FROM BA_USER_TYPES;

INSERT INTO BA_USER_TYPES VALUES
    (1, 'CUSTOMER'),
    (2, 'ADMIN'),
    (3, 'SUPER_ADMIN');