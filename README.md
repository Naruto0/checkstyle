# Checkstyle - Java zápočtový test

## Popis
Napísať zjednodušenú verziu: <http://checkstyle.sourceforge.net>

Úlohou checkstyle programu je kontrolovať správnu štruktúru a dodržovanie pravidiel čistého kódu.
Často je checkstyle spúšťaný ešte pred kompiláciou projektu a pri nájdení chyby zastaví celý build proces.

Implementáciu začnite písať do `main` metódy v `cz.cuni.mff.checkstyle.Main` triede. V `args` parametroch bude cesta k projektu.
Projekt obsahuje `checkstyle.config` súbor. Pokiaľ ho neobsahuje, tak program nič nevykoná. 

Súbor `checkstyle.config` obsahuje zoznam kontrôl, ktoré sa majú vykonať. Pokiaľ sa nájde chyba, tak je potrebné ju 
vypísať do errorového výstupu (`System.err.println(...);`). Na poradí vypisovania nezáleží.

Kontrolujú sa iba súbory s koncovkou `.java` a to rekurzívne (t.j. pokiaľ narazíte na adresár, tak začnete robiť 
kontrolu aj v tomto adresári)

Poznámka: Môžete predpokladať, že všetky súbory sa zmestia do pamäte.

### Formát `checkstyle.config` súboru

Príklad:
```
CheckHeader=header.txt
LineLength=120
TabChar
NewlineAtEnd
PackageFormat
```

Súbor môže obsahovať ľubovolnú podmnožinu daných príkazov a v rôznom poradí.

#### CheckHeader
Header je nejaká dôležitá informácia, ktorá by sa mala nachádzať na začiatku každého súboru. Väčšinou obsahuje 
informácie o licencii a firme, ktorá daný kód vlastní.

Formát: `CheckHeader={cesta}`, kde 
* `{cesta}` predstavuje cestu k textovému súboru,
 ktorý definuje, ako má vyzerať header. 
 
Príklad obsahu: 
```java
/*
 * Matfyz and all its associates.
 * Use is subject to license terms.
 */
```

Error output: `{file}: Wrong header`, kde 
* `{file}` premenná reprezentuje relatívnu cestu k súboru od adresára projektu

#### LineLength
Špecifikuje dĺžku riadku, ktorú by daný súbor nemal presiahnuť. V minulosti bol štandard 80 znakov. S nástupom FullHD 
je terajší štandard 120 znakov.

Formát: `LineLength={length}`, kde 
* `{length}` predstavuje dĺžku riadku, ktorý daný súbor nesmie presiahnuť.

Error output: `{file}: ${row} LineLength exceeded: actual length {length}, maximum {max}`, kde
* `{file}` reprezentuje relatívnu cestu k súboru od adresára projektu
* `${row}` riadok, na ktorom sa chyba vyskytuje
* `{length}` predstavuje aktuálnu dĺžku riadku (je väčšia ako `max`)
* `{max}` maximálna dĺžka riadku z config súboru

#### TabChar
Špecifikuje, že súbory nemôžu obsahovať `\t` znak. 

Formát: `TabChar`

Error output: `{file}: contains tab char at {row}:{column}`, kde
* `{file}` reprezentuje relatívnu cestu k súboru od adresára projektu
* `{row}` riadok, na ktorom sa tab char nachádza
* `{column}` stĺpec, na ktorom sa tab char nachádza

#### NewlineAtEnd
Špecifikuje, že každý súbor, by mal obsahovať samostatný `\n` na konci súboru.

Formát: `NewlineAtEnd`

Error output: `{file}: does not contain newline at the end of file`, kde
* `{file}` reprezentuje relatívnu cestu k súboru od adresára projektu

#### PackageFormat
Definuje, že package by mal mať formát `^[a-z]+(\.[a-zA-Z_][a-zA-Z0-9_]*)*$` regulárneho výrazu. 
Môžete predpokladať, že len jeden riadok začína slovom `package` a v súboroch sa nevyskytujú časti kódu podobného typu:
```java
/*
package test;
 */
```

Formát: `PackageFormat`

Error output: `{file}: wrong package format`, kde
* `{file}` reprezentuje relatívnu cestu k súboru od adresára projektu

### Testovanie

V termináli choďte do root adresára projektu (pomocou príkazov `cd`), potom spustite príkaz `mvn clean test`.

Pokiaľ všetky testy prejdú, tak ma zavolajte.

Veľa šťaštia :\)
