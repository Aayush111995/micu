# micu

   login --- address 

   1 . user --
   			       id 
   			       phone_number
   			       name
   			       email
   			       address_id

   2 . address --
   				   id
   				   user_id
   				   locality
   				   city
   				   pincode
   				   lat
   				   long
   				   address

   3 . product --- 
   				   id
   				   name
   				   mrp
   				   product_meta_data(json){img:"","productDetail":""}
   				   product_type // dairy
   				   quantity -- 500
   				   unit -- ml
   				   discounted_percentage

   4 . order   ---
   					id
   					user_id
   					product_id
   					amount
   					created_date
   					status // initiated,cancel,delivered,failed,hold
   					address_id
   					delivery_date
   					timeslot

   5 . user_plan ---
   					id
   					user_id
   					plan_type // alternate
   					start_date // 23 jan 2022 
   					status -- running,completed,notStarted,cancel
   					expiry_date	
   					product_id

   6 . cart ----
   					id
   					user_id
   					product_json //{id,quantity,amount,start_date,end_date,planType}
   					payable_amount
   					total_amount
   					discount_amount
   					status
   					coupon_id
   					address_id 	
   					updated_date
   					created_date				

  7 . payment -- 
  					id
  					amount
  					status -- //initiated,success,failed
  					cart_id
  					payment_response
  					pg
  					created_date
  					updated_date
  					user_id
  					payment_type

 8 . refund -- 
 					id
 					amount
 					status
 					//more to go

 9 . delivery_boy -- 	
 					 id
 					 name
 					 phone_number
 					 address
 10 . delivery_orders
 					id
 					delivery_boy_id
 					order_id
 					time
 					status
 					source // source of pick up 					 	 					

 
google apis -- lat long --- map show -- locality---city
graphql --- use rest api for now
java
mysql 
redis -- onhold
gupshup









   



   				  


