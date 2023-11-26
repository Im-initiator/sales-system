package com.leminhtien.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    public static String saveImage(MultipartFile file, ServletContext servletContext) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            fileName = fileName.substring(0, fileName.lastIndexOf(".")) +"tm36"
                    + currentTime.format(formatter) + fileName.substring(fileName.lastIndexOf("."));
            String filePath = servletContext.getRealPath("/")
                    + "template" + File.separator + "image" + File.separator;
            try {
                File fileImage = new File(filePath, fileName);
                file.transferTo(fileImage);
                return "/template/image/" + fileName;
            } catch (IOException e) {
                throw new IOException();
            }
        } else {
            return null;
        }
    }

    public static boolean deleteFile(String path, ServletContext servletContext) {
        String filePath = servletContext.getRealPath("/") + path;
        File fileDelete = new File(filePath);
        if (fileDelete.exists()) {
            return fileDelete.delete();
        } else {
            return true;
        }
    }

    public static CommonsMultipartFile loadImage(String path, ServletContext servletContext) throws IOException {
        if (path == null || path.isEmpty()) {
            return null;
        }
        path = path.replace("/", "\\");
        path = path.substring(1);
        String filePath = servletContext.getRealPath("") + path;

        File file = new File(filePath);
        if (file.exists() && file.length() == 0) {
            return null;
        } else {
            String fileName = path.substring(path.lastIndexOf("\\") + 1);
            DiskFileItem fileItem = new DiskFileItem(fileName, Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            try  {
                byte[] fileData = Files.readAllBytes(file.toPath());
                fileItem.getOutputStream().write(fileData);
                return new CommonsMultipartFile(fileItem);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }


    }

}
