/***********************************************************************************************
AUTHOR : Namdev Arade
select saveProfileRating(46,47)
***********************************************************************************************/
-- DROP FUNCTION public.saveProfileRating(int,int);

CREATE OR REPLACE FUNCTION public.saveprofilerating(
    userby integer,
    userto integer)
  RETURNS character varying AS
$BODY$
  DECLARE result character varying; 
 BEGIN

      
      INSERT INTO txn_profilerating(ratingby,ratingto,createddate,lastmodifieddate)
      SELECT userby,userto,now(),now() 
      WHERE NOT EXISTS (select 1 from txn_profilerating rating where rating.ratingby=userby and rating.ratingto=userto limit 1)
      RETURNING ratingid into result;

     --raise notice 'value%',  ret;
 
      if(result IS NULL)then
       UPDATE txn_profilerating 
          SET 
		lastmodifieddate=now()
       WHERE ratingby=userby and ratingto=userto
		        
             RETURNING ratingid into result;
     end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;