#!/bin/bash
sqlite3 -batch ORFVoorspeller.db "";
sqlite3 -batch ORFVoorspeller.db "CREATE TABLE ORF (ID int NOT NULL CONSTRAINT ORF_pk PRIMARY KEY, seqORF varchar(500) NOT NULL, startPos int NOT NULL, stopPos int NOT NULL, readingFrame int NOT NULL, Sequentie_ID int NOT NULL, CONSTRAINT ORF_Table_2 FOREIGN KEY (Sequentie_ID) REFERENCES Sequentie (ID));"
sqlite3 -batch ORFVoorspeller.db "CREATE TABLE Sequentie (ID int NOT NULL CONSTRAINT Sequentie_pk PRIMARY KEY, header varchar(50) NOT NULL UNIQUE, sequentie varchar(3000) NOT NULL UNIQUE);"

echo Database gemaakt
