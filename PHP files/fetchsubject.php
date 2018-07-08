<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['sem']) && isset($_POST['branch'])){ 
        $sem = $_POST['sem'];
	    $branch = $_POST['branch'];
	
        $query = "SELECT sid FROM class_sub ". 
        " WHERE sem = '$sem' and dept = '$branch' and status = 1"; 
        
        $result = mysqli_query($conn, $query);
        
        if($result->num_rows > 0){
			$row = $result->fetch_assoc();
			$sid = $row["sid"];
			$query1 = "SELECT sname FROM subject ". 
                      " WHERE sid = 'cs801'"; 
        
        $result1 = mysqli_query($conn, $query1);
		if($result1->num_rows>0){
		$row1 = $result1->fetch_assoc();
		
				echo $row1["sname"].",".$sid;
		}
		else{
			echo "error";
		}		//.",".json_encode($row)			
		} 
		else{ 
            echo "Something wrong happened <br/>"; 
        } 
    } 
?>