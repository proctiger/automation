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
package uol.bt.cruncher.behavior;

import java.util.List;
import java.util.Map;
import java.util.Set;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;

@ConfigurationRepository(component="bt-cruncher", product="bt")
public interface RemoteConfiguration {

    @ConfigurationItem("urls.groups")
    public Map<String, Set<String>> getUrlGroups();

    @ConfigurationItem("cruncher.batch.size")
    public int getBatchSize();

    @ConfigurationItem("cruncher.threads")
    public int getThreads();

    @ConfigurationItem("scanner.filename.pattern.whitelist")
    public List<String> getScannerWhiteList();

    @ConfigurationItem("mongo.authentication.data")
    public Map<String, String> getAuthenticationData();

    @ConfigurationItem("partners.cookie.parameters")
    public Map<String, Map<String, String>> getPartnersCookieParameters();

    @ConfigurationItem(component="bt-commons", value="mongo.active")
    boolean isMongoActive();
}
