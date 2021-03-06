package com.gmail.wysocki.maciej;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neuroph.core.exceptions.VectorSizeMismatchException;

public class NeuralNetworkTest {

	private static final Double MAX_ERROR = 0.1;
	
	private static CarLogoRecognizer ai;
	private static ImageConverter converter;

	@BeforeClass
	public static void setUp() throws VectorSizeMismatchException, IOException, InterruptedException {
		converter = new ImageConverter(false);
		ai = new CarLogoRecognizer();
	}
	
	@Test
	public void shouldRecognizeBmw() throws IOException {
		BufferedImage image = extracted("/bmw.jpg");
		double[] input = converter.convertToNormalized(image);

		Answer answer = ai.recognizeCarLogo(input);
		Assert.assertArrayEquals(new double[]{1, 0, 0, 0}, answer.getData(), MAX_ERROR);
	}
	
	@Test
	public void shouldRecognizeFord() throws IOException {
		BufferedImage image = extracted("/ford.png");
		double[] input = converter.convertToNormalized(image);

		Answer answer = ai.recognizeCarLogo(input);
		Assert.assertArrayEquals(new double[]{0, 1, 0, 0}, answer.getData(), MAX_ERROR);
	}
	
	@Test
	public void shouldRecognizeMazda() throws IOException {
		BufferedImage image = extracted("/mazda.png");
		double[] input = converter.convertToNormalized(image);

		Answer answer = ai.recognizeCarLogo(input);
		Assert.assertArrayEquals(new double[]{0, 0, 1, 0}, answer.getData(), MAX_ERROR);
	}
	
	@Test
	public void shouldRecognizeMerc() throws IOException {
		BufferedImage image = extracted("/merc.png");
		double[] input = converter.convertToNormalized(image);

		Answer answer = ai.recognizeCarLogo(input);
		Assert.assertArrayEquals(new double[]{0, 0, 0, 1}, answer.getData(), MAX_ERROR);
	}
	
	@Test
	public void shouldNotRecognizeEmpty() throws IOException {
		BufferedImage image = extracted("/empty.png");
		double[] input = converter.convertToNormalized(image);

		Answer answer = ai.recognizeCarLogo(input);
		Assert.assertArrayEquals(new double[]{0, 0, 0, 0}, answer.getData(), MAX_ERROR);
	}

	private BufferedImage extracted(String fileName) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
