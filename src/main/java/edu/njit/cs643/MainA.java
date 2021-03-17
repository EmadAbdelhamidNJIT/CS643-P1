package edu.njit.cs643;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class MainA {

	public static void main(String[] args) throws Exception {

		List<S3ObjectSummary> retrievedList = null;
		List<S3ObjectSummary> carList = new ArrayList<S3ObjectSummary>();

		try {
			retrievedList = ListKeys.listAllObjects();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (S3ObjectSummary summary : retrievedList) {
			List<Label> labels = DetectLabels.getLabels(summary.getKey());

			for (Label label : labels) {
				System.out.println(label.getName() + ": " + label.getConfidence().toString());
				if (label.getName().toString().toLowerCase().equals("car")) {
					if (label.getConfidence() > 90) {
						SQSmsg.sendSQS(summary.getKey());
						System.out.println(summary.toString());
						carList.add(summary);
					}
				}
			}

		}
		System.out.println(carList.size() + " " + carList.toString());
	}

}
