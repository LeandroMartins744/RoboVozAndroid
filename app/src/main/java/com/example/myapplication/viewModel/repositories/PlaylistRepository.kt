//package com.example.myapplication.viewModel.repositories
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import com.example.myapplication.model.response.UserResponse
//import com.example.myapplication.client.AuthTokenService
//import com.example.myapplication.client.PlaylistEndpoints
//import com.example.myapplication.client.RetrofitInitializer
//import com.example.myapplication.client.UsersEndpoints
//import com.example.myapplication.model.request.PlayListRequest
//import com.example.myapplication.model.response.PlayListResponse
//import com.example.myapplication.model.response.UserRequest
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class PlaylistRepository {
//    private val call = RetrofitInitializer.getInstance().create(PlaylistEndpoints::class.java)
//
////    fun get(data: MutableLiveData<ArrayList<PlayListResponse>>){
////        call.get(AuthTokenService().getAuthToken())
////            .enqueue(object: Callback<ArrayList<PlayListResponse>>{
////                override fun onResponse(call: Call<ArrayList<PlayListResponse>>, response: Response<ArrayList<PlayListResponse>>) {
////                    Log.d("TAG", "onResponse response:: $response")
////
////                    if(response.body() != null)
////                        data.value = response.body()!!
////                }
////
////                override fun onFailure(call: Call<ArrayList<PlayListResponse>>, response: Throwable) {
////                    Log.d("TAG ERROS", "onResponse response:: $response")
////                    data.value = null
////                }
////            })
////    }
//
//    fun get(id: Int, data: MutableLiveData<PlayListResponse>){
//        call.get(AuthTokenService().getAuthToken(), id)
//            .enqueue(object: Callback<PlayListResponse>{
//                override fun onResponse(call: Call<PlayListResponse>, response: Response<PlayListResponse>) {
//                    Log.d("TAG", "onResponse response:: $response")
//
//                    if(response.body() != null)
//                        data.value = response.body()!!
//                }
//
//                override fun onFailure(call: Call<PlayListResponse>, response: Throwable) {
//                    Log.d("TAG ERROS", "onResponse response:: $response")
//                    data.value = null
//                }
//            })
//    }
//
//    fun post(obj: PlayListRequest, data: MutableLiveData<PlayListResponse>){
//        call.post(AuthTokenService().getAuthToken(), obj)
//            .enqueue(object: Callback<PlayListResponse>{
//                override fun onResponse(call: Call<PlayListResponse>, response: Response<PlayListResponse>) {
//                    Log.d("TAG", "onResponse response:: $response")
//
//                    if(response.body() != null)
//                        data.value = response.body()!!
//                }
//
//                override fun onFailure(call: Call<PlayListResponse>, response: Throwable) {
//                    Log.d("TAG ERROS", "onResponse response:: $response")
//                    data.value = null
//                }
//            })
//    }
//
//    fun put(obj: PlayListRequest, data: MutableLiveData<PlayListResponse>){
//        call.put(AuthTokenService().getAuthToken(), obj)
//            .enqueue(object: Callback<PlayListResponse>{
//                override fun onResponse(call: Call<PlayListResponse>, response: Response<PlayListResponse>) {
//                    Log.d("TAG", "onResponse response:: $response")
//
//                    if(response.body() != null)
//                        data.value = response.body()!!
//                }
//
//                override fun onFailure(call: Call<PlayListResponse>, response: Throwable) {
//                    Log.d("TAG ERROS", "onResponse response:: $response")
//                    data.value = null
//                }
//            })
//    }
//
//    fun del(obj: Int, data: MutableLiveData<Int>){
//        call.delete(AuthTokenService().getAuthToken(), obj)
//            .enqueue(object: Callback<Int>{
//                override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                    Log.d("TAG", "onResponse response:: $response")
//
//                    if(response.body() != null)
//                        data.value = response.body()!!
//                }
//
//                override fun onFailure(call: Call<Int>, response: Throwable) {
//                    Log.d("TAG ERROS", "onResponse response:: $response")
//                    data.value = null
//                }
//            })
//    }
//}