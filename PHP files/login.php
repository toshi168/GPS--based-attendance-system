<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['txtUsername']) && isset($_POST['txtPassword']) ) { 
        $username = $_POST['txtUsername'];
        $password = $_POST['txtPassword'];
        
        $query1 = "SELECT * FROM user ". 
        " WHERE user_id = '$username' AND password = '$password' AND user_type='admin'"; 
		
		$query2 = "SELECT * FROM user ". 
        " WHERE user_id = '$username' AND password = '$password' AND user_type='student'"; 
		
		$query3 = "SELECT * FROM user ". 
        " WHERE user_id = '$username' AND password = '$password' AND user_type='teacher'"; 
        
        $result1 = mysqli_query($conn, $query1);
		$result2 = mysqli_query($conn, $query2);
        $result3 = mysqli_query($conn, $query3);
		
        if($result1->num_rows > 0){
            
                echo "admin success"; 
                exit; 
             
        } 
		else if($result2->num_rows > 0){
            
                echo "student success"; 
                exit; 
             
        }
		else if($result3->num_rows > 0){
            
                echo "teacher success"; 
                exit; 
             
        }
		
		else{ 
            echo "Login Failed"; 
        } 
    } 
?>