package dam.pmdm.cursokotlin.firstapp
// Indica el paquete donde se encuentra esta clase (organiza el proyecto)

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import dam.pmdm.cursokotlin.R
// Importaciones necesarias para usar componentes de Android y recursos del proyecto

class FirstAppActivity : AppCompatActivity() {
    // Declaramos la clase de la actividad principal, que hereda de AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        // Método que se ejecuta cuando se crea la actividad (pantalla)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)
        // Enlaza esta clase con el diseño XML (activity_first_app.xml)

        // Obtenemos las referencias a los elementos de la interfaz por su ID
        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)   // Botón de inicio
        val etName = findViewById<AppCompatEditText>(R.id.etName)     // Campo de texto para el nombre

        // Asignamos una acción al hacer click en el botón
        btnStart.setOnClickListener {
            val name = etName.text.toString() // Guardamos el texto que el usuario escribió en el EditText

            // Comprobamos que el campo no esté vacío
            if (name.isNotEmpty()) {
                // Creamos un Intent para cambiar a la pantalla ResultActivity
                val intent = Intent(this, ResultActivity::class.java)

                // Añadimos un dato extra al Intent (el nombre introducido)
                intent.putExtra("EXTRA_NAME", name)

                // Iniciamos la nueva actividad (abrimos ResultActivity)
                startActivity(intent)
            }
        }

        // Este comentario indica que este código se ejecuta al arrancar la pantalla
    }
}