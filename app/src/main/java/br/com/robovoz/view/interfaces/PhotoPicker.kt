package br.com.robovoz.view.interfaces

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.io.File


class PhotoPicker {
    @Composable
    fun photoPickerScreen() {
        var photoUri: Uri? by remember { mutableStateOf(null) }

        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            photoUri = uri
        }


        Column {
            Button(
                onClick = {
                    launcher.launch(
                        PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Text("Select Photo")
            }

            if (photoUri != null) {
                //Base64.encode(photoUri.toString(), Base64.DEFAULT)
                if(File(photoUri.toString()).exists()) {
                    var baseImge = Base64.encodeToString(File(photoUri.toString()).readBytes(), Base64.DEFAULT)
                    var bytes = Base64.decode(baseImge, Base64.DEFAULT)
                    var iii = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    var i = iii
                }
                val painter = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = photoUri)
                        .build()
                )
                Button(onClick = { photoUri = null } ) {
                    Text("Remover imgem")
                }
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(2.0.dp, Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }else{
                Image(
                    painter = painterResource(R.drawable.img),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(2.0.dp, Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}