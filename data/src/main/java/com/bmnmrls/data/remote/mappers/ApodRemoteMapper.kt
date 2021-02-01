package com.bmnmrls.data.remote.mappers

import com.bmnmrls.data.remote.models.ApodResponse
import com.bmnmrls.domain.mappers.Transformer
import com.bmnmrls.domain.models.Apod

class ApodRemoteMapper : Transformer<ApodResponse, Apod>() {

    override fun transform(value: ApodResponse): Apod = Apod(
        value.copyright.orEmpty(),
        value.date.orEmpty(),
        value.description.orEmpty(),
        value.hdUrl.orEmpty(),
        value.mediaType.orEmpty(),
        value.title.orEmpty(),
        value.url.orEmpty()
    )
}
