package ru.spectr.composetest.ui.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import ru.spectr.composetest.ui.resolveColorAttr

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()

    @ExperimentalComposeApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getStringExtra(ARG_SESSION_ID) ?: ""
        viewModel.setId(id)

        val primaryTextColor = resolveColorAttr(android.R.attr.textColorPrimary)

        setContent {
            val session = viewModel.session.value

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .height(224.dp)
                        .width(224.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(shape = CircleShape),
                    painter = rememberCoilPainter(request = session.imageUrl),
                    contentDescription = "",
                )
                Text(
                    text = session.speaker,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(22f, TextUnitType.Sp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp), color = Color(primaryTextColor)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "",
                        tint = Color(primaryTextColor),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "${session.date}, ${session.timeInterval}",
                        fontWeight = FontWeight.Bold, color = Color(primaryTextColor)
                    )
                }

                Text(
                    text = session.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    companion object {
        const val ARG_SESSION_ID = "ARG_SESSION_ID"
    }
}