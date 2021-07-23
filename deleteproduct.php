<?php 

	include 'db.php';
  if($_SERVER["REQUEST_METHOD"] == "POST"){
 $id = $_POST['id'];
 
echo $sql="DELETE FROM addcart WHERE id = '$id';";
 
if ($con->query($sql)) {
       echo "Success";
} else {
    echo "Fails159";
}
}

	
?>