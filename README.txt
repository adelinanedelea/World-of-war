Nedelea Maria - Adelina, 324CC
timp alocat : 2 saptamani
Implementare : am implementat clasele specificate in enunt
clasa Game contine metoda playtext ce ruleaza jocul in terminal: 
se extrag datele din json prin metoda run  si parseCredentialsObj, 
se creeaza obiecte de tip Account si se pun in ArrayList accounts.
Information din credentials este construit folosind Builder pattern.
Se alege un cont din terminal, se realizeaza autentificarea, se 
alege un personaj din lista contului prin factory pattern.
Pe harta generata in clasa Grid, personajul va incepe intotdeauna
pe pozitia (0, 0 ) din harta. Daca ajunge pe o 
celula magazin ii sunt afisate posibilitatile de a cumpara si suma
pe care o are. Daca celula curenta este un inamic, apar trei posibilitati
ataca inamic, foloseste abilitate si potiune. Lupta cu inamicul se
desfasoara intr-o bucla while.Jucatorul alege o modalitate de atac, 
inamicul ataca la randul sau pana cand unul dintre ei ramane fara viata.
Dupa fiecare alegere din terminal se afiseaza harta in terminal, iar pentru 
o comanda gresita se genereaza o exceptie.
Pentru GUI clasa choose modeleaza autentificarea si jocul propriu zis.
Tabla de joc este formata dintr-un grid de JButton, directiile de mers 
sunt 4 butoane ce au adaugat ActionListener. Daca este apasat unul dintre 
cele 4 butoane, personajul se muta in directia respectiva.
ShopFrame modeleaza fereastra de magazinului, afiseaza butoane pentru fiecare 
potiune.
EnemyFrame modeleaza lupta dintre cele 2 personaje: daca personajul pierde este 
deschisa fereastra de tip Finally ce afiseazza experienta si nivelul personajului.
Aceasta este afisata si la final in caz ca personajul ajunge pe o casuta de 
tip FINISH.
Visitor Pattern : am implementat interfata Element si Visitor.
Pentru un spell s , in Warrior, Enemy,  Rogue si Mage exista functia accept, 
prin care este acceptat efectuul spell ului.
Iar in clasele Ice, Fire si Earth exista metoda visit care modeleaza efectul
abilitatii asupra unui personaj.
SimpleFrame este utilizata pentru a afisa povestea casutei
Reguli de joc : inamicul are sansa de 75% sa atace normal si 25% sa 
foloseasca abilitate. Formulele de getDamage si receiveDamage sunt bazate
doar pe paritate si impartiri.