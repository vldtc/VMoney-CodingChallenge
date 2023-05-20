package com.example.vmoney_codingchallenge.ui.screens.people

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.ui.theme.Purple80

@Composable
fun PeopleScreen(){

    val viewModel = hiltViewModel<PeopleViewModel>()
    val people by viewModel.people.collectAsState()
    viewModel.getPeopleData()

    Column {
        LazyColumn{
            items(people.size){ item->
                PersonCard(people = people.get(item))
            }
        }
    }

}

@Composable
fun PersonCard(
    people: PeopleItemModel
){
    Card(
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .height(99.dp)
                .background(Purple80)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "${people.avatar}"),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(height = 90.dp, width = 60.dp)
                    .weight(1f)
                    .padding(5.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "${people.id}",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 10.dp),
                fontSize = 20.sp
            )
            Text(
                text = "${people.firstName} ${people.lastName}",
                color = Color.Black,
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 5.dp),
                textAlign = TextAlign.End
            )
        }
    }
}