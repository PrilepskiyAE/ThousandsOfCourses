package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.prilepskiy.thousandsofcourses.common.DarkGrayColor
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun SearchComponent(modifier: Modifier, searchText: String, onSearchChange: (String) -> Unit,onFilterClick: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BaseTextFieldComponent(
            modifier = Modifier
                .padding(vertical = Spaces.space6)
                .fillMaxWidth()
                .weight(1f)
                .height(Spaces.space56),

            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(Spaces.space56),
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            valueText = searchText,
            onValueChange = onSearchChange,
            placeholderText = stringResource(R.string.search_component_search_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            backgroundColor = DarkGrayColor
        )

        Image(
            modifier = Modifier
                .size(Spaces.space56)
                .padding(start = Spaces.space4)
                .clickable(onClick = onFilterClick),
            painter = painterResource(R.drawable.ic_filter_btn),
            contentDescription = ""
        )
    }
}