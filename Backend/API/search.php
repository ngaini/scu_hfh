<?php

    include_once 'dbconfig.php';
  	 
	
	$phone = $_REQUEST['From'];
	$body = $_REQUEST['Body'];
    $pieces = explode("#", $body);
    $name = $pieces[0];
    $items = $pieces[1];
    $pincode = $pieces[2];

    $sql = "INSERT INTO `tel_recv` (`PHONE`, `NAME`, `ITEM`, `LOCATION`) VALUES ('".$phone."','".$name."', '".$items."', ".$pincode.")";
   
    $result=mysql_query($sql);
    
echo "<?xml version='1.0' encoding='UTF-8'?>";
echo "<Response>";
    echo "<Sms>Hey".$name."Someone will get you ".$items."</Sms>";
    echo "</Response>";

?>

	
