package com.example.app_consumo_retrofit.service

import okhttp3.Interceptor
import okhttp3.Response

class GeoLocationInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requisicaoOriginal = chain.request()

        return chain.proceed(requisicaoOriginal)
    }

}
