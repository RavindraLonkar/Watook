-- Function: public.saveLocation(text)

-- DROP FUNCTION public.saveLocation(text);

CREATE OR REPLACE FUNCTION public.savelocation(locationjson text)
  RETURNS character varying AS
$BODY$
  DECLARE result character varying; 
 BEGIN

      INSERT INTO txn_usertracking (userid,latitude,longitude,createddate,lastmodifieddate)
      SELECT userid,latitude,longitude,now(),now() FROM json_populate_record(null::txn_usertracking, locationjson::json)
      WHERE NOT EXISTS (select 1 from txn_usertracking where userid = (select cast(locationjson::json->>'userid' as int)) limit 1)
      RETURNING id into result;

     --raise notice 'value%',  ret;
 
      if(result IS NULL)then
       UPDATE txn_usertracking 
          SET userid = (select cast(locationjson::json->>'userid'  as int))
             ,latitude = (select cast(locationjson::json->>'latitude' as double precision))
             ,longitude = (select cast(locationjson::json->>'longitude' as double precision))
             ,lastmodifieddate=now()    
             WHERE userid = (select cast(locationjson::json->>'userid' as int))
             RETURNING id into result;
     end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
 
