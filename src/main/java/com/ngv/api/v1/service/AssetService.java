package com.ngv.api.v1.service;

import com.ngv.api.v1.dto.AssetDto;
import com.ngv.api.v1.dto.AssetGroupDto;
import com.ngv.api.v1.dto.AssetStatusDto;
import java.util.List;

public interface AssetService extends CommonService {

  List<AssetGroupDto> getAssetGroup(String date);

  AssetDto getAssetDetail(String date);

  AssetStatusDto getStatusAsset(String date);

  List<AssetGroupDto> getAssetGroupByDtlIdAndCdId(String dtlId, String cdId);

  AssetDto getAssetById(Long id);
}
