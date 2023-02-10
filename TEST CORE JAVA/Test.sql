create table EMPLOYEE (EMP_ID number(5) primary key, NAME varchar(25), AGE number(2), SALARY number(8), DESIGNATION varchar(20), DEPT varchar(15), MANAGER_ID number(5) references EMPLOYEE(EMP_ID));

select * from EMPLOYEE where AGE = (select max(AGE) from EMPLOYEE);

select E1.NAME as EMPLOYEE_NAME, E2.NAME as MANAGER_NAME, E1.SALARY as EMPLOYEE_SALARY, E2.SALARY as MANAGER_SALARY from 
EMPLOYEE E1 join EMPLOYEE E2 
ON E1.MANAGER_ID = E2.EMP_ID where E1.SALARY > E2.SALARY;

select DEPT, sum(SALARY) as TOTAL_EXPENSE from EMPLOYEE group by DEPT;