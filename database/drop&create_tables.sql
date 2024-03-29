DROP TABLE filmy_dvd CASCADE CONSTRAINTS;

DROP TABLE klienci CASCADE CONSTRAINTS;

DROP TABLE wypożyczenia CASCADE CONSTRAINTS;

CREATE TABLE filmy_dvd (
    id_płyty                 NUMBER GENERATED BY DEFAULT ON NULL as IDENTITY (START with 1 INCREMENT by 1),
    polski_tytuł_filmu       VARCHAR2(75) NOT NULL,
    oryginalny_tytuł_filmu   VARCHAR2(75),
    rok_produkcji            INTEGER
);

ALTER TABLE filmy_dvd ADD CONSTRAINT filmy_dvd_pk PRIMARY KEY ( id_płyty );

CREATE TABLE klienci (
    id_klienta       NUMBER GENERATED BY DEFAULT ON NULL as IDENTITY (START with 1 INCREMENT by 1),
    imię             VARCHAR2(15) NOT NULL,
    nazwisko         VARCHAR2(20) NOT NULL,
    adres            VARCHAR2(50),
    numer_telefonu   INTEGER
);

ALTER TABLE klienci ADD CONSTRAINT klienci_pk PRIMARY KEY ( id_klienta );

CREATE TABLE wypożyczenia (
    data_wypożyczenia    DATE NOT NULL,
    data_zwrotu          DATE,
    filmy_dvd_id_płyty   INTEGER NOT NULL,
    klienci_id_klienta   INTEGER NOT NULL
);

ALTER TABLE wypożyczenia
    ADD CONSTRAINT wypożyczenia_pk PRIMARY KEY ( data_wypożyczenia,
                                                 filmy_dvd_id_płyty,
                                                 klienci_id_klienta );

ALTER TABLE wypożyczenia
    ADD CONSTRAINT wypożyczenia_filmy_dvd_fk FOREIGN KEY ( filmy_dvd_id_płyty )
        REFERENCES filmy_dvd ( id_płyty );

ALTER TABLE wypożyczenia
    ADD CONSTRAINT wypożyczenia_klienci_fk FOREIGN KEY ( klienci_id_klienta )
        REFERENCES klienci ( id_klienta );