/*********************************************************************************************************
* BOOK ADDICT                                                                                  *
* ------------------------------------------------------------------------------------------------------ *
*                                                                            *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_BOOKS  (
    ID_BOOK  INT auto_increment,
    BOOK_NAME  VARCHAR(100),
    DE_BOOK VARCHAR(300),
    PRICE DECIMAL,
    QUANTITY INT,
    ID_WRITER INT,
    ID_PUBLISHER INT,
    IMG_NAME VARCHAR(100),
    CONSTRAINT  PK_BA_BOOKS PRIMARY KEY ( ID_BOOK )
);

--== INSERT DUMMY BOOKS
DELETE FROM BA_BOOKS;

INSERT INTO BA_BOOKS VALUES
    (
        1,
        'Steve Jobs',
        'This biography has been published in 2011 as a tribute to the man that created Apple.',
        45.5,
        60,
        3,
        1,
        'stevaJobs.jpg'
    ),
    (
        2,
        'Robots in Neverland',
        'Being on of the best SF novel of Asimov, "Robots in Neverland" recreates the times of the year 3500. ',
        39.9,
        45,
        4,
        2,
        'robotsInNeverland.jpg'
    );