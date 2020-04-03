
package application.haveri.tourism.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MediaGallery implements Serializable {
    @SerializedName("images")
    @Expose
    private List<Images> imagesData = null;
    @SerializedName("videos")
    @Expose
    private List<Videos> videosData = null;

    public List<Images> getImagesData() {
        if(imagesData == null) {
            imagesData =  new ArrayList<>();
        }
        return imagesData;
    }

    public void setImagesData(List<Images> imagesData) {
        this.imagesData = imagesData;
    }

    public List<Videos> getVideosData() {
        if(videosData == null) {
            videosData =  new ArrayList<>();
        }
        return videosData;
    }

    public void setVideosData(List<Videos> videosData) {
        this.videosData = videosData;
    }
}
