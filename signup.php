<?php

 include 'db.php';
if($_SERVER["REQUEST_METHOD"] == "POST"){
$username = $_POST['username'];
$email= $_POST['email'];
$phoneno= $_POST['phoneno'];
$password = $_POST['password'];


$sql="INSERT INTO userdata(username,email,phoneno,password)VALUES('$username','$email','$phoneno','$password')";

if ($con->query($sql)) {
       echo "valid";
} else {
    echo "invalid";
}
}
$con->close();

?>