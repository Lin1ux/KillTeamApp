package com.example.killteam

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.killteam.Objects.Passive
import com.example.killteam.Objects.SelectionRuleList
import com.example.killteam.Objects.SelectionRuleListChapterTactics
import com.example.killteam.Objects.SelectionRuleListWithPrimary
import com.example.killteam.ui.theme.KTColors

@Composable
fun PassiveDescription(passiveRule : Passive)
{
    Box(
        modifier = Modifier.fillMaxWidth().padding(5.dp).background(KTColors.Conceal),
        contentAlignment = Alignment.CenterStart)
    {
        var startDialog by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.fillMaxWidth().padding(10.dp).clickable(
                onClick = { startDialog = true}
            )
        )
        {
            Text(" "+passiveRule.name,style = TextStyle(fontSize = 28.sp),color = Color.White)
        }
        if(startDialog)
        {
            InfoPopUp(
                title = passiveRule.name,
                description = passiveRule.description,
                onDismiss = { startDialog = false }
            )
        }
    }
}

@Composable
fun ListDescription(viewModel: ScoreViewModel,firstPlayer : Boolean,ListRules : SelectionRuleList)
{
    Column(Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Conceal,RectangleShape))
    {
        Box(
            modifier = Modifier.fillMaxWidth().background(KTColors.Conceal),
            contentAlignment = Alignment.CenterStart)
        {
            var startDialog by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxWidth().padding(10.dp).clickable(
                    onClick = { startDialog = true}
                )
            )
            {
                Text(" "+ListRules.name,style = TextStyle(fontSize = 28.sp),color = Color.White)
            }
            if(startDialog)
            {
                InfoPopUp(
                    title = ListRules.name,
                    description = ListRules.description,
                    onDismiss = { startDialog = false }
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth())
        {

            ListRules.selectionList.forEachIndexed { index, rule ->
                val selected : Boolean = index == ListRules.selectedIndex.value
                var startDialog by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Conceal.copy(GetReadyAlpha(selected)), RectangleShape).clickable(){
                        startDialog = true
                    }
                )
                {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(5.dp)
                    )
                    {
                        Text(" "+rule.name,style = TextStyle(fontSize = 28.sp),color = KTColors.Conceal.copy(GetReadyAlpha(selected)))
                    }
                }
                if(startDialog)
                {
                    RuleSelectionInfoDialog(
                        index = index,
                        ruleIndex = ListRules.ruleIndex,
                        firstPlayer = firstPlayer,
                        viewModel = viewModel,
                        onDismiss = {startDialog = false},
                        onAccept = { RecievedIndex ->
                            viewModel.GetPlayer(firstPlayer).SetTeamRuleActiveIndex(RecievedIndex,ListRules.ruleIndex)
                            startDialog = false
                        }
                    )
                }
            }
        }
    }
}

//Dialog Window which allows player to take or remove equipment
@Composable
fun RuleSelectionInfoDialog(
    index : Int,
    ruleIndex : Int,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel,
    onDismiss: () -> Unit,
    onAccept: (Int) -> Unit
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
                val rule = viewModel.GetPlayer(firstPlayer).GetTeamRule(ruleIndex) as? SelectionRuleList
                Text(rule?.selectionList?.get(index)?.name ?: "", textAlign = TextAlign.Center)
            }
        },
        text = {
            val rule = viewModel.GetPlayer(firstPlayer).GetTeamRule(ruleIndex) as? SelectionRuleList
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    //Description
                    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.Center)
                    {
                        Text(FormatTextWithMarkers(rule?.selectionList?.get(index)?.description ?: ""),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }

                    if (rule?.selectedIndex?.value ?: -1 == index)
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                            onClick = {onAccept(-1)}
                        )
                        {
                            Text("Unselect")
                        }
                    }
                    else    //Add Button
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                            onClick = {onAccept(index)}
                        )
                        {
                            Text("Select")
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}

@Composable
fun ListDescriptionWithPrimary(viewModel: ScoreViewModel,firstPlayer : Boolean,ListRules : SelectionRuleListWithPrimary)
{
    Column(Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Conceal,RectangleShape))
    {
        Box(
            modifier = Modifier.fillMaxWidth().background(KTColors.Conceal),
            contentAlignment = Alignment.CenterStart)
        {
            var startDialog by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxWidth().padding(10.dp).clickable(
                    onClick = { startDialog = true}
                )
            )
            {
                Text(" "+ListRules.name,style = TextStyle(fontSize = 28.sp),color = Color.White)
            }
            if(startDialog)
            {
                InfoPopUp(
                    title = ListRules.name,
                    description = ListRules.description,
                    onDismiss = { startDialog = false }
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth())
        {

            ListRules.selectionList.forEachIndexed { index, rule ->
                val selected : Boolean = index == ListRules.selectedIndex.value
                val primary : Boolean = index == ListRules.primaryIndex.value
                var startDialog by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Conceal.copy(GetReadyAlpha(selected)), RectangleShape).clickable(){
                        startDialog = true
                    }
                )
                {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(5.dp)
                    )
                    {
                        Text(" "+rule.name.FormatPrimary(primary),style = TextStyle(fontSize = 28.sp),
                            color = KTColors.Conceal.copy(GetReadyAlpha(selected)),
                            fontWeight = if(primary) { FontWeight.Bold } else { FontWeight.Normal }
                        )
                    }
                }
                if(startDialog)
                {
                    RuleSelectionWithPrimaryInfoDialog(
                        index = index,
                        ruleIndex = ListRules.ruleIndex,
                        firstPlayer = firstPlayer,
                        viewModel = viewModel,
                        onDismiss = {startDialog = false},
                        selectRule = { RecievedIndex ->
                            viewModel.GetPlayer(firstPlayer).SetTeamRuleActiveIndex(RecievedIndex,ListRules.ruleIndex)
                            startDialog = false
                        },
                        selectPrimary = { RecievedIndex ->
                            viewModel.GetPlayer(firstPlayer).SetTeamRulePrimaryIndex(RecievedIndex,ListRules.ruleIndex)
                            startDialog = false
                        },

                    )
                }
            }
        }
    }
}

