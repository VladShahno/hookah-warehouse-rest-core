package hookah.warehouse.com.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"created", "updated"},
    allowGetters = true
)
public class Product {
  @Id
  @Column(name = "name", unique = true, nullable = false)
  private String productName;

  @Column(name = "amount", nullable = false)
  private Long amount;

  @Column(name = "purchase_price", nullable = false)
  private Long purchasePrice;

  @Column(name = "sale_price", nullable = false)
  private Long salePrice;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {
      CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
  })
  private ProductGroup productGroup;

  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {
      CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
  })
  private Warehouse warehouse;

  @Column(name = "article", nullable = false)
  private String article;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created", updatable = false)
  @CreatedDate
  private Date created;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_updated")
  @LastModifiedDate
  private Date updated;
}
