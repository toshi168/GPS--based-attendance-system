<?php
	error_reporting(E_ALL);
	ini_set('display_errors', 1);
	if(isset($_POST['sem']) && !empty(isset($_POST['sem']))
		&& isset($_POST['sid']) && !empty(isset($_POST['sid']))
		&& isset($_POST['tid']) && !empty(isset($_POST['tid']))){ 
	include_once("connection.php"); 
	$sem = $_POST['sem']; 
	$sid = $_POST['sid'];
	$tid = $_POST['tid'];
	$dept = "";
	//starting class
	$sql = "Update class_sub set status = 1 where sem = '$sem' and sid = '$sid'";
	
	//fetching student data to insert in attendance table
	$sql1 = "Select enrollment_id from student where sem='8'";
	$result = mysqli_query($conn, $sql1);
	
	//fetching dept of teacher
	$sql2 = "Select dept from teacher where tid = '$tid'";
	$result1 = $conn->query($sql2);
	if($result1->num_rows>0){
		$row1 = $result1->fetch_assoc();
		$dept = $row1["dept"];		
	}
	$table = "attendance_".$sem."_".$dept;
	$date = date("Y/m/d");
	//inserting students in attendance table
	if ($result->num_rows > 0) {
    // output data of each row
		while($row = $result->fetch_assoc()) {
			$eid = $row["enrollment_id"];
			$sql2 = "Insert into $table values ('$eid', '$sid', '$date',0)"; 
			mysqli_query($conn, $sql2);
		}
		} else {
			echo "0 results";
		}
	if ($conn->query($sql) === TRUE) {			  //
		echo "Class Started";
		
	} else { 
		echo "ErrorInsert"; 
		echo "Error: " . $sql . "<br>" . $conn->error; 
  }
}
?>