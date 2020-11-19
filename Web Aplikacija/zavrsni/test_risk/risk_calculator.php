<?php 
  session_start(); 

  if (isset($_GET['logout'])) {
  	session_destroy();
  	unset($_SESSION['username']);
  	header("location: ../index.php");
  }

//must be logged in to save score
	if (isset($_SESSION['username'])) {
		if(isset($_POST['save_score'])){
			$con = mysqli_connect('localhost', 'root', '', 'registration');
			$score = mysqli_real_escape_string($con, $_POST['hidden_score']);
			
			if($score != ""){
				$user = $_SESSION['username'];				
 	  		   	$query = "UPDATE users SET score='$score' WHERE username='$user'";
    			mysqli_query($con, $query);

    			$_SESSION['score'] = $score;
    		}
 	 	}
 	 }
?>
<!DOCTYPE html>
<html>
<head> 

	<title>Osteoporosis Risk Calculator</title>
	<link rel="stylesheet" type="text/css" href="../styles/risk_test_style.css?v=<?php echo time(); ?>">
	<script src="main.js"></script>

</head>

<body>
	<div class="header">
		<h2>Risk Calculator</h2>
		<br>	
		<?php if (isset($_SESSION['username'])) : ?>
	      <span class="logout"><a href="../index.php?logout='1'">Logout</a> </span>
	    <?php endif ?>
		
		<?php if (isset($_SESSION['username'])) : ?>
	    	<p>Logged in as user: <strong><?php echo $_SESSION['username']; ?></strong></p>
	    	<p>Saved score: <strong><?php echo $_SESSION['score']; ?></strong></p>
	    <?php endif ?>	    
	</div>


    <div class="content">
		<form id="quiz" name="quiz" action="risk_calculator.php" method="post">

			<p>1. Age (years)</p>
			<input type="number" name="question1" id="q1" min="1">

			<p>2. Race</p>
			<input type="radio" name="question2" id="q2_1" value="black"> Black (0) <br>
			<input type="radio" name="question2" id="q2_2" value="nonBlack"> Non-Black (5) <br>

			<p>3. Rheumatoid arthritis</p>
			<input type="radio" name="question3" id="q3_1" value="present"> Present (4) <br>
			<input type="radio" name="question3" id="q3_2" value="absent"> Absent (0) <br>

			<p>4. Estrogen</p>
			<input type="radio" name="question4" id="q4_1" value="priorUse"> Prior use (0) <br>
			<input type="radio" name="question4" id="q4_2" value="noPriorUse"> No prior use (1) <br>

			<p>5. Fracture history</p>
			<input type="radio" name="question5" id="q5_1" value="noFracture"> No nontraumatic fractures (0) <br>
			<input type="radio" name="question5" id="q5_2" value="oneFracture"> 1 nontraumatic fracture (4) <br>
			<input type="radio" name="question5" id="q5_3" value="twoFracture"> 2 nontraumatic fractures (8) <br>
			<input type="radio" name="question5" id="q5_4" value="threeFracture"> 3 or more nontraumatic fractures (12) <br>

			<p>6. Weight (kg)</p>
			<input type="number" name="question6" id="q6">
			
			<br><br>
			
			<p>
				<input type="button" id="scoreBtn" name="update_score" class="btn" value="Calculate Risk" onclick="validate();check();">

				<?php if (isset($_SESSION['username'])) : ?>
					<input type="submit" name="save_score" class="btn" value="Save Result">
			</p>
					<input type="text" name="hidden_score" style="visibility: hidden;">	
		    	<?php endif ?>
	    	
		</form>

	</div>


	

	<div class="footer">
		<div id="after_error">
			<p id="error_message"></p>
		</div>
		<div id="after_submit" style="visibility: hidden;">
			<p>Your score</p>
			<p id="number_score"></p>
		</div>

		<table>
          <tr>
            <td align="right">
              0-6 Points:
            </td>
            <td align="left">
              Low Risk
            </td>
          </tr>
          <tr>
            <td align="right">
              7-15 Points:
            </td>
            <td align="left">
              Moderate Risk
            </td>
          </tr>
          <tr>
            <td align="right">
              16-50 Points:
            </td>
            <td align="left">
              High Risk
            </td>
          </tr>
        </table>

		<p><a href="../index.php">Go back</a></p>
	</div>

</body>
</html>