package com.projetopratico.cqp.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CrawlerService {

    public CrawlerService create() {

        WebDriver webDriver = new EdgeDriver();
        try {
            webDriver.navigate().to("https://www.toyota.com.br/modelos/yaris-hatch");

            // Example
            WebElement nameElement = webDriver
                    .findElement(By.xpath("//*[@id=\"dynamic-component-0\"]/div[2]/section/div/section[1]/div[1]/p"));
            String nome = nameElement.getText();

            // Modelo
            WebElement modelElement = webDriver.findElement(By.xpath(
                    "//*[@id=\"dynamic-component-0\"]/div[2]/section/div/section[1]/div[2]/div/div/div/div/div[1]/div/div/button"));
            System.out.println(modelElement.getText());

            // Preço
            WebElement priceElement = webDriver.findElement(By
                    .xpath("//*[@id=\"dynamic-component-0\"]/div[2]/section/div/section[2]/div/div[1]/div[3]/span[2]"));
            System.out.println(priceElement.getText());

            // Cor
            WebElement colorElement = webDriver.findElement(By.xpath(
                    "//*[@id=\"dynamic-component-0\"]/div[2]/section/div/section[2]/div/div[1]/div[1]/div[2]/div[2]/span"));
            System.out.println(colorElement.getText());

            // Imagem
            WebElement imageElement = webDriver
                    .findElement(By.xpath(
                            "//*[@id=\"dynamic-component-4\"]/section/ul/li[1]/div[1]/div/img"));
            System.out.println(imageElement.getAttribute("src"));

            return null;
        } finally {
            webDriver.quit();
        }
    }
}