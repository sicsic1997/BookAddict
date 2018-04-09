/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_PUBLISHERS  (
    ID_PUBLISHER  INT auto_increment,
    DE_PUBLISHER  VARCHAR(50),
    ADDRESS VARCHAR(100),
    EMAIL VARCHAR(50),
    PHONE_NUMBER VARCHAR(20),
    CONSTRAINT  PK_BA_PUBLISHERS  PRIMARY KEY (ID_PUBLISHER )
);

--== INSERT DUMMY PUBLISHERS
DELETE FROM BA_PUBLISHERS;

INSERT INTO BA_PUBLISHERS VALUES
    (1, 'Corint', 'Cal. Lautarilor 94C', 'corint@ymail.com', '0723444333'),
    (2, 'Paralela 45', 'Bd. Libertatii 23A', 'paralela45@yahoo.co.uk', '0723123123');