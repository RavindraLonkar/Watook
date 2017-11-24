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
          SET firstname = case when (userJson::json->>'firstname') is null then (select firstname from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'firstname') end
			  
             ,middlename = case when (userJson::json->>'middlename') is null then (select middlename from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'middlename') end

             ,lastname = case when (userJson::json->>'lastname') is null then (select lastname from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'lastname') end  
			  
             ,dob =  case when (userJson::json->>'dob') is null then (select dob from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'dob')  end
			    
             ,genderid = case when (userJson::json->>'genderid') is null then (select genderid from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select cast(userJson::json->>'genderid' as int)) end 

             ,contactmobile = case when (userJson::json->>'contactmobile') is null then (select contactmobile from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'contactmobile') end

             ,contactmobile2 = case when (userJson::json->>'contactmobile2') is null then (select contactmobile2 from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'contactmobile2') end

             ,emailid = case when (userJson::json->>'emailid') is null then (select emailid from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'emailid') end
			  
             ,advertiseid = case when (userJson::json->>'advertiseid') is null then (select advertiseid from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'advertiseid') end
			  	  
             ,aboutyou = case when (userJson::json->>'aboutyou') is null then (select aboutyou from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'aboutyou') end
	  
	     ,workemployer = case when (userJson::json->>'workemployer') is null then (select workemployer from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'workemployer') end
			  
	     ,worklocation = case when (userJson::json->>'worklocation') is null then (select worklocation from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'worklocation') end

             ,workposition = case when (userJson::json->>'workposition') is null then (select workposition from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'workposition') end
	 
             ,statusinfo = case when (userJson::json->>'statusinfo') is null then (select statusinfo from txn_user where  fbid = (select userJson::json->>'fbid') ) else
			  (select cast(userJson::json->>'statusinfo' as int)) end
	 
             ,fbimages = case when (userJson::json->>'fbimages') is null then (select fbimages from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'fbimages') end
	 
             ,profileimage = case when (userJson::json->>'profileimage') is null then (select profileimage from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'profileimage') end
	 
             ,firebasetoken= case when (userJson::json->>'firebasetoken') is null then (select firebasetoken from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select userJson::json->>'firebasetoken') end

            ,lastmodifieddate = now() 

            ,isactive=case when (userJson::json->>'isactive') is null then (select isactive from txn_user where  fbid = (select userJson::json->>'fbid')) else
			  (select cast(userJson::json->>'isactive' as int)) end
			  
             WHERE fbid = (select userJson::json->>'fbid')
             RETURNING userid into result;
     end if;

      RETURN result;
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;



