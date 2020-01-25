package com.andyshon.jetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.loadData()
        setContent {
            setContentView(viewModel)
        }
    }

    @Composable
    fun setContentView(model: MainViewModel) {
        MaterialTheme {
            VerticalScroller {
                Column(
                    crossAxisSize = LayoutSize.Expand,
                    crossAxisAlignment = CrossAxisAlignment.Stretch,
                    mainAxisAlignment = MainAxisAlignment.Start,
                    mainAxisSize = LayoutSize.Expand
                ) {
                    val viewState = +observe(model.state)
                    viewState?.buildUI()
                    viewState?.let {
                        when (it) {
                            is MainViewState.Loading -> {
                                showLoading()
                            }
                            is MainViewState.HasData -> {
                                showData(it.items)
                            }
                            is MainViewState.NoData -> {
                                showError(it.msg) {
                                    model.loadData()
                                }
                            }
                            is MainViewState.Error -> {
                                showError(it.reason) {
                                    model.loadData()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun showLoading() {
        Container {
            CircularProgressIndicator(color = Color.Red)
        }
    }

    @Composable
    fun showData(items: List<MainContentModel>) {
        items.forEach {
            Padding(left = 16.dp, right = 16.dp, bottom = 8.dp, top = 8.dp) {
                drawCell(it, onInfoClick = { item ->
                    Toast.makeText(this@MainActivity, item.title, Toast.LENGTH_SHORT).show()
                }, onDeleteClick = { item ->
                    viewModel.deleteItem(item)
                })
            }
        }
    }

    @Composable
    fun showError(reason: String, retry: () -> Unit) {
        Container {
            Padding(padding = 16.dp) {
                Column(
                    crossAxisSize = LayoutSize.Wrap,
                    crossAxisAlignment = CrossAxisAlignment.Center,
                    mainAxisAlignment = MainAxisAlignment.Center,
                    mainAxisSize = LayoutSize.Wrap
                ) {
                    Text(text = reason, style = +themeTextStyle { body1 })
                    Padding(top = 16.dp) {
                        Button(text = "Reload", onClick = {
                            retry()
                        })
                    }
                }
            }
        }
    }

// general purpose observe effect. this will likely be provided by LiveData.
// effect API for compose will also simplify soon.
    private fun <T> observe(data: LiveData<T>) = effectOf<T?> {
        val result = +state { data.value }
        val observer = +memo { Observer<T> { result.value = it } }

        +onCommit(data) {
            data.observeForever(observer)
            onDispose { data.removeObserver(observer) }
        }

        result.value
    }
}