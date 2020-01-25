package com.andyshon.jetpackcompose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.surface.Card
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight

@Composable
fun drawCell(
    model: MainContentModel,
    onInfoClick: ((model: MainContentModel) -> Unit)? = null,
    onDeleteClick: ((model: MainContentModel) -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(
            topLeft = 8.dp,
            topRight = 8.dp,
            bottomLeft = 8.dp,
            bottomRight = 8.dp
        ),
        elevation = 8.dp
    ) {
        Column {
            Clickable(onClick = {
                onInfoClick?.invoke(model)
            }) {
                Container(
                    expanded = true,
                    height = 180.dp
                ) {
                    val image = +imageResource(model.image)
                    DrawImage(image)
                }
            }

            Padding(top = 16.dp, left = 16.dp, right = 16.dp) {
                Text(
                    text = model.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.W700,
                        fontSize = 16.sp
                    )
                )
            }

            Padding(left = 16.dp, right = 16.dp, bottom = 8.dp) {
                Text(
                    text = model.title,
                    style = TextStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.W700,
                        fontSize = 12.sp
                    )
                )
            }

            Padding(left = 16.dp, right = 16.dp, bottom = 8.dp) {
                Row(
                    crossAxisAlignment = CrossAxisAlignment.Stretch,
                    mainAxisAlignment = MainAxisAlignment.End,
                    mainAxisSize = LayoutSize.Expand
                ) {
                    Button(
                        onClick = {
                            onInfoClick?.invoke(model)
                        },
                        style = TextButtonStyle()
                    ) {
                        Text("Info")
                    }

                    WidthSpacer(12.dp)

                    Button(
                        onClick = {
                            onDeleteClick?.invoke(model)
                        },
                        style = TextButtonStyle()
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}