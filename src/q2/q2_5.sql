ALTER TABLE dept 
  DROP CONSTRAINT dept_managerid_fkey; 

ALTER TABLE dept 
  ADD CONSTRAINT dept_managerid_fkey FOREIGN KEY (managerid) REFERENCES emp(eid) 
  ON DELETE CASCADE; 