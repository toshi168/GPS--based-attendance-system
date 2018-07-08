<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtEnrollmentID']) ) { 
        $username = $_POST['txtEnrollmentID'];
        
        $query = "SELECT name, sem, branch FROM student ". 
        " WHERE enrollment_id = '$username' "; 
        
        $result = mysqli_query($conn, $query);
        
        if($result->num_rows > 0){
                
				$row = $result->fetch_assoc();
				echo json_encode($row);
			} 
		else{ 
            echo "Something wrong happened <br/>"; 
        } 
    } 
?>