# language: es
Característica: Gestión de nuevos recursos

Escenario: Añadir recursos como anonimo
  Añadir recursos como anonimo

    Dada una lista de recursos inicial:
      | name 	| access_level |
      
    Cuando añado un recurso "MiPais" a la lista de recursos con nivel de acceso 5
    Entonces obtengo la nueva lista:
    	|	name	|	access_level	|
    	|	MiPais	|	5				|
		    
Escenario: Añadir recursos como usuario privilegiado
	Añadir recursos como usuario privilegiado
    Dada una lista de recursos inicial:
      | name 	| access_level |
      
    Cuando añado un recurso "MiPais" a la lista de recursos con nivel de acceso 10
    Entonces obtengo la nueva lista:
    	| name 		| access_level 	|
    	|	MiPais	|	10			|
    
    
     
    
