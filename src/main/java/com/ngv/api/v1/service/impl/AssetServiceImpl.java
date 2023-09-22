package com.ngv.api.v1.service.impl;

import com.ngv.api.v1.dto.AssetDto;
import com.ngv.api.v1.dto.AssetGroupDto;
import com.ngv.api.v1.dto.AssetStatusDto;
import com.ngv.api.v1.event.Event;
import com.ngv.api.v1.repository.dao.AssetDAO;
import com.ngv.api.v1.service.AssetService;
import com.ngv.base.data.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl extends BaseService implements AssetService {

  private final AssetDAO assetDAO;

  @Override
  public String handleMessageQueue(Object msg) {
    String logPrefix = "handleMessageQueue";
    LOGGER.info(logPrefix + "|msg|" + msg);
    String[] data = msg.toString().split(":");
    if (data[0].equals(Event.ASSET_GROUP.getEventName())) {
      String[] ids = msg.toString().split("_");
      List<AssetGroupDto> assetGroupDtoList = getAssetGroupByDtlIdAndCdId(ids[0],
          ids[1]);
      String url = "";
      HttpHeaders headers = getHttpHeaders();
      HttpEntity<Object> eHeader = new HttpEntity<Object>(assetGroupDtoList, headers);
      return restClient.execute(url, HttpMethod.POST, eHeader, String.class);
    } else {
      AssetDto assetDto = getAssetDetail(data[1]);
      String url = "";
      HttpHeaders headers = getHttpHeaders();
      HttpEntity<Object> eHeader = new HttpEntity<Object>(assetDto, headers);
      restClient.execute(url, HttpMethod.POST, eHeader, String.class);

      AssetStatusDto assetStatusDto = AssetStatusDto.builder()
          .assetCode(assetDto.getAssetCode())
          .status(assetDto.getStatus())
          .assetId(assetDto.getAssetId()).
          reason(assetDto.getReason())
          .build();
      eHeader = new HttpEntity<Object>(assetStatusDto, headers);
      return restClient.execute(url, HttpMethod.POST, eHeader, String.class);
    }
  }

  /**
   * @param date
   * @return ListAssetGroup
   */
  @Override
  public List<AssetGroupDto> getAssetGroup(String date) {
    String logPrefix = "getAssetGroup";
    LOGGER.info(logPrefix + "|date|" + date);
    return assetDAO.getAssetGroup(date);
  }

  @Override
  public List<AssetGroupDto> getAssetGroupByDtlIdAndCdId(String dtlId, String cdId) {
    String logPrefix = "getAssetGroupByDtlIdAndCdId";
    LOGGER.info(logPrefix + "|dtlId|" + dtlId + "|cdId|" + cdId);
    return assetDAO.getAssetGroupByDtlIdAndCdId(dtlId, cdId);
  }

  /**
   * @param date
   * @return
   */
  @Override
  public AssetDto getAssetDetail(String date) {
    String logPrefix = "getAssetDetail";
    LOGGER.info(logPrefix + "|date|" + date);
    return assetDAO.getAssetDetail(date);
  }

  @Override
  public AssetDto getAssetById(Long id) {
    String logPrefix = "getAssetById";
    LOGGER.info(logPrefix + "|id|" + id);
    return assetDAO.getAssetById(id);
  }

  /**
   * @param date
   * @return
   */
  @Override
  public AssetStatusDto getStatusAsset(String date) {
    String logPrefix = "getStatusAsset";
    LOGGER.info(logPrefix + "|date|" + date);
    return assetDAO.getStatusAsset(date);
  }
//  @Override
//  public AssetStatusDto getStatusAsset(String date) {
//    return assetDAO.getStatusAsset(date);
//  }
}
