package com.example.trafficlightt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trafficlightt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class LightState {
        BLUE, PURPLE, PINK
    }

    private lateinit var binding: ActivityMainBinding
    private var currentState = LightState.BLUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Сохранение цвета при повороте
        currentState = savedInstanceState?.let {
            LightState.values().getOrNull(it.getInt("STATE")) ?: LightState.BLUE
        } ?: LightState.BLUE

        updateLights()

        binding.nextButton.setOnClickListener {
            currentState = when (currentState) {
                LightState.BLUE -> LightState.PURPLE
                LightState.PURPLE -> LightState.PINK
                LightState.PINK -> LightState.BLUE
            }
            updateLights()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("STATE", currentState.ordinal)
    }

    private fun updateLights() {
        // Сбрасываем все цвета на серый
        binding.blueLight.setBackgroundResource(R.drawable.circle_grey)
        binding.purpleLight.setBackgroundResource(R.drawable.circle_grey)
        binding.pinkLight.setBackgroundResource(R.drawable.circle_grey)

        // Включаем текущий цвет
        when (currentState) {
            LightState.BLUE -> binding.blueLight.setBackgroundResource(R.drawable.circle_light_blue)
            LightState.PURPLE -> binding.purpleLight.setBackgroundResource(R.drawable.circle_purple)
            LightState.PINK -> binding.pinkLight.setBackgroundResource(R.drawable.circle_pink)
        }
    }
}