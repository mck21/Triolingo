# Triolingo - Aplicación de Traducciones

Aplicación Android de traducciones simples que utiliza **Room** y **Retrofit** para almacenar y sincronizar datos, ejecutada en `json-server-master` con un archivo `db.json`.

## Stack Tecnológico

![Kotlin](https://img.shields.io/badge/kotlin-%23564FCC.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/android%20studio-%233DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)


- **Room**: Gestión de base de datos local.
- **Retrofit**: Llamadas API para sincronización.
- **JSON Server**: Simulación de API REST con `db.json`.
  
## Estructura de la Aplicación

### Menú de Navegación

<p align="center">
  <img src="https://github.com/mck21/Triolingo/assets/122030012/dba8a100-26fc-45f1-b744-cc8abf1f7588" alt="Menu de Navegación" />
</p>

Fragment con un recycler para categorías como números, días, colores y favoritos.

### Interacciones en RecyclerView

- **Eliminar Traducción**: Mantén pulsado para borrar.
- **Actualizar Traducción**: Toca el elemento para editar.

<p align="center">
  <img src="https://github.com/mck21/Triolingo/assets/122030012/2fa12667-1d66-4a87-a28d-fdc3d98c3195" alt="Eliminar/Actualizar" />
</p>

### Menú de Opciones

Incluye insertar traducción y configurar preferencias.

<p align="center">
  <img src="https://github.com/mck21/Triolingo/assets/122030012/c2edcc8a-b655-4672-9ae2-f4ed63d69d33" alt="Menu de Opciones" />
</p>

## Instalación

### Requisitos previos

- [Android Studio](https://developer.android.com/studio) instalado.
- [Kotlin](https://kotlinlang.org/) configurado como lenguaje principal.
- [JSON Server](https://www.npmjs.com/package/json-server) instalado para servir el archivo `db.json`.

### Pasos de Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/mck21/Triolingo.git
    ```
2. Configura JSON Server con el archivo `db.json` adjunto.
3. Conecta la app a la URL del servidor en el archivo de configuración de **Retrofit**.
4. Ejecuta la aplicación en un emulador Android desde Android Studio.

## Licencia

Este proyecto está bajo la licencia MIT.
