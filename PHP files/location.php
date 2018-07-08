<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['sem']) && isset($_POST['dept'])){ 
        $sem = $_POST['sem'];
	    $dept = $_POST['dept'];
	
        $query = "SELECT latitude, longitude FROM class ". 
        " WHERE sem = '$sem' and dept = '$dept'"; 
        
        $result = mysqli_query($conn, $query);
        
        if($result->num_rows > 0){
			$row = $result->fetch_assoc();
			echo json_encode($row);
			} 
		else{ 
            echo "Something wrong happened <br/>"; 
        } 
 //   } 
?>