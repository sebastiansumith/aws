package com.suse.cloud;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.suse.cloud.service.AWSService;
import com.suse.cloud.service.impl.AWSServiceImpl;

public class AWSTestMain {

  private static final Logger LOGGER = Logger.getLogger(AWSTestMain.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    final AWSService service = new AWSServiceImpl();
    try {
      service.listBuckets();
      service.getObjectFromS3("<bucketName>", "<key>");
      service.createNewBucket("<newBucketName>");
      service.uploadFile("<newBucketName>", "<fileName>",
          "src/main/resources/");
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
    }
  }
}
