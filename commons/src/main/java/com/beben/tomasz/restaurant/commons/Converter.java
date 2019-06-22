package com.beben.tomasz.restaurant.commons;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface Converter<In, Out> {

    Out convert(In in);

    default Out convertNonNull(In in) {
        if (Objects.isNull(in)) {
            return null;
        }

        return convert(in);
    }

    default List<Out> convert(Collection<In> ins) {
        return CollectionUtils.emptyIfNull(ins).stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    default Set<Out> convert(Set<In> ins) {
        return CollectionUtils.emptyIfNull(ins).stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
