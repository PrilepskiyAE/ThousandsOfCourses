package com.prilepskiy.thousandsofcourses.presintation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.prilepskiy.thousandsofcourses.common.BlueColor
import com.prilepskiy.thousandsofcourses.common.Spaces
import com.prilepskiy.thousandsofcourses.common.WhiteColor
import com.prilepskiy.thousandsofcourses.presintation.R

@Composable
fun SocialNetworkComponent(
    modifier: Modifier = Modifier,
    vkButton: () -> Unit,
    okButton: () -> Unit,
) {

    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = vkButton,
            modifier = Modifier
                .width(Spaces.space156)
                .height(Spaces.space40)
                .alpha(1f),
            shape = RoundedCornerShape(Spaces.space30),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueColor
            ),
            contentPadding = PaddingValues(
                vertical = Spaces.space4, horizontal = Spaces.space0
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_vk),
                contentDescription = "vk",
                tint = WhiteColor
            )
        }
        Spacer(modifier = Modifier.width(Spaces.space16))
        Card(
            shape = RoundedCornerShape(Spaces.space30),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier
                .width(Spaces.space156)
                .height(Spaces.space40)
                .padding(top = Spaces.space2)
                .clickable { okButton.invoke() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFF98509), Color(0xFFF95D00))
                        ),
                        shape = RoundedCornerShape(Spaces.space30)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ok),
                    contentDescription = "ok",
                    tint = Color.White,
                )
            }
        }
    }
}