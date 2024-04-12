Estructura/Funcionamiento de la aplicacion:
 -Navigation menu: Fragment con recycler de numeros, dias, colores y favoritos.
 -En cada elemento Traduccion del recycler: Mantener para borrar (dialogo), clickar para actualizar traduccion.
 -Menu de opciones: Insertar traduccion, preferencias.

 Problemas: Los metodos de Retrofit no funcionan correctamente, fallo en la respuesta. Error 404 al añadir una traduccion por ejemplo. 

 He probado cambiando la ruta de varias maneras, porque los datos que recibe MainActivity (retrofit), son correctos (creo):
 @PUT("{tipo}")
    fun guardarTraduccion(@Path("tipo") tipo: String, @Body traduccion: Traduccion): Call<Traduccion>

@PUT("/{tipo}")
    fun guardarTraduccion(@Path("tipo") tipo: String, @Body traduccion: Traduccion): Call<Traduccion>

@PUT("palabras/{tipo}")
    fun guardarTraduccion(@Path("tipo") tipo: String, @Body traduccion: Traduccion): Call<Traduccion>


Otro problema es con Room, que despues de testearlo, al rato me aparecia un error para migrar la version del schema de la BaseDatosRoom y que hiciera una clase para migrarlo...
 Pero al principio me funcionaba y al ejecutarlo en clase tampoco me daba problemas. Es cuando ejecuto en casa cuando salta una excepción al abrir Favoritos:

 Caused by: java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.

 Creo que al ejecutarlo en otro equipo no va a dar problema pero me gustaria solucionarlo.