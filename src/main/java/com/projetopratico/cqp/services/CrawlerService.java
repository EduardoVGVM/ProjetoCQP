package com.projetopratico.cqp.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.dto.CarroDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CrawlerService {
	public CarroDTO create(String url) {
		EdgeDriver driver = new EdgeDriver();
		try {
			driver.navigate().to(url);
			CarroDTO carroDTO = new CarroDTO().builder().build();
			return carroDTO;
		} finally {
			driver.quit();
		}
	}
}

//// Example
// WebElement nameElement = driver.findElement(By.xpath("//*[@id=\"dynamic-component-0\"]/div[2]/section/div/section[1]/div[1]/p"));
// String nome = nameElement.getText();