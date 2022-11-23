package com.example.demo.entity;

import lombok.*;


@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DestinationDetailedMessage {
    public String paymentDate;
    public Integer paymentID;
    public Integer bundleID;
    public String CPR;
    public String bankAccount;
    public Float amount;
    
    
}
