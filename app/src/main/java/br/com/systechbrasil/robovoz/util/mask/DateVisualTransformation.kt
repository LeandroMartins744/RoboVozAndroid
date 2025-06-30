package br.com.systechbrasil.robovoz.util.mask

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation(): VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val maskPhone = text.text.mapIndexed { index, c ->
            when(index){
                1 -> "$c/"
                3 -> "$c/"
                else -> c
            }
        }.joinToString(separator = "")

        return TransformedText(
            AnnotatedString(maskPhone),
            offsetMapping = PhoneOffSetMapping
        )
    }

    object PhoneOffSetMapping: OffsetMapping{
        override fun originalToTransformed(offset: Int): Int {
            return when{
                offset > 4 -> offset + 2
                offset > 1 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when{
                offset > 6 -> offset - 2
                offset > 0 -> offset - 1
                else -> offset
            }
        }

    }
}