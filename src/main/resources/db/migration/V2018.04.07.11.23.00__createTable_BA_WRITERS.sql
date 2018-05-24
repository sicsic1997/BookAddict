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
    (4, 'Issac Asimov'),
    (5, 'Juan Reinaldo Sanchez'),
    (6, 'F.M. Dostoievski'),
    (7, 'Marius Chivu'),
    (8, 'Charles Baudelaire'),
    (9, 'Matt Haig'),
    (10, 'Victoria Aveyard'),
    (11, 'Penelope Douglas'),
    (12, 'Gayle Forman'),
    (13, 'Vitali Cipileaga'),
    (14, 'Mihaela Noroc'),
    (15, 'Radu Ciorniciuc'),
    (16, 'Jamie Oliver'),
    (17, 'Adi Hadean'),
    (18, 'Peter Kaminski');