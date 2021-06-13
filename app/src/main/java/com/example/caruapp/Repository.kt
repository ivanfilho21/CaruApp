package com.example.caruapp

import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Repository {
    companion object RequestType {
        const val GET = "GET"
        const val POST = "POST"
    }

    fun<T> communicate(
        request: String,
        path: String,
        modelClass: Class<T>
    ): Result<T> {
        val connection = URL(path).openConnection() as HttpURLConnection
        var response = ""

        try {
            connection.run {
                doInput = true
//            doOutput = true  used with POST
                requestMethod = request
                readTimeout = 1000 * 60
                connectTimeout = 1000 * 60
                setRequestProperty("Accept", "application/json")
                setRequestProperty("Content-Type", "application/json; utf-8")

                val isr = InputStreamReader(inputStream, "utf-8")
                val br = BufferedReader(isr)
                val sb = StringBuilder()
                var line: String? = ""

                while (line != null) {
                    sb.append(line)
                    line = br.readLine()
                }

                br.close()
                response = sb.toString()

                Log.e(javaClass.simpleName, response)
            }
        } catch (e: java.lang.Exception) {
            return Result.Error(e)
        }

        val obj = Gson().fromJson(response, modelClass)
        return Result.Success(obj)
    }

    sealed class Result<out R> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }
}