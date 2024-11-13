package com.example.myapplication.view.interfaces

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
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
import com.example.myapplication.R


class PhotoPicker {
    @Composable
    fun photoPickerScreen(tttttt: MutableState<String>) {
        var photoUri: Uri? by remember { mutableStateOf(null) }

        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            photoUri = uri


//FUNCIONA A CONVERSÃO
//            var input: InputStream? = uri?.let { context.contentResolver.openInputStream(it) }
//
//            val onlyBoundsOptions = BitmapFactory.Options()
//            onlyBoundsOptions.inJustDecodeBounds = true
//            onlyBoundsOptions.inDither = true //optional
//            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888 //optional
//            BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
//            input?.close()
//
//            val originalSize =
//                if ((onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth)) onlyBoundsOptions.outHeight else onlyBoundsOptions.outWidth
//
//            //val ratio = if ((originalSize > THUMBNAIL_SIZE)) (originalSize / THUMBNAIL_SIZE) else 1.0
//
//            val bitmapOptions = BitmapFactory.Options()
//           // bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio)
//            bitmapOptions.inDither = true //optional
//            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888 //optional
//            input = uri?.let { context.contentResolver.openInputStream(it) }
//            val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
//            input?.close()
//
//            val outputStream = ByteArrayOutputStream()
//            bitmap!!.compress(Bitmap.CompressFormat.PNG, 10, outputStream)
//
//            var str = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
//            var x = str
//
//            val decodedBytes: ByteArray = Base64.decode(
//                str.substring(str.indexOf(",") + 1),
//                Base64.DEFAULT
//            )
//
//            var btm = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }

        Column {
            Button(
                onClick = {
                    launcher.launch("image/*")
                }
            ) {
                Text("Selecionar Foto")
            }

            if (photoUri != null) {
                tttttt.value = photoUri!!.path.toString()
                val image: ImageRequest = ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = photoUri)
                    .build()

                val painter = rememberAsyncImagePainter(
                    image
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

