package  com.example.marval.network

import com.example.marval.App
import com.example.marval.model.GenericResponse
import com.example.marval.model.main.ChractersListModel
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


}