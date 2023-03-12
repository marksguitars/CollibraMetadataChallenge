package com.collibra.codingchallenge.demo;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Asset {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private AssetType assetType;
    private Asset parentAsset;
    private List<Asset> childAssets;
    private boolean promoted;
    private LocalDateTime created;

  public Asset(String name, AssetType assetType, Asset parentAsset,
      List<Asset> childAssets, boolean promoted, LocalDateTime created) {
    this.name = name;
    this.assetType = assetType;
    this.parentAsset = parentAsset;
    this.childAssets = childAssets;
    this.promoted = promoted;
    this.created = created;
  }
}
