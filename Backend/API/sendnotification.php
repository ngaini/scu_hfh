<?php
    /* Send an SMS using Twilio. You can run this file 3 different ways:
     *
     * - Save it as sendnotifications.php and at the command line, run 
     *        php sendnotifications.php
     *
     * - Upload it to a web host and load mywebhost.com/sendnotifications.php 
     *   in a web browser.
     * - Download a local server like WAMP, MAMP or XAMPP. Point the web root 
     *   directory to the folder containing this file, and load 
     *   localhost:8888/sendnotifications.php in a web browser.
     */
 
    // Step 1: Download the Twilio-PHP library from twilio.com/docs/libraries, 
    // and move it into the folder containing this file.
    require "Services/Twilio.php";
 
  	$number=$_GET['number'];
  	$message=$_GET['message'];
  	
    // Step 2: set our AccountSid and AuthToken from www.twilio.com/user/account
    $AccountSid = "AC622c54c69478603f37725a7a77040565";
    $AuthToken = "c93c7b5ebedee2f7e38985ac9c89312b";
 
    // Step 3: instantiate a new Twilio Rest Client
    $client = new Services_Twilio($AccountSid, $AuthToken);
 
    
 
    
        $sms = $client->account->messages->sendMessage(
 
        // Step 6: Change the 'From' number below to be a valid Twilio number 
        // that you've purchased, or the (deprecated) Sandbox number
            "828-675-2880", 

            // the number we are sending to - Any phone number
            $number,
 
            // the sms body
            $message
        );
 
        // Display a confirmation message on the screen
        echo "Sent message to $name";
 
    ?>