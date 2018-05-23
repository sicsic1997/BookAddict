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
    (2, 3),
    (3, 4),
    (4, 4),
    (5, 4),
    (6, 4),
    (7, 2),
    (7, 5),
    (8, 2),
    (9, 5),
    (10, 5),
    (11, 5),
    (12, 6),
    (13, 6),
    (14, 7),
    (15, 7),
    (16, 7);