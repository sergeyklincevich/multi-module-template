package com.klinserg.news.template.navigation.drawernav.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.klinserg.news.ui.R

@Composable
fun NavDrawerHeader(
    modifier: Modifier = Modifier,
) {
    Row {
        Icon(
            modifier = modifier
                .size(64.dp)
//                .shadow(elevation = 1.dp, shape = CircleShape)
                .clip(shape = CircleShape),
            painter = painterResource(id = R.drawable.account_circle),
            contentDescription = stringResource(R.string.user_name),
            tint = Color.White
        )
        Spacer(modifier = modifier.width(16.dp))
        Column(modifier = modifier.align(Alignment.CenterVertically)) {
            Text(
                text = stringResource(id = R.string.user_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp,
                ),
            )
            Spacer(modifier = modifier.height(3.dp))
            Text(
                text = stringResource(R.string.contact_user),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 14.sp,
                ),
            )
        }
    }
}