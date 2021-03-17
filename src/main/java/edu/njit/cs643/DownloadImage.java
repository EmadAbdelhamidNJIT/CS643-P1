package edu.njit.cs643;

import java.io.File;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class DownloadImage {
    
	static AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
//            .build();


	public static void main(String[] args) {

		File localFile = new File("C:\\\\Users\\Emad\\7.jpg");

		ObjectMetadata object = s3Client.getObject(new GetObjectRequest("njit-cs-643", "7.jpg"), localFile);
	}

}
