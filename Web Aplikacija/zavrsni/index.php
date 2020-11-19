<?php 
  session_start(); 


  if (isset($_GET['logout'])) {
  	session_destroy();
  	unset($_SESSION['username']);
  	header("location: index.php");
  }
?>
<!DOCTYPE html>
<html>
  <head>
  	<title>Home</title>
  	<link rel="stylesheet" type="text/css" href="styles/home_style.css?v=<?php echo time(); ?>">
  </head>
  <body>

    <div class="header">
    	<h2>Home Page</h2>
    </div>
    <div class="content">

      	<?php if (isset($_SESSION['username'])) : ?>
          <span class="logout"><a href="index.php?logout='1'">Logout</a></span>
          <br><br><br>
        <?php endif ?>


        <!-- notification message -->
      	<?php if (isset($_SESSION['success'])) : ?>
          <div class="error success" >
          	<h3>
              <?php 
              	echo $_SESSION['success']; 
              	unset($_SESSION['success']);
              ?>
          	</h3>
          </div>
      	<?php endif ?>    

        
        <!-- logged in user information -->
        <?php if (isset($_SESSION['username'])) : ?>
          <p>Logged in as user: <strong><?php echo $_SESSION['username']; ?></strong></p>
          
          <?php if (isset($_SESSION['score'])) : ?>
            <p>Saved score: <strong><?php echo $_SESSION['score']; ?></strong></p>

            <?php if($_SESSION['score'] <= 6) : ?>
              <p><strong>Based on your score you have a low osteoporosis risk.</strong></p>
            <?php elseif($_SESSION['score'] > 6 && $_SESSION['score'] <=15) : ?>
              <p><strong>Based on your score you have a moderate osteoporosis risk.</strong></p>
            <?php else: ?>
              <p><strong>Based on your score you have a high osteoporosis risk.</strong></p>
            <?php endif ?>
            <br><br>
          <?php endif ?>
        <?php endif ?>


        <p>Welcome to the osteoporosis risk calculator.</p>
        <p>To take the test please click the "Take test" button.</p>
        <br>    


        <?php if (!isset($_SESSION['username'])) : ?>
          <table>
            <tr>
              <th>
                <a href="user_authentication/login.php">Log in</a>
                <a href="user_authentication/register.php">Sign up</a>
              </th>
            </tr>
          </table>
          
          
        <?php endif ?>

        <p><a href="test_risk/risk_calculator.php">Take test</a></p>        

    </div>
  		
  </body>
</html>