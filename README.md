# Laboration 1

Med hjälp av designmönstret **Decorator** ska vi försöka bygga upp ett rabattsystem för varor. Varje dekoratörklass ska representera en viss typ av rabatt med sina egna regler, och dessa ska sedan kunna kedjas ihop för att representera aktuella rabatter. De följande stegen är en mall för att komma igång. Namngivning och exakt utformning kan variera vid implementationen.

Länkar för mer information:
- [Decorator Pattern](https://www.oodesign.com/decorator-pattern)
- [Structural Design: Decorator Pattern](https://www.javacodegeeks.com/2018/08/structural-design-decorator-pattern.html)

1. Skapa en klass eller record som heter **Product**. Klassen ska ha följande data:
   - `name`: Produktens namn.
   - `price`: Produktens pris.
   - `quantity`: Produktens kvantitet.

2. Skapa ett gränssnitt (interface) som heter **Discount**. Gränssnittet ska ha följande metoder:
   - `apply(Product product)`: Applicerar rabatten på produkten och returnerar rabattbeloppet.
   - `getDescription(Product product)`: Returnerar en beskrivning av rabatten som textsträng.
   
   Vill vi kunna applicera rabatter där besluten baseras på mer än en enskild produkts information t.ex. vem är kunden, hur många övriga varor har vi köpt m.m., så behöver vi utöka med en ytterligare parameter som tillhandahåller information om dessa saker. Båda dessa metoder behöver anropa samma metod på `nextDiscount` för att även utföra beräkning av rabatt baserat på dessa.

3. Skapa en abstrakt klass som heter **BaseDiscount** och som implementerar gränssnittet **Discount**. Klassen ska ha följande metoder:
   - `protected abstract boolean isApplicable(Product product);`
   - `protected abstract double calculateDiscount(Product product);`

   Klassen behöver även ett fält av typen `Discount` som lagrar nästa rabatt i kedjan av rabatter och sätts via konstruktorn.
   - `Discount nextDiscount`: Den nästa rabatten i kedjan.

4. Skapa tre klasser som ärver från klassen **BaseDiscount**:
   - **FridayDiscount**: Applicerar en rabatt på 10% på fredagar.
   - **MilkDiscount**: Applicerar en rabatt på 5% på mjölk.
   - **QuantityDiscount**: Applicerar en rabatt på 10 kronor per produkt om det köps minst 5 produkter.
   
   Metoden `isApplicable()` ska returnera true om rabatten är tillämplig på produkten, annars false. Metoden `calculateDiscount()` ska beräkna rabattbeloppet för produkten. Metoden `getDescription` ska returnera en kort textsträng som talar om vad för rabatt det är.

5. Skriv en kort `main`-metod som skapar ett par produkter och rabatter och provkör dessa.

**VG uppgift:**
Försök att med hjälp av funktionella interface och lambda/metodreferenser skapa en generell **Discount**-klass där metoderna `isApplicable` och `calculateDiscount` kan skickas in till konstruktorn vid skapandet. På det sättet kan vi generera många olika sorters rabatter utan att behöva ärva nya klasser. Skapa minst 2 tester för implementationen.
