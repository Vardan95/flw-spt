package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.paging.PagingSource
import com.vapetrosyan.flowrspot.data.model.FlowerListItem

class FlowerListPagingSource(
    private val fetchData: suspend (Int) -> List<FlowerListItem>
) : PagingSource<Int, FlowerListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlowerListItem> {
        return try {
            val nextPage = params.key ?: 1
            val response = fetchData(nextPage)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}