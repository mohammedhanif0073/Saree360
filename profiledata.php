<?php 
 include 'db.php';
 if($_SERVER["REQUEST_METHOD"] == "POST"){
	 
$id = $_POST['logid'];
 
 $get = "SELECT * FROM `userdata` where Id='$id' ";
  $queryResult =$con->query($get);

  $result = array();
  while ($fetchdata=$queryResult->fetch_assoc()) {
	  
	  
	  array_push($result,array(
			"username"=>$fetchdata['username'],
			"email"=>$fetchdata['email'],
			"phoneno"=>$fetchdata['phoneno'],
			"password"=>$fetchdata['password'],
	
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