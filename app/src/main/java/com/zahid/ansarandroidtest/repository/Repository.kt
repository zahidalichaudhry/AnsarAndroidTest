package com.zahid.ansarandroidtest.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zahid.ansarandroidtest.api.MyRetrofitBuilder
import com.zahid.ansarandroidtest.model.CatApiResponseModel
import com.zahid.ansarandroidtest.utils.IHandleAPICallBack
import com.zahid.ansarandroidtest.utils.NetworkUtils

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {


    lateinit var newtworkutils: NetworkUtils
    var isLoading = MutableLiveData<Boolean>()
    fun init(application: Application) {
        newtworkutils = NetworkUtils(application)
    }


    var job: CompletableJob? = null

    //
    fun getCategories(handler: IHandleAPICallBack<CatApiResponseModel>) {
        isLoading.postValue(true)

        if (!newtworkutils.isConnectedToInternet) {
            handler.onConnectionError()
            return
        }

        job = Job()

        job?.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob).launch {
                try {
                    val getSession = MyRetrofitBuilder.apiService.categories()
                    getSession.enqueue(object :
                        Callback<CatApiResponseModel> {
                        override fun onResponse(
                            call: Call<CatApiResponseModel>,
                            response: Response<CatApiResponseModel>
                        ) {
                            isLoading.postValue(false)
                            if (response.isSuccessful) {

                                handler.handleWebserviceCallBackSuccess(response)

                            } else {
                                // Handle error returned from server
                                handler.handleWebserviceCallBackFailure(
                                    response.errorBody().toString()
                                )
                            }
                        }

                        override fun onFailure(
                            call: Call<CatApiResponseModel>,
                            t: Throwable
                        ) {
                            isLoading.postValue(false)
                            t.printStackTrace()
                            handler.handleWebserviceCallBackFailure(t.message)
                        }
                    })
                } catch (e: Exception) {
                    isLoading.postValue(false)
                    e.printStackTrace()
                    handler.handleWebserviceCallBackFailure(e.message)
                }
                withContext(Dispatchers.Main) {
                    theJob.complete()
                }
            }

        }
    }


    fun cancelJobs() {
        job?.cancel()
    }


}
















