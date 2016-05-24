package info.androidhive.materialdesign.Bean;

import android.graphics.Bitmap;

public class RecipeSubCatListResults {

	String str_Title="",str_URL="",str_VideoURL="",str_HtmlBody="",str_picurl;
	Bitmap thumbnail;
	public void setTitle(String str_Title) {
		this.str_Title=str_Title;
	}

	public String getTitle() {
		return str_Title;
	}
	
	public void setHtmlURL(String str_URL) {
		this.str_URL=str_URL;
	}

	public String getHtmlURL() {
		return str_URL;
	}
	
	public void setVideoURL(String str_VideoURL) {
		this.str_VideoURL=str_VideoURL;
	}

	public String getVideoURL() {
		return str_VideoURL;
	}
	
	public void setPic(Bitmap thumbnail) {
		this.thumbnail=thumbnail;
	}

	public Bitmap getPic() {
		return thumbnail;
	}
	
	public void setPicURL(String PicUrl) {
		this.str_picurl=PicUrl;
	}

	public String getPicURL() {
		return str_picurl;
	}
	
	public void setHtmlBody(String str_HtmlBody) {
		this.str_HtmlBody=str_HtmlBody;
	}

	public String getHtmlBody (){
		return str_HtmlBody;
	}
}
