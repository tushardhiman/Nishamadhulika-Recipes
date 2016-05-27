package info.androidhive.materialdesign.Bean;

/**
 * Created by EnemSML2 on 20-05-2016.
 */
public class LatestdishPojo {
    String title;
    Integer _id;
    String url;


    public LatestdishPojo(Integer _id,String title,  String url) {
        this.title = title;
        this._id = _id;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        this._id = id;
    }


}
