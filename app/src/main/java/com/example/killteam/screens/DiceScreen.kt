package com.example.killteam.screens

import android.app.Application
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.killteam.Sensor.AccelerometerSensor
import com.example.killteam.ui.theme.KTColors
import kotlinx.coroutines.delay
import kotlin.random.Random

//Screen which shows allows to show dices
@Composable
fun DiceScreen()
{
    val DiceViewModel: DiceViewModel = viewModel()
    var roll by remember { mutableStateOf(false) }

    LazyColumn(modifier =  Modifier.fillMaxWidth())
    {
        item()
        {
            Row(modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center
            )
            {
                Box(modifier = Modifier
                    .weight(0.5f)
                    .padding(5.dp)
                    .border(2.dp, KTColors.Red, RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center)
                {
                    Dices(DiceViewModel,true,roll, KTColors.Red)
                }
                Box(modifier = Modifier
                    .weight(0.5f)
                    .padding(5.dp)
                    .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center)
                {
                    Dices(DiceViewModel,false,roll, KTColors.Blue)
                }
            }
        }
        item()
        {
            Row(modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center
            )
            {
                DiceCounter(Modifier.weight(0.5f).padding(5.dp),DiceViewModel, KTColors.Red,true)
                DiceCounter(Modifier.weight(0.5f).padding(5.dp),DiceViewModel, KTColors.Blue,false)
            }
        }
        item()
        {
            Row(modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center
            )
            {
                Spacer(modifier = Modifier.weight(0.25f))
                Box(modifier = Modifier
                    .weight(0.5f)
                    .background(color = KTColors.Orange, shape = RoundedCornerShape(5.dp))
                    .clickable{
                    roll = !roll
                },
                    contentAlignment = Alignment.Center)
                {
                    if(roll)
                    {
                        Text("Stop Rolling",
                            modifier = Modifier.padding(5.dp),
                            style = TextStyle(fontSize = 24.sp),
                            color = Color.White,
                            textAlign = TextAlign.Center)
                    }
                    else
                    {
                        Text("Start Rolling",
                            modifier = Modifier.padding(5.dp),
                            style = TextStyle(fontSize = 24.sp),
                            color = Color.White,
                            textAlign = TextAlign.Center)
                    }

                }
                Spacer(modifier = Modifier.weight(0.25f))
            }
        }
    }
}

@Composable
fun Dices(
    viewModel: DiceViewModel,
    leftSide: Boolean,
    run : Boolean,
    color: Color
)
{

    val squares = List(viewModel.getNumberOfDices(leftSide))
    {
        remember { Animatable(initialValue = 0.0f) }
    }

    val squaresRotation = List(viewModel.getNumberOfDices(leftSide))
    {
        remember { Animatable(initialValue = 0.0f) }
    }


    if (run) {
        squares.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                while (true)
                {
                    val value = Random.nextInt(-10,10).toFloat()/10
                    viewModel.setDiceValues()
                    animatable.animateTo(
                        targetValue = 0.0f,
                        animationSpec = keyframes {
                            durationMillis = 500
                            animatable.value at 0 with LinearOutSlowInEasing
                            value at 250 with LinearOutSlowInEasing
                        }
                    )
                }
            }
        }
        squaresRotation.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                while (true)
                {
                    val value = Random.nextInt(720, 1440).toFloat()
                    val minus = listOf<Float>(-1.0f,1.0f).random()
                    animatable.animateTo(
                        targetValue = 360.0f,
                        animationSpec = infiniteRepeatable(
                            animation = keyframes {
                                durationMillis = 500
                                animatable.value at 0 with LinearOutSlowInEasing
                                minus * value at 500 with LinearOutSlowInEasing
                            },
                            repeatMode = RepeatMode.Restart
                        )
                    )
                }
            }
        }
    }
    else
    {
        squares.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                animatable.animateTo(
                    targetValue = 0.0f,
                    animationSpec = keyframes {
                        durationMillis = 500
                        animatable.value at 0 with LinearOutSlowInEasing
                        0.0f at 500 with LinearOutSlowInEasing
                    }
                )
            }
        }
        squaresRotation.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                animatable.animateTo(
                    targetValue = 360.0f,
                    animationSpec = keyframes {
                        durationMillis = 500
                        animatable.value at 0 with LinearOutSlowInEasing
                        0.0f at 500 with LinearOutSlowInEasing
                    }
                )
            }
        }
    }

    val squareValues = squares.mapIndexed { index, animatable ->
        SquareState(
            position = animatable.value,
            rotation = squaresRotation[index].value
        )
    }
    val distance = with(LocalDensity.current) { 50.dp.toPx() }
    val lastSquare = squareValues.size - 1

    Column(
        modifier = Modifier
            .height(420.dp)
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        squareValues.forEachIndexed { index, value ->
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .graphicsLayer {
                        translationX = -value.position * distance
                        rotationZ = value.rotation
                    }
                    .background(
                        color = color,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = color,
                        shape = RoundedCornerShape(4.dp)
                    ),
                contentAlignment = Alignment.Center
            )
            {
                Text("${viewModel.getDiceValue(leftSide, index)}",style = TextStyle(fontSize = 30.sp),color = Color.White)
            }

            if (index != lastSquare) {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun DiceCounter(
    modifier : Modifier,
    diceViewModel: DiceViewModel,
    color : Color,
    leftSide : Boolean
)
{
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically)
    {
        //Button for decreasing number of dice
        Box(
            modifier = Modifier.
            weight(0.15f).
            fillMaxHeight().
            background(color).
            clickable
            {
                diceViewModel.deacreseNumberOfDices(leftSide)
            },
            contentAlignment = Alignment.Center
        ) {
            Text("-", style = TextStyle(fontSize = 36.sp),color = Color.White)
        }
        //Show number of dice
        Box(modifier = Modifier.
        weight(0.2f).
        fillMaxHeight().
        border(2.dp, color, RectangleShape),
            contentAlignment = Alignment.Center)
        {
            Text("${diceViewModel.getNumberOfDices(leftSide)}",style = TextStyle(fontSize = 28.sp), textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))
        }
        //Button for increasing number of dice
        Box(
            modifier = Modifier.
            weight(0.15f).
            fillMaxHeight().
            background(color).
            clickable
            {
                diceViewModel.increaseNumberOfDices(leftSide)
            },
            contentAlignment = Alignment.Center
        ) {
            Text("+", style = TextStyle(fontSize = 36.sp), color = Color.White)
        }
    }
}

