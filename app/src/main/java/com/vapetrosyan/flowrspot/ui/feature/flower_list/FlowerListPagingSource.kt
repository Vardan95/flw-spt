package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.paging.PagingSource
import androidx.paging.PagingState
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
                nextKey = if (response.isNotEmpty()) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FlowerListItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}