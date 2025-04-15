package com.example.killteam

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.killteam.ui.theme.KTColors

//Dialog Window which ask player is sure he want to do something
@Composable
fun ConfirmDialog(
    title : String,
    description : String,
    onDismiss: () -> Unit,
    onAccept: () -> Unit
)
{
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text(title, textAlign = TextAlign.Center)
            }
        },
        text = {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    //Description
                    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.Center)
                    {
                        Text(description,style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                        onClick = {onAccept()}
                    )
                    {
                        Text("KILL")
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}