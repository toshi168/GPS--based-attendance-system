<?php
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
	if(isset($_POST['txtTID']) && !empty(isset($_POST['txtTID']))) {
		include_once("connection.php"); 
		$tid = $_POST['txtTID']; 
		$sql = "delete from teacher where tid = '$tid' ";
		$sql1= "delete from user where user_id = '$tid'";
		if (($conn->query($sql) === TRUE) && ($conn->query($sql1) === TRUE)) {	
			echo "Teacher deleted!";
		}
		else {
			echo "Teacher not deleted!";
		}
	}
	else {
		echo " Something went wrong!";
	}
	
?>
	