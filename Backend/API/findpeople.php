<?php
include_once 'dbconfig.php';
 // variables for input data
 $pin=$_GET['pin'];

 // sql query for inserting data into database

        $sql = "SELECT * FROM `tel_recv` Where `LOCATION` >=".($pin-3)." AND `LOCATION`<=".($pin+3);
       //echo $sql;
        $result=mysql_query($sql);
        $num_rows = mysql_num_rows($result);
      
        echo '{"people":[';

        if($num_rows>0)
        {
            $counter = 0;

            while($row =  mysql_fetch_assoc($result)) 
            {
               // echo "id: " . $row["PHONE"]. " - Name: " . $row["NAME"]. " " . $row["ITEM"]. " " . $row["LOCATION"]. "<br>";
                if (++$counter == $num_rows) 
                {
                echo '{"PhoneNo":"'.$row["PHONE"].'", "Name":"'.$row["NAME"].'", "Items":"'.$row["ITEM"].'", "Location":"'.$row["LOCATION"].'"}';
                }
                else
                {
                echo '{"PhoneNo":"'.$row["PHONE"].'", "Name":"'.$row["NAME"].'", "Items":"'.$row["ITEM"].'", "Location":"'.$row["LOCATION"].'"},';
                }            }


        }
        else
        {
            echo "NORECORDS";
        }
        echo ']}';
        
        // sql query for inserting data into database
 
?>