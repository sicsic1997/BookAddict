/*********************************************************************************************************
* BOOK ADDICT                                                                                            *
* ------------------------------------------------------------------------------------------------------ *
*                                                                                                        *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_BOOKINGS  (
    ID_BOOKING  INT,
    CREATED_ON DATETIME,
    EXPIRES_ON DATETIME,
    DE_BOOKING varchar(500),
    ID_USER INT,
    TOTAL_PRICE DECIMAL(17, 5),
    ID_STATUS INT,
    CONSTRAINT  PK_BA_BOOKINGS PRIMARY KEY (ID_BOOKING)
);