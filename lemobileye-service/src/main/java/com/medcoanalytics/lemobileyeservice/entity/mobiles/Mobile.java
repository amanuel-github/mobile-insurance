package com.medcoanalytics.lemobileyeservice.entity.mobiles;

import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mobiles")
public class Mobile extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 36, max = 40)
    private String mobileUuid = UUID.randomUUID().toString();

    @Size(min = 36, max = 40)
    private String personUuid;

    @Size(min = 36, max = 40)
    private String mobileNumber;

    @Size(max = 25)
    private String imei1;

    @Size(max = 25)
    private String imei2;

    @Size(max = 20)
    private String screenTest;

    @Size(max = 50)
    private String board;

    @Size(max = 50)
    private String manufacturer;

    private long latitude;

    private long longitude;

    @Size(max = 40)
    private String brand;

    @Size(max = 15)
    private String device;

    @Size(max = 20)
    private String model;

    @Size(max = 25)
    private String host;

    @Size(max = 10)
    private String consent;

    @Size(max = 25)
    private String platform;

    @Size(max = 20)
    private String androidVersion;

    @Size(max = 20)
    private String sdkVersion;

    @Size(max = 25)
    private String serialNumber;

    @Size(max = 25)
    private String hostId;

    @Size(max = 25)
    private String incremental;

    private Date consentDate;

    private Date manufacturingDate;

    private Date paymentDate;

    private Date renewableDate;

    private Date osDate;

    private double premium;

    @Size(max = 25)
    private String policyNumber;

    private Date beginDate;

    @Column(name = "status", length = 25)
    @ColumnDefault("'Active'")
    private String status;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

}
