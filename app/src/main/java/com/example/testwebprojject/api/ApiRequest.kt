package com.example.testwebprojject.api


import com.example.testwebprojject.Catfact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
/**
 * Интерфейс Сервлет запроса
 *
 *
 */
interface ApiRequest {
    @GET("./fact")
    @Headers("Content-Type: application/json")
            /** Функция Возвращает значения полученные из Api Get-Запроса
             * @return CatFact class
             *
             */
    fun getQuestListItem(): Call<Catfact>

}