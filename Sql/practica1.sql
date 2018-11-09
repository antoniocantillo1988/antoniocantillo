/*EJERCICIO PRACTICA UNO*/

-- 1. Todos los datos de los departamentos.
select * from departamentos;

-- 2. El nombre, puesto y salario de todos los empleados, por orden alfabético.
select nombre, puesto, salario from empleados order by nombre;

-- 3. Contar el número de empleados.
select count(*) from empleados;

-- 4. Nombre de los distintos puestos de trabajo.
select distinct(puesto) from empleados;

-- 5. Contar el número de puestos de trabajos distintos.
select count(distinct(puesto)) from empleados;

-- 6. Datos de los empleados cuyo nombre acabe en ‘z’.
select nombre from empleados where nombre like '%z';

-- 7. Datos de los empleados que ganen más de 2000 y menos de 4000.
select * from empleados where (salario > 2000) and (salario <4000);

-- 8. Nombre de los empleados que empezaron a trabajar en el 81
select nombre from empleados where fechaAlta like '1981%';

-- 9. Salario de los empleados, y salario resultante si se incrementa un 10%.
select nombre, salario*1.10 as salario_aumentado from empleados;

-- 10.Nombre y departamento de los empleados sin comisión.
select empleados.nombre, departamentos.nombre from empleados left join departamentos ON  departamentos.numero = empleados.dnumero where empleados.comision is null ;

-- 11.Datos de los empleados y de sus departamentos.
select e.nombre, d.nombre from empleados e, departamentos d where d.numero = e.dnumero;

-- 12.Todos los empleados que no son del departamento de Ventas.
select e.nombre, d.nombre from empleados e, departamentos d where e.dnumero = d.numero and d.nombre != 'ventas';

-- 13.Nombre de cada empleado, y el nombre de su jefe.
select e.nombre, d.nombre from empleados e left join empleados d on e.jefe=d.numero;

-- 14.Nombre y número de meses que llevan trabajando los empleados de Sevilla.
select empleados.nombre, TIMESTAMPDIFF(MONTH, fechaAlta, NOW()) from empleados join departamentos on departamentos.numero = empleados.dnumero where departamentos.ciudad = 'Sevilla' ;

-- 15.Nombre, puesto y un mensaje ‘Sin comisión’ si no tiene comisión, o ‘Con comisión: XXX’ si tiene comisión (XXX es la comisión).
select e.nombre, e.puesto, concat('con comision: ', e.comision) as comision_es_nulo from empleados e, departamentos d where d.numero = e.dnumero order by e.comision desc;

-- 16.Obtener los 5 empleados con menor sueldo.
select nombre from empleados group by salario order by salario limit 5;

-- 17.Nombre de cada departamento y la media de sus sueldos.
select d.nombre, avg(e.salario) from empleados e, departamentos d where d.numero = e.dnumero group by d.numero;

-- 18.Media de los sueldos de los departamentos Ventas y Contabilidad.
select d.nombre, avg(e.salario) from empleados e, departamentos d where d.numero = e.dnumero and d.nombre like 'Ventas' or d.nombre like 'Contabilidad' group by d.nombre;

-- 19.Nombre de cada departamento cuyo número de empleados es mayor a dos.  NO VA!! TO DOnumero!!
select d.nombre from empleados e, departamentos d where d.numero = e.dnumero group by d.numero having count (e.numero > 2);

-- 20.Nombre de los empleados que tienen un sueldo igual al máximo de cada departamento.
select empleados.nombre from empleados join (select max(salario) as sal, dnumero as num from empleados group by dnumero) as tabla on empleados.salario = tabla.sal and empleados.dnumero = tabla.num;