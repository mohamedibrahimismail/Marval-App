package  com.example.marval.network.retrofit

import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
import com.example.marval.model.resource.ResourceModel
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Service is API call interface for retrofit
 * @author Mohamed Ibrahim
 */
interface Service {

    @GET("v1/public/characters")
    fun getChractersList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Observable<GenericResponse<ChractersListModel>>

    @GET("v1/public/characters")
    fun getSerchedList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("nameStartsWith") nameStartsWith: String,
    ): Observable<GenericResponse<ChractersListModel>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsList(
        @Path("characterId") characterId: String
    ): Observable<GenericResponse<ResourceModel>>

    @GET("/v1/public/characters/{characterId}/events")
    fun getEventsList(
        @Path("characterId") characterId: String
    ): Observable<GenericResponse<ResourceModel>>

    @GET("/v1/public/characters/{characterId}/series")
    fun getSeriesList(
        @Path("characterId") characterId: String
    ): Observable<GenericResponse<ResourceModel>>

    @GET("/v1/public/characters/{characterId}/stories")
    fun getStoriesList(
        @Path("characterId") characterId: String
    ): Observable<GenericResponse<ResourceModel>>


}