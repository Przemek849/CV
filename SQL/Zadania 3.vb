--Zad. 1
DROP TABLE Towary_w_magazynie;
DROP TABLE Partia_towarów;

CREATE TABLE Towary_w_magazynie (
Nazwa_towaru VARCHAR2(50) PRIMARY KEY,
Iloœæ INTEGER
);

CREATE TABLE Partia_towarów (
Nazwa_towaru VARCHAR2(50) PRIMARY KEY,
Iloœæ INTEGER
);

INSERT INTO Towary_w_magazynie VALUES ('Bluza', 100);
INSERT INTO Towary_w_magazynie VALUES ('Czapka', 60);
INSERT INTO Towary_w_magazynie VALUES ('Buty', 30);
INSERT INTO Towary_w_magazynie VALUES ('Spodnie', 50);
INSERT INTO Towary_w_magazynie VALUES ('Rêkawiczki', 200);

INSERT INTO Partia_towarów VALUES ('Ananas', 150);
INSERT INTO Partia_towarów VALUES ('Gruszka', 300);
INSERT INTO Partia_towarów VALUES ('Jab³ko', 90);
INSERT INTO Partia_towarów VALUES ('Pomarañcza', 190);
INSERT INTO Partia_towarów VALUES ('Mandarynka', 230);
INSERT INTO Partia_towarów VALUES ('Persymona', 100);
INSERT INTO Partia_towarów VALUES ('Banan', 200);


MERGE INTO Towary_w_magazynie t
USING Partia_towarów p ON (t.Nazwa_towaru = p.Nazwa_towaru)
WHEN MATCHED THEN UPDATE SET t.Iloœæ = p.Iloœæ
WHEN NOT MATCHED THEN INSERT (t.Nazwa_towaru, t.Iloœæ) VALUES (p.Nazwa_towaru, p.Iloœæ);


--Zad. 2
CREATE VIEW Emp_na_urlopie_bezp³atnym
AS SELECT * FROM Emp e
WHERE e.Sal = 0 OR e.Sal IS NULL
WITH CHECK OPTION;

UPDATE Emp_na_urlopie_bezp³atnym
SET Sal = 500;


CREATE VIEW Pracownicy
AS SELECT * FROM Emp
WITH READ ONLY;

UPDATE Pracownicy 
SET Sal = Sal *1.5;


--Zad 3
WITH a as
(SELECT d.Loc lol, d.deptno asd, COUNT(d.deptno) dep
FROM Dept d
GROUP BY d.deptno, d.Loc),
b as
(SELECT d.deptno dsa, COUNT(e.empno) prac
FROM dept d full outer join emp e on d.deptno = e.deptno
GROUP BY d.deptno)
SELECT a.lol, a.dep, b.prac FROM a join b on a.asd = b.dsa
;

--Zad 4
SELECT User FROM Dual;

INSERT INTO Emp VALUES (7731, 'PD4177', 'PRESIDENT', NULL, SYSDATE, 9000, 800, 10);

CREATE VIEW uzytkownik AS 
SELECT e.*, d.deptno ddeptno, d.dname, d.loc, s.grade, s.losal, s.hisal
FROM Emp e 
JOIN Dept d ON e.deptno = d.deptno
JOIN Salgrade s ON e.Sal BETWEEN s.losal AND s.hisal
WHERE e.ename = USER;

SELECT * FROM uzytkownik;
--Zad 5

CREATE VIEW test AS
WITH a as
(SELECT Table_name nazwa, num_rows wiersze From User_Tables),
b as
(SELECT u.Table_name nazwa, Count(u.Column_Name) kolumny
FROM User_Tab_Columns u
GROUP BY u.Table_name)
SELECT a.nazwa, b.kolumny, a.wiersze FROM a join b on a.nazwa = b.nazwa;

SELECT * FROM test;

--Zad 6

--Klient
CREATE VIEW klient_widok AS
SELECT Nazwa, Cena FROM Produkty;

CREATE VIEW klient_zamowienie AS
SELECT k.Imie, k.Nazwisko, k.Adres, s.Iloœæ, s.Data 
FROM Sprzeda¿ s
JOIN Klienci k on k.Id_klienta = s.Id_klienta;


--Kierownik
CREATE VIEW kierownik_prac AS
SELECT p.Id_prac, p.Imiê, p.Nazwisko, z.Stanowisko, z.Data_koniec, z.Zarobki FROM Pracownicy p
JOIN Zatrudnienie z on z.Id_prac = p.Id_prac;

CREATE VIEW Kierownik_sprzedaz AS
SELECT s.Id_sprzeda¿y, s.Id_towaru, p.Id_prac, p.Imiê, p.Nazwisko, s.Iloœæ, s.Data
FROM Pracownicy p
JOIN Sprzeda¿ s on s.Id_prac = p.Id_prac;

--Ksiêgowoœæ
CREATE VIEW ksiêgowoœæ AS
SELECT p.Id_prac, p.Imiê, p.Nazwisko, p.Data_urodzenia, p.Adres, z.Stanowisko, z.Data_pocz, z.Data_koniec, z.Zarobki
FROM Zatrudnienie z
JOIN Pracownicy p on p.Id_prac = z.Id_prac;

--Analityk
CREATE VIEW analityk_sprzedaz AS
SELECT * FROM Klienci k
JOIN Sprzeda¿ s on s.Id_Klienta = k.Id_Klienta;

