CREATE OR REPLACE PROCEDURE GENERATE_DVDS IS 

TYPE TABNUM IS TABLE OF NUMBER(4);
TYPE TABSTR1 IS TABLE OF VARCHAR2(75);
TYPE TABSTR2 IS TABLE OF VARCHAR2(75);

title_pl TABSTR1;
title TABSTR2;
prod_year TABNUM;

total_num NUMBER(2);

BEGIN

	title_pl := TABSTR1('Skazani na Shawsank', 'Nietykalni', 'Zielona mila', 'Ojciec chrzestny', 'Dwunastu gniewnych ludzi', 'Forrest Gump', 'Lot nad kukułczym gniazdem', 
						'Ojciec chrzestny II', 'Lista Schindlera', 'Władca Pierścieni: Powrót króla', 'Pulp Fiction', 'Życie jest piękne', 'Siedem', 'Władca Pierścieni: Dwie wieże', 
						'Podziemny krąg', 'Chłopcy z ferajny', 'Piękny umysł', 'Pianista', 'Incepcja', 'Django', 'Milczenie owiec', 'Król Lew', 'Człowiek z blizną', 'Gran Torino', 
						'Wyspa tajemnic', 'Więzień nienawiści', 'Leon zawodowiec', 'Green Book', 'Chłopiec w pasiastej piżamie', 'Gladiator', 'Braveheart - Waleczne Serce', 
						'Avengers: Wojna bez granic', 'Przełęcz ocalonych', 'Avengers: Koniec gry', 'Coco', 'Szeregowiec Ryan', 'Whiplash', 'Buntownik z wyboru', 'Prestiż', 
						'Zapach kobiety', 'Gwiezdne wojny: Część V - Imperium kontratakuje', 'Czas Apokalipsy', 'Gwiezdne wojny: Część VI - Powrót Jedi', 'W pogoni za szczęściem', 
						'Dawno temu w Ameryce', 'Dobry, zły i brzydki', 'Służące', 'Pamiętnik', 'Kasyno', 'Pluton');
					
	title := TABSTR2('The Shawshank Redemption', 'Intouchables', 'The Green Mile', 'The Godfather', '12 Angry Men', 'Forrest Gump', 'One Flew Over the Cuckoo''s Nest', 
					 'The Godfather: Part II', 'Schindler''s List', 'The Lord of the Rings: The Return of the King', 'Pulp Fiction', 'La vita e bella', 'Se7en',
					 'The Lord of the Rings: The Two Towers', 'Fight Club', 'Goodfellas', 'A Beautiful Mind', 'The Pianist', 'Inception', 'Django Unchained', 
					 'The Silence of the Lambs', 'The Lion King', 'Scarface', 'Gran Torino', 'Shutter Island', 'American History X', 'Leon', 'Green Book', 
					 'The Boy in the Striped Pyjamas', 'Gladiator', 'Braveheart', 'Avengers: Infinity War', 'Hacksaw Ridge', 'Avengers: Endgame', 'Coco', 'Saving Private Ryan', 
					 'Whiplash', 'Good Will Hunting', 'The Prestige', 'Scent of a Woman', 'Star Wars: Episode V - The Empire Strikes Back', 'Apocalypse Now', 
					 'Star Wars: Episode VI - Return of the Jedi', 'The Pursuit of Happyness', 'Once Upon a Time in America', 'Il buono, il brutto, il cattivo', 'The Help', 
					 'The Notebook', 'Casino', 'Platoon');
				 
	prod_year := TABNUM(1994, 2011, 1999, 1972, 1957, 1994, 1975, 1974, 1993, 2003, 1994, 1997, 1995, 2002, 1999, 1990, 2001, 2002, 2010, 2012, 1991, 1994, 1983, 2008, 2010, 1998,
						1994, 2018, 2008, 2000, 1995, 2018, 2016, 2019, 2017, 1998, 2014, 1997, 2006, 1992, 1980, 1979, 1983, 2006, 1984, 1966, 2011, 2004, 1995, 1986);
				   
	total_num := title_pl.count;

	FOR i IN 1..total_num LOOP

		INSERT INTO FILMY_DVD (polski_tytuł_filmu, oryginalny_tytuł_filmu, rok_produkcji) VALUES (title_pl(i), title(i), prod_year(i));
	
	END LOOP;

	DBMS_OUTPUT.put_line('Dodano wszystkie filmy DVD.');
    
END GENERATE_DVDS;
/





CREATE OR REPLACE PROCEDURE GENERATE_CLIENTS (number_of_clients NUMBER) IS

TYPE TABSTR1 IS TABLE OF VARCHAR2(15);
TYPE TABSTR2 IS TABLE OF VARCHAR2(15);
TYPE TABSTR3 IS TABLE OF VARCHAR2(20);
TYPE TABSTR4 IS TABLE OF VARCHAR2(20);
TYPE TABSTR5 IS TABLE OF VARCHAR2(10);

