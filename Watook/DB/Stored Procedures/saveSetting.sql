/***********************************************************************************************
AUTHOR : Ravindra Lonkar
select saveSetting('{"userid":"10","distancerange":"3.5","distancein":"3","agemin":"20","agemax":"25","femaleinterest":"202","maleinterest":"201"}')
***********************************************************************************************/
-- DROP FUNCTION public.saveLocation(text);
CREATE OR REPLACE FUNCTION public.saveSetting(settingjson text)
  RETURNS character varying AS
$BODY$
  DECLARE result character varying; 
 BEGIN

      INSERT INTO txn_usersetting (userid,distancerange,distancein,agemin,agemax,femaleinterest,maleinterest,createddate,lastmodifieddate)
      SELECT userid,distancerange,distancein,agemin,agemax,femaleinterest,maleinterest,now(),now() FROM json_populate_record(null::txn_usersetting, settingjson::json)
      WHERE NOT EXISTS (select 1 from txn_usersetting where userid = (select cast(settingjson::json->>'userid' as int)) limit 1)
      RETURNING settingid into result;

     --raise notice 'value%',  ret;
 
      if(result IS NULL)then
       UPDATE txn_usersetting 
          SET distancerange = (select cast(settingjson::json->>'distancerange' as double precision))
             ,distancein = (select cast(settingjson::json->>'distancein' as int))
             ,agemin = (select cast(settingjson::json->>'agemin' as int))
             ,agemax =(select cast(settingjson::json->>'agemax' as int))
             ,femaleinterest = (select cast(settingjson::json->>'femaleinterest' as int))
             ,maleinterest = (select cast(settingjson::json->>'maleinterest' as int))
             ,lastmodifieddate = now()
             WHERE userid = (select cast(settingjson::json->>'userid' as int))
             RETURNING settingid into result;
      end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
 
