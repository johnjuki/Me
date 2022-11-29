package com.tnh.me

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnh.me.ui.theme.MeTheme
import com.tnh.me.ui.theme.greenAndroidColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MeApp()
                }
            }
        }
    }
}

@Composable
fun MeApp() {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AndroidLogo(
                    androidLogo = painterResource(id = R.drawable.me)
                )
                PersonalInfo(
                    name = stringResource(R.string.my_name),
                    occupation = stringResource(R.string.my_occupation)
                )
            }
        }

        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
            ContactInfo()
        }
    }
}

@Composable
private fun AndroidLogo(androidLogo: Painter) {
    Image(
        painter = androidLogo,
        contentDescription = stringResource(R.string.android_logo_content_description),
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
private fun PersonalInfo(name: String, occupation: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = occupation,
            color = greenAndroidColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ContactInfo(
) {
    val context = LocalContext.current

    Column(Modifier.padding(bottom = 50.dp)) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:+254758837879")
                context.startActivity(intent)
            }) {
            ContactInfoDetails(
                iconResource = R.drawable.ic_baseline_local_phone_24,
                contentDescription = stringResource(R.string.phone_number_content_desc),
                contact = stringResource(R.string.phone)
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = (Uri.parse("https://github.com/johnjuki"))
                }
                context.startActivity(intent)
            }) {
            ContactInfoDetails(
                iconResource = R.drawable.ic_baseline_share_24,
                contentDescription = stringResource(R.string.github_content_desc),
                contact = stringResource(R.string.github_username)
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("john.prodev@proton.me"))
                }
                context.startActivity(intent)

            }) {
            ContactInfoDetails(
                iconResource = R.drawable.ic_baseline_mail_24,
                contentDescription = stringResource(R.string.email_content_desc),
                contact = stringResource(R.string.email)
            )
        }

    }
}

@Composable
private fun ContactInfoDetails(iconResource: Int, contentDescription: String, contact: String) {
    Icon(
        painter = painterResource(iconResource),
        contentDescription = contentDescription,
        tint = greenAndroidColor
    )
    Spacer(modifier = Modifier.width(20.dp))
    Text(text = contact, color = Color.White)
}

@Preview(showSystemUi = true)
@Composable
fun MeAppPreview() {
    MeTheme {
        MeApp()
    }
}
