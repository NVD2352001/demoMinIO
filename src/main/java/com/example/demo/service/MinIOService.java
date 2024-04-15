package com.example.demo.service;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MinIOService {
    private final MinioClient minioClient;
    private static final String BUCKET_NAME = "dev";
    private static final String END_POINT = "http://42.96.43.28:9000";

    public MinIOService() throws MinioException {
        // Kết nối tới MinIO
        minioClient = MinioClient.builder()
                .endpoint(END_POINT)
                .credentials("ROOTNAME", "CHANGEME123")
                .build();
    }

    public List<String> getAllImageUrls() {
        List<String> imageUrls = new ArrayList<>();

        try {
            // Lấy danh sách các object trong bucket
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(BUCKET_NAME).recursive(true).build());

            // Lặp qua từng object và lấy đường dẫn URL
            for (Result<Item> result : results) {
                Item item = result.get();
                String objectName = item.objectName();
                String imageUrl = END_POINT + "/" + BUCKET_NAME + "/" + objectName;
                imageUrls.add(imageUrl);
            }
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            // Xử lý ngoại lệ nếu cần
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return imageUrls;
    }
    public void createObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception {
        try {
            // Kiểm tra xem bucket đã tồn tại chưa
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                // Nếu bucket chưa tồn tại, tạo mới
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            // Upload đối tượng
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build()
            );
            System.out.println("Object created successfully");
        } catch (MinioException e) {
            System.err.println("Error occurred: " + e);
            throw new Exception("Error occurred while creating object in Minio", e);
        }
    }
}
