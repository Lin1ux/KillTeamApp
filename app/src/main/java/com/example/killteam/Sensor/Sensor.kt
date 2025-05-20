package com.example.killteam.Sensor

abstract class MeseruableSensor(
    protected val sensorType: Int
)
{
    protected var onSensorValuesChanged: ((List<Float>) -> Unit)? = null

    abstract val isExist: Boolean

    abstract fun startListening()

    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (List<Float>) -> Unit) {
        onSensorValuesChanged = listener
    }
}