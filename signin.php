<?php

 include 'db.php';
 if($_SERVER["REQUEST_METHOD"] == "POST"){
	 
 $username= $_POST['username'];
 $password =  $_POST['password'];
  
if($con)
{
 $sql="SELECT id,username,password FROM `userdata` WHERE username='$username' and password='$password'";
  
    $result =mysqli_query($con,$sql);
	    
	if(mysqli_affected_rows($con)>0)
	{  
	        //echo "login success !";
	        $row = mysqli_fetch_assoc($result);
	        $myObj=array("data"=>"valid","sql"=>"$sql");
            echo "valid_".$row['id'];
	}
	else
	{
	    $myObj=array("data"=>"invalid","sql"=>"$sql");
        echo "invalid_"."0";
   	}
}	
else
{
    
       echo "invalid";
}
 } 
else{
echo "Check Again";
} 
mysqli_close($con);
?>