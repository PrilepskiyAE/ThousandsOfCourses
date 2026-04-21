package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.prilepskiy.thousandsofcourses.common.DarkGreenColor
import com.prilepskiy.thousandsofcourses.common.GlassColor
import com.prilepskiy.thousandsofcourses.common.GreenColor
import com.prilepskiy.thousandsofcourses.common.Sizes
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.WhiteColor
import com.prilepskiy.thousandsofcourses.domain.model.CourseModel
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun CourseListComponent(
    list: List<CourseModel>,
    onItemClick: (CourseModel) -> Unit,
    onClickFavoriteItem: (CourseModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { item ->
            CourseItemComponent(
                item = item,
                onItemClick = onItemClick,
                onClickFavoriteItem = onClickFavoriteItem
            )
        }
    }
}


@Composable
private fun CourseItemComponent(
    item: CourseModel, onItemClick: (CourseModel) -> Unit,
    onClickFavoriteItem: (CourseModel) -> Unit
) {
    Card(
        Modifier
            .height(Spaces.space236)
            .fillMaxWidth()
    ) {

        Column {
            PosterComponent(item) {
                onClickFavoriteItem.invoke(item);
            }
            InfoComponent(item) {
                onItemClick(item);
            }
        }
    }

}

@Composable
private fun PosterComponent(item: CourseModel, onItemClick: (CourseModel) -> Unit) {
    Box(
        modifier = Modifier
            .height(Spaces.space114)
            .clip(RoundedCornerShape(Spaces.space12))
    ) {

        AsyncImage(
            model = item.posterUrl,
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
        FavoriteIconCircle(
            modified = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(Spaces.space8),
            item,
            { onItemClick.invoke(item) })
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(alignment = Alignment.BottomStart)
        ) {
            RatingBadge(modifier = Modifier.padding(Spaces.space8), ratingValue = item.rate)
            DataTime(dataTime = item.startDate)
        }


    }
}

@Composable
private fun InfoComponent(item: CourseModel, onItemClick: () -> Unit) {
    Column(modifier = Modifier.padding(Spaces.space16)) {
        Text(
            text = item.title,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size16,
                lineHeight = Sizes.size18,
                letterSpacing = Sizes.size05
            )
        )

        Spacer(modifier = Modifier.size(Spaces.space8))
        Text(
            text = item.text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size12,
                lineHeight = Sizes.size16,
                letterSpacing = Sizes.size04
            )
        )
        Spacer(modifier = Modifier.size(Spaces.space8))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(item.price + " " + stringResource(R.string.rub))
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.clickable(onClick = onItemClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.course_list_component_button),
                    color = DarkGreenColor,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W600,
                        fontStyle = FontStyle.Normal,
                        fontSize = Sizes.size12,
                        lineHeight = Sizes.size16,
                        letterSpacing = Sizes.size04
                    )

                )
                Icon(
                    painterResource(R.drawable.ic_arrow_right_short_fill),
                    "",
                    tint = DarkGreenColor
                )
            }

        }
    }
}

@Composable
fun FavoriteIconCircle(modified: Modifier = Modifier, item: CourseModel, onItemClick: () -> Unit) {
    Box(
        modifier = modified.clickable(onClick = onItemClick)
    ) {
        Image(
            painter = if (item.hasLike) painterResource(R.drawable.ic_favorite_active,) else painterResource(
                R.drawable.bookmark
            ),
            contentDescription = "",
            modifier = Modifier
                .size(Spaces.space28)
                .align(Alignment.Center),
        )
    }
}


@Composable
fun RatingBadge(modifier: Modifier = Modifier, ratingValue: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(Spaces.space22)
            .padding()
            .background(
                color = GlassColor.copy(alpha = 0.3f),
                shape = RoundedCornerShape(Spaces.space12)
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_star_green),
            contentDescription = "Звезда рейтинга",
            modifier = Modifier
                .padding(start = Spaces.space4)
                .size(Spaces.space12)
                .padding(end = Spaces.space4)
        )


        Text(
            modifier = Modifier.padding(end = Spaces.space4),
            text = ratingValue,
            fontSize = Sizes.size12,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DataTime(modifier: Modifier = Modifier, dataTime: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = GlassColor.copy(alpha = 0.3f),
                shape = RoundedCornerShape(Spaces.space12)
            )
    ) {
        Text(
            modifier = Modifier.padding(Spaces.space4),
            text = dataTime, color = WhiteColor,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                fontStyle = FontStyle.Normal,
                fontSize = Sizes.size12,
                lineHeight = Sizes.size16,
                letterSpacing = Sizes.size04
            )

        )
    }
}



