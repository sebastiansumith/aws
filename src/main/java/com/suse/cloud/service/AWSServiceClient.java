package com.suse.cloud.service;

import org.apache.log4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * AWSServiceClient class.
 */
public class AWSServiceClient {

  private static final Logger LOGGER = Logger.getLogger(AWSServiceClient.class);


  private static final AWSCredentials CREDENTIALS = new BasicAWSCredentials("<accessKey>",
      "<secretKey>");
  private static AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("<region>")
      .withCredentials(new AWSStaticCredentialsProvider(CREDENTIALS)).build();

  /**
   * This method returns the Amazon s3 instance.
   * @return AmazonS3
   */
  public static AmazonS3 getS3Instance() {
      return s3;
  }
}
