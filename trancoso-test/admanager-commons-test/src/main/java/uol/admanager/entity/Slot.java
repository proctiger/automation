/* Copyright (c) - UOL Inc,
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

/**
 * @author lnferreira@uolinc.com
 */
public class Slot {

    private long id;
    private long channelId;
    private long typeAdvertisementId;
    private String description;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getChannelId() {
        return channelId;
    }
    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getTypeAdvertisementId() {
        return typeAdvertisementId;
    }
    public void setTypeAdvertisementId(long typeAdvertisementId) {
        this.typeAdvertisementId = typeAdvertisementId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
