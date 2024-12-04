package com.example.dbnetworkpatterns

/**
Реализовать Interceptor, который будет логировать код ответа сервера. */

class LogInterceptor() : Interceptor {
    override fun intercept(chain: Chain): Response {
        return chain.proceed(chain.request()).apply {
            Log.d("logTAG", "<-- START>")
            Log.d("logTAG", "code: $code")
            Log.d("logTAG", "headers: $headers")
            Log.d("logTAG", "body: ${peekBody(Long.MAX_VALUE).string()}")
            Log.d("logTAG", "END -->")

        }
    }
}