NJIT CS643 Cloud computing
Instructor: Manoop Talasila
mt57@njit.edu 


Project by


Emad A Abdel hamid
eaa38@njit.edu


Module 03 Assignment 03: Programming Assignment 1

Code @ https://github.com/EmadAbdelhamidNJIT/CS643-P1/tree/master


This project is running on 2 AWS EC2 instances. Each one is responsible for running a main java method.
Instance contains MainA class which is responsible for reading labels of images stored on the S3 bucket provided (https://njit-cs-643.s3.us-east-1.amazonaws.com ), perform image recognition using AWS rekognition.
When a car is detected using Rekognition, with confidence higher than 90%, the index of that image is stored in an AWS FIFO SQS (https://sqs.us-east-1.amazonaws.com/125764523568/Emad-CS643-SQS.fifo )


Instance B reads indexes of images from SQS and performs text recognition on these images. Once text is detected, it will log into a file the index of that image and the recognized text.


The applications have been developed in cloud 9 IDE and they are using Maven for managing dependencies. 
To run the application on Instance A you need to ssh into the instance 
ssh -i InstanceAprivateKey.pem  ec2-user@ec2-34-229-241-144.compute-1.amazonaws.com 


Then change directory into Code 
Cd Code 


When in there run the following command to execute the main method in MainA class
java -cp target/cs643-p1-0.0.1-SNAPSHOT-jar-with-dependencies.jar edu.njit.cs643.MainA my-test-bucket us-east-1


To run the application on Instance B you need to ssh into the instance 
ssh -i InstanceBprivateKey.pem  ec2-user@ec2-3-90-40-176.compute-1.amazonaws.com 


Then change directory into Code 
Cd Code 
When in there run the following command to execute the main method in MainA class
java -cp target/cs643-p1-0.0.1-SNAPSHOT-jar-with-dependencies.jar edu.njit.cs643.MainB my-test-bucket us-east-1