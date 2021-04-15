package  com.example.marval.network

import com.example.marval.App
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
import com.example.marval.model.resource.ResourceModel
import io.reactivex.Observable


/**
 * AppRepository is the holder and repo for all network call apis
 * @author Mohamed Ibrahim
 */
class AppRepository : DataManager {
    override fun getChractersList(
        limit: Int,
        offset: Int
    ): Observable<GenericResponse<ChractersListModel>> {
        return App.getService.getChractersList(limit = limit, offset = offset)
    }

    override fun getSerchedList(
        limit: Int,
        offset: Int,
        nameStartsWith: String
    ): Observable<GenericResponse<ChractersListModel>> {
        return App.getService.getSerchedList(
            limit = limit,
            offset = offset,
            nameStartsWith = nameStartsWith
        )
    }

    override fun getComicsList(characterId: String): Observable<GenericResponse<ResourceModel>> {
        return App.getService.getComicsList(characterId)
    }

    override fun getEventsList(characterId: String): Observable<GenericResponse<ResourceModel>> {
        return App.getService.getEventsList(characterId)
    }

    override fun getSeriesList(characterId: String): Observable<GenericResponse<ResourceModel>> {
        return App.getService.getSeriesList(characterId)
    }

    override fun getStoriesList(characterId: String): Observable<GenericResponse<ResourceModel>> {
        return App.getService.getStoriesList(characterId)
    }


}