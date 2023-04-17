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
import com.benshapiro.footballscores.data.viewData.ScoreViewData
import com.benshapiro.footballscores.ui.theme.FootballScoresTheme
import com.benshapiro.footballscores.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScoreCard(
    scoreViewData: ScoreViewData
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
            ScoreText(text = scoreViewData.homeTeamName)
            ScoreText(text = scoreViewData.homeTeamScore.toString())
            ScoreText(text = ":")
            ScoreText(text = scoreViewData.awayTeamScore.toString())
            ScoreText(text = scoreViewData.awayTeamName)
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
fun ScoreText(text: String){
    Text(
        modifier = Modifier.
        padding(horizontal = 4.dp),
        text = text)
}

@Preview
@Composable
private fun PreviewScoreItem(
    previewScoreModel: ScoreViewData = ScoreViewData("MasjhdklkasldaU", "AFC", 0, 1)
) {
    FootballScoresTheme() {
        ScoreCard(scoreViewData = previewScoreModel)
    }
}