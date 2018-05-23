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
        '1.jpg'
    ),
    (
        2,
        'Wild Robots',
        'Being on of the best SF novel of Asimov, "Wild Robots" recreates the times of the year 3500. ',
        39.9,
        45,
        4,
        2,
        '2.jpg'
    ),
    (
        3,
        'The double life of Fidel Castro',
        'In The Double Life of Fidel Castro, one of Castro’s soldiers of seventeen years breaks his silence and shares his memoirs of his years of service.',
        35.5,
        72,
        5,
        3,
        '3.jpg'
    ),
    (
        4,
        'Letters I',
        'Letters of Fyodor Michailovitch Dostoevsky to His Family and Friends',
        40.99,
        24,
        6,
        4,
        '4.jpg'
    ),
    (
        5,
        'Three weeks in the Andes',
        'The publication, written in a travel journal format, tells the author’s journey through different regions of Peru for three weeks.',
        50.5,
        67,
        7,
        5,
        '5.jpg'
    ),
    (
        6,
        'Intimate Journals',
        'This volume of essays and drawings — collected and published after his death — includes cryptic memoranda, literary notes, quotations, rough drafts of prose poems, and personal tirades.',
        37.99,
        29,
        8,
        6,
        '6.jpg'
    ),
    (
        7,
        'How to stop time',
        'A love story across the ages - and for the ages - about a man lost in time, the woman who could save him, and the lifetimes it can take to learn how to live',
        65.5,
        55,
        9,
        7,
        '7.jpg'
    ),
    (
        8,
        'King’s Cage',
        'In this breathless third installment to Victoria Aveyard’s bestselling Red Queen series, allegiances are tested on every side.',
        59.99,
        86,
        10,
        8,
        '8.jpg'
    ),
    (
        9,
        'Falling Away',
        'The New York Times bestselling series from Penelope Douglas that follows a close-knit circle of friends through their college years',
        44.99,
        32,
        11,
        9,
        '9.jpg'
    ),
    (
        10,
        'Where She Went',
        'Where She Went explores the devastation of grief, the promise of new hope, and the flame of rekindled romance.',
        52.5,
        46,
        12,
        10,
        '10.jpg'
    ),
    (
        11,
        'In the shadow of your steps',
        'A psychological novel about losses, secrets, and searches.',
        40.0,
        30,
        13,
        11,
        '11.jpg'
    ),
    (
        12,
        'The Atlas of Beauty',
        'Photographs and stories of 500 women from around the world, based on the author’s hugely popular website.',
        75.5,
        43,
        14,
        12,
        '12.jpg'
    ),
    (
        13,
        'Home',
        'The book "Home", based on which a documentary is also produced, is also a journalistic, artistic and social product.',
        60.5,
        31,
        15,
        13,
        '13.jpg'
    ),
    (
        14,
        '5 Ingredients',
        'Jamie’s new recipes, using just 5 ingredients, will change the way you cook forever.',
        59.99,
        22,
        16,
        14,
        '14.jpg'
    ),
    (
        15,
        '#24centimeters',
        'In part an autobiography, in part a manifesto for good dishes and direct men',
        70.0,
        55,
        17,
        15,
        '15.jpg'
    ),
    (
        16,
        'Culinary Intelligence',
        'Think before you eat * Choose the best ingredients you can afford * Understand flavor, and pack us much of it as you can into each bite',
        62.99,
        27,
        18,
        16,
        '16.jpg');