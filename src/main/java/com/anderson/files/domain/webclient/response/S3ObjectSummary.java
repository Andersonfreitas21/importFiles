package com.anderson.files.domain.webclient.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class S3ObjectSummary {
  protected String bucketName;
  protected String key;
  protected String eTag;
  protected long size;
  protected Date lastModified;
  protected String storageClass;
}
