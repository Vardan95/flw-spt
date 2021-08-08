package com.vapetrosyan.flowrspot.ui.feature.flower_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchView(
    state: FlowersListContract.LoadingState,
    onSearchTextChanged: (query: String?) -> Unit
) {
    val isEmpty = state is FlowersListContract.LoadingState.None ||
            (state as? FlowersListContract.LoadingState.Searching)?.searchQuery.isNullOrEmpty()

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value =  (state as? FlowersListContract.LoadingState.Searching)?.searchQuery ?: "",
            label = {
                Text(text = stringResource(id = com.vapetrosyan.flowrspot.R.string.search_hint))
            },
            onValueChange = onSearchTextChanged,
            trailingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(id = com.vapetrosyan.flowrspot.R.color.color_hint_help),
                trailingIconColor = colorResource(id = com.vapetrosyan.flowrspot.R.color.color_hint_help_icon),
            )
        )
    }

//    TextField(
//        value = (state as? FlowersListContract.State.Data)?.searchQuery ?: stringResource(id = com.vapetrosyan.flowrspot.R.string.search_hint),
//        onValueChange = onSearchTextChanged,
//        modifier = Modifier
//            .fillMaxWidth(),
//        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
//        trailingIcon = {
//            Icon(
//                Icons.Default.Search,
//                contentDescription = "",
//                modifier = Modifier
//                    .padding(15.dp)
//                    .size(24.dp)
//            )
//        },
//        singleLine = true,
//        shape = RectangleShape,
//        colors = TextFieldDefaults.textFieldColors(
//            textColor = colorResource(id = com.vapetrosyan.flowrspot.R.color.color_hint_help),
//            cursorColor = colorResource(id = com.vapetrosyan.flowrspot.R.color.color_hint_help),
//            leadingIconColor = colorResource(id = com.vapetrosyan.flowrspot.R.color.color_hint_help),
//            trailingIconColor = colorResource(id = com.vapetrosyan.flowrspot.R.color.color_hint_help_icon),
//            backgroundColor = Color.White,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            disabledIndicatorColor = Color.Transparent
//        )
//    )
}
