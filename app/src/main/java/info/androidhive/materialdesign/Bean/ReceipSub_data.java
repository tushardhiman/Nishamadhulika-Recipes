package info.androidhive.materialdesign.Bean;

public class ReceipSub_data {
	
	public String ID="";
	public String Description="";
	public String Title="";
	public String ThumbURL="";
	
	
	public String GetThumbURL(){
		return ThumbURL;
	}
	
	public void SetThumbURL(String ThumbURL)
	{
		this.ThumbURL=ThumbURL;
	}
	
	public String GetTitle()
	{
		return Title;
	}
	
	public void SetTitle(String Title){
		this.Title=Title;
	}
	
	
	
	public String GetID()
	{
		return ID;
	}
	
	
	public void SetID(String ID)
	{
		this.ID=ID;
	}
	
	
	public String GetDescp()
	{
	return Description;
	}
	
	
	public void SetDescp(String Description){
		this.Description=Description;
	}
}
