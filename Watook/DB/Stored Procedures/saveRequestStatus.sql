/***********************************************************************************************
AUTHOR : Namdev Arade
select saveRequestStatus('{"requestid":0,"requestby":30,"requestto":27,"reqstatus":503}')
***********************************************************************************************/
-- DROP FUNCTION public.saveRequestStatus(text);

CREATE OR REPLACE FUNCTION public.saverequeststatus(requestjson text)
  RETURNS character varying AS
$BODY$
  DECLARE result character varying; 
 BEGIN

      update txn_userrequest set isactive=0 
      where ((requestby=cast(requestjson::json->>'requestby' as int) and requestto=cast(requestjson::json->>'requestto' as int) )
	  or (requestby=cast(requestjson::json->>'requestto' as int) and requestto=cast(requestjson::json->>'requestby' as int))
      );
      
      INSERT INTO txn_userrequest (requestby,requestto,reqstatus,rejectattemtcount,createddate,lastmodifieddate,isactive)
      SELECT requestby,requestto,reqstatus,0, now(),now(),1 FROM json_populate_record(null::txn_userrequest, requestjson::json)
      WHERE NOT EXISTS (select 1 from txn_userrequest where (requestby=cast(requestjson::json->>'requestby' as int) and 
			requestto=cast(requestjson::json->>'requestto' as int))
			limit 1)
      RETURNING id into result;

     --raise notice 'value%',  ret;
 
      if(result IS NULL)then
       UPDATE txn_userrequest 
          SET requestby = (select cast(requestjson::json->>'requestby'  as int))
             ,requestto = (select cast(requestjson::json->>'requestto' as int))
             ,reqstatus = (select cast(requestjson::json->>'reqstatus' as int))  
             ,rejectattemtcount =  case when (cast(requestjson::json->>'reqstatus' as int)=501 or cast(requestjson::json->>'reqstatus' as int)=503 or cast(requestjson::json->>'reqstatus' as int)=504 or cast(requestjson::json->>'reqstatus' as int)=0)
					 then 
						(select  cast(rejectattemtcount as int) from txn_userrequest where 
						(requestby=cast(requestjson::json->>'requestby' as int) and requestto=cast(requestjson::json->>'requestto' as int))
						)
					when cast(requestjson::json->>'reqstatus' as int)=502 
					then
						(select  cast(rejectattemtcount as int)+1 from txn_userrequest where 
						(requestby=cast(requestjson::json->>'requestby' as int) and requestto=cast(requestjson::json->>'requestto' as int))
						)
				        end
	    ,isactive=1
	    ,lastmodifieddate=now()
             WHERE (requestby=cast(requestjson::json->>'requestby' as int) and requestto=cast(requestjson::json->>'requestto' as int)) 
		        
             RETURNING id into result;
     end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;