class DiceViewModel(application: Application) : AndroidViewModel(application)
{

    private val accelerometerSensor = AccelerometerSensor(application.applicationContext)

    var accX by mutableStateOf(0.0f)
    var accY by mutableStateOf(0.0f)
    var accZ by mutableStateOf(0.0f)


    init {
        startAccelerometerListening()
    }

    private fun startAccelerometerListening() {
        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            accX = values[0]  // Wartość X
            accY = values[1]  // Wartość Y
            accZ = values[2]  // Wartość Z
            Log.d("AKCJA", "X: $accX, Y: $accY, Z: $accZ")
        }
    }


    var leftDice by mutableStateOf(1)
    var rightDice  by mutableStateOf(1)
    var leftValues = mutableStateListOf(1,1,1,1,1,1)
    var rightValues = mutableStateListOf(1,1,1,1,1,1)

    fun increaseNumberOfDices(leftSide: Boolean)
    {
        if(leftSide && leftDice < 6)
        {
            leftDice++
        }
        if(!leftSide && rightDice < 6)
        {
            rightDice++
        }
    }
    fun deacreseNumberOfDices(leftSide: Boolean)
    {
        if(leftSide && leftDice > 0)
        {
            leftDice--
        }
        if(!leftSide && rightDice > 0)
        {
            rightDice--
        }
    }
    fun setDiceValues()
    {
        for(i in 0..5)
        {
            leftValues[i] = Random.nextInt(1,7)
            rightValues[i] = Random.nextInt(1,7)
        }
    }
    fun getNumberOfDices(leftSide: Boolean) : Int
    {
        if(leftSide)
        {
            return leftDice
        }
        return rightDice
    }
    fun getDiceValue(leftSide: Boolean,index : Int) : Int
    {
        if(leftSide)
        {
            return leftValues[index]
        }
        return rightValues[index]
    }
}

data class SquareState(
    val position: Float,
    val rotation: Float,
)