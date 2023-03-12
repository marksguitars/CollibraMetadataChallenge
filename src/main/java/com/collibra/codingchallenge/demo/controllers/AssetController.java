package com.collibra.codingchallenge.demo.controllers;

import com.collibra.codingchallenge.demo.Asset;
import com.collibra.codingchallenge.demo.services.AssetService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/assets")
@AllArgsConstructor
public class AssetController {

    Logger logger;

    {
        LoggerFactory.getLogger(AssetController.class);
    }

    @Autowired
    private final AssetService assetService;

    @GetMapping
    public List<Asset> findAllAssets() {
        logger.info("findAllAssets controller function . . .");
        return assetService.getAssets();
    }

    @GetMapping(path = "{assetId}")
    public Asset findAssetById(@PathVariable String assetId) {
        logger.info("findAssetById controller function for assetId " + assetId + ". . .");
        return assetService.findAssetById(assetId);
    }

    @PutMapping(path = "{assetId}",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public Asset updateAssetById(@PathVariable String assetId, @RequestBody Asset asset) {
        logger.info("updateAssetById controller function for assetId " + assetId + ". . .");
        return assetService.updateAssetById(asset);
    }

    @DeleteMapping(path = "{assetId}")
    public void deleteAssetById(@PathVariable String assetId) {
        logger.info("deleteAssetById controller function for assetId " + assetId + ". . .");
        assetService.deleteAssetById(assetId);
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        logger.info("createAsset controller function for asset name " + asset.getName() + ". . .");
        return assetService.createAsset(asset);
    }
}
