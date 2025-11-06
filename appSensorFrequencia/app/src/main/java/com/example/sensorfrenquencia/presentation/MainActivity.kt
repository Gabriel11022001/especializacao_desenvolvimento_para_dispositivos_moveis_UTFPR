package com.example.sensorfrenquencia.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {

    private lateinit var gerenciadorSensor: SensorManager
    private var sensor: Sensor? = null
    private var sensorListener: SensorEventListener? = null
    private var batimentos = mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
            // o usuário ainda não deu permissão, solicitar
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BODY_SENSORS, Manifest.permission.ACTIVITY_RECOGNITION, "android.permission.health.READ_HEART_RATE"), 1)
        }

        gerenciadorSensor = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = gerenciadorSensor.getDefaultSensor(Sensor.TYPE_HEART_RATE)

        setContent {

            // quando a aplicação for inicializada
            LaunchedEffect(Unit) {

                sensorListener = object : SensorEventListener {
                    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

                    override fun onSensorChanged(event: SensorEvent?) {
                        // quando ocorrer uma alteração nos batimentos, tratar nesse método

                        if (event != null) {
                            print(event.values.first().toInt())
                            batimentos.value = event.values.first().toInt()
                        }

                    }

                }

                if (sensor != null) {
                    gerenciadorSensor.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
                }

            }

            DisposableEffect(Unit) {

                onDispose {

                    if (sensorListener != null) {
                        gerenciadorSensor.unregisterListener(sensorListener)
                    }

                }

            }

            FrenquenciaCardiacaUsuario(batimentos.value)
        }
    }
}

@Composable
fun Imagem(frequenciaCardiaca: Int) {

    var imagem: Int = 0

    if (frequenciaCardiaca <= 100) {
        imagem = com.example.sensorfrenquencia.R.drawable.happiness
    } else if (frequenciaCardiaca <= 102) {
        imagem = com.example.sensorfrenquencia.R.drawable.sad
    } else {
        imagem = com.example.sensorfrenquencia.R.drawable.worried
    }

    androidx.compose.foundation.Image(
        painter = painterResource(imagem),
        contentDescription = "Foto descrevendo estado do usuário!",
        modifier = Modifier.width(40.dp).height(40.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun FrenquenciaCardiacaUsuario(frequenciaCardiaca: Int) {
    var batimentos: Int = frequenciaCardiaca
    var corIndicandoFrenquencia: Color? = null
    var mensagem: String = ""

    if (batimentos <= 100) {
        corIndicandoFrenquencia = Color(Color.Green.value)
        mensagem = "A sua frequência cardiáca está OK!"
    } else if (batimentos <= 102) {
        corIndicandoFrenquencia = Color(Color.Yellow.value)
        mensagem = "Cuidado! Você está com a frequência cardiáca alterada!"
    } else {
        corIndicandoFrenquencia = Color(Color.Red.value)
        mensagem = "Cuidado! Frequência elevada!"
    }

    Box(
        modifier = Modifier.fillMaxSize().background(corIndicandoFrenquencia).padding(10.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Frequência cardiaca",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = batimentos.toString() + " bpm",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = mensagem,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )

            Imagem(frequenciaCardiaca)

        }

    }

}
