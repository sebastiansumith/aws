package com.suse.cloud.service;

import java.io.IOException;

public interface AWSService {

    /**
     * This method gets the object from S3.
     * @param bucketName bucketName
     * @param key key
     * @throws IOException
     */
    void getObjectFromS3(final String bucketName, final String key)  throws IOException;

    /**
     * This method lists the bucketName.
     */
    void listBuckets();

    /**
     *This method creates the new bucket.
     */
    void createNewBucket(final String bucketName);

    /**
     * This method uploads the new file to the bucket.
     * @param bucketName bucketName
     * @param fileName fileName
     * @param fileName  filePath
     */
    void uploadFile(final String bucketName, final String fileName, final String filePath);

}
