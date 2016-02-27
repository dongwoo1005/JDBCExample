/*
 * Assumption:
 * - Work data disappears when Employee is gone
 * - managerid of Department is set to null when manager is gone
 */

ALTER TABLE works 
  DROP CONSTRAINT works_eid_fkey; 

ALTER TABLE works 
  ADD CONSTRAINT works_eid_fkey FOREIGN KEY (eid) REFERENCES emp(eid) ON DELETE 
  CASCADE; 

ALTER TABLE dept 
  DROP CONSTRAINT dept_managerid_fkey; 

ALTER TABLE dept 
  ADD CONSTRAINT dept_managerid_fkey FOREIGN KEY (managerid) REFERENCES emp(eid) 
  ON DELETE SET NULL; 

DELETE FROM emp E 
WHERE  E.eid IN (SELECT W.eid 
                 FROM   works W, 
                        emp E2, 
                        dept D 
                 WHERE  W.did = D.did 
                        AND D.managerid = E2.eid 
                        AND E.salary > E2.salary) 