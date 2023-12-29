--Zadanie 1
--1.1
SELECT * FROM Emp WHERE Emp.sal > 2000 AND Emp.ename NOT LIKE 'A%' ORDER BY Emp.hiredate;


--1.2
SELECT *  FROM Emp WHERE Emp.sal < 1500 AND Emp.ename NOT LIKE '%S' ORDER BY Emp.hiredate;

--1.3
SELECT DISTINCT Emp.job FROM Emp;

--1.4
SELECT * FROM Emp WHERE Emp.comm IS NULL OR Emp.comm = 0;

--1.5
SELECT Emp.empno, Emp.sal, Emp.job FROM Emp WHERE Emp.mgr IS NULL ORDER BY Emp.sal ASC;

--Zadanie 3
--3.1
DROP TABLE Rejestracje;
DROP TABLE Studenci;
DROP TABLE Kursy;
DROP TABLE Wyk³adowcy;


CREATE TABLE Studenci(
NrIndeksu NUMBER(3) PRIMARY KEY,
Nazwisko VARCHAR2(25),
RokUrodzenia NUMBER(4),
Kierunek VARCHAR2(15)
);

CREATE TABLE Wyk³adowcy(
IdWyk³adowcy NUMBER(4) PRIMARY KEY,
Nazwisko VARCHAR2(20),
Stopieñ VARCHAR2(13),
Stanowisko VARCHAR2(13)
);

CREATE TABLE Kursy(
IdKursu NUMBER(2) PRIMARY KEY,
Nazwa VARCHAR2(25),
IdWyk³adowcy NUMBER(4),
CONSTRAINT fk_IdWyk³adowcy
    FOREIGN KEY (IdWyk³adowcy)
    REFERENCES Wyk³adowcy(IdWyk³adowcy)
);

CREATE TABLE Rejestracje(
NrIndeksu NUMBER(3) NOT NULL,
IdKursu NUMBER(2) NOT NULL,
Data DATE,
CONSTRAINT pk_Rejestracje PRIMARY KEY(NrIndeksu, IdKursu),
 CONSTRAINT fk_NrIndeksu
    FOREIGN KEY (NrIndeksu)
    REFERENCES Studenci(NrIndeksu),
 CONSTRAINT fk_IdKursu
    FOREIGN KEY (IdKursu)
    REFERENCES Kursy(IdKursu)
);

--+++++++++++++++++++++++++++++++++++++++++++++++++
--3.2

CREATE UNIQUE INDEX index_Nazwa ON Kursy(Nazwa);

CREATE INDEX index_Nazwisko_Studenci ON Studenci(Nazwisko);

CREATE INDEX index_Nazwisko_Wykladowcy ON Wyk³adowcy(Nazwisko);

--++++++++++++++++++++++++++++++++++++++++++++++++++++++
--3.3
INSERT INTO Studenci VALUES (101, 'Kuczyñska Ewa', 1980, 'Bazy danych');
INSERT INTO Studenci VALUES (102, 'Lubicz Robert', 1985, 'Multimedia');
INSERT INTO Studenci VALUES (103, 'Krajewski Bogdan', 1988, 'Bazy danych');
INSERT INTO Studenci VALUES (104, 'Lityñska Anna', 1987, 'Multimedia');
INSERT INTO Studenci VALUES (105, 'Marzec Marcin', 1982, 'Multimedia');
INSERT INTO STudenci VALUES (106, 'Cichocki Rafa³', 1989, 'Bazy danych');

INSERT INTO Wyk³adowcy VALUES (1010, 'Kowalski Jan', 'Dr', 'Adiunk');
INSERT INTO Wyk³adowcy VALUES (1011, 'Jakubowski Emil', 'Dr hab', 'Docent');
INSERT INTO Wyk³adowcy VALUES (1012, 'Gazda Miros³awa', 'Dr', 'Profesor');

INSERT INTO Kursy VALUES (1, 'Bazy danych', 1010);
INSERT INTO Kursy VALUES (2, 'Systemy operacyjne', 1012);
INSERT INTO Kursy VALUES (3, 'Multimedia', 1011);
INSERT INTO Kursy VALUES (4, 'Sieci komputerowe', NULL);



INSERT INTO Rejestracje VALUES (101, 1, NULL);
INSERT INTO Rejestracje VALUES (102, 3, NULL);
INSERT INTO Rejestracje VALUES (104, 3, NULL);
INSERT INTO Rejestracje VALUES (106, 1, NULL);
INSERT INTO Rejestracje VALUES (104, 2, NULL);
INSERT INTO Rejestracje VALUES (101, 4, NULL);
INSERT INTO Rejestracje VALUES (103, 1, NULL);
INSERT INTO Rejestracje VALUES (105, 1, NULL);

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--3.4

UPDATE Rejestracje SET IdKursu = 3 WHERE (NrIndeksu = 105 AND IdKursu = 1);
COMMIT;


--3.5

DELETE FROM Rejestracje WHERE (NrIndeksu = (SELECT NrIndeksu FROM Studenci WHERE Nazwisko LIKE 'Marzec %'));
ROLLBACK;

--3.6

UPDATE Rejestracje SET Data = Sysdate;

--3.7

SELECT Kursy.Nazwa FROM Kursy WHERE Kursy.IdWyk³adowcy IS NULL;
SELECT * FROM Rejestracje WHERE NrIndeksu = 101;
SELECT Kursy.Nazwa FROM Kursy ORDER BY Kursy.Nazwa;
SELECT Studenci.Nazwisko AS Nazwisko, (EXTRACT(YEAR FROM Sysdate) - RokUrodzenia) AS Wiek FROM Studenci;
