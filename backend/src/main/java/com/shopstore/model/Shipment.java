package com.shopstore.model;

import jakarta.persistence.*;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private BookOrder order;

    @Column(nullable = false)
    private String logisticsCompany;

    @Column(nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private String status;

    public Long getId() { return id; }
    public BookOrder getOrder() { return order; }
    public void setOrder(BookOrder order) { this.order = order; }
    public String getLogisticsCompany() { return logisticsCompany; }
    public void setLogisticsCompany(String logisticsCompany) { this.logisticsCompany = logisticsCompany; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
