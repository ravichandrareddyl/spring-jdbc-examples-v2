CREATE OR REPLACE PACKAGE DEMOPKG AS 
   PROCEDURE insertCust(p_id customer.id%type,p_name customer.name%type,p_email customer.email%type); 
   PROCEDURE GETCUSTOMERS(p_id in customer.id%type,cust_results out sys_refcursor); 
END DEMOPKG;
/


CREATE OR REPLACE PACKAGE BODY DEMOPKG AS  
   
PROCEDURE insertCust(p_id customer.id%type,
    p_name customer.name%type,
    p_email customer.email%type
   )
IS
BEGIN

  INSERT INTO CUSTOMER ("ID", "NAME", "EMAIL", "CREATED_DATE")
  VALUES (p_id, p_name,p_email, SYSDATE);

  COMMIT;

END insertCust;
procedure GETCUSTOMERS(p_id in customer.id%type,cust_results out sys_refcursor)
   is
   begin
     open cust_results for
       select name, id
        from CUSTOMER;
end GETCUSTOMERS;
END DEMOPKG;
/
