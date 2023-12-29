--Zadanie 1.1
SELECT Emp.deptno, MIN(Emp.ename)
FROM Emp
GROUP BY Emp.deptno
ORDER BY Emp.deptno;

--Zadanie 1.2
SELECT Emp.job, COUNT(*), ROUND(AVG(Emp.sal))
FROM Emp
GROUP BY emp.job;

--Zadanie 1.3
SELECT Emp.job, COUNT(DISTINCT Emp.deptno)
FROM Emp
GROUP BY emp.job;

--Zadanie 1.4
SELECT *
FROM Emp kierownik
WHERE job = 'MANAGER'
AND (SELECT COUNT(*) FROM Emp podwladny WHERE podwladny.Mgr = kierownik.Empno) = 2;


--Zadanie 1.5
SELECT e.Ename, e.Sal,
AVG(e.Sal) over (order by e.Sal RANGE BETWEEN 100 PRECEDING AND 100 FOLLOWING) as srednia
FROM Emp e
WHERE e.deptno = 10
UNION
SELECT e.Ename, e.Sal,
AVG(e.Sal) over (order by e.Sal RANGE BETWEEN 100 PRECEDING AND 100 FOLLOWING) as srednia
FROM Emp e
WHERE e.deptno = 20
UNION
SELECT e.Ename, e.Sal,
AVG(e.Sal) over (order by e.Sal RANGE BETWEEN 100 PRECEDING AND 100 FOLLOWING) as srednia
FROM Emp e
WHERE e.deptno = 30;

--Zadanie 2.1
SELECT STUDENCI.nazwisko, COUNT(Rejestracje.nrindeksu)
FROM STUDENCI
INNER JOIN Rejestracje on STUDENCI.nrindeksu = Rejestracje.nrindeksu
GROUP BY STUDENCI.nazwisko;

--Zadanie 2.2
SELECT Wyk³adowcy.nazwisko, Kursy.nazwa, COUNT(Rejestracje.nrindeksu)
FROM Wyk³adowcy
JOIN Kursy on Wyk³adowcy.idwyk³adowcy = Kursy.idwyk³adowcy
JOIN Rejestracje on Rejestracje.idkursu = Kursy.idkursu
GROUP BY Wyk³adowcy.nazwisko, Kursy.nazwa
;

--Zadanie 2.3
SELECT Kursy.nazwa, COUNT(Rejestracje.idkursu)
FROM Kursy 
JOIN Rejestracje on Rejestracje.idkursu = Kursy.idkursu
GROUP BY Kursy.nazwa
HAVING COUNT(Rejestracje.idkursu)>=5;

--Zadanie 2.4
SELECT Studenci.nazwisko, COUNT(Rejestracje.nrindeksu)
FROM Studenci
JOIN REJESTRACJE on Rejestracje.nrindeksu = Studenci.nrindeksu
GROUP BY Studenci.nazwisko
HAVING COUNT(Rejestracje.nrindeksu) > 2;

--Zadanie 2.5
SELECT MAX(Maks)
FROM (
SELECT COUNT(Rejestracje.nrindeksu) over (partition by Rejestracje.nrindeksu) AS Maks
FROM Rejestracje);
