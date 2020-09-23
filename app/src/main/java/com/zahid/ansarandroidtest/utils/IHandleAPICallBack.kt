package com.zahid.ansarandroidtest.utils

import retrofit2.Response

interface IHandleAPICallBack<T> {
    fun handleWebserviceCallBackSuccess(response: Response<T>?)
    fun handleWebserviceCallBackFailure(error: String?)
    fun onConnectionError()
}