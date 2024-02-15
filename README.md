
Departamento de Gestión de Proyectos y Sistemas.
Estructuras de datos (BPTSP06).
Trimestre 2324-2.



Proyecto: Algoritmo de optimización basado en el comportamiento de colonias de hormigas.

Preliminares
	En el siguiente proyecto, usted tendrá que realizar una investigación documental que le permita obtener información sobre el contexto del problema. En tal sentido, se sugiere que comience por realizar la siguiente lectura relativa a grafos, pero tome en cuenta que es solo un recurso inicial que debe ser complementado con la búsqueda autónoma de información por parte de los integrantes del equipo de trabajo:  
https://drive.google.com/file/d/1pSpSOFGz_jAzqMOwNI-cPbMGj18aqHYL/view?usp=drive_link

El problema

	La Optimización de Colonia de Hormiga, agrupa un conjunto de algoritmos que se inspira en el comportamiento de las colonias de hormigas para resolver problemas discretos de optimización. El pionero en esta área fue Marco Dorigo, quien en 1992 introdujo la primera propuesta de algoritmo.

	Al observar el diagrama anterior, la pregunta a responder es: ¿Cómo hacen las hormigas para terminar recorriendo el camino más corto entre el nido y la comida? El secreto es que las hormigas marcan su trayectoria con feromonas, lo que le indica a sus compañeras un rastro que es más fuerte en la medida en que la trayectoria es más frecuentada. Las feromonas se acumulan más en trayectorias cortas que en las largas, por lo que, después de un tiempo, todas las hormigas terminan transitando la trayectoria más corta, al ser esta la más atractiva.
En este comportamiento de las hormigas, se han inspirado muchos algoritmos de optimización, aplicados a problemas de enrutamiento de redes y en los sistemas de transportes urbanos. La primera propuesta (Sistema Hormiga o Ant System) tenía como objetivo resolver el problema del viajero que consiste en encontrar el camino hamiltoniano más corto en un grafo completo. El problema del viajero (TSP o travelling salesman problem, por sus siglas en inglés) puede ser modelado como un grafo ponderado no dirigido, en el cual los vértices son las ciudades y los caminos entre ellas son las aristas que en este caso están ponderados, pues cada arista tendrá al menos un valor que corresponde a la distancia entre las dos ciudades que conecta. Tome en cuenta que una arista puede ser multiponderada.
	En el algoritmo de optimización llamado Sistema Hormiga (de tipo ant-cycle), se realizan los siguientes pasos:
1. Se inicializan los valores de la simulación. Al principio cada camino entre las ciudades tiene una cantidad de feromona inicial. Además, las hormigas son ubicadas en la ciudad inicial (nido). Debe tomarse en cuenta que cada hormiga debe irse moviendo hasta alcanzar la ciudad final (comida). El valor inicial de la cantidad de feromonas en los caminos puede ser calculado de la siguiente manera: τ=1/m, donde τ es la cantidad inicial de feromonas y m es la cantidad de nodos o ciudades.
2. Acciones por ciclo. Una vez que se inicializan los parámetros generales, comienza el funcionamiento del sistema AS (Ant System). Para lo cual, se lleva a cabo la cantidad de ciclos especificada al inicio. Tome en cuenta que un ciclo en el sistema AS estará conformado por la cantidad de iteraciones necesarias para que cada hormiga llegue al destino (comida). Por otra parte, en una iteración, cada hormiga se mueve de una ciudad a otra, de manera que este proceso se repite hasta alcanzar la comida (se completa un ciclo). 
2.1. Selección del camino a seguir. Por cada hormiga, debe determinarse la siguiente ciudad a la cual va a moverse. Esto se logra de manera probabilística a través de la siguiente expresión:





Donde:
α es el grado de importancia de la feromona. Se recomienda un valor de 1.
β representa el grado de visibilidad de la ciudad. (α y β son parámetros propios del algoritmo). Se recomienda un valor de 2.
u representa a una de las ciudades que pertenecen al conjunto de las ciudades adyacentes a la ciudad r en la que la hormiga k puede visitar.
η es un factor que usualmente se calcula con la expresión η=Q/drs , donde drs es la distancia entre las ciudades r y s. Q es, en algunos casos, considerada como el aprendizaje que usualmente es 1. En otras palabras, η es el inverso de la distancia drs, y es conocido como el factor de visibilidad del camino (en algunas bibliografías se puede conseguir como información heurística o preferencia heurística).
Mk es el conjunto de las ciudades que pueden ser visitadas por la hormiga k. Una ciudad no puede ser visitada dos veces por una hormiga en una misma iteración.
	Tome en cuenta que una vez calculadas las probabilidades de cada camino disponible, es necesario generar un número aleatorio para saber cuál camino seguirá la hormiga. 
