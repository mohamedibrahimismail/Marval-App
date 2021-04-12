package  com.example.marval.network

import com.example.marval.App
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.CompletereServation
import com.example.marval.model.main.ServiceModel
import com.example.marval.model.main.SliderModel
import io.reactivex.Observable


/**
 * AppRepository is the holder and repo for all network call apis
 * @author Mohamed Ibrahim
 */
class AppRepository : DataManager {
    override fun getMainSliders(): Observable<GenericResponse<MutableList<SliderModel>>> {
        return App.getService.getMainSliders()
    }

    override fun getServices(): Observable<GenericResponse<MutableList<ServiceModel>>> {
        return App.getService.getServices()
    }

    override fun completeReservation(completereServation: CompletereServation): Observable<GenericResponse<ServiceModel>> {
        return App.getService.completeReservation(completereServation)
    }
}