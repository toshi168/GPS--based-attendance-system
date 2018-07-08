<?php
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
	if(isset($_POST['sem']) && !empty(isset($_POST['sem']))
		&& isset($_POST['sid']) && !empty(isset($_POST['sid']))){ 
	include_once("connection.php"); 
	$sem = $_POST['sem']; 
	$sid = $_POST['sid'];
	
	$sql = "Update class_sub set status = 0 where sem = '$sem' and sid = '$sid'";
	
	if ($conn->query($sql) === TRUE) {			  //
		echo "Class Over";
		
	} else { 
		echo "ErrorInsert"; 
		echo "Error: " . $sql . "<br>" . $conn->error; 
  }
}?>