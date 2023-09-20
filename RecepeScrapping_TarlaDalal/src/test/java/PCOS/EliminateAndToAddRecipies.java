package PCOS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelUtils.ReadFromExcel;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EliminateAndToAddRecipies {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://tarladalal.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]")).click();
		driver.findElement(By.id("ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335")).click();
		
		int paginationSize = driver.findElements(By.cssSelector("#pagination>a")).size();
		
		List<String> eliRecID=new ArrayList<String>();
		List<String> eliRecName=new ArrayList<String>();
		List<String> eliPreparationTime=new ArrayList<String>();
		List<String> eliCookTime=new ArrayList<String>();
		List<String> eliIngradients=new ArrayList<String>();
		List<String> eliPreparationMethod=new ArrayList<String>();
		List<String> eliNutritionValue = new ArrayList<String>();
		List<String> eliRecipeUrl=new ArrayList<String>();
	//ToAdd	
		List<String> addRecID=new ArrayList<String>();
		List<String> addRecName=new ArrayList<String>();
		List<String> addPreparationTime=new ArrayList<String>();
		List<String> addCookTime=new ArrayList<String>();
		List<String> addIngradients=new ArrayList<String>();
		List<String> addPreparationMethod=new ArrayList<String>();
		List<String> addNutritionValue = new ArrayList<String>();
		List<String> addRecipeUrl=new ArrayList<String>();
		
		List<String> allergyRecID=new ArrayList<String>();
		List<String> allergyRecName=new ArrayList<String>();
		List<String> allergyPreparationTime=new ArrayList<String>();
		List<String> allergyCookTime=new ArrayList<String>();
		List<String> allergyIngradients=new ArrayList<String>();
		List<String> allergyPreparationMethod=new ArrayList<String>();
		List<String> allergyNutritionValue = new ArrayList<String>();
		List<String> allergyRecipeUrl=new ArrayList<String>();
//nutsAllergy		
		List<String> nutsallergyRecID=new ArrayList<String>();
		List<String> nutsallergyRecName=new ArrayList<String>();
		List<String> nutsallergyPreparationTime=new ArrayList<String>();
		List<String> nutsallergyCookTime=new ArrayList<String>();
		List<String> nutsallergyIngradients=new ArrayList<String>();
		List<String> nutsallergyPreparationMethod=new ArrayList<String>();
		List<String> nutsallergyNutritionValue = new ArrayList<String>();
		List<String> nutsallergyRecipeUrl=new ArrayList<String>();
	//Milk allergy
		
		List<String> milkallergyRecID=new ArrayList<String>();
		List<String> milkallergyRecName=new ArrayList<String>();
		List<String> milkallergyPreparationTime=new ArrayList<String>();
		List<String> milkallergyCookTime=new ArrayList<String>();
		List<String> milkallergyIngradients=new ArrayList<String>();
		List<String> milkallergyPreparationMethod=new ArrayList<String>();
		List<String> milkallergyNutritionValue = new ArrayList<String>();
		List<String> milkallergyRecipeUrl=new ArrayList<String>();
					
		
	//Eliminate Part
		ReadFromExcel object= new ReadFromExcel();		
		List<String> ingradientsToEliminate = object.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\IngredientsDataInput.xlsx", "ToEliminate");
		List<String> fruitsVeggiesToAdd = object.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\IngredientsDataInput.xlsx", "ToAdd");
		List<String> allergiesToAdd = object.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\IngredientsDataInput.xlsx", "Allergies");
		List<String> nutsAllergiesToAdd = object.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\IngredientsDataInput.xlsx", "Nut_Allergies");
		List<String> milkAllergiesToAdd = object.getData(System.getProperty("user.dir") + "\\src\\test\\resources\\IngredientsDataInput.xlsx", "Milk_Allergy");					
		try {
		for(int k = 1; k <= paginationSize; k++)
			//for(int k=0;k<2;k++)

		{
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
			String paginationSelector ="#pagination>a:nth-child("+k+")";
			w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(paginationSelector)));
			driver.findElement(By.cssSelector(paginationSelector)).click();
			List<WebElement> recipeCards = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));
			//for(int j=0;j<recipeCards.size();j++)
			for(int j=8;j<17;j++)
			{
				List<WebElement> recipeName= driver.findElements(By.xpath("//span[@class='rcc_recipename']"));

			List<WebElement> recCards = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));
			GetterSetter_PCOS obj = new GetterSetter_PCOS();
			String RecipeId = recCards.get(j).getText();
			
			String[] id1 = RecipeId.split("\n");
			String id2 = id1[0];
			String[] id3 = id2.split("#");
			String id4 = id3[1];
			obj.setRecipeID(id4);
			System.out.println("RecipeId = " + obj.getRecipeID());
			//recID.add(obj.getRecipeID());			
			obj.setRecipeName(recipeName.get(j).getText());						
			String RecipeName = obj.getRecipeName(); 
			System.out.println(RecipeName +" Loop - "+j);
			//recName.add(RecipeName);
			
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='rcc_recipename']")));
			recipeName.get(j).click();
			
			String prepTime = driver.findElement(By.xpath("//p/time[@itemprop = 'prepTime']")).getText();
			System.out.println("PreparationTime = " + prepTime);
			//preparationTime.add(prepTime);
			
			String cookTime = driver.findElement(By.xpath("//p/time[@itemprop = 'cookTime']")).getText();
			System.out.println("cookingTime = " + cookTime);
			//CookTime.add(cookTime);
			
			String prepMethod = driver.findElement(By.id("ctl00_cntrightpanel_pnlRcpMethod")).getText();
			System.out.println("preparationMethod = " + prepMethod);
			//preparationMethod.add(prepMethod);
			
			String nutritionalValue = driver.findElement(By.id("rcpnutrients")).getText();
			System.out.println("NutritionalValue = " + nutritionalValue);
			
			System.out.println("Recipe Url = " + driver.getCurrentUrl());
			//recipeUrl.add(driver.getCurrentUrl());
			
			//List eliminateList = Arrays.asList(ingradientsToEliminate);
			String ingredientList = driver.findElement(By.id("rcpinglist")).getText();
			String[] ingradientsArray=ingredientList.split(" ");
			System.out.println("ingredientList = " + ingredientList);
			System.out.println(ingradientsToEliminate.size());
			boolean eliminateRec = false;
			boolean addRec = false;
			boolean allergyRec = false;
			boolean nutsAllergyRec = false;
			boolean milkAllergyRec = false;
	//Elimination Part		
			for(int x=0;x<ingradientsToEliminate.size();x++){
				for(int y=0; y<ingradientsArray.length; y++) {
							
			if(ingradientsToEliminate.get(x).equals(ingradientsArray[y])) {
				System.out.println("Match Found for eliminate" +ingradientsToEliminate.get(x));
				eliminateRec = true;
				break;
			} } }
			
			if (eliminateRec == false) {
				eliRecID.add(obj.getRecipeID());	
				eliRecName.add(RecipeName);
				eliPreparationTime.add(prepTime);
				eliCookTime.add(cookTime);
				eliIngradients.add(ingredientList);
				eliPreparationMethod.add(prepMethod);
				eliNutritionValue.add(nutritionalValue);
				eliRecipeUrl.add(driver.getCurrentUrl());
			}
	//ToAdd part
			for(int p=0;p<fruitsVeggiesToAdd.size();p++){
				
				for(int q=0; q<ingradientsArray.length; q++) {
									
			if(fruitsVeggiesToAdd.get(p).equals(ingradientsArray[q])) {
				System.out.println("Match Found for Add " +fruitsVeggiesToAdd.get(p));
				addRec = true;
				break;
			}
				}
			}
	
			
			if ((eliminateRec == false)&&(addRec == true)) {
				addRecID.add(obj.getRecipeID());	
				addRecName.add(RecipeName);
				addPreparationTime.add(prepTime);
				addCookTime.add(cookTime);
				addIngradients.add(ingredientList);
				addPreparationMethod.add(prepMethod);
				addNutritionValue.add(nutritionalValue);
				addRecipeUrl.add(driver.getCurrentUrl());
			}
  //Allergy Part
			
			for(int r=0;r<allergiesToAdd.size();r++){
				
				for(int s=0; s<ingradientsArray.length; s++) {
									
			if(allergiesToAdd.get(r).equals(ingradientsArray[s])) {
				System.out.println("Match Found for Add " +allergiesToAdd.get(r));
				allergyRec = true;
				break;
			}
				}
			}
			if ((eliminateRec == false)&&(allergyRec == true)) {
				allergyRecID.add(obj.getRecipeID());	
				allergyRecName.add(RecipeName);
				allergyPreparationTime.add(prepTime);
				allergyCookTime.add(cookTime);
				allergyIngradients.add(ingredientList);
				allergyPreparationMethod.add(prepMethod);
				allergyNutritionValue.add(nutritionalValue);
				allergyRecipeUrl.add(driver.getCurrentUrl());
			}
