USE cineteca;

DROP TABLE IF EXISTS acquisto;
DROP TABLE IF EXISTS risposta;
DROP TABLE IF EXISTS recensione;
DROP TABLE IF EXISTS Seleziona;
DROP TABLE IF EXISTS Prodotto;
DROP TABLE IF EXISTS Domanda;
DROP TABLE IF EXISTS Utente;

CREATE TABLE Utente (
                        email VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        cognome VARCHAR(255) NOT NULL,
                        password_d VARCHAR(255) NOT NULL,
                        stato BOOLEAN NOT NULL,
                        ruolo ENUM('utente', 'admin', 'barista', 'magazziniere') DEFAULT 'utente'
);

CREATE TABLE Prodotto (
                          id_prodotto INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) UNIQUE NOT NULL,
                          descrizione VARCHAR(512) NOT NULL,
                          quantita INT,
                          prezzo DOUBLE NOT NULL
);

CREATE TABLE Seleziona (
                           email_utente VARCHAR(255) NOT NULL,
                           id_prod INT NOT NULL,
                           PRIMARY KEY (email_utente, id_prod),
                           FOREIGN KEY (email_utente) REFERENCES Utente(email) ON DELETE CASCADE,
                           FOREIGN KEY (id_prod) REFERENCES Prodotto(id_prodotto) ON DELETE CASCADE
);

CREATE TABLE recensione (
                            id_recensione INT AUTO_INCREMENT PRIMARY KEY,
                            email_ut VARCHAR(255) NOT NULL,
                            id_prod INT NOT NULL,
                            corpo_recensione VARCHAR(512) NOT NULL,
                            data_rec DATE NOT NULL,
                            FOREIGN KEY (email_ut) REFERENCES Utente(email) ON DELETE CASCADE,
                            FOREIGN KEY (id_prod) REFERENCES Prodotto(id_prodotto) ON DELETE CASCADE
);

CREATE TABLE domanda (
                         id_domanda INT AUTO_INCREMENT PRIMARY KEY,
                         corpo_messaggio VARCHAR(512) NOT NULL,
                         data_rec DATETIME DEFAULT CURRENT_TIMESTAMP,
                         stato BOOLEAN DEFAULT FALSE,
                         email_utente VARCHAR(255) NOT NULL,
                         FOREIGN KEY (email_utente) REFERENCES Utente(email) ON DELETE CASCADE
);

CREATE TABLE risposta (
                          id_risposta INT AUTO_INCREMENT PRIMARY KEY,
                          corpo_messaggio VARCHAR(512) NOT NULL,
                          data_rec DATETIME DEFAULT CURRENT_TIMESTAMP,
                          email_barista VARCHAR(255) NOT NULL,
                          FOREIGN KEY (email_barista) REFERENCES Utente(email) ON DELETE CASCADE
);

CREATE TABLE acquisto (
                          id_acquisto INT AUTO_INCREMENT PRIMARY KEY,
                          data_acquisto DATETIME DEFAULT CURRENT_TIMESTAMP,
                          prezzo INT,
                          prodotti_comprati VARCHAR(1024) NOT NULL,
                          email_utente VARCHAR(255) NOT NULL,
                          FOREIGN KEY (email_utente) REFERENCES Utente(email) ON DELETE CASCADE
);

-- Inserimenti di esempio per la tabella Utente

INSERT INTO Utente (email, nome, cognome, password_d, stato, ruolo) VALUES
                                                                        ('mario.rossi@email.com', 'Mario', 'Rossi', 'password123', TRUE, 'utente'),
                                                                        ('lucia.bianchi@email.com', 'Lucia', 'Bianchi', 'password456', TRUE, 'admin'),
                                                                        ('giuseppe.verdi@email.com', 'Giuseppe', 'Verdi', 'password789', TRUE, 'barista'),
                                                                        ('anna.neri@email.com', 'Anna', 'Neri', 'password101', FALSE, 'magazziniere');

INSERT INTO Prodotto (nome, descrizione, quantita, prezzo) VALUES
                                                               ('Gin', 'Gin artigianale di alta qualità, ideale per cocktail raffinati.', 3, 25.99),
                                                               ('Whisky Classico', 'Whisky invecchiato 12 anni con note di vaniglia e caramello.', 30, 35.50),
                                                               ('Rum Scuro', 'Rum dal sapore deciso, perfetto per serate speciali.', 20, 18.75),
                                                               ('Vodka Cristallina', 'Vodka distillata cinque volte per un sapore morbido e puro.', 40, 22.00),
                                                               ('Tequila', 'Tequila premium con un gusto intenso e sfumature speziate.', 25, 28.40),
                                                               ('Liquore al caffe', 'Liquore dolce e aromatico, ideale per dessert e cocktail.', 35, 15.60);


-- Inserimenti per la tabella recensione
INSERT INTO recensione (email_ut, id_prod, corpo_recensione, data_rec) VALUES
                                                                           ('mario.rossi@email.com', 1, 'Gin di altissima qualità, perfetto per cocktail raffinati.', '2025-01-01'),
                                                                           ('lucia.bianchi@email.com', 2, 'Whisky con un sapore eccezionale, perfetto per chi ama i distillati invecchiati.', '2025-01-02'),
                                                                           ('giuseppe.verdi@email.com', 3, 'Rum scuro dal gusto deciso, ottimo per serate tra amici.', '2025-01-03');



