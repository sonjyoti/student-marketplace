package com.pm.studentmarketplace.listing.service;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.UUID;

@Service
public class ImageStorageService {
    private final Path uploadRoot = Paths.get("uploads/listings");
    @Value("${image.max-width}")
    private int maxWidth;

    @Value("${image.max-height}")
    private int maxHeight;

    @Value("${image-quality}")
    private float quality;

    public String store(MultipartFile file) {
        try{
            if(file.isEmpty()){
                return null;
            }
            if (!Files.exists(uploadRoot)){
                Files.createDirectories(uploadRoot);
            }

            // read image file
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            if(originalImage == null){
                throw  new RuntimeException("Invalid image file");
            }

            // resize image
            BufferedImage resizedImage = Scalr.resize(
                    originalImage,
                    Scalr.Method.QUALITY,
                    Scalr.Mode.AUTOMATIC,
                    maxWidth,
                    maxHeight
            );

            //output file
            String fileName = UUID.randomUUID() + ".jpg";
            File destination = uploadRoot.resolve(fileName).toFile();

            // write compressed jpeg
            writeJpeg(resizedImage, destination, quality);

            return fileName;
        }
        catch (IOException e){
            throw new RuntimeException("Failed to store image!", e);
        }
    }

    private void writeJpeg(
            BufferedImage resizedImage, File destination, float quality
    ) throws IOException {
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);

        try(FileImageOutputStream output = new FileImageOutputStream(destination)) {
            writer.setOutput(output);
            writer.write(null, new IIOImage(resizedImage, null, null), param);
        } finally {
            writer.dispose();
        }
    }

    public void delete(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            return;
        }

        try{
            Path filePath = uploadRoot.resolve(imagePath);
            Files.deleteIfExists(filePath);
        }
        catch (IOException e){
            System.err.println("Failed to delete image: " + imagePath);
        }
    }
}
