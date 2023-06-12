package com.valkotova.testassignmentpeopledata.ui.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valkotova.testassignmentpeopledata.data.UserData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserHolder(
    data: UserData,
    onClick : (UserData) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            //.background(color = MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick.invoke(data)
            }
    ) {

        Column(
        ) {
            Box(
                modifier = Modifier.height(160.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                data.picture.medium?.let { imageInfo ->
                    GlideImage(
                        model = imageInfo,
                        contentDescription = "User image",
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.background)
                    ) {
                        val circularProgressDrawable = CircularProgressDrawable(context)
                        circularProgressDrawable.strokeWidth = 5f
                        circularProgressDrawable.centerRadius = 30f
                        circularProgressDrawable.start()
                        it.thumbnail()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(circularProgressDrawable)
                            .centerCrop()
                            .load(imageInfo)
                    }
                }

            }
            Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                Text(
                    modifier = Modifier.padding(6.dp).fillMaxSize(),
                    style = MaterialTheme.typography.bodyMedium,
                    text = data.name.title + " " + data.name.first + " " + data.name.last,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    minLines = 2,
                    maxLines = 2
                )
            }

        }
    }
}