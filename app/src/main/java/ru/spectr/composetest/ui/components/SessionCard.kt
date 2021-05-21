package ru.spectr.composetest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import ru.spectr.composetest.R
import ru.spectr.composetest.domain.SessionVs


@Composable
fun SessionCard(
    session: SessionVs,
    onClick: (id: String) -> Unit,
    onFavoriteClick: (item: SessionVs) -> Unit
) {
    MaterialTheme {
        Card(
            modifier = Modifier
                .clickable { onClick(session.id) }
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                val painter = rememberCoilPainter(
                    request = session.imageUrl
                )

                Image(
                    modifier = Modifier
                        .height(56.dp)
                        .width(56.dp)
                        .clip(shape = CircleShape),
                    painter = painter,
                    contentDescription = "",
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = session.speaker,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = session.timeInterval,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = session.description,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                val resId = if (session.isFavorite) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(resId),
                    contentDescription = "",
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onFavoriteClick(session)
                        }
                    )
                )
            }
        }
    }
}