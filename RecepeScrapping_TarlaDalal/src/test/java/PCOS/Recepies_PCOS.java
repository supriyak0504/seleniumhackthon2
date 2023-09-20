package PCOS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Recepies_PCOS {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://tarladalal.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//driver.findElement(By.partialLinkText("A To Z")).click();
	//Recipe
		driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]")).click();
	//PCOS
	//div.rcc_rcpcore
		driver.findElement(By.id("ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335")).click();
		
		int paginationSize = driver.findElements(By.cssSelector("#pagination>a")).size();
		System.out.println("pageSize: " +paginationSize);				
				
				List<String> recID=new ArrayList<String>();
				List<String> recName=new ArrayList<String>();
				List<String> preparationTime=new ArrayList<String>();
				List<String> CookTime=new ArrayList<String>();
				List<String> ingradients=new ArrayList<String>();
				List<String> preparationMethod=new ArrayList<String>();
				List<String> recipeUrl=new ArrayList<String>();
				
		for(int k = 1; k <= paginationSize; k++)
		{
			String paginationSelector ="#pagination>a:nth-child("+k+")";
			driver.findElement(By.cssSelector(paginationSelector)).click();
		//List<WebElement> PCOS = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
		//System.out.println(PCOS.size());
		List<WebElement> recipeCards = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));
		System.out.println("Size:" +recipeCards.size());
																	
		//for(int j=0;j<recipeCards.size();j++)
			for(int j=0;j<9;j++)
		{
			driver.getCurrentUrl();
			System.out.println("Refreshed - " + j);
			
			List<WebElement> recipeName= driver.findElements(By.xpath("//span[@class='rcc_recipename']"));

			List<WebElement> recCards = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));
			System.out.println(recCards.size() +" Loop-"+j);
			GetterSetter_PCOS obj = new GetterSetter_PCOS();
			String RecipeId = recCards.get(j).getText();
			
			String[] id1 = RecipeId.split("\n");
			String id2 = id1[0];
			String[] id3 = id2.split("#");
			String id4 = id3[1];
			obj.setRecipeID(id4);
			System.out.println("RecipeId = " + obj.getRecipeID());
			recID.add(obj.getRecipeID());			
			obj.setRecipeName(recipeName.get(j).getText());						
			String RecipeName = obj.getRecipeName(); 
			System.out.println(RecipeName +" Loop - "+j);
			recName.add(RecipeName);
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='rcc_recipename']")));
			recipeName.get(j).click();
			
			String prepTime = driver.findElement(By.xpath("//p/time[@itemprop = 'prepTime']")).getText();
			System.out.println("PreparationTime = " + prepTime);
			preparationTime.add(prepTime);
			
			String cookTime = driver.findElement(By.xpath("//p/time[@itemprop = 'cookTime']")).getText();
			System.out.println("cookingTime = " + cookTime);
			CookTime.add(cookTime);
			
			String ingredientList = driver.findElement(By.id("rcpinglist")).getText();
			System.out.println("ingredientList = " + ingredientList);
			ingradients.add(ingredientList);
			
			String prepMethod = driver.findElement(By.id("ctl00_cntrightpanel_pnlRcpMethod")).getText();
			System.out.println("preparationMethod = " + prepMethod);
			preparationMethod.add(prepMethod);
			
			System.out.println("Recipe Url = " + driver.getCurrentUrl());
			recipeUrl.add(driver.getCurrentUrl());
			driver.navigate().back();
						
		}					
		}
				
	//WritingToExcel			
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelPCOS\\RecipeScrapping_PCOS.xlsx";
		//System.out.println(filePath);
		File xlFile = new File(filePath);
		XSSFWorkbook workBook = new XSSFWorkbook();
		if (xlFile.exists()) {
			System.out.println("Removing sheet");
			XSSFSheet sheet = workBook.getSheet("PCOS");
			if(sheet != null)   {
			   int index = workBook.getSheetIndex(sheet);
			    workBook.removeSheetAt(index);

		}		}
		
		XSSFSheet workSheet = workBook.createSheet("PCOS");		
		workSheet.setColumnWidth(2, 7500);
		Row rowRecId= workSheet.createRow(0);
		Row rowRecName= workSheet.createRow(1);
		Row rowPrepTime = workSheet.createRow(2);
		Row rowCookTime = workSheet.createRow(3);
		Row rowIngradientsList = workSheet.createRow(4);
		Row rowPrepMethod = workSheet.createRow(5);
		Row rowRecipeURL = workSheet.createRow(6);
		
		int arrSize = recID.size();
		for (int m = 0; m < arrSize; m++) {
			
			if(m==0){
				Cell cellHeader1= rowRecId.createCell(m);
				Cell cellHeader2= rowRecName.createCell(m);
				Cell cellHeader3 = rowCookTime.createCell(m);
				Cell cellHeader4 = rowIngradientsList.createCell(m);
				Cell cellHeader5 = rowPrepMethod.createCell(m);
				
				Cell cellHeader6 = rowRecipeURL.createCell(m);
				Cell cellHeader7 = rowPrepTime.createCell(m);
				cellHeader1.setCellValue("Recipe ID");
				cellHeader2.setCellValue("Recipe Name");
				cellHeader3.setCellValue("Cooking Time");
				cellHeader4.setCellValue("Ingradients List");
				cellHeader5.setCellValue("Preparation Method");
				cellHeader6.setCellValue("Recipe URL");
				cellHeader7.setCellValue("Preparation Time");
			}
			
				Cell cellId= rowRecId.createCell(m+1);
				Cell cellName= rowRecName.createCell(m+1);
				Cell cookTime = rowCookTime.createCell(m+1);
				Cell ingradientList = rowIngradientsList.createCell(m+1);
				Cell prepMethod = rowPrepMethod.createCell(m+1);
				Cell recipeNameUrl = rowRecipeURL.createCell(m+1);
				Cell prepTime = rowPrepTime.createCell(m+1);
				cellId.setCellValue(recID.get(m));
				cellName.setCellValue(recName.get(m));
				cookTime.setCellValue(CookTime.get(m));
				ingradientList.setCellValue(ingradients.get(m));
				prepMethod.setCellValue(preparationMethod.get(m));
				recipeNameUrl.setCellValue(recipeUrl.get(m));
				prepTime.setCellValue(preparationTime.get(m));				
				
		}
		FileOutputStream outstream = new FileOutputStream(filePath);
		workBook.write(outstream);
		workBook.close();
		driver.quit();
		driver.close();	
	}	

}
