package com.valkotova.testassignmentpeopledata.ui.fragments.PeopleList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.valkotova.testassignmentpeopledata.ui.fragments.UserHolder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PeoplesListFragment(
    navController: NavController,
    viewModel : PeoplesListViewModel = hiltViewModel()
) {
    val listData = viewModel.list.collectAsState()
    val listState = rememberLazyGridState()
    val state = viewModel.state.collectAsState()
    when(state.value){
        is PeoplesListViewModel.ViewModelState.Loading -> {
            Box(modifier = Modifier
                .padding(30.dp)
                .zIndex(4f)
                .fillMaxSize()
                , contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is PeoplesListViewModel.ViewModelState.Empty -> {

        }
        else -> {}
    }
    val refreshing = state.value is PeoplesListViewModel.ViewModelState.Loading
    val pullRefreshState = rememberPullRefreshState(refreshing, onRefresh = {
        viewModel.onRefresh()
    })
    Box(Modifier.fillMaxSize().pullRefresh(pullRefreshState)) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(12.dp)
                .background(color = MaterialTheme.colorScheme.onTertiary)
                .fillMaxSize()
        ) {
            items(listData.value.size) { index ->
                UserHolder(listData.value[index]) {
                    //appState.navigate("${Destinations.BROWSE_WISH}$it")
                    navController.navigate("user/" + viewModel.makeUserJson(it))
                }
            }
        }
        Box(
            modifier = Modifier.zIndex(10f).fillMaxWidth(),
            contentAlignment = TopCenter,
        ) {
            PullRefreshIndicator(refreshing, pullRefreshState)
        }
    }
    ErrorBox(state = state)
}