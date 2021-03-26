#sqlite3 -batch ORFVoorspeller.db "select * from ORF INNER JOIN Sequentie ON Sequentie.id = ORF.Sequentie_ID WHERE Sequentie = \"$1\";"
sqlite3 -batch ORFVoorspeller.db "Select * from ORF where sequentie_ID = (select id from Sequentie where sequentie = \"$1\");"
