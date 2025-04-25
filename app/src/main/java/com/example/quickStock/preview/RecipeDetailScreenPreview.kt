package com.example.quickStock.preview


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quickStock.model.recipe.RecipeData
import com.example.quickStock.screensUI.common.goBack.ScreenName
import com.example.quickStock.ui.theme.*

@Preview(showBackground = true)
@Composable
fun RecipeDetailScreenPreview() {
    QuickStockTheme {
        RecipeDetailPreviewContent()
    }
}

@Composable
fun RecipeDetailPreviewContent() {
    // Datos de ejemplo para el preview
    val sampleRecipe = RecipeData(
        id = "1",
        name = "Grilled Steak",
        ingredients = listOf("Steak", "Salt", "Pepper", "Olive Oil"),
        measurements = listOf("1 lb", "to taste", "to taste", "2 tbsp"),
        steps = listOf("Season the steak", "Heat the grill", "Cook the steak for 7 minutes per side")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingExtraLarge)
    ) {
        // Header con botón de regreso y nombre de la receta
        ScreenName(
            onGoBack = {},
            title = sampleRecipe.name,
        )

        // Contenido
        Column(
            modifier = Modifier.padding(paddingExtraLarge)
        ) {
            // Tarjeta para la imagen de la receta (placeholder)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingExtraLarge),
                elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
                shape = RoundedCornerShape(radiusMedium)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Imagen de la receta",
                        color = White
                    )
                }
            }

            // Tarjeta de ingredientes con medidas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingExtraLarge),
                elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(paddingExtraLarge)
                ) {
                    // Encabezado
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = paddingLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Kitchen,
                            contentDescription = "Ingredientes",
                            tint = PrimaryGreen,
                            modifier = Modifier.size(sizeIcon)
                        )
                        Spacer(modifier = Modifier.width(paddingLarge))
                        Text(
                            text = "Ingredientes",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    HorizontalDivider(color = LightDividerColor.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(spacingMedium))

                    // Lista de ingredientes y medidas
                    sampleRecipe.ingredients.forEachIndexed { index, ingredient ->
                        val measurement = if (index < sampleRecipe.measurements.size)
                            sampleRecipe.measurements[index] else ""

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = paddingMedium),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Punto e ingrediente
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(sizeCircles)
                                        .clip(RoundedCornerShape(radiusSmall))
                                        .background(PrimaryGreen.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.TopCenter
                                ) {
                                    Text(
                                        text = "•",
                                        color = PrimaryGreen,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                                Spacer(modifier = Modifier.width(spacingMedium))
                                Text(
                                    text = ingredient,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            // Medida
                            Text(
                                text = measurement,
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                textAlign = TextAlign.End
                            )
                        }

                        if (index < sampleRecipe.ingredients.size - 1) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = paddingSmall),
                                color = LightGray.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }

            // Tarjeta de pasos
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = elevationMedium),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(paddingExtraLarge)
                ) {
                    // Encabezado
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = paddingLarge)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatListNumbered,
                            contentDescription = "Pasos",
                            tint = PrimaryGreen,
                            modifier = Modifier.size(sizeIcon)
                        )
                        Spacer(modifier = Modifier.width(paddingLarge))
                        Text(
                            text = "Pasos",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    HorizontalDivider(color = LightDividerColor.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(paddingLarge))

                    // Lista de pasos
                    sampleRecipe.steps.forEachIndexed { index, step ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = paddingMedium)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(sizeIcon)
                                    .clip(RoundedCornerShape(radiusMedium))
                                    .background(PrimaryGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    color = White,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(paddingLarge))
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}