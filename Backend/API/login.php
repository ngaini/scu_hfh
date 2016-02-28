<?php
include_once 'dbconfig.php';
 // variables for input data
 $email=$_GET['email'];
 $password=$_GET['password'];
 // sql query for inserting data into database
        
        $sql = "SELECT * FROM `tbl_donaters` Where `EMAIL`='".$email."' AND `PASSWORD` = ".$password;
       
        $result=mysql_query($sql);
        $num_rows = mysql_num_rows($result);
        if($num_rows)
        {
        	echo "LOGIN_SUCCESS";
        }
        else
        {
        	echo "LOGIN_ERR";
        }
        
        // sql query for inserting data into database
 
?>