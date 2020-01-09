-- 1. Run mysql docker image
-- docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

-- 2. Connect to db (e.g. using MySQLWorkbench)

-- 3. Run below scripts to create databases
CREATE DATABASE kkukielka_dev;
CREATE DATABASE kkukielka_prod;

-- 4. Create database service accounts (create users for localhost and any host - %)
CREATE USER 'kkukielka_dev_user'@'localhost' IDENTIFIED BY 'kkukielka';
CREATE USER 'kkukielka_prod_user'@'localhost' IDENTIFIED BY 'kkukielka';
CREATE USER 'kkukielka_dev_user'@'%' IDENTIFIED BY 'kkukielka';
CREATE USER 'kkukielka_prod_user'@'%' IDENTIFIED BY 'kkukielka';

-- 5. Database grants
GRANT SELECT ON kkukielka_dev.* TO 'kkukielka_dev_user'@'localhost';
GRANT INSERT ON kkukielka_dev.* TO 'kkukielka_dev_user'@'localhost';
GRANT DELETE ON kkukielka_dev.* TO 'kkukielka_dev_user'@'localhost';
GRANT UPDATE ON kkukielka_dev.* TO 'kkukielka_dev_user'@'localhost';
GRANT SELECT ON kkukielka_prod.* TO 'kkukielka_prod_user'@'localhost';
GRANT INSERT ON kkukielka_prod.* TO 'kkukielka_prod_user'@'localhost';
GRANT DELETE ON kkukielka_prod.* TO 'kkukielka_prod_user'@'localhost';
GRANT UPDATE ON kkukielka_prod.* TO 'kkukielka_prod_user'@'localhost';
GRANT SELECT ON kkukielka_dev.* TO 'kkukielka_dev_user'@'%';
GRANT INSERT ON kkukielka_dev.* TO 'kkukielka_dev_user'@'%';
GRANT DELETE ON kkukielka_dev.* TO 'kkukielka_dev_user'@'%';
GRANT UPDATE ON kkukielka_dev.* TO 'kkukielka_dev_user'@'%';
GRANT SELECT ON kkukielka_prod.* TO 'kkukielka_prod_user'@'%';
GRANT INSERT ON kkukielka_prod.* TO 'kkukielka_prod_user'@'%';
GRANT DELETE ON kkukielka_prod.* TO 'kkukielka_prod_user'@'%';
GRANT UPDATE ON kkukielka_prod.* TO 'kkukielka_prod_user'@'%';

-- 6. Run application with -Dspring.profiles.active=dev,
-- 7. Copy content of kkukielka_database_dev.sql, add missing semicolons on the end of statements, run it in SQL client