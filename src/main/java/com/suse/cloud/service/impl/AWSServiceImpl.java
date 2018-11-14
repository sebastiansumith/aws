package com.suse.cloud.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.suse.cloud.service.AWSService;
import com.suse.cloud.service.AWSServiceClient;
import com.suse.cloud.utils.AWSUtils;

/**
 * AWSServiceImpl class.
 */
public class AWSServiceImpl implements AWSService {

  private AmazonS3 client;

  private static final Logger LOGGER = Logger.getLogger(AWSServiceImpl.class);

  /**
   * Constructor.
   */
  public AWSServiceImpl() {
    this.client = AWSServiceClient.getS3Instance();
  }

  /**
   * Method gets the object from S3.
   *
   * @throws IOException
   * @param bucketName bucketName
   * @param key key
   */
  public void getObjectFromS3(final String bucketName, final String key) throws IOException {

    S3Object s3Object = null;
    try {
      s3Object = this.client.getObject(bucketName, key);
      AWSUtils.displayTextInputStream(s3Object.getObjectContent());
    } catch (AmazonServiceException e) {
      LOGGER.error(e.getMessage());
      throw e;
    } catch (SdkClientException e) {
      LOGGER.error(e.getMessage());
      throw e;
    } finally {
      if (s3Object != null) {
        s3Object.close();
      }
    }
  }

  /**
   * This method lists the bucketName.
   */
  public void listBuckets() {
    List<Bucket> buckets = this.client.listBuckets();
    for (Bucket bucket : buckets) {
      LOGGER.info("Bucket Name:: " + bucket.getName());
    }
  }

  /**
   * This method creates new bucket.
   */
  public void createNewBucket(final String bucketName) {
    if (!this.client.doesBucketExist(bucketName)) {
      this.client.createBucket(bucketName);
      LOGGER.info("After BucketCreation +++++++++++++++ ");
      this.listBuckets();
    } else {
      LOGGER.info("Bucket already exists");
    }
  }

  /**
   * This method uploads the new file to the bucket.
   *
   * @param bucketName bucketName
   * @param fileName   fileName
   * @param fileName   filePath
   */
  public void uploadFile(String bucketName, String fileName, final String filePath) {
    try {
      this.client.putObject(bucketName, fileName, new File(filePath + fileName));
      AWSUtils
          .displayTextInputStream(this.client.getObject(bucketName, fileName).getObjectContent());
    } catch (AmazonServiceException exception) {
      LOGGER.error(exception);
    } catch (IOException exception) {
      LOGGER.error(exception);
    }
  }
}
