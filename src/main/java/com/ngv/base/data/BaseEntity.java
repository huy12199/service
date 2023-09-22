package com.ngv.base.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedDate
  @Column(name = "CREATED_AT", updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "MODIFIED_AT")
  private LocalDateTime modifiedAt;

  @CreatedBy
  @Column(name = "CREATED_BY", updatable = false)
  private String createdBy;

  @LastModifiedBy
  @Column(name = "MODIFIED_BY")
  private String modifiedBy;
  @Column(name = "STATUS", length = 1)
  private Integer status;
}
