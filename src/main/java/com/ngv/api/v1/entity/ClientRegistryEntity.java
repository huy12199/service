package com.ngv.api.v1.entity;

import com.ngv.base.data.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client_registry")
public class ClientRegistryEntity extends BaseEntity {
  @Id
  private String id;
  private String clientCode;
  private String clientId;
  private String apiPath;

}
