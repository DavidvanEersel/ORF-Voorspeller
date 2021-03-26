# ORF-Voorspeller
Database installeren:
1. Installeer sqlite3 met: sudo apt-get install sqlite3
2. Run het bestand: DatabaseMaker.sh
3. Ga naar de directory Database
4. Geef de bestanden 'checkInDatabase.sh', 'getResults.sh' en 'setResults.sh' de goede rechten met:
    chmod 711 checkInDatabase.sh
    chmod 711 getResults.sh
    chmod 711 setResults.sh
5. Verander de drie paths in de class 'Databasehandler' naar respectievelijk het path waar 
   'setResults.sh', 'getResults.sh' en 'checkInDatabase.sh' staan. 
    Let er op dat er een spatie achter het path staat en dat er op juiste wijze met spaties in paths wordt gehandeld!
6. De GUI class kan nu gerunt worden. 

Verschillen met het ontwerp en analyse:
- De GUI is niet instelbaar
- Er is geen 'vorige' button met dropdownmenu gemaakt om de vorige analyses weer te geven
- De applicatie werkt niet op Windows
Deze verschillen zijn ontstaan doordat er niet genoeg tijd was om deze dingen te implementeren 
en er is vervolgens gekozen om te focussen op de belangrijkere functionaliteiten.
- Er zijn een aantal return statements veranderd. 
Dit verschil is ontstaan doordat het makkelijker was om te werken met de aangepaste return statements. 