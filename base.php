
<?php
$mysqli = new mysqli('localhost','root','','servicios');
$mysqli ->query("SET NAMES 'utf8'");

if($mysqli->connect_error){
	die('Error de ('.$mysqli->connect_error.')'.$mysqli->connect_error);
}

?>
