package ru.spectr.composetest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.spectr.composetest.Router
import ru.spectr.composetest.ui.components.FavoriteCard
import ru.spectr.composetest.ui.components.SessionCard
import ru.spectr.composetest.ui.detail.DetailActivity
import ru.spectr.composetest.ui.resolveColorAttr

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.attachRouter(object : Router {
            override fun navigateTo(id: String) {
                startActivity(
                    Intent(
                        baseContext,
                        DetailActivity::class.java
                    ).apply { putExtra(DetailActivity.ARG_SESSION_ID, id) }
                )
            }
        })

        val primaryTextColor = resolveColorAttr(android.R.attr.textColorPrimary)

        setContent {
            val focusManager = LocalFocusManager.current

            val snackbarVisibleState = viewModel.snackbarVisibleState
            val queryState = viewModel.query
            val mainState = viewModel.sessions

            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                item {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        value = queryState.value,
                        placeholder = { Text(text = "Поиск", color = Color(primaryTextColor)) },
                        singleLine = true,
                        onValueChange = { queryState.value = it },
                        label = null,
                        textStyle = TextStyle(color = Color(primaryTextColor)),
                        leadingIcon = {
                            Icon(Icons.Filled.Search, "", tint = Color(primaryTextColor))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        })
                    )

                    val favorites = mainState.value.filter { it.isFavorite }

                    Box(modifier = Modifier.animateContentSize()) {
                        if (favorites.isNotEmpty()) {
                            Text(
                                text = "Избранное",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                            )
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                items(favorites.size) {
                                    FavoriteCard(session = favorites[it], onClick = { id ->
                                        viewModel.onCardClick(id)
                                    })
                                }
                            }
                        }
                    }

                    Text(
                        text = "Сессии",
                        fontWeight = FontWeight.Bold,
                        color = Color(primaryTextColor),
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                    )
                }

                val searchResult = mainState.value.filter {
                    it.speaker.contains(
                        queryState.value,
                        ignoreCase = true
                    ) || it.description.contains(
                        queryState.value,
                        ignoreCase = true
                    )
                }

                val groups = searchResult.groupBy { it.date }

                groups.forEach { (header, sessions) ->
                    item {
                        Text(
                            text = header,
                            color = Color(primaryTextColor),
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                        )
                    }

                    items(sessions.size) { index ->
                        SessionCard(
                            sessions[index],
                            onClick = {
                                viewModel.onCardClick(it)
                            },
                            onFavoriteClick = { session ->
                                viewModel.onFavoriteClick(session.id)
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize()
            ) {
                if (snackbarVisibleState.value) {
                    Snackbar(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) { Text(text = "Не удалось добавить сессию в избранное") }
                    lifecycleScope.launch {
                        delay(3_000)
                        snackbarVisibleState.value = false
                    }
                }
            }
        }
    }
}

