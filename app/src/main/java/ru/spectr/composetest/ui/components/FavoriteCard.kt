package ru.spectr.composetest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.spectr.composetest.domain.SessionVs

@Composable
fun FavoriteCard(session: SessionVs, onClick: (id: String) -> Unit) {
    MaterialTheme {
        Card(
            modifier = Modifier.clickable { onClick(session.id) },
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Column(
                modifier = Modifier
                    .height(160.dp)
                    .width(160.dp)
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(text = session.timeInterval, fontWeight = FontWeight.Bold)
                Text(text = session.date)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = session.speaker,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = session.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}