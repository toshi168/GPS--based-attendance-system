<?PHP 
    include_once("connection.php"); 
    if( isset($_POST['sem']) && isset($_POST['dept']) 
		&& isset($_POST['eid']) && isset($_POST['sid'])
		&& isset($_POST['lati']) && isset($_POST['longi'])){ 
		
        $sem = $_POST['sem'];
	    $dept = $_POST['dept'];
		$eid = $_POST['eid'];
		$sid = $_POST['sid'];
		$lati = $_POST['lati'];
		$longi = $_POST['longi'];
		
		function haversineGreatCircleDistance($latitudeFrom, $longitudeFrom, $latitudeTo, $longitudeTo, $earthRadius = 6371000)
	{
	// convert from degrees to radians
		$latFrom = deg2rad($latitudeFrom);
		$lonFrom = deg2rad($longitudeFrom);
		$latTo = deg2rad($latitudeTo);
		$lonTo = deg2rad($longitudeTo);

		$latDelta = $latTo - $latFrom;
		$lonDelta = $lonTo - $lonFrom;

		$angle = 2 * asin(sqrt(pow(sin($latDelta / 2), 2) +
			cos($latFrom) * cos($latTo) * pow(sin($lonDelta / 2), 2)));
		return $angle * $earthRadius;
	}
	
	$query = "SELECT latitude, longitude FROM class ". 
        " WHERE sem = '$sem' and dept = '$dept'"; 
        
        $result = mysqli_query($conn, $query);
		$row = $result->fetch_assoc();
		//coordinates of class
		$clat = $row["latitude"];
		$clon = $row["longitude"];


	if( haversineGreatCircleDistance($clat,$clon,$lati,$longi)<=1000){ 
		
		$table = "attendance_".$sem."_".$dept;
		$date = date("Y/m/d");
        $sql = "update attendance_8_cse set attend = 1 where enrollment_id = '$eid'".
		" and sid = '$sid' and date = '$date'"; 
        
        if($conn->query($sql) === TRUE){
			echo "Attendance Marked";
			} 
		else{ 
            echo "Something wrong happened <br/>"; 
        }
	}
	else{
		echo "You are not in class";
	}
	
    } 
?>

