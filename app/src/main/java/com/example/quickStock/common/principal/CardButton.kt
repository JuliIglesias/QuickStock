package com.example.quickStock.common.principal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickStock.icon.MyIcon


@Composable
fun CardButton(
    buttonData: ICardButton
) {

    Button(
        onClick = buttonData.onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = buttonData.modifier.size(180.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MyIcon(
                icon = buttonData.icon,
                contentDescription = buttonData.title,
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = buttonData.title,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}