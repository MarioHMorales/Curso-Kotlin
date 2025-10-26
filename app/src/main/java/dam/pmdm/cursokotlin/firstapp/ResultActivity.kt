package dam.pmdm.cursokotlin.firstapp
// Indica el paquete donde se encuentra esta clase (organiza el código del proyecto)

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dam.pmdm.cursokotlin.R
// Importaciones necesarias para usar componentes de Android y acceder a los recursos del proyecto

class ResultActivity : AppCompatActivity() {
    // Declaramos la clase ResultActivity, que representa una nueva pantalla (actividad)
    // Hereda de AppCompatActivity para tener compatibilidad con versiones anteriores de Android

    override fun onCreate(savedInstanceState: Bundle?) {
        // Método que se ejecuta cuando la actividad se crea (se muestra en pantalla)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        // Enlaza esta clase con el diseño XML llamado activity_result.xml

        // Buscamos en el diseño el TextView con el ID "tvResult" y lo guardamos en una variable
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Recuperamos el valor que se envió desde la actividad anterior (FirstAppActivity)
        // "intent.extras" contiene los datos añadidos con putExtra()
        // getString("EXTRA_NAME") obtiene el valor del nombre enviado
        // orEmpty() devuelve una cadena vacía ("") si el valor es nulo
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()

        // Mostramos un saludo personalizado en el TextView
        tvResult.text = "Hola $name"
    }
}