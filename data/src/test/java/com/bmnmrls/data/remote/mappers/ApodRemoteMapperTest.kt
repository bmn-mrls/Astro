package com.bmnmrls.data.remote.mappers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.bmnmrls.data.remote.models.ApodResponse
import com.bmnmrls.domain.models.Apod
import org.junit.Test

class ApodRemoteMapperTest {

    private val apodRemoteMapper: ApodRemoteMapper by lazy { ApodRemoteMapper() }

    @Test
    fun `when apod's response has valid values, apod will have valid values`() {
        assertThat(apodRemoteMapper.transform(apodResponse))
            .isEqualTo(apod)
    }

    @Test
    fun `when apod's response has null values, apod will set empty values`() {
        assertThat(apodRemoteMapper.transform(nullApodResponse))
            .isEqualTo(emptyApod)
    }

    companion object {
        private const val COPYRIGHT = "Mike Smolinsky"
        private const val DATE = "2021-01-02"
        private val DESCRIPTION =
            """In the mid 19th century, one of the first photographic technologies 
                |used to record the lunar surface was the wet-plate collodion process, notably 
                |employed by British astronomer Warren De la Rue.""".trimMargin()
        private const val HD_URL =
            "https://apod.nasa.gov/apod/image/2101/WetCollodionLunar112820SMO.jpg"
        private const val MEDIA_TYPE = "image"
        private const val SERVICE_VERSION = "v1"
        private const val TITLE = "21st Century Wet Collodion Moon"
        private const val URL =
            "https://apod.nasa.gov/apod/image/2101/WetCollodionLunar112820SMO_1024.jpg"
        private val nullApodResponse = ApodResponse()
        private val emptyApod = Apod("", "", "", "", "", "", "")
        private val apodResponse = ApodResponse(
            copyright = COPYRIGHT,
            date = DATE,
            description = DESCRIPTION,
            hdUrl = HD_URL,
            mediaType = MEDIA_TYPE,
            serviceVersion = SERVICE_VERSION,
            title = TITLE,
            url = URL
        )
        private val apod: Apod = Apod(
            copyright = COPYRIGHT,
            date = DATE,
            description = DESCRIPTION,
            hdUrl = HD_URL,
            mediaType = MEDIA_TYPE,
            title = TITLE,
            url = URL
        )
    }
}
