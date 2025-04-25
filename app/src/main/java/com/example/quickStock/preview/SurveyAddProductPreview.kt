package com.example.quickStock.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.quickStock.screensUI.addProducts.AddProductSurvey
import com.example.quickStock.ui.theme.QuickStockTheme

@Preview(showBackground = true)
@Composable
fun SurveyAddProductPreview () {
    QuickStockTheme {
        AddProductSurvey(
            onClick = {  }
        )
    }
}

