/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_BOOK_TO_CATEGORIES_MAP  (
    ID_BOOK  INT,
    ID_CATEGORY  INT,
    CONSTRAINT  PK_BA_BOOK_TO_CATEGORIES_MAP  PRIMARY KEY (ID_BOOK, ID_CATEGORY)
);

DELETE FROM BA_BOOK_TO_CATEGORIES_MAP;

INSERT INTO BA_BOOK_TO_CATEGORIES_MAP VALUES
    (1, 4),
    (1, 1),
    (2, 3);