package edu.njit.cs643;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import java.util.Date;
import java.util.List;

public class SQSmsg
{
    private static final String QUEUE_NAME = "Emad-CS634-SQS.fifo" + new Date().getTime();
    final static AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    //final static String queueUrl = "https://sqs.us-east-1.amazonaws.com/685598371878/Emad-CS634-SQS.fifo";
    final static String queueUrl = "https://sqs.us-east-1.amazonaws.com/125764523568/Emad-CS643-SQS.fifo";

    public static void sendSQS(String string)
    {
        
        
		String msgSend = string.toString();


        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageGroupId("1")
                .withMessageBody(msgSend);
                //.withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }
    
    public static List<Message> receiveSQS() {
    	 // receive messages from the queue
    	ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
    			.withQueueUrl(queueUrl).withVisibilityTimeout(1).withMaxNumberOfMessages(10);
    	
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        System.out.println(messages.size());
        return messages;
	}
}