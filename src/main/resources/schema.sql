CREATE TABLE PostSted
(
    postnummer VARCHAR(10) NOT NULL,
    poststed VARCHAR (50) NOT NULL,

    PRIMARY KEY(postnummer)
);

CREATE TABLE DyrDropdown
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    dyr VARCHAR (255) NOT NULL,
    type VARCHAR (255) NOT NULL,
    kjønn VARCHAR (10) NOT NUll,

    PRIMARY KEY(id)
);

CREATE TABLE BrukerDropdown

(
    id INTEGER AUTO_INCREMENT NOT NULL,
    kjonn VARCHAR (10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Bruker
(
    brukerId INTEGER AUTO_INCREMENT NOT NULL,
    brukernavn VARCHAR(255) NOT NULL,
    passord VARCHAR(255) NOT NULL,
    fornavn VARCHAR(255) NOT NULL,
    etternavn VARCHAR(255) NOT NULL,
    epost VARCHAR(255) NOT NULL,
    mobilNr VARCHAR(255) NOT NULL,
    gateAdresse VARCHAR(255) NOT NULL,
    postnummer VARCHAR(255) NOT NULL,
    kjønn VARCHAR(255) NOT NULL,
    admin smallint NOT NULL,

    PRIMARY KEY (brukerId),
    FOREIGN KEY(postnummer) REFERENCES PostSted(postnummer)
);

CREATE TABLE Dyr
(
    dyrID INTEGER AUTO_INCREMENT NOT NULL,
    brukerID INTEGER,
    navn VARCHAR(255) NOT NULL,
    alder VARCHAR (10) NOT NULL,
    beskrivelse VARCHAR (255) NOT NULL,
    dyr VARCHAR (255) NOT NULL,
    type VARCHAR (255) NOT NULL,
    kjønn VARCHAR (255) NOT NULL,

    PRIMARY KEY(dyrID),
    FOREIGN KEY(brukerID) REFERENCES Bruker(brukerID)
);

CREATE TABLE Liste
(
    dyrID INTEGER NOT NULL,
    brukernavn VARCHAR(255) NOT NULL,
    navn VARCHAR(255) NOT NULL,
    alder VARCHAR (10) NOT NULL,
    beskrivelse VARCHAR (255) NOT NULL,
    dyr VARCHAR (255) NOT NULL,
    type VARCHAR (255) NOT NULL,
    kjønn VARCHAR (255) NOT NULL,

    PRIMARY KEY(dyrID),
    FOREIGN KEY(dyrID) REFERENCES Dyr(dyrID)
);