//NutsAllergy			
			for(int r=0;r<nutsAllergiesToAdd.size();r++){
				
				for(int s=0; s<ingradientsArray.length; s++) {
									
			if(nutsAllergiesToAdd.get(r).equals(ingradientsArray[s])) {
				System.out.println("Match Found for Add " +nutsAllergiesToAdd.get(r));
				nutsAllergyRec = true;
				break;
			}
				}
			}
			if ((eliminateRec == false)&&(nutsAllergyRec == true)) {
				nutsallergyRecID.add(obj.getRecipeID());	
				nutsallergyRecName.add(RecipeName);
				nutsallergyPreparationTime.add(prepTime);
				nutsallergyCookTime.add(cookTime);
				nutsallergyIngradients.add(ingredientList);
				nutsallergyPreparationMethod.add(prepMethod);
				nutsallergyNutritionValue.add(nutritionalValue);
				nutsallergyRecipeUrl.add(driver.getCurrentUrl());
			}
		//Milk Allergy
			for(int r=0;r<milkAllergiesToAdd.size();r++){
				
				for(int s=0; s<ingradientsArray.length; s++) {
									
			if(milkAllergiesToAdd.get(r).equals(ingradientsArray[s])) {
				System.out.println("Match Found for Add " +milkAllergiesToAdd.get(r));
				milkAllergyRec = true;
				break;
			}
				}
			}
			if ((eliminateRec == false)&&(milkAllergyRec == true)) {
				milkallergyRecID.add(obj.getRecipeID());	
				milkallergyRecName.add(RecipeName);
				milkallergyPreparationTime.add(prepTime);
				milkallergyCookTime.add(cookTime);
				milkallergyIngradients.add(ingredientList);
				milkallergyPreparationMethod.add(prepMethod);
				milkallergyNutritionValue.add(nutritionalValue);
				milkallergyRecipeUrl.add(driver.getCurrentUrl());
			}								
			driver.navigate().back();
			}
		}
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		} finally {			
			addRecipeToExcel("RecipeScrapping_PCOS.xlsx", "eliminateRecepePCOS", eliRecID, eliRecName, eliPreparationTime, eliCookTime, eliIngradients, eliPreparationMethod, eliNutritionValue, eliRecipeUrl);
			addRecipeToExcel("RecipeToAdd.xlsx", "FruVegToAdd", addRecID, addRecName, addPreparationTime, addCookTime, addIngradients, addPreparationMethod, addNutritionValue, addRecipeUrl);
			addRecipeToExcel("AllergyRecepies.xlsx", "AllergyToAdd", allergyRecID, allergyRecName, allergyPreparationTime, allergyCookTime, allergyIngradients, allergyPreparationMethod, allergyNutritionValue, allergyRecipeUrl);
			addRecipeToExcel("NutsAllergyRecepies.xlsx", "NutsAllergyToAdd", nutsallergyRecID, nutsallergyRecName, nutsallergyPreparationTime, nutsallergyCookTime, nutsallergyIngradients, nutsallergyPreparationMethod, nutsallergyNutritionValue, nutsallergyRecipeUrl);
			addRecipeToExcel("MilkAllergyRecepies.xlsx", "MilkAllergyToAdd", milkallergyRecID, milkallergyRecName, milkallergyPreparationTime, milkallergyCookTime, milkallergyIngradients, milkallergyPreparationMethod, milkallergyNutritionValue, milkallergyRecipeUrl);		
		driver.close();
		}
	}
		
	
