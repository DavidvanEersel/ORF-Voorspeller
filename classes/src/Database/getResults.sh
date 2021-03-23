seq=$(sqlite3 -batch ORFVoorspeller.db "select * from ORF INNER JOIN Sequentie ON Sequentie.id = ORF.Sequentie_ID;")
echo $seq
