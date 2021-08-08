package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.vapetrosyan.flowrspot.R
import com.vapetrosyan.flowrspot.data.model.FlowerListItem
import com.vapetrosyan.flowrspot.helper.items
import com.vapetrosyan.flowrspot.ui.theme.WhiteBackground
import kotlinx.coroutines.flow.Flow

@Composable
fun FlowerListScreen(
    state: FlowersListContract.State,
    effectFlow: Flow<FlowersListContract.Effect>?,
    onEventSent: (event: FlowersListContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: FlowersListContract.Effect.Navigation) -> Unit
) {
    when (state) {
        is FlowersListContract.State.Data -> {
            Column(modifier = Modifier.background(WhiteBackground)) {
                SearchView(state = state.loadingState, onSearchTextChanged = {
                    onEventSent(FlowersListContract.Event.SearchFlower(it))
                })
                state.pager?.apply {
                    FlowerList(pager = this)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun FlowerList(pager: Pager<Int, FlowerListItem>) {
    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()

    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        cells = GridCells.Fixed(2),
    ) {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(lazyPagingItems) {
            it?.apply {
                FlowerGridItem(flowerListItem = this)
            }
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                ProgressIndicator()
            }
        }
    }
}

@Composable
fun FlowerGridItem(flowerListItem: FlowerListItem) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = flowerListItem.profilePicture, builder = {
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),

        )
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()) {
            val (favButton, txtSightingsCount, txtLatinName, txtName)= createRefs()

            IconButton(onClick = {},
                modifier = Modifier
                    .constrainAs(favButton) {
                        top.linkTo(parent.top, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
                    .clip(CircleShape)
                    .size(32.dp)) {
                Icon(
                    imageVector = if(flowerListItem.favorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .constrainAs(txtSightingsCount) {
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.white_half_transparent)),
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(id = R.string.txt_sightings_count, flowerListItem.sightings),
                    color = Color.White
                )
            }

            Text(
                modifier = Modifier
                    .constrainAs(txtLatinName) {
                        bottom.linkTo(txtSightingsCount.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    },
                text = flowerListItem.latinName,
                color = colorResource(id = R.color.color_latin_name),
            )

            Text(
                modifier = Modifier
                    .constrainAs(txtName) {
                        bottom.linkTo(txtLatinName.top, margin = 8.dp)
                        start.linkTo(parent.start, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    },
                text = flowerListItem.name,
                color = Color.White
            )
        }
    }

}

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}