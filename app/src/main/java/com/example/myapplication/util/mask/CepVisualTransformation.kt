package com.example.myapplication.util.mask

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CepVisualTransformation(): VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val maskPhone = text.text.mapIndexed { index, c ->
            when(index){
                4 -> "$c-"
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
                offset > 4 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when{
                offset > 4 -> offset - 1
                else -> offset
            }
        }

    }
}