package com.andyshon.jetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = arrayListOf(
            MainContentModel("Washington", R.drawable.washington),
            MainContentModel("Moscow", R.drawable.moscow),
            MainContentModel("Paris", R.drawable.paris),
            MainContentModel("Berlin", R.drawable.berlin),
            MainContentModel("Amsterdam", R.drawable.amsterdam),
            MainContentModel("Vienna", R.drawable.vienna),
            MainContentModel("Minsk", R.drawable.minsk),
            MainContentModel("Havana", R.drawable.havana)
        )

        setContent {
            MaterialTheme {
                VerticalScroller {
                    Column(
                        crossAxisSize = LayoutSize.Expand,
                        crossAxisAlignment = CrossAxisAlignment.Stretch,
                        mainAxisAlignment = MainAxisAlignment.Start,
                        mainAxisSize = LayoutSize.Expand
                    ) {
                        HeightSpacer(16.dp)
                        items.forEach {
                            Padding(
                                left = 16.dp, right = 16.dp, bottom = 16.dp, top = 0.dp
                            ) {
                                drawCell(it, onInfoClick = {
                                    Toast.makeText(this@MainActivity, "Info", Toast.LENGTH_SHORT).show()
                                }, onShareClick = {
                                    Toast.makeText(this@MainActivity, "Share", Toast.LENGTH_SHORT).show()
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
