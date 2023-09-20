package PCOS;
import org.openqa.selenium.WebElement;

public class GetterSetter_PCOS {
	private String recipeName;
	private String recipeID;
	private WebElement gotoRecipeDetails;
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}
	public WebElement getGotoRecipeDetails() {
		return gotoRecipeDetails;
	}
	public void setGotoRecipeDetails(WebElement gotoRecipeDetails) {
		this.gotoRecipeDetails = gotoRecipeDetails;
		
	}

}
