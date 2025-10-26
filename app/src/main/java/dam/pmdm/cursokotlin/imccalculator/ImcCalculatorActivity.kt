package dam.pmdm.cursokotlin.imccalculator
// Define el paquete donde se encuentra esta clase (organiza el proyecto por módulos)

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import dam.pmdm.cursokotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
// Importa todas las clases necesarias para trabajar con vistas, botones, sliders y colores

class ImcCalculatorActivity : AppCompatActivity() {
    // Esta clase representa la pantalla principal del cálculo del IMC (Índice de Masa Corporal)
    // Hereda de AppCompatActivity para ser una actividad de Android

    // Variables para guardar los valores actuales del usuario
    private var isMaleSelected: Boolean = true       // Indica si el sexo masculino está seleccionado
    private var isFemaleSelected: Boolean = false    // Indica si el sexo femenino está seleccionado
    private var currentWeight: Int = 70              // Peso inicial por defecto
    private var currentAge: Int = 30                 // Edad inicial por defecto
    private var currentHeight: Int = 120             // Altura inicial por defecto

    // Declaración de vistas (componentes del layout)
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object {
        // Constante usada como clave para enviar el resultado del IMC a otra actividad
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Método que se ejecuta cuando se crea la actividad (pantalla)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        // Conecta esta clase con el diseño XML activity_imc_calculator.xml

        initComponents()  // Inicializa las referencias a los elementos visuales (findViewById)
        initListeners()   // Asigna los eventos y acciones a los botones y sliders
        initUI()          // Configura la interfaz inicial (colores y valores por defecto)
    }

    private fun initComponents() {
        // Busca los elementos del layout por su ID y los asigna a las variables
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        // Configura todos los eventos de clicks y cambios de valores

        // Al hacer click en el card "Hombre", cambia el sexo seleccionado y actualiza los colores
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        // Igual para el card "Mujer"
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        // Listener del slider de altura (cuando cambia el valor)
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")             // Formato con 2 decimales
            currentHeight = df.format(value).toInt()   // Convierte el valor a entero
            tvHeight.text = "$currentHeight cm"        // Actualiza el texto mostrado
        }

        // Aumentar peso
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        // Disminuir peso
        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        // Aumentar edad
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        // Disminuir edad
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }

        // Al pulsar el botón "Calcular", se calcula el IMC y se pasa a otra pantalla
        btnCalculate.setOnClickListener {
            val result = calculateIMC()       // Calcula el IMC
            navigateToResult(result)          // Abre la pantalla del resultado
        }
    }

    private fun navigateToResult(result: Double) {
        // Crea un Intent para ir a la pantalla ResultIMCActivity
        val intent = Intent(this, ResultIMCActivity::class.java)
        // Envía el resultado del IMC como dato extra
        intent.putExtra(IMC_KEY, result)
        // Inicia la nueva actividad
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        // Fórmula del IMC = peso / (altura en metros al cuadrado)
        val imc = currentWeight / ((currentHeight.toDouble() / 100) * (currentHeight.toDouble() / 100))

        // Redondea el resultado a 2 decimales y lo devuelve como Double
        return String.format("%.2f", imc).replace(",", ".").toDouble()
    }

    private fun setAge() {
        // Actualiza el texto que muestra la edad
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        // Actualiza el texto que muestra el peso
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender() {
        // Alterna los valores de género seleccionado
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        // Cambia el color del fondo de los CardView según si están seleccionados o no
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        // Devuelve el color adecuado dependiendo de si el componente está seleccionado
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected     // Color si está seleccionado
        } else {
            R.color.background_component              // Color si no lo está
        }

        // Convierte el ID del color en un color real del sistema
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        // Configura los valores iniciales de la interfaz al abrir la app
        setGenderColor()  // Aplica los colores correctos al género seleccionado
        setWeight()       // Muestra el peso inicial
        setAge()          // Muestra la edad inicial
    }
}
