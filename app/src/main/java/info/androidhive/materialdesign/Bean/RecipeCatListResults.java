package info.androidhive.materialdesign.Bean;


public class RecipeCatListResults{
	private String recipecat_name="",category_id="",article_count="";
	
	public void setRecipeCategoryName(String profile_name)
	{
		this.recipecat_name=profile_name;
	}
	
	public void setRecipeCategoryID(String category_id)
	{
		this.category_id=category_id;
	}
	
	public void setRecipeArticleCount(String article_count)
	{
		this.article_count=article_count;
	}	
	
	public String getRecipeCategoryName()
	{
		return recipecat_name;
	}
	public String getRecipeCategoryID()
	{
		return category_id;
	}
	public String getRecipeArticleCount()
	{
		return article_count;
	}
}
