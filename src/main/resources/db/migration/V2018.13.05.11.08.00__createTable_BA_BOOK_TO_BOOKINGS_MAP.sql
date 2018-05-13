/*********************************************************************************************************
* BOOK ADDICT                                                                                            *
* ------------------------------------------------------------------------------------------------------ *
*                                                                                                        *
*                                                                                                        *
*********************************************************************************************************/

USE book_addict;

CREATE TABLE IF NOT EXISTS  BA_BOOK_TO_BOOKING_MAP  (
    ID_BOOKING VARCHAR(100),
    ID_BOOK INT,
    QUANTITY INT,
    CONSTRAINT  PK_BA_BOOK_TO_BOOKING_MAP PRIMARY KEY (ID_BOOKING, ID_BOOK)
);