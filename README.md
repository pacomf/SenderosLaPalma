# [Patea La Palma](http://tiny.cc/pateaLaPalma)

<p align="center">
    <a href="http://tiny.cc/pateaLaPalma" target="_blank">

        <img alt="App" src="https://raw.githubusercontent.com/pacomf/SenderosLaPalma/develop/android/PateaLaPalma/app/src/main/res/drawable-xxhdpi/ic_launcher.png?token=ABGEZrEry1pb6u5I3rrrDKHtw9f9tRK-ks5VRyd-wA%3D%3D" width="210"/>

    </a>
    <a href="https://play.google.com/store/apps/details?id=com.jelcaf.pacomf.patealapalma" target="_blank">
        <img alt="Descargar Patea La Palma" src="http://www.eleconomista.es/CanalPDA/files/logo_google_play.jpg" height="200" width="180" />
    </a>
</p>

La aplicación es el primer **Recomendador Inteligente de Senderos** de la isla de La Palma. De forma que cualquier turista (app multilenguaje), en la palma de su mano pueda tener una herramienta tan potente como para recomendar el mejor sendero que se adecua a sus gustos y necesidades en cada momento. La aplicación cuenta con un algoritmo inteligente de recomendación de senderos que aprende de los gustos del usuario de forma evolutiva. La inteligencia artificial desarrollada selecciona una serie de senderos acorde a las preferencias descritas por el usuario. De manera que mediante una serie de preguntas fáciles, con una interfaz gráfica sencilla y muy visual, el usuario podrá saber qué Sendero le conviene hacer. Esta recomendación también se aclimata a Grupos de Personas, por lo que en función de una serie de premisas (cuántas personas irán a realizar el sendero, si irán o no niños, etc.) la aplicación buscará y recomendará los mejores senderos bajo estos requisitos.

El Recomendador aconsejará al usuario la cantidad de agua que debe llevar para la realización de dicho sendero, además de que le indicará en un mapa la ruta del sendero completa, así como los puntos en el sendero donde hay una fuente de agua potable.

El Recomendador, aconsejará al usuario que ropa llevar al Sendero en cuestión en función del tiempo que se prevé que haga el día que el usuario tiene previsto realizar el sendero.

El usuario podrá ver toda la información necesaria y relevante del sendero (distancia, dificultad, etc.), saber como llegar a él desde su posición actual, si es circular o no, y mucha otra información que hace de la utilización de la app una experiencia imperdible para los amantes del senderismo.

Además, el usuario puede valorar los senderos, dejar comentarios y subir fotos en la aplicación móvil, por lo que la dimensión Social juega un papel fundamental.

La información que se visualiza del Sendero, proviene mayoritariamente de dos dataset del Portal de Open Data La Palma. Los datasets utilizados han sido:
Red de Senderos
Puntos de Interés

Este proyecto está desarrollado de forma modular, por lo que si se quiere la inclusión de nuevos datos acerca de los senderos que provengan de otra fuente, el propio sistema será capaz de introducirlos de forma rápida y sencilla. Por lo que mejorar los datos o incluir datos nuevos o más detallados, sería un proceso simple que permitiría seguir usando la aplicación de la misma manera.

## Tecnologías Utilizadas

<a href="http://tiny.cc/pateaLaPalma" target="_blank">
  <p align="center">
    <img alt="App" src="https://raw.githubusercontent.com/pacomf/SenderosLaPalma/develop/android/PateaLaPalma/app/src/main/res/drawable-xxhdpi/ic_launcher.png?token=ABGEZrEry1pb6u5I3rrrDKHtw9f9tRK-ks5VRyd-wA%3D%3D" width="210"/>
  </p>
</a>

El proyecto se divide en dos partes (Tecnológicamente hablando):
* Parte Cliente (Aplicación Android Nativa): para el cliente, la app que será el artefacto final que manejará el usuario, las tecnología usada ha sido el SDK Nativo de Android, que utiliza como lenguaje de programación Java 6. Para el desarrollo de la aplicación se ha usado el IDE Android Studio que utiliza como motor de construcción de la aplicación a Gradle. Los datos locales, se almacenan por la app usando MySQL en Android. El motor de mapas usados en la app, es Open Street Map, ya que la aplicación queríamos que fuese lo más Open Source posible, y que pudiera funcionar con el tiempo de forma offline y gratuita. Para loguear a usuarios, y no tener que manejar datos confidenciales, hacemos uso del SDK de Facebook para Android. Para el alojamiento de las fotos, hacemos uso de la API de IMGUR. Para conocer el tiempo de un determinado lugar en tiempo real se hace uso de la API de OpenWeather.

* Parte Servidor (Servidor Node): para la parte del servidor, que contendrá toda la información centralizada de usuarios, senderos y demás datos relevantes que se use en la app móvil, se ha usado un fullstack de JavaScript. Concretamente se ha usado como framework del backend a Node.js. Se han implementado Web Services de tipo RESTful para la comunicación entre la app y el servidor. Para ello se ha utilizado como framework a Express.js. Como base de datos del lado del servidor, y para que el proyecto fuera escalable, hemos usado MongoDB, una base de datos no relacional. El servidor está alojado en una MicroInstacia de Amazon, bajo una cuenta de Amazon EC2.

## Licencia

The MIT License (MIT)

Copyright (c) 2015 Jorge y Paco

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