CREATE VIEW analityk_produkty AS
SELECT * FROM Produkty;

--Pracownik
CREATE VIEW pracownik_widok AS
SELECT * FROM Klienci k
JOIN Sprzeda¿ s on s.Id_Klienta = k.Id_Klienta;

CREATE VIEW pracownik_produkty AS
SELECT * FROM Produkty;

--Zad 7
--W popronowanych przeze mnie perspektywach zmiany dotkn¹³ wszystkich tabel, które s¹ powi¹zane w jakiœ sposób z tabelami Produkty oraz Pracownicy

--nowa tabela dla Kierownika
CREATE VIEW Premia AS
SELECT z.Id_prac, z.Stanowisko, p.Nazwisko, p.Imiê, prem.Data, prem.Premia
JOIN Pracownicy p on p.Id_prac = z.Id_prac
JOIN Premia prem on z.Id_prac = prem.Id_prac;

--zmiana dla ksiêgowoœæ
CREATE VIEW ksiêgowoœæ AS
SELECT p.Id_prac, p.Imiê, p.Nazwisko, p.Data_urodzenia, p.Adres, z.Stanowisko, z.Data_pocz, z.Data_koniec, z.Zarobki, prem.Premia, prem.Data
FROM Zatrudnienie z
JOIN Pracownicy p on p.Id_prac = z.Id_prac
JOIN Premia prem on prem.id_prac = z.Id_prac;

--zmiana dla Klient
CREATE VIEW klient_widok AS
SELECT k.Nazwa, p.Nazwa, p.Cena 
FROM Produkty p
JOIN Kategorie k on k.Id_kat = p.Id_kat;


--zmiana dla Analityk
CREATE VIEW analityk_produkty AS
SELECT p.Id_produktu, k.Id_kat, k.Nazwa, p.Nazwa, p.Cena
FROM Produkty p
JOIN Kategorie k on k.Id_kat = p.Id_kat;


--zmiana dla Pracownik
CREATE VIEW pracownik_produkty AS
SELECT p.Id_produktu, k.Id_kat, k.Nazwa, p.Nazwa, p.Cena
FROM Produkty p
JOIN Kategorie k on k.Id_kat = p.Id_kat;


--Zad.8
DELETE FROM Rejestracje;
DELETE FROM Kursy;
DELETE FROM Studenci;
DELETE FROM Wyk³adowcy;

CREATE SEQUENCE Studenci_seq
INCREMENT BY 1
START WITH 101;


INSERT INTO Studenci VALUES (Studenci_seq.NextVal, 'Kuczyñska Ewa', 1980, 'Bazy danych');
INSERT INTO Studenci VALUES (Studenci_seq.NextVal, 'Lubicz Robert', 1985, 'Multimedia');
INSERT INTO Studenci VALUES (Studenci_seq.NextVal, 'Krajewski Bogdan', 1988, 'Bazy danych');
INSERT INTO Studenci VALUES (Studenci_seq.NextVal, 'Lityñska Anna', 1987, 'Multimedia');
INSERT INTO Studenci VALUES (Studenci_seq.NextVal, 'Marzec Marcin', 1982, 'Multimedia');
INSERT INTO STudenci VALUES (Studenci_seq.NextVal, 'Cichocki Rafa³', 1989, 'Bazy danych');

CREATE SEQUENCE Wyk³adowcy_seq
INCREMENT BY 1
START WITH 1010;

INSERT INTO Wyk³adowcy VALUES (Wyk³adowcy_seq.NextVal, 'Kowalski Jan', 'Dr', 'Adiunk');
INSERT INTO Wyk³adowcy VALUES (Wyk³adowcy_seq.NextVal, 'Jakubowski Emil', 'Dr hab', 'Docent');
INSERT INTO Wyk³adowcy VALUES (Wyk³adowcy_seq.NextVal, 'Gazda Miros³awa', 'Dr', 'Profesor');

CREATE SEQUENCE Kursy_seq
INCREMENT BY 1
START WITH 1;

INSERT INTO Kursy VALUES (Kursy_seq.NextVal, 'Bazy danych', 1010);
INSERT INTO Kursy VALUES (Kursy_seq.NextVal, 'Systemy operacyjne', 1012);
INSERT INTO Kursy VALUES (Kursy_seq.NextVal, 'Multimedia', 1011);
INSERT INTO Kursy VALUES (Kursy_seq.NextVal, 'Sieci komputerowe', NULL);


INSERT INTO Rejestracje VALUES (101, 1, SYSDATE);
INSERT INTO Rejestracje VALUES (102, 3, SYSDATE);
INSERT INTO Rejestracje VALUES (104, 3, SYSDATE);
INSERT INTO Rejestracje VALUES (106, 1, SYSDATE);
INSERT INTO Rejestracje VALUES (104, 2, SYSDATE);
INSERT INTO Rejestracje VALUES (101, 4, SYSDATE);
INSERT INTO Rejestracje VALUES (103, 1, SYSDATE);
INSERT INTO Rejestracje VALUES (105, 1, SYSDATE);

--Zad 9
SELECT table_name FROM all_tables;

--Zad.10
SELECT view_name FROM all_views;

--Zad 11
SELECT sequence_name FROM all_sequences;