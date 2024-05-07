package com.example.fit5046_a3.screens_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fit5046_a3.R
import com.example.fit5046_a3.viewmodel.UserViewModel

@Composable
fun ProfileScreen(userViewModel: UserViewModel, userEmail: String) {
    // Observe the user profile data as LiveData
    val userProfile by userViewModel.getUserProfile(userEmail).observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Show profile details dynamically based on the user's data
        userProfile?.let { user ->
            Text(
                text = "Profile Photo",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            ProfileAvatar() // Load user's avatar, customize as needed
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem("Email Address", user.email)
            ProfileItem("User Name", user.userName)
            ProfileItem("Gender", user.gender)
            ProfileItem("Date of Birth", user.dateOfBirth)
            ProfileItem("Weight", "${user.weight} kg")
            ProfileItem("Height", "${user.height} cm")
        } ?: run {
            Text("Loading user profile...")
        }

        Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to top

        Button(
            onClick = {
                // Implement logic to edit profile
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Change details")
        }
    }
}

@Composable
fun ProfileAvatar() {
    // Load user's avatar image
    val avatar: Painter = painterResource(id = R.drawable.user_avatar)

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = avatar,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
//        Divider(color = Color.Gray, thickness = 0.5.dp)
        Divider(modifier = Modifier.padding(vertical = 6.dp))


    }

}

