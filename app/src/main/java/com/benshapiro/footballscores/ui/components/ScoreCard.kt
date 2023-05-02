package com.benshapiro.footballscores.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benshapiro.footballscores.R
import com.benshapiro.footballscores.data.viewData.ScoreCardViewData
import com.benshapiro.footballscores.ui.theme.FootballScoresTheme
import com.benshapiro.footballscores.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScoreCard(
    scorecardViewData: ScoreCardViewData
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        backgroundColor = White,
        elevation = 4.dp
    ) {
        FlowRow(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Image(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "home team icon"
            )
            scorecardViewData.apply {
                ScoreTextName(text = homeTeamShortName)
                ScoreText(text = homeTeamScore.toString())
                ScoreText(text = ":")
                ScoreText(text = awayTeamScore.toString())
                ScoreTextName(text = awayTeamShortName)
            }
            Image(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "away team icon"
            )
        }
    }
}

@Composable
fun ScoreTextName(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(0.2f)
            .padding(horizontal = 4.dp),
        text = text
    )
}

@Composable
fun ScoreText(text: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        text = text
    )
}

@Preview
@Composable
private fun PreviewScoreItem(
    previewScoreCardViewData: ScoreCardViewData = ScoreCardViewData(
        null,
        null,
        "MUFC",
        "AVFC",
        2,
        0,
        42
    )
) {
    FootballScoresTheme() {
        ScoreCard(scorecardViewData = previewScoreCardViewData)
    }
}