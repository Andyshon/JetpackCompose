package com.andyshon.jetpackcompose

import androidx.compose.Composable

sealed class MainViewState {

    @Composable abstract fun buildUI()

    object Loading : MainViewState() {
        @Composable override fun buildUI() {}
    }

    class HasData(val items: List<MainContentModel>) : MainViewState() {
        @Composable override fun buildUI() {}
    }

    class Error(val reason: String) : MainViewState() {
        @Composable override fun buildUI() {}
    }

    class NoData(val msg: String) : MainViewState() {
        @Composable override fun buildUI() {}
    }
}