INSERT INTO PostSted(postnummer,poststed) VALUES('1277','OSLO');
INSERT INTO PostSted(postnummer,poststed) VALUES('5883','BERGEN');
INSERT INTO PostSted(postnummer,poststed) VALUES('9001','TROMSØ');

INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Australian sheperd','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Australian sheperd','Hunn');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Cocker spaniel','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Cocker spaniel','Hunn');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Dalmatiner','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Dalmatiner','Hunn');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Fransk bulldog','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Hund','Fransk bulldog','Hunn');

INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Bengal','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Bengal','Hunn');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Devon rex','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Devon rex','Hunn');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Hellig birma','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Hellig birma','Hunn');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Norsk skogkatt','Hann');
INSERT INTO DyrDropdown(dyr,type,kjønn) VALUES('Katt','Norsk skogkatt','Hunn');

INSERT INTO BrukerDropdown(kjonn) VALUES('Mann');
INSERT INTO BrukerDropdown(kjonn) VALUES('Kvinne');

INSERT INTO Bruker(brukernavn,passord,fornavn,etternavn,epost,mobilNr,gateAdresse,postnummer,kjønn,admin) VALUES('Danger1','Danger1!','Ola','Nordmann','ola@nordmann.no','90000000','OsloVeien 22','1277','Mann','1');
INSERT INTO Bruker(brukernavn,passord,fornavn,etternavn,epost,mobilNr,gateAdresse,postnummer,kjønn,admin) VALUES('Admin','Admin1!','NA','NA','NA','NA','NA','1277','NA','1');

