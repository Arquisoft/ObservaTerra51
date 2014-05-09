ObservaTerra51
=============

ObservaTerra51

==== Integrantes ==== 
 - Gabriel Calvo García			: UO222522
 - Daniel Lebredo Acebal		: UO227826
 - Fernando Rodríguez Sánchez	: UO219560
 
==== Anotaciones ====

Como rellenar la BD de observaciones:
  - GET /parser (Se descarga una pagina HTML de la OMS y la parsea obteniendo sus observaciones)
  
Como subir archivos a la BD (No funciona correctamente)
  - Habria que estar logueado y acceder a profile

Statistics: No terminado, TODO

==== Distribucion del prototipo ====

Es una aplicacion Play normal, por lo que las pruebas se encuentran en Test y el codigo de la aplicacion en App.

El crawler unicamente se conecta a un link, descarga una pagina HTML y la parsea (podriamos haber añadido mas paginas, un archivo de configuracion con mas URLs...etc, tuvimos que cortar)

Global no se utiliza finalmente...

Aviso: Hay documentacion en el codigo, aunque una necesita una buena limpieza y añadir un JavaDoc decente.
Aviso: Hay un poco de codigo del anterior proyecto de Labra que realmente no se utiliza, este se encuentra en la clase "Prueba", teniamos pensado utilizarlo, pero no ha dado tiempo.



