pHeader=$1
pSeq=$2
seqORF=$3
oStart=$4
oStop=$5
oReadingFrame=$6

id_nummer=$(sqlite3 -batch ORFVoorspeller.db "select count(*) from Sequentie;")
id_nummer=$(($id_nummer+1))

sqlite3 -batch ORFVoorspeller.db "INSERT OR IGNORE INTO Sequentie(id,header, sequentie) VALUES (\"$id_nummer\",\"$pHeader\",\"$pSeq\");"
sqlite3 -batch ORFVoorspeller.db "select * from Sequentie;"

id_nummer_ORF=$(sqlite3 -batch ORFVoorspeller.db "select count(*) from ORF;")
id_nummer_ORF=$(($id_nummer_ORF+1))

sqlite3 -batch ORFVoorspeller.db "INSERT INTO ORF(ID,seqORF,startPos, stopPos, readingFrame, Sequentie_ID) VALUES (\"$id_nummer_ORF\",\"$seqORF\", \"$oStart\",\"$oStop\",\"$oReadingFrame\",\"$id_nummer\");"
sqlite3 -batch ORFVoorspeller.db "select * from ORF;"