# language: es
Característica: Gestión de permisos

Escenario: Ver recursos como anonimo
  Obtener lista de recursos visibles como anonimo

    Dada una lista de recursos:
      | name 	| access_level |
      | Spain   | 3,5   	|
      | Rusia  	| 7,5   	|
      | EEUU   	| 10   		|
      | Chile   | 1     	|
    Cuando calculo la lista de recursos para nivel de acceso 5
    Entonces obtengo la lista:
    	|	name	|	access_level	|
    	|	Spain	|	3,5				|
		|	Chile	|	1				|
		    
Escenario: Ver recursos como administrador

    Dada una lista de recursos:
      | name 	| access_level |
      | Spain   | 3,5   	|
      | Rusia  	| 7,5   	|
      | EEUU   	| 10   		|
      | Chile   | 1     	|
    Cuando calculo la lista de recursos para nivel de acceso 10
    Entonces obtengo la lista:
    	| name 		| access_level |
      	| Spain   	| 3,5   	|
      	| Rusia  	| 7,5   	|
      	| EEUU   	| 10   		|
      	| Chile   	| 1     	|
    
    
     
    
