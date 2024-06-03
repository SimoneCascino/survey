package it.simonecascino.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.simonecascino.theme.AppDimensions
import it.simonecascino.theme.AppTheme

@Composable
fun StartButton(
    text: String,
    onClick: () -> Unit
){

    LargeFloatingActionButton(
        modifier = Modifier.size(AppDimensions.u800),
        onClick = onClick,
        shape = CircleShape,
    ) {
        Text(text = text)
    }
    
}

@Composable
@Preview
private fun StartButtonPreview(){
    
    AppTheme {
        
        Box(modifier = Modifier.fillMaxSize()){
            StartButton(text = "Start Survey") {
                
            }
        }
        
    }
    
}