package com.projetopratico.cqp.services;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.projetopratico.cqp.models.Carro;
import com.projetopratico.cqp.models.CarroDetalhes;
import com.projetopratico.cqp.repositories.CarroDetalhesRepository;
import com.projetopratico.cqp.repositories.CarroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CrawlerService {
	private final CarroRepository carroRepository;
	private final CarroDetalhesRepository carroDetalhesRepository;

	@Async
	public CompletableFuture<Carro> update(int id) {
		WebDriver webDriver = new EdgeDriver();

		try {
			Carro carro = this.carroRepository.findById(id).orElse(null);
			CarroDetalhes carroDetalhes = this.carroDetalhesRepository.findById(carro.getCarroDetalhes().getId())
					.orElse(null);

			if (carro != null && carroDetalhes != null) {
				webDriver.navigate().to(carroDetalhes.getUrlDetalhes());
				String nomeUpdate = getText(webDriver, carroDetalhes.getXpathNome());
				String modeloUpdate = getText(webDriver, carroDetalhes.getXpathModelo());
				String corUpdate = getText(webDriver, carroDetalhes.getXpathCor());
				Double precoUpdate = getDouble(webDriver, carroDetalhes.getXpathPreco());
				String urlImagemUpdate = getSrc(webDriver, carroDetalhes.getXpathUrlImagem());

				carro.setNome(nomeUpdate);
				carro.setModelo(modeloUpdate);
				carro.setCor(corUpdate);
				carro.setPreco(precoUpdate);
				carro.setUrlImagem(urlImagemUpdate);
				carro.setDataAtualizacao(LocalDate.now());

				return CompletableFuture.completedFuture(this.carroRepository.save(carro));
			}
			return null;
		} finally {
			webDriver.quit();
		}
	}

	private String getText(WebDriver webDriver, String xpath) {
		WebElement element = webDriver.findElement(By.xpath(xpath));
		return element.getText();
	}

	private Double getDouble(WebDriver webDriver, String xpath) {
		WebElement element = webDriver.findElement(By.xpath(xpath));
		return Double.parseDouble(element.getText().replaceAll("[^\\d,]", "").replace(",", "."));
	}

	private String getSrc(WebDriver webDriver, String xpath) {
		WebElement element = webDriver.findElement(By.xpath(xpath));
		return element.getAttribute("src");
	}

}