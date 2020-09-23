package com.zahid.ansarandroidtest.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.zahid.ansarandroidtest.model.CatApiResponseModel
import com.zahid.ansarandroidtest.repository.Repository
import com.zahid.ansarandroidtest.utils.IHandleAPICallBack
import com.zahid.ansarandroidtest.utils.ResultWrapper
import com.zahid.ansarandroidtest.utils.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var _application:Application = application
    var repository:Repository = Repository
    init {
        repository.init(application)
    }
    var allCategoriesMut: MutableLiveData<ResultWrapper<CatApiResponseModel>> = MutableLiveData()


    //Changing Title of App bar according to state
    var titleOfAppbar: MutableLiveData<String> = MutableLiveData()
    fun setTitleAppbar(title: String) {
        titleOfAppbar.value = title
    }


    fun requestCategories(){
        repository.getCategories(object :
            IHandleAPICallBack<CatApiResponseModel> {
            override fun handleWebserviceCallBackSuccess(response: Response<CatApiResponseModel>?) {

                CoroutineScope(Dispatchers.Main.immediate).launch {
                    allCategoriesMut.value = ResultWrapper(Status.Status.SUCCESS, response?.body(), "Success")
                }
            }

            override fun handleWebserviceCallBackFailure(error: String?) {

                CoroutineScope(Dispatchers.Main.immediate).launch {
                    allCategoriesMut.value = error?.let {
                        ResultWrapper(Status.Status.ERROR, null,
                            it
                        )
                    }
                }
            }

            override fun onConnectionError() {

                CoroutineScope(Dispatchers.Main.immediate).launch {
                    allCategoriesMut.value = ResultWrapper(Status.Status.ERROR, null, "Network Error")
                }
            }


        })
    }

    fun isLoading(): MutableLiveData<Boolean> {
        return repository.isLoading
    }


    override fun onCleared() {
        super.onCleared()
        repository.cancelJobs()
    }
}