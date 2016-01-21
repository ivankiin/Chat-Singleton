<!DOCTYPE html>
<html>
<head>
	<title>Generador Ivan</title>

<script type="text/javascript" src="procesos.js"></script>
</head>
<body>

<h1>SERVICIOS A GENERAR</h1>


<?php include 'base.php' ?>


<?php


$mysqli->real_query("SELECT facial FROM faciales where id_facial=1;"); 
$query=$mysqli->store_result(); 
while($row=$query->fetch_assoc()){ echo "<option>".$row['facial']."</option>"; } 
?>






<table border="1px;">
<form name="formularioContacto" method="get" action="#">

<tr>
	<td>Servicios</td>
	<td>Tipo de dato</td>
	<td>Formato de datos</td>
	<td>Selecciona los datos</td>
	<td>Ejemplo</td>

</tr>


<tr>
<td>
	
	<select id="selector" onchange="cambialo()">
	<option value="1">SPA
	<option value="2">MUSICA
	<option value="3">HOTELES
	</select>	
	
</td>

<td>
<p id="elegido">

</td>
	
	<td>
<input type="radio" name="eleccion" value="1">Lista de datos individuales (1)</input></br>
<input type="radio" name="eleccion" value="2">Lista de datos doble (2)</input></br>	
<input type="radio" name="eleccion" value="3">Lista con todas las opciones (3+)</input>

	</td>

	<td>
	<div id="datos" >
	</td>

</tr>




</form>
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

<script type="text/javascript">

window.onload = function () {

                /* Variables globales (por estar declaradas sin var) */

                casillaDatos = document.getElementById('datos'); //Nodo donde vamos a mostrar los datos

                radioelegido = document.getElementsByName("eleccion"); //Nodos radio buttons

                checkboxElements = new Array();

               

                var elementosDelForm = document.getElementsByTagName('input'); //Elementos input

                

                for(var i=0; i<elementosDelForm.length;i++) {

                if (elementosDelForm[i].type == 'radio') {elementosDelForm[i].addEventListener("click", actualizarDatos);}

                else {elementosDelForm[i].addEventListener("change", actualizarDatos);}

                if (elementosDelForm[i].type == 'checkbox') {checkboxElements.push(elementosDelForm[i]);}

                }

                for (var i=0; i<elementosSelect.length;i++) {elementosSelect[i].addEventListener("change", actualizarDatos);}

}

 

function actualizarDatos() {



var radioButSelValue = '';

for (var i=0; i<radioelegido.length; i++) {
	if (radioelegido[i].checked == true) { 
		radioButSelValue= radioelegido[i].value;} 
	}




	



}

</script>

<script type="text/javascript">
	var checkBoxSel = new Array();

for (var i=0; i<checkboxElements.length;i++) {

if (checkboxElements[i].checked ==true) {
	checkBoxSel.push(checkboxElements[i].name);
}

}
</script>




</body>






</html>
