<?php
$host = 'localhost';
$user = 'root';
$pass = '';
$dbname = 'saree360';

$con=mysqli_connect($host,$user,$pass,$dbname);
if(!$con)
{
 echo "Connection Error!!".mysqli_connect_error();
}
?>
