<?php
$alphabet = '1234567890';
    $pass = array(); //remember to declare $pass as an array
    $alphaLength = strlen($alphabet) - 1; //put the length -1 in cache
    for ($i = 0; $i < 6; $i++) {
        $n = rand(0, $alphaLength);
        $pass[] = $alphabet[$n];
    }
    $password = implode($pass);
	echo $password;
	$api_key = '35ACA8E004808E';
			$contacts = '8989660248';
			$from = 'ATTEND';
			$sms_text = urlencode('Student Registered. Password is'.$password);

			//Submit to server

			$ch = curl_init();
			curl_setopt($ch,CURLOPT_URL, "http://sms.starinfotechbhopal.in/app/smsapi/index.php");
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS, "key=".$api_key."&campaign=0&routeid=7&type=text&contacts=".$contacts."&senderid=".$from."&msg=".$sms_text);
			$response = curl_exec($ch);
			curl_close($ch);
			echo $response;
	
	?>