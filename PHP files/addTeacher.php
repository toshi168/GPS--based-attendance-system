<?php
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
	if(isset($_POST['tid']) && !empty(isset($_POST['tid'])) 
		&& isset($_POST['name']) && !empty(isset($_POST['name']))
		&& isset($_POST['dept']) && !empty(isset($_POST['dept']))
		&& isset($_POST['phone']) && !empty(isset($_POST['phone']))){ 
	include_once("connection.php"); 
	$tid = $_POST['tid']; 
	$name = $_POST['name'];
	$dept = $_POST['dept'];
	$phone = $_POST['phone']; 
	
	//random password generator
	$alphabet = '1234567890';
    $pass = array(); //remember to declare $pass as an array
    $alphaLength = strlen($alphabet) - 1; //put the length -1 in cache
    for ($i = 0; $i < 6; $i++) {
        $n = rand(0, $alphaLength);
        $pass[] = $alphabet[$n];
    }
    $password = implode($pass);

	$sql = "INSERT INTO student VALUES ( '$tid', '$name', '$dept, '$phone')"; 
	
	$sql1 = "Insert into user values ( '$tid', '$password', 'teacher')";
	if ($conn->query($sql) === TRUE) {			  //
		echo "Yayy!";
		$conn->query($sql1);
		//Password SMS
			$api_key = '35ACA8E004808E';
			$contacts = $phone;
			$from = 'ATTEND';
			$sms_text = urlencode('Faculty Member '.$name.' Registered. Your password is  :'.$password);

			//Submit to server

			$ch = curl_init();
			curl_setopt($ch,CURLOPT_URL, "http://sms.starinfotechbhopal.in/app/smsapi/index.php");
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
			curl_setopt($ch, CURLOPT_POST, 1);
			curl_setopt($ch, CURLOPT_POSTFIELDS, "key=".$api_key."&campaign=0&routeid=7&type=text&contacts=".$contacts."&senderid=".$from."&msg=".$sms_text);
			$response = curl_exec($ch);
			curl_close($ch);
			echo $response;
	} else { 
		echo "ErrorInsert"; 
		echo "Error: " . $sql . "<br>" . $conn->error; 
  }
}?>