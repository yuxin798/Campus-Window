package com.campuswindow.fileupload;

import com.campuswindow.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private MinioProperties minioProperties;

    @Override
    public String save(MultipartFile file, String fileRootPath) {
        InputStream in = null;
        try {
            in = file.getInputStream();
            // 创建minioClient对象
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioProperties.getEndpoint())
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                            .build();

            // Make 'spzx-bucket' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
            if (!found) {
                // Make a new bucket called 'spzx-bucket'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
            }

            // fileName = "avatar" + date + UUID
            // /avatar/20230917/uuid.png
            String fileName =
                    fileRootPath +
//                    LocalDate.now(ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyyMMdd")) +
                    "/" + UUID.randomUUID().toString().replaceAll("-", "") +
                    "." + getSuffix(file.getContentType());
            // Upload
            minioClient
                    .putObject(
                     PutObjectArgs
                             .builder()
                             .bucket(minioProperties.getBucket())
                             .object(fileName)
                             .stream(in, file.getSize(), -1)
                             .contentType(file.getContentType())
                             .build()
                    );

            log.info("头像文件 " + file.getOriginalFilename() + " 成功保存为 " + fileName);

            // http://172.17.0.4:9000/spzx-bucket/avatar/20231120/99b01bae8a7446248af40d6082750694.jpg
            // http://192.168.144.132:9090/spzx-bucket/avatar/20231120/99b01bae8a7446248af40d6082750694.jpg
//            return minioProperties.getEndpoint() + "/" + minioProperties.getBucket() + fileName;
            return "http://192.168.3.36:9000" + "/" + minioProperties.getBucket() + fileName;
        } catch (MinioException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getSuffix(String contentType) {
        return minioProperties.getAllowedContentType().get(contentType);
    }

    @Autowired
    public void setMinioProperties(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }
}
