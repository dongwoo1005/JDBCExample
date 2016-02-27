ALTER TABLE emp 
  ADD CONSTRAINT check_salary CHECK (salary >= 20000); 