2.2. Actualización de la cantidad de feromonas. Al finalizar un ciclo, todas las hormigas deberían haber llegado al destino o en su defecto, haber quedado atrapada en una “calle ciega”. Además, se deben actualizar los niveles de feromonas en cada camino (arista). Dicha actualización se obtiene, por una parte, gracias al incremento de feromona aportado por las hormigas que transitaron el camino y por la evaporación que esta sufre.  
2.2.1. Actualización por incremento. Una vez que una hormiga pase por una arista que conecta a dos ciudades, deja su rastro de feromonas. La actualización de la cantidad de feromonas en una arista se calcula de la siguiente manera:











Donde:
m es el número de hormigas.
r,sk=QLk Es calculado para cada hormiga k que ha transitado ese camino. Si una hormiga ha transitado el camino cr,sk entre las ciudades r y s, entonces Q es una constante (usualmente igual a 1) y Lk es la longitud del recorrido realizado por la hormiga.
2.2.2 Actualización por evaporación: una vez que todas las hormigas han culminado su viaje (es decir, han llegado a la ciudad destino o alimento), se actualiza la cantidad de feromonas de todos los caminos entre las ciudades mediante la siguiente ecuación:
τr,s ←(1−ρ)∗τr,s
Donde ρ ⊆ (0,1] es el factor de evaporación. Usualmente es 0.5. Es en este momento cuando termina la iteración; es decir, cada hormiga debe irse moviendo hasta alcanzar el alimento y una vez que lo alcanza, no se sigue moviendo, pues no se está considerando el camino de regreso como una forma de simplificar el problema. Es importante considerar la posibilidad de que una hormiga llegue a una calle ciega, es decir, que no pueda moverse debido a que ya ha visitado las ciudades que se comunican con la ciudad en la que está ubicada. 
	Todo lo anterior se resume a la siguiente expresión:
(t)r,s=(1-)*(t-1)r,s+k=1k=mr,sk
Requerimientos funcionales:
Nueva simulación: el usuario podrá iniciar otra, manteniéndose la estructura del grafo en el caso de que no se haya realizado una simulación anterior; sin embargo, debe ser opcional el agregar o eliminar ciudades, pero únicamente antes de iniciar una simulación. Se deberán indicar los otros valores iniciales, a saber:
1. Número de ciclos a realizar. Por cada ciclo, cada hormiga debe haber terminado su recorrido, es decir, la hormiga debe haber llegado a su destino.
2. Número de hormigas que formarán parte de la simulación.
3. Valores de α, β y ρ. El usuario podrá utilizar valores por defecto o indicar otros.
4. Indicar ciudad de partida y ciudad de llegada.
Agregar ciudad. Tome en cuenta que una ciudad debe conectarse con otras ciudades y que dicho camino corresponde a una arista no dirigida. Cuando se agrega una ciudad, se debe indicar las distancias a las otras ciudades. Las simulaciones se podrán realizar con un mínimo de 4 ciudades y hasta un máximo de 20 ciudades.
Eliminar ciudad. Lo que implica que se deben eliminar los caminos conectados a esta. Esto solo puede ocurrir antes de comenzar las iteraciones.
Guardar grafo. El programa debe poder guardar la estructura del grafo en un archivo de texto, de manera que al salir del programa, dicha información no se pierda y pueda ser cargada nuevamente si lo desea el usuario.
Cargar grafo. El usuario podrá cargar un grafo desde un archivo de texto, antes de comenzar una simulación.
Al final cada ciclo. Se requiere que la interfaz muestre:
	a. Cantidad de hormigas que están en la simulación.
b. El camino identificado como el más óptimo y por cada hormiga, el recorrido realizado y su distancia correspondiente. Lo anterior debe ser mostrado de manera gráfica. El camino más óptimo es el de menor longitud (de entre los caminos recorridos por las hormigas).
	c. El valor de τr,s de cada camino (arco entre cada ciudad).
