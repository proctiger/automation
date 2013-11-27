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
package uol.bt.cruncher.test.classifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class URLClassifier {

    private static final Pattern ASTERISK_PATTERN = Pattern.compile("\\*");
    private static final Pattern SCHEME_PATTERN = Pattern.compile("(http://|https://)");
    private static final Pattern SLASH_PATTERN = Pattern.compile("(/)+$");

    private final Map<String, Set<String>> urlSuffixesGroups = new HashMap<String, Set<String>>();
    private final Map<String, Set<String>> urlPrefixesGroups = new HashMap<String, Set<String>>();
    private final Map<String, Set<String>> urlMiddleGroups = new HashMap<String, Set<String>>();
    private final Map<String, Set<String>> urlFullGroups = new HashMap<String, Set<String>>();

    public URLClassifier(Map<String, Set<String>> cruncherUrlGroups) {
        populateUrlPatternsGroups(cruncherUrlGroups);
    }

    private void populateUrlPatternsGroups(Map<String, Set<String>> configUrlGroups) {
        for (Entry<String, Set<String>> groupAndUrls : configUrlGroups.entrySet()) {
            associateGroupToUrlPatterns(groupAndUrls.getKey(), groupAndUrls.getValue());
        }
    }

    private void associateGroupToUrlPatterns(String group, Set<String> urlPatterns) {
        for (String urlPattern : urlPatterns) {
            urlPattern = removeSchemeFrom(urlPattern);
            if (urlPattern.startsWith("*")) {
                if (urlPattern.endsWith("*")) {
                    insertUrlMiddleGroup(urlPattern, group);
                } else {
                    insertUrlSuffixGroup(urlPattern, group);
                }
            } else if (urlPattern.endsWith("*")) {
                insertUrlPrefixGroup(urlPattern, group);
            } else {
                insertUrlFullGroup(urlPattern, group);
            }
        }
    }

    private void insertUrlPrefixGroup(String prefix, String group) {
        final String url = removeAsterisksFrom(prefix);
        final Set<String> groups = urlPrefixesGroups.containsKey(url) ? urlPrefixesGroups.get(url) : new TreeSet<String>();
        groups.add(group);
        urlPrefixesGroups.put(url, groups);
    }

    private void insertUrlSuffixGroup(String suffix, String group) {
        final String url = removeTrailingSlashesFrom(removeAsterisksFrom(suffix));
        final Set<String> groups = urlSuffixesGroups.containsKey(url) ? urlSuffixesGroups.get(url) : new TreeSet<String>();
        groups.add(group);
        urlSuffixesGroups.put(url, groups);
    }

    private void insertUrlFullGroup(String full, String group) {
        final String url = removeTrailingSlashesFrom(full);
        final Set<String> groups = urlFullGroups.containsKey(url) ? urlFullGroups.get(url) : new TreeSet<String>();
        groups.add(group);
        urlFullGroups.put(url, groups);
    }

    private void insertUrlMiddleGroup(String middle, String group) {
        final String url = removeAsterisksFrom(middle);
        final Set<String> groups = urlMiddleGroups.containsKey(url) ? urlMiddleGroups.get(url) : new TreeSet<String>();
        groups.add(group);
        urlMiddleGroups.put(url, groups);
    }

    private String removeSchemeFrom(String url) {
        return SCHEME_PATTERN.matcher(url).replaceFirst("");
    }

    private String removeAsterisksFrom(String string) {
        return ASTERISK_PATTERN.matcher(string).replaceAll("");
    }

    private String removeTrailingSlashesFrom(String url) {
        return SLASH_PATTERN.matcher(url).replaceAll("");
    }

    private String addTrailingSlashTo(String url) {
        return url + "/";
    }

    public Set<String> classificationFor(String url) {
        final Set<String> classification = new TreeSet<String>();
        final String simpleUrl = removeTrailingSlashesFrom(removeSchemeFrom(url));

        addAllGroupsFromPrefixesInto(classification, simpleUrl);
        addAllGroupsFromSuffixesInto(classification, simpleUrl);
        addAllGroupsFromMiddleInto(classification, simpleUrl);
        addAllGroupsFromFullsInto(classification, simpleUrl);

        return classification;
    }

    private void addAllGroupsFromSuffixesInto(Set<String> groups, String urlToMatch) {
        for (String suffix : urlSuffixesGroups.keySet()) {
            if (urlToMatch.endsWith(suffix)) {
                groups.addAll(urlSuffixesGroups.get(suffix));
            }
        }
    }

    private void addAllGroupsFromPrefixesInto(Set<String> groups, String urlToMatch) {
        for (String prefix : urlPrefixesGroups.keySet()) {
            if (urlToMatch.startsWith(prefix) || addTrailingSlashTo(urlToMatch).startsWith(prefix)) {
                groups.addAll(urlPrefixesGroups.get(prefix));
            }
        }
    }

    private void addAllGroupsFromMiddleInto(Set<String> groups, String urlToMatch) {
        for (String middle : urlMiddleGroups.keySet()) {
            if (urlToMatch.contains(middle) || addTrailingSlashTo(urlToMatch).contains(middle)) {
                groups.addAll(urlMiddleGroups.get(middle));
            }
        }
    }

    private void addAllGroupsFromFullsInto(Set<String> groups, String urlToMatch) {
        for (String full : urlFullGroups.keySet()) {
            if (urlToMatch.equals(full)) {
                groups.addAll(urlFullGroups.get(full));
            }
        }
    }
}
