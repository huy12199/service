package com.ngv.api.v1.event;

import com.ngv.api.v1.service.AssetService;
import com.ngv.base.event.DrivenEventListener;
import com.ngv.base.event.EventInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author dungnv
 * @version 1.0
 */
@Service
public class ServiceDrivenEventListener extends DrivenEventListener {

  @SuppressWarnings("unused")
  private final ApplicationContext applicationContext;


  @SuppressWarnings("unused")
  private final ThreadPoolTaskExecutor taskExecutor;

  private final AssetService assetService;

  @Autowired
  private ServiceDrivenEventListener(
      ApplicationContext applicationContext,
      ThreadPoolTaskExecutor taskExecutor, AssetService assetService) {
    super(applicationContext, taskExecutor);
    this.applicationContext = applicationContext;
    this.taskExecutor = taskExecutor;
    this.assetService = assetService;
  }

  @Override
  protected void processHandleErrorEventAsync(EventInfo eventInfo) {

  }

  @Override
  protected void processLogHandleEventAsync(EventInfo eventInfo) {

//    switch (type) {
//      case TypeQueue.ASSET_GROUP.getValue():
//        assetService.getAssetDetail(data[1]);
//        break;
//      default:
//        // Thực hiện hành động nếu không khớp với bất kỳ trường hợp nào ở trên.
//        break;
//    }
//
//    if (type.equals(TypeQueue.ASSET_GROUP.getValue())) {
//      assetService.getAssetDetail(data[1]);
//    }
//    if (type.equals(TypeQueue.ASSET_STATUS.getValue())) {
//      assetService.getAssetDetail(data[1]);
//    }
//    if (type.equals(TypeQueue.CONTRACT_STATUS.getValue())) {
//      assetService.getAssetDetail(data[1]);
//    }
//    if (type.equals(TypeQueue.CONTRACT.getValue())) {
//      assetService.getAssetDetail(data[1]);
//    }
//    if (type.equals(TypeQueue.DEVICE_GROUP.getValue())) {
//      assetService.getAssetDetail(data[1]);
//    }
//    if (type.equals(TypeQueue.PROVIDER.getValue())) {
//      assetService.getAssetDetail(data[1]);
//    }
//
//
  }
}
