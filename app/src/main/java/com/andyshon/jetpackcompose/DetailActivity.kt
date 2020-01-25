package com.andyshon.jetpackcompose

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.core.sp
import androidx.ui.foundation.DrawImage
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight

class DetailActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context, item: MainContentModel) {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("item", item)
            }
            context.startActivity(intent)
        }
    }

    lateinit var item: MainContentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = intent.extras?.getParcelable("item") ?: MainContentModel("default", R.drawable.paris)
        setContent {
            setContentView(item)
        }
    }

    @Composable
    fun setContentView(item: MainContentModel) {
        MaterialTheme {
            Padding(left = 16.dp, right = 16.dp, bottom = 8.dp, top = 8.dp) {
                Column {
                    Container(
                        expanded = true,
                        height = 180.dp
                    ) {
                        val image = +imageResource(item.image)
                        DrawImage(image)
                    }

                    Padding(top = 16.dp, left = 16.dp, right = 16.dp) {
                        Row(
                            crossAxisAlignment = CrossAxisAlignment.Stretch,
                            mainAxisAlignment = MainAxisAlignment.Center,
                            mainAxisSize = LayoutSize.Expand
                        ) {
                            Text(
                                text = item.title,
                                style = TextStyle(
                                    color = Color.Blue,
                                    fontWeight = FontWeight.W700,
                                    fontSize = 24.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