mens_names TABSTR1;
womens_names TABSTR2;
mens_surnames TABSTR3;
womens_surnames TABSTR4;
address TABSTR5;

house_num NUMBER(2);
phone_num NUMBER(9);
rand_address VARCHAR2(15);
rand_name VARCHAR2(15);
rand_surname VARCHAR2(20);
sex NUMBER(3);
	
BEGIN

	mens_names := TABSTR1('Antoni', 'Jakub', 'Jan', 'Szymon', 'Aleksander', 'Franciszek', 'Filip', 'Mikołaj', 'Wojciech', 'Kacper', 'Adam', 'Marcel', 'Stanisław', 'Michał', 'Wiktor', 
						 'Leon', 'Piotr', 'Nikodem', 'Igor', 'Ignacy', 'Tymon', 'Miłosz', 'Maksymilian', 'Oliwier', 'Tymoteusz');
	
	womens_names := TABSTR2('Zuzanna', 'Julia', 'Maja', 'Zofia', 'Hanna', 'Lena', 'Alicja', 'Maria', 'Amelia', 'Oliwia', 'Aleksandra', 'Wiktoria', 'Emilia', 'Laura', 'Natalia',
							'Antonina', 'Pola', 'Liliana', 'Iga', 'Marcelina', 'Anna', 'Gabriela', 'Helena', 'Michalina', 'Nadia');
						 
	mens_surnames := TABSTR3('Nowak', 'Kowalski', 'Wiśniewski', 'Wójcik', 'Kowalczyk', 'Kamiński', 'Lewandowski', 'Zieliński', 'Szymański', 'Woźniak', 'Dąbrowski', 'Kozłowski', 
							   'Jankowski', 'Wojciechowski', 'Kwiatkowski', 'Mazur', 'Krawczyk', 'Kaczmarek', 'Piotrowski', 'Grabowski', 'Pawłowski', 'Michalski', 'Zając', 'Król',
							   'Wróbel');
						 
	womens_surnames := TABSTR4('Nowak', 'Kowalska', 'Wiśniewska', 'Wójcik', 'Kowalczyk', 'Kamińska', 'Lewandowska', 'Zielińska', 'Szymańska', 'Woźniak', 'Dąbrowska', 'Kozłowska', 
							   'Jankowska', 'Wojciechowska', 'Kwiatkowska', 'Mazur', 'Krawczyk', 'Kaczmarek', 'Piotrowska', 'Grabowska', 'Pawłowska', 'Michalska', 'Zając', 'Król',
							   'Jabłońska');
						 
	address := TABSTR5('Polna', 'Leśna', 'Słoneczna', 'Krótka', 'Szkolna', 'Ogrodowa', 'Lipowa', 'Brzozowa', 'Łąkowa', 'Kwiatowa');

	FOR i IN 1..number_of_clients LOOP
		
		house_num := TRUNC(DBMS_RANDOM.value(1,51));
		phone_num := TRUNC(DBMS_RANDOM.value(500000000,900000000));
		sex := TRUNC(DBMS_RANDOM.value(0,1000));
		
		IF sex >= 500 THEN
		
			rand_name := mens_names(TRUNC(DBMS_RANDOM.value(1,mens_names.count + 1)));
			rand_surname := mens_surnames(TRUNC(DBMS_RANDOM.value(1,mens_surnames.count + 1)));
			
		ELSE
		
			rand_name := womens_names(TRUNC(DBMS_RANDOM.value(1,womens_names.count + 1)));
			rand_surname := womens_surnames(TRUNC(DBMS_RANDOM.value(1,womens_surnames.count + 1)));
			
		END IF;
		
		rand_address := address(TRUNC(DBMS_RANDOM.value(1,address.count + 1))) || ' ' || house_num;
		
		INSERT INTO KLIENCI (imię, nazwisko, adres, numer_telefonu) VALUES (rand_name, rand_surname, rand_address, phone_num);
		
	END LOOP;
	
	DBMS_OUTPUT.put_line('Dodano wszystkich klientów.');
    
END GENERATE_CLIENTS;
/





CREATE OR REPLACE PROCEDURE GENERATE_RENTALS (number_of_rentals NUMBER, number_of_returns NUMBER) IS

daysForwardLimit NUMBER(2);
diff NUMBER(2);
is_rented NUMBER(1);
number_of_clients NUMBER(3);
number_of_dvds NUMBER(3);
number_of_returns_counter NUMBER(2);
rand_rental_date DATE;
rand_client NUMBER(3);
rand_dvd NUMBER(3);
rand_return_date DATE;
stuck_protection NUMBER(2);
	
