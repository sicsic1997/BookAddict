/*********************************************************************************************************
* BOOK ADDICT                                                                                            *
* ------------------------------------------------------------------------------------------------------ *
*                                                                                                        *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_BOOKING_STATUS  (
    ID_STATUS INT,
    DE_STATUS varchar(20),
    CONSTRAINT  PK_BA_BOOKING_STATUS PRIMARY KEY (ID_STATUS)
);

DELETE FROM BA_BOOKING_STATUS;

INSERT INTO BA_BOOKING_STATUS values
(1, 'IN_PROGRESS'),
(2, 'CONFIRMED'),
(3, 'REJECTED'),
(4, 'PURCHASED'),
(5, 'EXPIRED');