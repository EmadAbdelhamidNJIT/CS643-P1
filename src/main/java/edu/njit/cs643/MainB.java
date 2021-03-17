package edu.njit.cs643;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import com.amazonaws.services.rekognition.model.TextDetection;
import com.amazonaws.services.sqs.model.Message;

public class MainB {

	public static void main(String[] args) throws Exception {
	
		File localFile = new File("carsWithText.txt");
		localFile.createNewFile();
		FileOutputStream oFile = new FileOutputStream(localFile, false); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oFile));
		
		List<Message> carList = SQSmsg.receiveSQS();
		System.out.println(carList.size());
		for (Message message : carList) {
			
			
			List<TextDetection> textDetections = DetectText.detectText(message.getBody().toString());
			if (textDetections.size() > 0) {
				System.out.println("Image index: " + message.getBody().toString());
				
				bw.write("Image index: " + message.getBody().toString());
				bw.newLine();
				
				for (TextDetection text : textDetections) {
					
					System.out.println("Detected text: " + text.getDetectedText());
					
					bw.write("Detected text: " + text.getDetectedText());
					bw.newLine();

				} 
			}
		}
		bw.close();
	}

}
