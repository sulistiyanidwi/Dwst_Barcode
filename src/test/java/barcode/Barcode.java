package barcode;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Barcode {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=MECARD%3AN%3ADwi+Sulistiyani%3BTEL%3A082352348588%3BEMAIL%3Adwisulistiiyani20%40gmail.com%3BADR%3AJln.+Kemakmuran+Gang+Knpi+No.14+Rt.+24%2C+Samarinda+Utara%2C+Kalimantan+Timur%3B%3B");
		
		String location = driver.findElement(By.xpath("//body//img")).getAttribute("src");

		
		URI uri = new URI(location);
        URL url = uri.toURL();
        BufferedImage image = ImageIO.read(url);
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        System.out.println(result.getText());
	}

}
