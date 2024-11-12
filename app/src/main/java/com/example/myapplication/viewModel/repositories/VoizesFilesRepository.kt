//package com.example.myapplication.viewModel.repositories
//
//import com.example.myapplication.client.AudioEndpoints
//import com.example.myapplication.client.AuthTokenService
//import com.example.myapplication.client.RetrofitInitializer
//import kotlinx.coroutines.*
//import retrofit2.HttpException
//import java.io.IOException
//
//class VoicesFilesRepository {
//    private val call = RetrofitInitializer.getInstance().create(AudioEndpoints::class.java)
//
//    suspend fun getUser(obj: String) {
//        val response = call.download(AuthTokenService().getAuthToken(), obj).execute()
//        if (response.isSuccessful) {
//            val input = response.body()?.byteStream()?.bufferedReader() ?: throw Exception()
//            try {
//                while (currentCoroutineContext().isActive) {
//                    val line = withContext(Dispatchers.IO) {
//                        input.readLine()
//                    }
//                    if (line != null && line.startsWith("data:")) {
//                        try {
////                                val answerDetailInfo = gson.fromJson(
////                                    line.substring(5).trim(),
////                                    ChatGPTResponse::class.java
////                                )
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//                    }
//                    delay(100)
//                }
//            } catch (e: IOException) {
//                throw Exception(e)
//            } finally {
//                input.close()
//            }
//        } else {
//            throw HttpException(response)
//        }
//    }
//}