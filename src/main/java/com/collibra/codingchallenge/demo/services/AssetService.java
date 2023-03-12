package com.collibra.codingchallenge.demo.services;

import com.collibra.codingchallenge.demo.Asset;
import com.collibra.codingchallenge.demo.AssetNotFoundException;
import com.collibra.codingchallenge.demo.AssetRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@AllArgsConstructor
@Service
public class AssetService {

    Logger logger;

    {
        LoggerFactory.getLogger(AssetService.class);
    }

    private final AssetRepository assetRepository;

    @Autowired
    private KafkaTemplate<String, Asset> kafkaTemplate;

    public List<Asset> getAssets() {
      return assetRepository.findAll();
    }

    public Asset findAssetById(String id) {
        if(assetRepository.existsById(id)) {
            return assetRepository.findById(id).get();
        }
        else {
            logger.error("Asset Id" + id + " not found");
            throw new AssetNotFoundException(id);
        }
    }

    public Asset updateAssetById(Asset asset) {
        if(assetRepository.existsById(asset.getId()))
            assetRepository.delete(asset);
        return createAsset(asset);
    }

    public void deleteAssetById(String id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent()) {
            assetRepository.delete(asset.get());
        }
    }

    public Asset createAsset(Asset asset) {
        if(asset.isPromoted())
            asset = processNewAssetPromotions(asset);
        Asset createdAsset = assetRepository.insert(asset);
        sendMessage("assets", createdAsset);
        return createdAsset;
    }

    private Asset processNewAssetPromotions(Asset asset) {
        asset.getParentAsset().setPromoted(true);
        asset.setPromoted(true);
        for(Asset childAsset : asset.getChildAssets()) {
            childAsset.setPromoted(true);
            processNewAssetPromotions(childAsset);
        }
        return asset;
    }

    public void sendMessage(String topicName, Asset asset) {
        kafkaTemplate.send(topicName, asset);
    }
}
