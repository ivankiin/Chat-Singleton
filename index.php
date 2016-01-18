<!DOCTYPE html>
<html>
<head>
	<title>Generador Ivan</title>
</head>
<body>

<h1>SERVICIOS A GENERAR</h1>
<?php include 'base.php' ?>






<table border="1px;">

<tr>
	<td>Servicios</td>
	<td>Datos</td>
	<td>Ejemplos</td>
</tr>
<tr>
	<td><select id="selector" onchange="cambialo()">
	<option value="1">SPA
	<option value="2">MUSICA
	<option value="3">HOTELES
	</select>
	</td>
</tr>

<tr>

<td>
<p id="elegido">
</td>
<td></td>	
</tr>

</table>










<script>
function cambialo() {
    var x = document.getElementById("selector").value;

    if (x==1) {
    
document.getElementById("elegido").innerHTML = 
'<select name="sp"><option>PAQUETES</option><option>FACIALES</option><option>TERAPIAS</option></select>';
}

if (x==2) {
    
document.getElementById("elegido").innerHTML = 
'<select name="mus"><option>ARTISTAS</option><option>CANCIONES</option><option>DURACION</option><option>PRECIO</option></select>';
}
if (x==3) {
    
document.getElementById("elegido").innerHTML = 
'<select name="hot"><option>NOMBRES</option><option>DIRECCION</option><option>DIA DE RESERVACION</option></select>';
}


    
}
</script>





<!---
<td>
	<select name="datos" >
<?php $mysqli->real_query("SELECT facial FROM faciales;"); 
$query=$mysqli->store_result(); 
while($row=$query->fetch_assoc()){ echo "<option>".$row['facial']."</option>"; } 
?> 
</select>
</td>
-->



</body>
</html>
