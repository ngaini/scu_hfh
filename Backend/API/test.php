<?php
include_once 'dbconfig.php';
//header("Content-type: text/xml");
echo "<?xml version='1.0' encoding='UTF-8'?>";
$messageid=$_REQUEST['MessageSid'];
$message=$_REQUEST['Body'];
$message='test';
$email="123";
$name="123";
$phone="123";
$password="123";
 // sql query for inserting data into database
$sql = "INSERT INTO `tbl_donaters` (`EMAIL`, `NAME`, `PHONE`, `PASSWORD`) VALUES ('".$message."','".$name."','".$phone."','".$password."');";
echo $sql;
$result=mysql_query($sql);
if($result)
{


	echo "<Response>";
	echo "<Sms>Remember me this weekend</Sms>";
	echo "</Response>";
}
else
{
	echo "REG_ERR";
}
?>

