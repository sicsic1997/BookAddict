/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_WRITERS  (
    ID_WRITER  INT auto_increment,
    DE_WRITER  VARCHAR(150),
    CONSTRAINT  PK_BA_WRITERS  PRIMARY KEY ( ID_WRITER )
);

--== INSERT DUMMY VALUES
DELETE FROM BA_WRITERS;

INSERT INTO BA_WRITERS VALUES
    (1, 'Dan Brown'),
    (2, 'Mark Twain'),
    (3, 'Donald Trump'),
    (4, 'Issac Asimov');