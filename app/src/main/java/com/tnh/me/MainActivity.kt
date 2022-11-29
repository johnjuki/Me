package com.tnh.me

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.viewinterop.AndroidView
import com.tnh.me.ui.theme.MeTheme
import com.tnh.me.ui.theme.greenAndroidColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeTheme {
                Surface(
                    color = Color.Black
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
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Column {
                Profile(
                    photo = painterResource(id = R.drawable.me),
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
private fun Profile(photo: Painter, name: String, occupation: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = photo,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = name,
            color = Color.White,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
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
        ContactInfoButton(
            iconResourceId = R.drawable.ic_baseline_local_phone_24,
            contentDescription = stringResource(R.string.phone_number_content_desc),
            contact = stringResource(R.string.phone)
        ) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+254758837879")
            context.startActivity(intent)
        }

        ContactInfoButton(
            iconResourceId = R.drawable.ic_baseline_share_24,
            contentDescription = stringResource(R.string.github_content_desc),
            contact = stringResource(R.string.github_username)
        ) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = (Uri.parse("https://github.com/johnjuki"))
            }
            context.startActivity(intent)
        }

        ContactInfoButton(
            iconResourceId = R.drawable.ic_baseline_mail_24,
            contentDescription = stringResource(R.string.email_content_desc),
            contact = stringResource(R.string.email)
        ) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("john.prodev@proton.me"))
            }
            context.startActivity(intent)
        }
    }
}

@Composable
private fun ContactInfoButton(
    iconResourceId: Int,
    contentDescription: String,
    contact: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)) {
        Icon(
            painter = painterResource(iconResourceId),
            contentDescription = contentDescription,
            tint = greenAndroidColor
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = contact, color = Color.White)
    }
}

/**
 * Loads my Github profile URL in a WebView
 */
@Composable
private fun LoadGithubUrl() {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl("https://github.com/johnjuki")
        }
    }, update = {
        it.loadUrl("https://github.com/johnjuki")
    })
}

@Preview(showSystemUi = true)
@Composable
fun MeAppPreview() {
    MeTheme {
        MeApp()
    }
}
