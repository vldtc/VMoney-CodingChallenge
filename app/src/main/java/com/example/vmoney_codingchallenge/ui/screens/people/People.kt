package com.example.vmoney_codingchallenge.ui.screens.people

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.vmoney_codingchallenge.data.model.people.PeopleItemModel
import com.example.vmoney_codingchallenge.domain.navigation.ScreensNavigation

@Composable
fun PeopleScreen(
    onClick: (Int) -> Unit
) {

    val viewModel = hiltViewModel<PeopleViewModel>()
    val allPeople by viewModel.people.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val (sortBy, setSortBy) = remember { mutableStateOf("") }
    val isSelected = remember { mutableStateOf(false) }
    viewModel.getPeopleData()

    val filteredPeople = remember(allPeople, searchQuery, sortBy) {
        val filteredList = if (searchQuery.isBlank()) {
            allPeople
        } else {
            allPeople.filter { person ->
                person.firstName?.contains(searchQuery, true) ?: false ||
                        person.lastName?.contains(searchQuery, true) ?: false
            }
        }

        when (sortBy) {
            "123" -> filteredList.sortedBy { it.id?.toInt() }
            "321" -> filteredList.sortedByDescending { it.id?.toInt() }
            "ABC" -> filteredList.sortedBy { it.firstName }
            "CBA" -> filteredList.sortedByDescending { it.firstName }
            else -> filteredList
        }
    }

    Column {
        FilterSection(onSortByChanged = setSortBy, isSelected = isSelected)
        NameSearchBar(query = searchQuery, isVisible = isSelected, onSearch = { value ->
            searchQuery = value
        })
        PeopleListContent(peopleList = filteredPeople, onClick = onClick)
    }
}

@Composable
fun FilterSection(
    onSortByChanged: (String) -> Unit,
    isSelected: MutableState<Boolean>
) {
    val radioOptions = listOf("123", "321", "ABC", "CBA")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Row(
        Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .height(40.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            onSortByChanged(text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (text == selectedOption) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.primaryContainer
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    fontWeight = FontWeight.Bold,
                    color = if (text == selectedOption) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
        }
        AnimatedContent(targetState = isSelected.value) { targetSelected ->
            IconButton(
                modifier = Modifier
                    .size(40.dp)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                onClick = {
                    isSelected.value = !isSelected.value
                }
            ) {
                if (targetSelected) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "searchIcon"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "searchIcon"
                    )
                }
            }
        }
    }
}

@Composable
fun NameSearchBar(
    query: String,
    onSearch: (String) -> Unit,
    isVisible: MutableState<Boolean>
) {
    val animatedOpacity = animateFloatAsState(
        targetValue = if (isVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 200)
    ).value

    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(
            initialOffsetY = { -100 },
            animationSpec = tween(durationMillis = 400)
        ),
        exit = slideOutVertically(
            targetOffsetY = { -160 },
            animationSpec = tween(durationMillis = 400)
        )
    ) {
        TextField(
            value = query,
            onValueChange = { onSearch(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
                .alpha(animatedOpacity),
            label = {
                Text(text = "Search...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "search"
                )
            },
            textStyle = TextStyle.Default.copy(MaterialTheme.colorScheme.onSurface),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                cursorColor = MaterialTheme.colorScheme.onSurface
            ),
            singleLine = true,
        )
    }
}


@Composable
fun PeopleListContent(
    peopleList: List<PeopleItemModel>,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 60.dp)
    ) {
        items(peopleList.size) { index ->
            PersonCard(people = peopleList[index], onClick = onClick)
        }
    }
}


@Composable
fun PersonCard(
    people: PeopleItemModel,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick(people.id?.toInt() ?: 0)
                ScreensNavigation.PeopleDetails.title = "${people.firstName} ${people.lastName}"
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 60.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = people.avatar),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    text = "${people.firstName} ${people.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1
                )
                Text(
                    text = "Employee #${people.id}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        LineDivider()
    }
}

@Composable
fun LineDivider(){
    Divider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier
            .padding(top = 4.dp)
    )
}




