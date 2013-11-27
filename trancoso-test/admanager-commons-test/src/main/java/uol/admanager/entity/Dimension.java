/* Copyright (sc) - UOL Inc,
 * Todos os direitos reservados
 *
 * Este arquivo e uma propriedade confidencial do Universo Online Inc.
 * Nenhuma parte do mesmo pode ser copiada, reproduzida, impressa ou
 * transmitida por qualquer meio sem autorizacao expressa e por escrito
 * de um representante legal do Universo Online Inc.
 *
 * All rights reserved
 *
 * This file is a confidential property of Universo Online Inc.
 * No part of this file may be reproduced or copied in any form or by
 * any means without written permission from an authorized person from
 * Universo Online Inc.
 */
package uol.admanager.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.gson.Gson;

/**
 * @author apoliveira
 */
public class Dimension {

    private Long id;
    private String type;
    private Date date;
    private Long channelId;
    private Long packSaleId;
    private Long slotId;
    private Long customerId;
    private Long advertisementId;
    private Long typeAdvertisementId;
    private Integer hour;
    private Long quantity;
    private boolean valid;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Long getChannelId() {
        return channelId;
    }
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getPackSaleId() {
        return packSaleId;
    }
    public void setPackSaleId(Long packSaleId) {
        this.packSaleId = packSaleId;
    }

    public Long getSlotId() {
        return slotId;
    }
    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }
    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Long getTypeAdvertisementId() {
        return typeAdvertisementId;
    }
    public void setTypeAdvertisementId(Long typeAdvertisementId) {
        this.typeAdvertisementId = typeAdvertisementId;
    }

    public Integer getHour() {
        return hour;
    }
    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getTruncatedDate() throws ParseException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        final String dateString = dateFormat.format(date);

        return dateFormat.parse(dateString);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dimension)) {
            return false;
        }
        Dimension rhs = (Dimension) obj;
        return new EqualsBuilder()
        .append(hour, rhs.hour)
        .append(channelId, rhs.channelId)
        .append(packSaleId, rhs.packSaleId)
        .append(slotId, rhs.slotId)
        .append(customerId, rhs.customerId)
        .append(advertisementId, rhs.advertisementId)
        .append(typeAdvertisementId, rhs.typeAdvertisementId)
        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(hour)
        .append(channelId)
        .append(packSaleId)
        .append(slotId)
        .append(customerId)
        .append(advertisementId)
        .append(typeAdvertisementId)
        .hashCode();
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public Dimension copy() {
        final Dimension copy = new Dimension();
        copy.setAdvertisementId(advertisementId);
        copy.setChannelId(channelId);
        copy.setCustomerId(customerId);
        copy.setDate(date);
        copy.setHour(hour);
        copy.setPackSaleId(packSaleId);
        copy.setQuantity(quantity);
        copy.setSlotId(slotId);
        copy.setType(type);
        copy.setTypeAdvertisementId(typeAdvertisementId);
        copy.setValid(valid);

        return copy;
    }
}
