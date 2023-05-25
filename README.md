# AssistenzaTelefonia

Questa webApp ha lo scopo mi testare alcuni funzioni realizzabili con l'uso delle nozioni apprese durante la mia formazione.

## Installazione

1. Assicurati di avere i seguenti requisiti installati nel tuo sistema:
    - Java (versione 11)
    - Spring Boot (versione 2.5.3)
    - Maven (versione 4.0.0)
    - MySQL Server (versione 10.4.28-MariaDB )

2. Clona il repository della web app sul tuo computer:
   ```
   git clone <URL_DEL_REPOSITORY>
   ```

3. Apri il progetto nella tua IDE preferita.

4. Configura il database MySQL:
    - Crea un database vuoto chiamato "asstelefonia".
    - Modifica le impostazioni di connessione al database nel file `application.properties` nella directory `src/main/resources`.
   ```
      spring.jpa.hibernate.ddl-auto=validate
      spring.datasource.url=jdbc:mysql://localhost:3306/asstelefonia
      spring.datasource.username=root
      spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
      spring.jpa.show-sql=true
      spring.thymeleaf.prefix=classpath:/templates/
      spring.thymeleaf.suffix=.html
   ```
- Importa il file asstelefonia.sql nel database

5. Fai il Run dell'applicazione

## Utilizzo

1. Avvia l'applicazione seguendo i passaggi di installazione.

2. Accedi all'applicazione utilizzando il tuo browser preferito e vai all'URL: `http://localhost:8080`, come nome inserisci "admin" e come password "password".

3. Segui le istruzioni per utilizzare le diverse funzionalità offerte dalla web app.

## Struttura del Progetto

Breve descrizione della struttura del progetto, evidenziando le directory principali e i loro scopi.

```
progetto
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.app/                # Directory principale del codice sorgente
│   │   │       ├── controller/                 # Controller della web app
│   │   │       ├── model/                      # Modelli dei dati
│   │   │       ├── service/                    # Servizi e logica di business
|   |   |       ├── repository/                 # Repository del progetto
|   |   |       ├── config/                     # Configurazioni di sicurezza
│   │   │       └── SpringHibernateApplication  # Classe di avvio dell'applicazione
│   │   └── resources/
│   │       └── application.properties          # File di configurazione dell'applicazione
│   └── test/                                   # Directory per i test
├── pom.xml                                     # File di configurazione di Maven
└── README.md                                   # Questo file README
```

## Dipendenze

Elenco delle principali dipendenze utilizzate nella web app:

- Spring Boot (versione 2.5.3)
- Maven (versione 4.0.0)
- Hibernate Entity Manager(versione 5.2.3.Final)
- Hibernate Core (versione 5.6.10.Final)
- MySQL Connector
- JQuery (versione 3.6.0)

## Principali endpoint presenti nell'applicazione

```
@RequestMapping("/api")
├── @GetMapping("/clienti")                  #Lettura lista clienti
├── @GetMapping("/articoli")                 #Lettura lista articoli
├── @PutMapping("/upassistenza/{id}")        #Aggiornamento centro assistenza
├── @DeleteMapping("/assistenza/{id}")       #Cancellazione centro assistenza 
├── @PostMapping("/assistenza")              #Crea centro assistenza                         
└── continue.....   
```

---