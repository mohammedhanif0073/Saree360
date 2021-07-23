<?php 

	include 'db.php';
  if($_SERVER["REQUEST_METHOD"] == "POST"){
 $id = $_POST['id'];

$username = $_POST['username'];
$email = $_POST['email'];
$phoneno = $_POST['phoneno'];
$password = $_POST['password'];


$sql="UPDATE userdata SET username='$username',email='$email',phoneno='$phoneno',password='$password' WHERE id='$id'";
 
if ($con->query($sql)) {
       echo "Success";
} else {
    echo "Fails159";
}
}

	
?>