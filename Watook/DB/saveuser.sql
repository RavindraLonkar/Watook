-- Function: public.saveuser(text)

-- DROP FUNCTION public.saveuser(text);

CREATE OR REPLACE FUNCTION public.saveuser(userjson text)
  RETURNS character varying AS
$BODY$
  DECLARE result character varying; 
 BEGIN

 
    
      INSERT INTO txn_user(fbid,firstname,middlename,lastname,dob,genderid,contactmobile,contactmobile2,emailid,advertiseid,aboutyou,workinfo,statusinfo,fbimages,
      profileimage,isactive,createdby,createddate,lastmodifiedby,lastmodifieddate)
      SELECT fbid,firstname,middlename,lastname,dob,genderid,contactmobile,contactmobile2,emailid,advertiseid,aboutyou,workinfo,statusinfo,fbimages,
      profileimage,isactive,1,now(),1,now() FROM json_populate_record(null::txn_user, userjson::json)
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
             ,workinfo = (select userJson::json->>'workinfo')
             ,statusinfo = (select cast(userJson::json->>'statusinfo' as int))
             ,fbimages = (select userJson::json->>'fbimages')
             ,profileimage = (select userJson::json->>'profileimage')
             ,isactive =  (select cast(userJson::json->>'isactive' as int))  
             ,lastmodifieddate = now() 
             WHERE fbid = (select userJson::json->>'fbid')
             RETURNING userid into result;
     end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION public.saveuser(text)
  OWNER TO "Watook";
