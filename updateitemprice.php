<?php 

	include 'db.php';
  if($_SERVER["REQUEST_METHOD"] == "POST"){
 $id = $_POST['id'];

$quantity = $_POST['quantity'];
$total = $_POST['total'];
 
echo $sql="UPDATE addcart SET quantity='$quantity',total='$total' WHERE id='$id'";
 
if ($con->query($sql)) {
       echo "Success";
} else {
    echo "Fails159";
}
}

	
?>