/*
 * COPY dept FROM '~/cs348/a3/JDBCExample/src/dept.csv' CSV DELIMITER ',';
 * ERROR:  must be superuser to COPY to or from a file
 */

\copy dept FROM '~/cs348/a3/JDBCExample/src/dept.csv' csv delimiter ','; 
\copy works FROM '~/cs348/a3/JDBCExample/src/works.csv' csv delimiter ','; 