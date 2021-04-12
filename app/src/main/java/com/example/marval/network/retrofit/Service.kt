package  com.example.marval.network.retrofit

import com.example.marval.model.GenericResponse
import com.example.marval.model.main.CompletereServation
import com.example.marval.model.main.ServiceModel
import com.example.marval.model.main.SliderModel
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Service is API call interface for retrofit
 * @author Mohamed Ibrahim
 */
interface Service {

    @GET("slider")
    fun getMainSliders(): Observable<GenericResponse<MutableList<SliderModel>>>

    @GET("sirvices")
    fun getServices(): Observable<GenericResponse<MutableList<ServiceModel>>>

    @POST("complete_resevation")
    fun completeReservation(
        @Body completereServation: CompletereServation
    ): Observable<GenericResponse<ServiceModel>>


}