package com.example.quickStock.screensUI.common.principal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.quickStock.model.common.ICardButton
import com.example.quickStock.screensUI.icon.MyIcon
import com.example.quickStock.ui.theme.*

@Composable
fun CardButton(
    buttonData: ICardButton
) {
    Button(
        onClick = buttonData.onClick,
        shape = RoundedCornerShape(radiusLarge),
        modifier = buttonData.modifier.size(sizeCard)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            MyIcon(
                icon = buttonData.icon,
                contentDescription = buttonData.title,
                modifier = Modifier.size(sizeExtraLargeIcon)
            )

            Spacer(modifier = Modifier.height(spacingSmall))

            Text(
                text = buttonData.title,
                fontSize = textSizeTitle,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}