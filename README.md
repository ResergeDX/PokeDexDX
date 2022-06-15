# Proyecto03_RezaSergio PokeDex
Proyecto: Pokedex implementando RecyclerView y la API [PokéAPI](https://pokeapi.co/)

Este proyecto consiste en la obtención de información, por medio de PokeAPI, de los 151 pokemon iniciales (Kanto). La información se desplegará al seleccionar el Pokemon de la lista creada.

## Visualización y Descripción
### Lista de Pokémon (1 al 151)
La vista de la actividad mostrará de forma descendente los 151 pokémon. Mostrando nombre, sprite base y número en dónde se encuentra en la pókedex original.
<p align="center">
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/ListaBase.jpg" alt="img" width="300" height="500" />
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/ListaBase1.jpg" alt="img" width="300" height="500" />
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/ListaBase2.jpg" alt="img" width="300" height="500" />
</p>

Cada elemento de la lista es seleccionable. Al elegir un pokémon se enviará a la actividad de la información especifica.

### Detalle de Pokémon Seleccionado
La información a desplegar se dividirá en 4 seccioneses:

### Imagen
La primera sección contiene
* Nombre
* Sprite del Pokémon
* Botón: Para cambiar el Sprite del Pokémon, de Estado base a Variocolor (Shiny) y viceversa.

### Información Base
La segunda sección tiene la siguiente información
* Tipos elementales: Se mostrarán los simbolos del o los tipos del Pokémon. Al presionar la imagen se desplegará un mensaje con el nombre del tipo
* Experiencia Base
* Altura: Mostrado en Metros
* Peso: Mostrado en Kilogramos

### Estadisticas
La tercera sección se tiene la información de las estadisticas base mostradas en una lista. Cada elemento tiene el nombre de la estadistica, el valor y una representación gráfica.

### Habilidades
La última sección tiene enlistadas las habilidades que puede tener el pokemon. Existen 2 tipos:
* La habilidad normal: Se mostrará con una carta en color Azul.
* Habilidad Oculta: Habilidad especial del Pokémon. Este se mostrará con una carta en color Rojo.

### Visualización en Español
<p align="center">
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/DetalleEsp1.jpg" alt="img" width="300" height="500" />
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/DetalleEsp2.jpg" alt="img" width="300" height="500" />
  
</p>
<p align="center">
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/S1.jpg" alt="img" width="300" height="500" />
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/S2.jpg" alt="img" width="300" height="500" />
  
</p>

### Visualización en Inglés
<p align="center">
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/DetalleInglés.jpg" alt="img" width="300" height="500" />
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/DetalleInglés1.jpg" alt="img" width="300" height="500" />
</p>
<p align="center">
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/DetalleInglés2.jpg" alt="img" width="300" height="500" />
  <img src="https://github.com/ResergeDX/PokedexCM/blob/master/about/DetalleEsp0.jpg" alt="img" width="300" height="500" />
</p>