package com.vapetrosyan.flowrspot.data.model

sealed class DataViewState<out T : Any> {
    object Empty : DataViewState<Nothing>()
    object Loading : DataViewState<Nothing>()
    object Failure : DataViewState<Nothing>()
    object NoFilterData : DataViewState<Nothing>()
    data class Data<out T : Any>(val data: T) : DataViewState<T>()
}
