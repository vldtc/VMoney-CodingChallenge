package com.example.vmoney_codingchallenge.ui.screens.rooms

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vmoney_codingchallenge.R
import com.example.vmoney_codingchallenge.data.model.rooms.RoomsItemModel
import com.example.vmoney_codingchallenge.ui.screens.people.LineDivider

@Composable
fun RoomsScreen() {

    val viewModel = hiltViewModel<RoomsViewModel>()
    val rooms by viewModel.rooms.collectAsState()
    viewModel.getRooms()

    Column {
        RoomsListContent(rooms = rooms)
    }
}

@Composable
fun RoomsListContent(
    rooms: List<RoomsItemModel>
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 60.dp)
            .fillMaxWidth()
    ) {
        items(rooms.size) { index ->
            RoomsCard(room = rooms[index])
        }
    }
}

@Composable
fun RoomsCard(
    room: RoomsItemModel
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ){
        Box(
            modifier = Modifier
                .size(width = 60.dp, height = 60.dp)
                .border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        startX = 0f,
                        endX = 140f
                    ),
                    shape = CircleShape
                )
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ROOM",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light,
                    maxLines = 1
                )
                Text(
                    text = room.id.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            }
        }
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 11.dp),
            horizontalAlignment = Alignment.CenterHorizontally
                ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Maximum occupancy: ${room.maxOccupancy.toString()}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Light,
                    maxLines = 1
                )
                if(room.isOccupied == true){
                    Image(painter = painterResource(id = R.drawable.ic_occupied), contentDescription = "occupied", modifier = Modifier.size(36.dp))
                }else{
                    Image(painter = painterResource(id = R.drawable.ic_vacant), contentDescription = "occupied", modifier = Modifier.size(36.dp))
                }
            }
            LineDivider()
        }
    }
}