package com.example.killteam.Sensor

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class AccelerometerSensor(
    context: Context
): AndroidSensor(context,PackageManager.FEATURE_SENSOR_ACCELEROMETER,Sensor.TYPE_ACCELEROMETER)
