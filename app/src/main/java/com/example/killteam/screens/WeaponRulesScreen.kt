package com.example.killteam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.killteam.InfoPopUp
import com.example.killteam.Objects.WeaponRules
import com.example.killteam.ui.theme.KTColors

@Composable
fun WeaponeRules()
{
    var showDialog by remember { mutableStateOf(false) }
    var selectedWeaponRule by remember { mutableStateOf(0) }
    LazyColumn(modifier = Modifier.background(KTColors.Background))
    {

        for(i in 0..WeaponRules.WeaponRuleList.size-1 step 2)
        {
            item()
            {
                Row(modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth())
                {
                    Box(modifier = Modifier.weight(0.5f).
                    padding(5.dp).
                    border(2.dp, KTColors.Orange, RoundedCornerShape(5.dp)).
                    clickable { showDialog = true
                                selectedWeaponRule = i
                    })
                    {
                        Text(   text = WeaponRules.WeaponRuleList[i].name,
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp).fillMaxWidth(),
                                style = TextStyle(fontSize = 24.sp),
                                textAlign = TextAlign.Center,
                                color = KTColors.Equipment)

                    }
                    if(i < WeaponRules.WeaponRuleList.size)
                    {
                        Box(modifier = Modifier.weight(0.5f).
                        padding(5.dp).
                        border(2.dp, KTColors.Orange, RoundedCornerShape(5.dp)).
                        clickable { showDialog = true
                                    selectedWeaponRule = i+1
                        })
                        {
                            Text(   text = WeaponRules.WeaponRuleList[i+1].name,
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp).fillMaxWidth(),
                                style = TextStyle(fontSize = 24.sp),
                                textAlign = TextAlign.Center,
                                color = KTColors.Equipment)

                        }
                    }
                }
            }
        }
    }
    if(showDialog)
    {
        InfoPopUp(
            title = WeaponRules.WeaponRuleList[selectedWeaponRule].name,
            description = WeaponRules.WeaponRuleList[selectedWeaponRule].description,
            onDismiss = {showDialog = false}
        )
    }
}
