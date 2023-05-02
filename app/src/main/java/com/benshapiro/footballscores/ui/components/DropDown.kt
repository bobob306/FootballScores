package com.benshapiro.footballscores.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

val leagueList = listOf("Select", "Premier League", "Serie A", "La Liga", "Etc")

@Immutable
data class DropdownFieldViewData(
    val text: String,
    val icon: String? = null
)

@Immutable
data class DropdownViewData(
    val valueList: List<DropdownFieldViewData>,
    val initialValue: DropdownFieldViewData? = null,
    val hint: String? = null,
    val enabled: Boolean = true,
    val readOnly: Boolean = true,
    val maxLines: Int = Int.MAX_VALUE,
)

fun DropdownMenu(
    expanded: Boolean,
    items: List<String>,
    disabledText: String,
    selectedText: String,
) {}

@Composable
fun DropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val items = leagueList
    val disabledValue = "Select"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex],
            color = if (items[selectedIndex].contains("Select"))
                MaterialTheme.colors.onPrimary.copy(0.1f) else
                    MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    if (items[selectedIndex].contains("Select"))
                        MaterialTheme.colors.primary.copy(alpha = 0.1f) else
                        MaterialTheme.colors.primary
                )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}

@Preview
@Composable
private fun previewDropDownMenu() {
    DropdownMenu()
}