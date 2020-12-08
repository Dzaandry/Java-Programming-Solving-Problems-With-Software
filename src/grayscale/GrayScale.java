package grayscale;

import java.io.File;
import edu.duke.*;

public class GrayScale {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(),
                inImage.getHeight());
        for (Pixel pixel : inImage.pixels()) {
            Pixel outPixel = outImage.getPixel(pixel.getX(), pixel.getY());
            int average = (pixel.getRed() + pixel.getBlue() +
                    pixel.getGreen()) / 3;
            outPixel.setRed(average);
            outPixel.setGreen(average);
            outPixel.setBlue(average);
        }
        return outImage;
    }
    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    public void makeGrayAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            ImageResource outImage = makeGray(inImage);
            outImage.setFileName(newName);
            outImage.draw();
            outImage.save();
        }
    }
    public ImageResource invertImage(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(),
                inImage.getHeight());
        for (Pixel pixel : inImage.pixels()) {
            Pixel outPixel = outImage.getPixel(pixel.getX(), pixel.getY());
            int newRed = 255 - pixel.getRed();
            int newGreen = 255 - pixel.getGreen();
            int newBlue = 255 - pixel.getBlue();
            outPixel.setRed(newRed);
            outPixel.setGreen(newGreen);
            outPixel.setBlue(newBlue);
        }
        return outImage;
    }
    public void testInvertImage() {
        ImageResource ir = new ImageResource();
        ImageResource invert = invertImage(ir);
        invert.draw();
    }
    public void invertAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            ImageResource outImage = invertImage(inImage);
            outImage.setFileName(newName);
            outImage.save();
        }
    }
}