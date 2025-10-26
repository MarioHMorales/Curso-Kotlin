package dam.pmdm.cursokotlin.imccalculator
// Define el paquete donde está esta clase (organización del proyecto)

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dam.pmdm.cursokotlin.R
import dam.pmdm.cursokotlin.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY
// Importa las clases necesarias: vistas, colores, recursos y la constante IMC_KEY usada para recibir el dato

class ResultIMCActivity : AppCompatActivity() {
    // Esta actividad muestra el resultado del cálculo del IMC (Índice de Masa Corporal)

    // Declaración de las variables para los elementos de la interfaz
    private lateinit var tvResult: TextView        // Muestra el texto del resultado (ej: "Peso normal")
    private lateinit var tvIMC: TextView           // Muestra el número calculado del IMC
    private lateinit var tvDescription: TextView   // Muestra una descripción según el resultado
    private lateinit var btnRecalculate: Button    // Botón para volver a calcular

    override fun onCreate(savedInstanceState: Bundle?) {
        // Método principal que se ejecuta al crear la pantalla
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        // Enlaza esta clase con el diseño XML activity_result_imcactivity.xml

        // Recupera el valor del IMC enviado desde ImcCalculatorActivity
        // Si no existe el dato, se asigna -1.0 como valor por defecto
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponents()  // Inicializa las vistas (findViewById)
        initUI(result)    // Muestra el resultado recibido en pantalla
        initListeners()   // Asigna la acción del botón "Recalcular"
    }

    private fun initListeners() {
        // Asigna el comportamiento del botón de recalcular
        // Al pulsarlo, vuelve a la pantalla anterior (ImcCalculatorActivity)
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        // Muestra el valor del IMC y personaliza el texto y color según el rango del resultado
        tvIMC.text = result.toString()

        // Evalúa el valor del IMC dentro de distintos rangos usando "when"
        when (result) {

            in 0.00..18.50 -> { // Bajo peso
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }

            in 18.51..24.99 -> { // Peso normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }

            in 25.00..29.99 -> { // Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.description_sobrepeso)
            }

            in 30.00..99.00 -> { // Obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.description_obesidad)
            }

            else -> { // En caso de error o valor fuera de rango
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        // Asigna cada variable a su correspondiente vista del XML mediante su ID
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}