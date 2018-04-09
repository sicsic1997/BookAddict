/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_CATEGORIES  (
    ID_CATEGORY  INT auto_increment,
    DE_CATEGORY  VARCHAR(50),
    CONSTRAINT  PK_BA_CATEGORIES  PRIMARY KEY ( ID_CATEGORY )
);

--== INSERT DUMMY CATEGORIES
DELETE FROM BA_CATEGORIES;

INSERT INTO BA_CATEGORIES VALUES
    (1, 'Personal Development'),
    (2, 'Fiction'),
    (3, 'SF'),
    (4, 'Biographies');