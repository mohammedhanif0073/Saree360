<?php 

 include 'db.php';
 $queryResult =$con->query("SELECT id,image,sareename,brand,price FROM cart");

  $result = array();
  while ($fetchdata=$queryResult->fetch_assoc()) {
	  
	  
	  array_push($result,array(
			"id"=>$fetchdata['id'],
			"image"=>$fetchdata['image'],
			"sareename"=>$fetchdata['sareename'],
			"brand"=>$fetchdata['brand'],
			"price"=>$fetchdata['price'],
	
	));  
  }

echo json_encode(array(
			"result"=>$result));
 ?>