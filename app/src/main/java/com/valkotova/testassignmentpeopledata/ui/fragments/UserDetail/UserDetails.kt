package com.valkotova.testassignmentpeopledata.ui.fragments.UserDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valkotova.testassignmentpeopledata.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserDetails(
    navController: NavController,
    viewModel : UserDetailsViewModel = hiltViewModel()
) {
    val data = viewModel.data.collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    data.value?.let{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {
            it.picture.large?.let { imageInfo ->
                GlideImage(
                    model = imageInfo,
                    contentDescription = "User image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    val circularProgressDrawable = CircularProgressDrawable(context)
                    circularProgressDrawable.strokeWidth = 5f
                    circularProgressDrawable.centerRadius = 30f
                    circularProgressDrawable.start()
                    it.thumbnail()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(circularProgressDrawable)
                        .circleCrop()
                        .load(imageInfo)
                }
            }
            Text(
                text = "${it.name.title} ${it.name.first} ${it.name.last}",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .align(CenterHorizontally),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.age, it.dob.age) ,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(id = R.string.birthday, viewModel.getBirthday(it.dob.date)),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(id = R.string.Phone, it.phone?:""),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(id = R.string.Cell, it.cell?:""),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(id = R.string.Email, it.email?:""),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = stringResource(
                    id = R.string.Address,
                    it.location.street?.number.toString(),
                    it.location.street?.name?:"",
                    it.location.city?:"",
                    it.location.state?:"",
                    it.location.country?:"",
                ),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

        }
    }
}