public static void addRecipeToExcel(String file, String sheetName,List<String> recId, List<String> recName, List<String> preptnTime, List<String> cookingTime, List<String> ingradients, List<String> preptnMethod, List<String> nutriVal, List<String> recURL ) throws IOException
{
	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\PCOSExcelOutput\\"+file;
	System.out.println(sheetName);
	File xlFile = new File(filePath);
	XSSFWorkbook workBook = new XSSFWorkbook();	
	XSSFSheet workSheet = workBook.createSheet(sheetName);			
	Row rowRecId= workSheet.createRow(0);
	Row rowRecName= workSheet.createRow(1);
	Row rowFoodCat = workSheet.createRow(2);
	Row rowPrepTime = workSheet.createRow(3);
	Row rowCookTime = workSheet.createRow(4);
	Row rowIngradientsList = workSheet.createRow(5);
	Row rowPrepMethod = workSheet.createRow(6);
	Row rownutritionalValue = workSheet.createRow(7);
	Row rowRecipeURL = workSheet.createRow(8);
	
	int arrSize = recId.size();
	for (int m = 0; m < arrSize; m++) {
		workSheet.setColumnWidth(m, 10000);
		workSheet.setDefaultRowHeight((short) 600);
										
		if(m==0){
			Cell cellHeader1= rowRecId.createCell(m);
			Cell cellHeader2= rowRecName.createCell(m);
			Cell cellHeader3= rowFoodCat.createCell(m);

			Cell cellHeader4 = rowCookTime.createCell(m);
			Cell cellHeader5 = rowIngradientsList.createCell(m);
			Cell cellHeader6 = rowPrepMethod.createCell(m);
			
			Cell cellHeader7 = rowRecipeURL.createCell(m);
			Cell cellHeader8 = rowPrepTime.createCell(m);
			Cell cellHeader9 = rownutritionalValue.createCell(m);
			
			
			cellHeader1.setCellValue("Recipe ID");
			cellHeader2.setCellValue("Recipe Name");
			cellHeader3.setCellValue("Food Category");

			cellHeader4.setCellValue("Cooking Time");
			cellHeader5.setCellValue("Ingradients List");
			cellHeader6.setCellValue("Preparation Method");
			cellHeader7.setCellValue("Recipe URL");
			cellHeader8.setCellValue("Preparation Time");
			cellHeader9.setCellValue("Nutritional Value");			
		}		
			Cell cellId= rowRecId.createCell(m+1);
			Cell cellName= rowRecName.createCell(m+1);
			Cell foodCat = rowFoodCat.createCell(m+1);
			Cell cookTime = rowCookTime.createCell(m+1);
			Cell ingradientList = rowIngradientsList.createCell(m+1);						
			Cell prepMethod = rowPrepMethod.createCell(m+1);
			Cell recipeNameUrl = rowRecipeURL.createCell(m+1);
			Cell prepTime = rowPrepTime.createCell(m+1);
			Cell nutriValue = rownutritionalValue.createCell(m+1);
			cellId.setCellValue(recId.get(m));
			cellName.setCellValue(recName.get(m));
			foodCat.setCellValue("Veg");
			cookTime.setCellValue(cookingTime.get(m));
			ingradientList.setCellValue(ingradients.get(m));
			prepMethod.setCellValue(preptnMethod.get(m));
			recipeNameUrl.setCellValue(recURL.get(m));
			prepTime.setCellValue(preptnTime.get(m));	
			nutriValue.setCellValue(nutriVal.get(m));
			
	}
	FileOutputStream outstream = new FileOutputStream(filePath);
	workBook.write(outstream);	
	workBook.close();
}
	

}
