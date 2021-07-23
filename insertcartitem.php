<?php 

	include 'db.php';
  if($_SERVER["REQUEST_METHOD"] == "POST"){
 $id = $_POST['id'];
 $image = $_POST['image'];
$sareename = $_POST['sareename'];
$brand = $_POST['brand'];
$quantity = $_POST['quantity'];
$price = $_POST['price'];
$total = $_POST['total'];
 
$sql="INSERT INTO addcart(id,image,sareename,brand,quantity,price,total)
VALUES('$id','$image','$sareename','$brand','$quantity','$price','$total')";
 
if ($con->query($sql)) {
       echo "Success";
} else {
    echo "Fails159";
}
}

	
?>