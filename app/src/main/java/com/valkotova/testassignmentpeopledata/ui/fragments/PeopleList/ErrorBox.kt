package com.valkotova.testassignmentpeopledata.ui.fragments.PeopleList

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun ErrorBox(state : State<PeoplesListViewModel.ViewModelState>) {

    AnimatedVisibility(
        visible = state.value is PeoplesListViewModel.ViewModelState.ViewModelError,
        enter = slideInHorizontally()
                + expandHorizontally(expandFrom = Alignment.End)
                + fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })
                + shrinkHorizontally()
                + fadeOut()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeightIn(min = 44.dp).zIndex(30f),
            tonalElevation = 30.dp,
            color = MaterialTheme.colorScheme.errorContainer
        ) {
            Text(
                modifier = Modifier.padding(20.dp).zIndex(30f),
                text = (state.value as? PeoplesListViewModel.ViewModelState.ViewModelError)?.let{ error->
                    error.id?.let{
                        stringResource(id = it)
                    } ?: run {
                        error.text?:""
                    }
                } ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onErrorContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}