Al finalizar la simulación.  Debe presentarse grafo correspondiente y se debe indicar el camino óptimo entre la ciudad de inicio y la de destino. Lo anterior utilizando una librería que permita la representación gráfica de del grafo. Se sugiere utilizar GraphStream (https://graphstream-project.org/).
Condiciones:

- Proyectos que sean iguales o parecidos, serán calificados con 0 (cero).
- Los programas que no corran, serán calificados con 0 (cero).
- La solución debe ser implementada con base en un grafo, que a su vez puede ser implementado mediante una matriz de adyacencia o una lista de adyacencia. 
- Puede utilizar cualquier otra estructura auxiliar de ser necesario. Sin embargo, NO podrá utilizar librerías para la implementación del tipo de dato abstracto, solo podrá utilizar librerías para lo relativo a la representación gráfica del grafo. Deberán indicar claramente mediante documentación interna, dónde se encuentran las estructuras de la solución lógica y sus relaciones con las librerías de visualización de grafos utilizada.
- El programa debe ser implementado en JAVA.
- La aplicación debe ofrecer una interfaz gráfica al usuario para poder ser aceptado.
- El proyecto debe estar correctamente documentado mediante Javadoc (documentación interna).
- El programa debe poder cargar un archivo de texto para la lectura de datos. Para ello, es requerido el uso del componente JFileChooser.
- Debe documentar el proyecto con Javadoc.
- Los proyectos podrán ser sometidos a defensa, es decir, el facilitador convocará al equipo para una revisión.
- Los equipos de trabajo deberán utilizar GitHub para el control de versiones y facilitar el trabajo en equipo de manera remota. De esta forma, podrán comenzar a crear su portafolio de trabajos, elemento que puede ser importante a la hora de buscar trabajo. En el registro se deberá reflejar la participación activa y significativa de los integrantes
- Los equipos pueden tener como máximo 3 personas.
Criterios de evaluación
Funcionalidad: Capacidad para proporcionar las funcionalidades que satisfacen las necesidades explícitas e implícitas bajo unas ciertas condiciones. (60%)
Adecuación: El programa ofrece todas funcionalidades que respondan a las necesidades, tanto explícitas (contenidas en el documento descriptivo del proyecto) como implícitas; entendiendo como necesidades implícitas, aquellas que, no estando descritas en el documento, surgen como resultado de un concienzudo análisis del problema planteado y que aseguran el correcto funcionamiento del programa.
Exactitud: El programa genera los resultados o efectos correctos o acordados, con el grado necesario de precisión.
Fiabilidad: Capacidad para mantener un nivel especificado de prestaciones cuando se usa bajo ciertas condiciones.
Madurez: El programa no presenta fallas originadas por errores de programación, análisis o diseño. (10%)
Tolerancia a fallos: El programa responde adecuadamente al manejo inadecuado del usuario; es decir, mantiene su correcto funcionamiento aun cuando el usuario introduzca datos erróneos o manipule inadecuadamente las interfaces de usuario. (10%)
Usabilidad: Capacidad del proyecto para ser entendido, aprendido, usado y al mismo tiempo, ser atractivo para el usuario, cuando se usa bajo condiciones específicas.
Comprensibilidad: El programa ofrece una interfaz de fácil comprensión, facilitando su aprendizaje y correcta utilización. El programa emite mensajes de alerta cuando se introducen valores erróneos. Existen elementos informativos que indican al usuario como operar el programa. (5%)
Capacidad de ser atractivo: El diseño de la interfaz de usuario, esto es: disposición de controles, esquema de colores, utilización de cajas de diálogo y demás elementos gráficos; es atractivo para el usuario. (5%)
Eficiencia: Capacidad para proporcionar prestaciones apropiadas, relativas a la cantidad de recursos utilizados, bajo condiciones determinadas.
Estructuras de datos: Utiliza eficientemente las estructuras de datos para la solución del problema. (10%)

Anexo:
 El formato del archivo de texto plano que puede ser utilizado para cargar la estructura del grafo es la siguiente (tome en cuenta que el grafo representado en el archivo es referencial y que el revisor podrá utilizar grafos más complejos):

ciudad
1
2
3
4
5
6
7
aristas
1,2,5
1,3,3.1
1,6,5.2
6,3,3.2
6,5,4.7
3,2,4.9
2,7,5.2
3,7,3
3,5,6
7,4,4.8
5,4,5.5

----------------------------------------------------------------------------------------------------------------------------
Donde la primera ciudad es el nido y la última es la comida.
Nota: El formato de las aristas responde a la estructura: origen, destino, distancia.
