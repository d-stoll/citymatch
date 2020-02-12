package de.clines.places.controller

import de.clines.places.service.PhotoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Api(tags = ["Place Photos"], description = "Endpoints for retrieving photos of places")
@RequestMapping("/photos")
@Controller
class PhotoController(
        private val photoService: PhotoService
) {

    @ApiOperation("Returns the photo with the specified photo reference",
            notes = "Fetches the photo with the given reference from the Google Maps Places API. For reducing the " +
                    "resolution of the photo, specify the maxWidth and maxHeight query parameters. Both values " +
                    "should be between 0 and 1600. If not specified they have a default value of 1600.")
    @GetMapping("/{photoReference}")
    @ResponseBody
    fun getPhoto(@PathVariable("photoReference") photoReference: String,
                 @RequestParam("maxWidth") maxWidth: Int?,
                 @RequestParam("maxHeight") maxHeight: Int?): ByteArray {
        return photoService.fetchPhoto(photoReference, maxWidth ?: 1600, maxHeight ?: 1600)
    }

}