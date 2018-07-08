<?php
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
	if(isset($_POST['txtEID']) && !empty(isset($_POST['txtEID']))) {
		include_once("connection.php"); 
		$eid = $_POST['txtEID']; 
		$sql = "delete from student where enrollment_id = '$eid' ";
		$sql1= "delete from user where user_id = '$eid'";
		if (($conn->query($sql) === TRUE) && ($conn->query($sql1) === TRUE)) {	
			echo "Student deleted!";
		}
		else {
			echo "Student not deleted!";
		}
	}
	
	
?>
	