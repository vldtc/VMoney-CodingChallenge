package com.example.vmoney_codingchallenge.ui.screens.people.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vmoney_codingchallenge.R
import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.domain.convertDateFormat
import com.example.vmoney_codingchallenge.ui.screens.people.LineDivider
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PeopleDetailsScreen(
    peopleID: Int,
    onClick: () -> Unit
) {
    val viewModel = hiltViewModel<PeopleDetailsViewModel>()
    val personDetails by viewModel.personDetails.collectAsState()
    viewModel.getPeopleById(peopleID)


    Column {
        BackButton(onClick = onClick)
        PeopleProfileContent(personDetails = personDetails)
        LineDivider()
        PeopleDetailsContent(personDetails = personDetails)
    }
}

@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun PeopleProfileContent(
    personDetails: PeopleItemModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = personDetails.avatar),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = "${personDetails.firstName} ${personDetails.lastName}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = "${personDetails.jobtitle}",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Light,
            maxLines = 1
        )
    }
}

@Composable
fun PeopleDetailsContent(
    personDetails: PeopleItemModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryCircle(icon = R.drawable.ic_id)
            Text(
                text = personDetails.id.toString(),
                modifier = Modifier
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryCircle(icon = R.drawable.ic_email)
            Text(
                text = personDetails.email.toString(),
                modifier = Modifier
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryCircle(icon = R.drawable.ic_colour)
            Text(
                text = personDetails.favouriteColor.toString().uppercase(),
                modifier = Modifier
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryCircle(icon = R.drawable.ic_date)
            Text(
                text = personDetails.createdAt.toString(),
                modifier = Modifier
                    .padding(start = 16.dp),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun CategoryCircle(
    icon: Int
) {
    Box(
        modifier = Modifier
            .size(width = 36.dp, height = 36.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}