BEGIN
	
	diff := number_of_rentals - number_of_returns;
	number_of_returns_counter := number_of_returns;
	stuck_protection := 1;
	
	SELECT COUNT(*) INTO number_of_clients FROM KLIENCI;
	SELECT COUNT(*) INTO number_of_dvds FROM FILMY_DVD;
	
	IF diff >= 0 AND diff <= 50 THEN
	
		FOR i IN 1..number_of_rentals LOOP
		
			rand_client := TRUNC(DBMS_RANDOM.value(1,number_of_clients + 1));
			rand_dvd := TRUNC(DBMS_RANDOM.value(1,number_of_dvds + 1));
			
			IF number_of_returns_counter > 0 THEN
			
				number_of_returns_counter := number_of_returns_counter - 1;
				rand_rental_date := SYSDATE - TRUNC(DBMS_RANDOM.value(0,15));
				daysForwardLimit := TRUNC(SYSDATE) - rand_rental_date;
				IF daysForwardLimit > 7 THEN
                    daysForwardLimit := 7;
                END IF;
                rand_return_date := rand_rental_date + TRUNC(DBMS_RANDOM.value(0,daysForwardLimit + 1));
			
				SELECT COUNT(*) INTO is_rented FROM WYPOŻYCZENIA
				WHERE filmy_dvd_id_płyty = rand_dvd AND ((data_wypożyczenia <= rand_rental_date AND rand_rental_date <= data_zwrotu) OR (data_wypożyczenia <= rand_return_date 
					AND rand_return_date <= data_zwrotu) OR (rand_rental_date <= data_wypożyczenia AND data_zwrotu <= rand_return_date));
			
				WHILE is_rented > 0 LOOP
				
					IF stuck_protection = 10 THEN
						
						rand_dvd := TRUNC(DBMS_RANDOM.value(1,number_of_dvds + 1));
						stuck_protection := 0;
						
					END IF;
					
					rand_rental_date := SYSDATE - TRUNC(DBMS_RANDOM.value(0,15));
					daysForwardLimit := TRUNC(SYSDATE) - rand_rental_date;
                    IF daysForwardLimit > 7 THEN
                        daysForwardLimit := 7;
                    END IF;
					rand_return_date := rand_rental_date + TRUNC(DBMS_RANDOM.value(0,daysForwardLimit + 1));
				
					SELECT COUNT(*) INTO is_rented FROM WYPOŻYCZENIA
					WHERE filmy_dvd_id_płyty = rand_dvd AND ((data_wypożyczenia <= rand_rental_date AND rand_rental_date <= data_zwrotu) 
						OR (data_wypożyczenia <= rand_return_date AND rand_return_date <= data_zwrotu) OR (rand_rental_date <= data_wypożyczenia 
						AND data_zwrotu <= rand_return_date));
					
					stuck_protection := stuck_protection + 1;
				
				END LOOP;
				
				stuck_protection := 1;
			
			ELSE
			
				rand_rental_date := SYSDATE - TRUNC(DBMS_RANDOM.value(0,8));
				rand_return_date := NULL;
			
				SELECT COUNT(*) INTO is_rented FROM WYPOŻYCZENIA
				WHERE filmy_dvd_id_płyty = rand_dvd 
				AND ((data_wypożyczenia <= rand_rental_date AND rand_rental_date <= data_zwrotu) OR (data_wypożyczenia <= rand_rental_date AND data_zwrotu IS NULL)
					OR (data_wypożyczenia >= rand_rental_date));
			
				WHILE is_rented > 0 LOOP
				
					IF stuck_protection = 10 THEN
						
						rand_dvd := TRUNC(DBMS_RANDOM.value(1,number_of_dvds + 1));
						stuck_protection := 0;
						
					END IF;
				
					rand_rental_date := SYSDATE - TRUNC(DBMS_RANDOM.value(0,8));
				
					SELECT COUNT(*) INTO is_rented FROM WYPOŻYCZENIA
					WHERE filmy_dvd_id_płyty = rand_dvd 
					AND ((data_wypożyczenia <= rand_rental_date AND rand_rental_date <= data_zwrotu) OR (data_wypożyczenia <= rand_rental_date AND data_zwrotu IS NULL)
						OR (data_wypożyczenia >= rand_rental_date));
						
					stuck_protection := stuck_protection + 1;
				
				END LOOP;
				
				stuck_protection := 1;
				
			END IF;
			
			INSERT INTO WYPOŻYCZENIA VALUES (rand_rental_date, rand_return_date, rand_dvd, rand_client);	
			
		END LOOP;
		
		DBMS_OUTPUT.put_line('Dodano wszystkie wypożyczenia.');
			
	ELSE
		
		DBMS_OUTPUT.put_line('Liczba wypożyczeń mniejsza od liczby zwrotów. / Za dużo wypożyczeń, za mało zwrotów (brak dostępnych płyt).');
	
	END IF;
    
END GENERATE_RENTALS;
/