//Dialog Window which allows player to take or remove fraction rule
@Composable
fun RuleSelectionWithPrimaryInfoDialog(
    index : Int,
    ruleIndex : Int,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel,
    onDismiss: () -> Unit,
    selectRule: (Int) -> Unit,
    selectPrimary: (Int) -> Unit
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
                val rule = viewModel.GetPlayer(firstPlayer).GetTeamRule(ruleIndex) as? SelectionRuleListWithPrimary
                Text(rule?.selectionList?.get(index)?.name ?: "", textAlign = TextAlign.Center)
            }
        },
        text = {
            val rule = viewModel.GetPlayer(firstPlayer).GetTeamRule(ruleIndex) as? SelectionRuleListWithPrimary
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    //Description
                    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.Center)
                    {
                        Text(FormatTextWithMarkers(rule?.selectionList?.get(index)?.description ?: ""),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                        onClick = {selectPrimary(index)}
                    )
                    {
                        Text("Set as Primary")
                    }

                    if (rule?.selectedIndex?.value ?: -1 == index)
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                            onClick = {selectRule(-1)}
                        )
                        {
                            Text("Unselect")
                        }
                    }
                    else    //Add Button
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                            onClick = {selectRule(index)}
                        )
                        {
                            Text("Select")
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}

@Composable
fun ListDescriptionChapterTactics(viewModel: ScoreViewModel,firstPlayer : Boolean,ListRules : SelectionRuleListChapterTactics)
{
    Column(Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Conceal,RectangleShape))
    {
        Box(
            modifier = Modifier.fillMaxWidth().background(KTColors.Conceal),
            contentAlignment = Alignment.CenterStart)
        {
            var startDialog by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxWidth().padding(10.dp).clickable(
                    onClick = { startDialog = true}
                )
            )
            {
                Text(" "+ListRules.name,style = TextStyle(fontSize = 28.sp),color = Color.White)
            }
            if(startDialog)
            {
                InfoPopUp(
                    title = ListRules.name,
                    description = ListRules.description,
                    onDismiss = { startDialog = false }
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth())
        {

            ListRules.selectionList.forEachIndexed { index, rule ->
                val selected : Boolean = index == ListRules.selectedIndex.value
                val primary : Boolean = index == ListRules.primaryIndex.value
                var startDialog by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Conceal.copy(GetReadyAlpha(selected || primary)), RectangleShape).clickable(){
                        startDialog = true
                    }
                )
                {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(5.dp)
                    )
                    {
                        Text(" "+rule.name.FormatPrimary(primary),style = TextStyle(fontSize = 28.sp),
                            color = KTColors.Conceal.copy(GetReadyAlpha(selected || primary)),
                            fontWeight = if(primary) { FontWeight.Bold } else { FontWeight.Normal }
                        )
                    }
                }
                if(startDialog)
                {
                    RuleSelectionChapterTacticsInfoDialog(
                        index = index,
                        ruleIndex = ListRules.ruleIndex,
                        firstPlayer = firstPlayer,
                        viewModel = viewModel,
                        onDismiss = {startDialog = false},
                        selectRule = { RecievedIndex ->
                            viewModel.GetPlayer(firstPlayer).SetTeamRuleActiveIndex(RecievedIndex,ListRules.ruleIndex)
                            startDialog = false
                        },
                        selectPrimary = { RecievedIndex ->
                            viewModel.GetPlayer(firstPlayer).SetTeamRulePrimaryIndex(RecievedIndex,ListRules.ruleIndex)
                            startDialog = false
                        },

                        )
                }
            }
        }
    }
}

@Composable
fun RuleSelectionChapterTacticsInfoDialog(
    index : Int,
    ruleIndex : Int,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel,
    onDismiss: () -> Unit,
    selectRule: (Int) -> Unit,
    selectPrimary: (Int) -> Unit
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
                val rule = viewModel.GetPlayer(firstPlayer).GetTeamRule(ruleIndex) as? SelectionRuleListChapterTactics
                Text(rule?.selectionList?.get(index)?.name ?: "", textAlign = TextAlign.Center)
            }
        },
        text = {
            val rule = viewModel.GetPlayer(firstPlayer).GetTeamRule(ruleIndex) as? SelectionRuleListChapterTactics
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    //Description
                    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.Center)
                    {
                        Text(FormatTextWithMarkers(rule?.selectionList?.get(index)?.description ?: ""),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                        onClick = {selectPrimary(index)}
                    )
                    {
                        Text("Set as Primary")
                    }

                    if (rule?.selectedIndex?.value ?: -1 == index)
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                            onClick = {selectRule(-1)}
                        )
                        {
                            Text("Unselect")
                        }
                    }
                    else    //Add Button
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                            onClick = {selectRule(index)}
                        )
                        {
                            Text("Select")
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}