/***********************************************************************************************
AUTHOR : Namdev Arade
select public.saveuser('{"fbid":"12366","lastname":"Avffffinash","workemployer":"Extrapreneurs","statusinfo":"401","isactive":"1"}')
***********************************************************************************************/
-- DROP FUNCTION public.saveuser(text);

CREATE OR REPLACE FUNCTION public.saveuser(userjson text)
  RETURNS character varying AS
$BODY$
	 DECLARE result character varying; 
 BEGIN

	
    
      INSERT INTO txn_user(fbid,firstname,middlename,lastname,dob,genderid,contactmobile,contactmobile2,emailid,advertiseid,aboutyou,workemployer,worklocation,workposition,statusinfo,fbimages,
      profileimage,isactive,firebasetoken,createdby,createddate,lastmodifiedby,lastmodifieddate)
      SELECT fbid,firstname,middlename,lastname,dob,genderid,contactmobile,contactmobile2,emailid,advertiseid,aboutyou,workemployer,worklocation,workposition,statusinfo,fbimages,
      profileimage,1,firebasetoken,1,now(),1,now() FROM json_populate_record(null::txn_user, userjson::json)
      WHERE NOT EXISTS (select 1 from txn_user where fbid = (select userJson::json->>'fbid') limit 1)
      RETURNING userid into result;

     --raise notice 'value%',  ret;
	
      if(result IS NULL)then
       UPDATE txn_user 
          SET firstname = (select userJson::json->>'firstname')
             ,middlename = (select userJson::json->>'middlename')
             ,lastname = (select userJson::json->>'lastname')    
             ,dob = (select to_date(userJson::json->>'dob', 'dd/mm/yyyy'))   
             ,genderid = (select cast(userJson::json->>'genderid' as int))
             ,contactmobile = (select userJson::json->>'contactmobile')  
             ,contactmobile2 = (select userJson::json->>'contactmobile2')
             ,emailid = (select userJson::json->>'emailid')
             ,advertiseid = (select userJson::json->>'advertiseid')  
             ,aboutyou = (select userJson::json->>'aboutyou')
	         ,workemployer = (select userJson::json->>'workemployer')
	         ,worklocation = (select userJson::json->>'worklocation')
             ,workposition = (select userJson::json->>'workposition')
             ,statusinfo = (select cast(userJson::json->>'statusinfo' as int))
             ,fbimages = (select userJson::json->>'fbimages')
             ,profileimage = (select userJson::json->>'profileimage')
             ,firebasetoken = (select userJson::json->>'firebasetoken')
             ,lastmodifieddate = now() 
             WHERE fbid = (select userJson::json->>'fbid')
             RETURNING userid into result;
     end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;


