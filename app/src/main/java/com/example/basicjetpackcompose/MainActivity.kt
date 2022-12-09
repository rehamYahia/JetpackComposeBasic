package com.example.basicjetpackcompose

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicjetpackcompose.ui.theme.BasicJetpackComposeTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                    MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}





@Composable
fun MyApp(
    modifier: Modifier = Modifier ,
)
{
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface(modifier , color = MaterialTheme.colors.background ) {
        if(shouldShowOnboarding){
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding= false})
        }
        else{
            Greetings()
        }
    }

}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier ,
    names:List<String> = List(100){"$it"}
){
   LazyColumn(modifier = Modifier.padding(vertical = 4.dp))
   {
       items(names) {name->
           Greeting(name = name)
       }

}

}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_700)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text ="Welcome to the Basics Codelab!" , color = Color.White , fontSize = 20.sp)
        Button(
            onClick = {onContinueClicked() },
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            Text("Continue")
        }

    }

}


@Composable
fun Greeting(
    name: String,
) {
    Card (
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        //backgroundColor = MaterialTheme.colors.surface
        backgroundColor = MaterialTheme.colors.primary
    )
    {
        CardContent(name)
    }

}


@Composable
fun CardContent(name:String)
{
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(modifier = Modifier
        .padding(24.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    
    ) {
        Column(modifier = Modifier
            .weight(1f)
            //.padding(bottom = extraPadding)
            .padding(12.dp)
                ) {
            Text(text = "Hello ")
            Text(text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if(expanded){
                Text(text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(3))
            }
        }

        IconButton(onClick = { expanded = !expanded}) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription =if(expanded) {
                    stringResource(id = R.string.show_less)
                }
            else{
                stringResource(id = R.string.show_more)
                }
            )
            
        }
    }
}

@Preview(showBackground = true , widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicJetpackComposeTheme {
       MyApp(modifier = Modifier.fillMaxSize())
    }
}

