package  com.example.marval.network.retrofit

import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
//
//    @GET("sirvices")
//    fun getServices(): Observable<GenericResponse<MutableList<ServiceModel>>>
//
//    @POST("complete_resevation")
//    fun completeReservation(
//        @Body completereServation: CompletereServation
//    ): Observable<GenericResponse<ServiceModel>>


}