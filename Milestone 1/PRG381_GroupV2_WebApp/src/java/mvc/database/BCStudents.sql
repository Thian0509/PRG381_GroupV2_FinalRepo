/*Database name: BCStudents*/

CREATE TABLE users (
    student_number VARCHAR(20) PRIMARY KEY CHECK (student_number ~ '^[A-Za-z0-9]+$'), /* Prevents special characters, Alpha numeric only */

    name           VARCHAR(100) NOT NULL CHECK (name ~ '^[A-Za-z ]+$'), /* Prevents numeric/symbolic names */
    surname        VARCHAR(100) NOT NULL CHECK (surname ~ '^[A-Za-z ]+$'), /* Enforces proper email syntax */

    email          VARCHAR(255) UNIQUE NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'), /*  */

    phone          VARCHAR(20) UNIQUE CHECK (phone ~ '^[0-9\-\+\(\) ]{7,20}$'), /* Validates phone numbers loosely */

    password       VARCHAR(255) NOT NULL CHECK (length(password) >= 8) /* Simple security rule */
);



/*Test Students To Ensure Validition Works*/
INSERT INTO users (student_number, name, surname, email, phone, password)
VALUES
('S001', 'Alice', 'Smith', 'alice.smith@gmail.com', '0756541456', 'SecurePass1'),
('S002', 'Bob', 'Johnson', 'bob.johnson@gmail.com', '+2765821634', 'Password123')


SELECT * FROM users



