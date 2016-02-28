<?php
include_once 'dbconfig.php';
 // variables for input data
 $email=$_GET['email'];
 $name=$_GET['name'];
 $phone=$_GET['phone'];
 $password=$_GET['password'];
 // sql query for inserting data into database
 
        $sql = "INSERT INTO `tbl_donaters` (`EMAIL`, `NAME`, `PHONE`, `PASSWORD`) VALUES ('".$email."','".$name."',".$phone.",'".$password."');";
        $result=mysql_query($sql);
        if($result)
        {
        	echo "REG_SUCCESS";
        }
        else
        {
        	echo "REG_ERR";
        }
        
        // sql query for inserting data into database
 
?>