<?php 

 include 'db.php';
 if($_SERVER["REQUEST_METHOD"] == "POST"){
 $id = $_POST['id'];

  $queryResult =$con->query("SELECT * FROM cart where id='$id'");

  $result = array();
  while ($fetchdata=$queryResult->fetch_assoc()) {
	  
	  
	  array_push($result,array(
			"id"=>$fetchdata['id'],
			"image"=>$fetchdata['image'],
			"sareename"=>$fetchdata['sareename'],
			"brand"=>$fetchdata['brand'],
			"quantity"=>$fetchdata['quantity'],
			"price"=>$fetchdata['price'],
	
	));  
  }

echo json_encode(array(
			"result"=>$result));
 }
else{
echo "Check Again";
} 
mysqli_close($con);
 ?>
 