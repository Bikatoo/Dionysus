package com.bikatoo.dionysus.dionysus.infrastructure.converter;

import com.bikatoo.dionysus.dionysus.infrastructure.exception.GlobalException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.bikatoo.dionysus.dionysus.infrastructure.utils.PreconditionUtils.checkNonNullAndThrow;

public interface Converter<E, D> {

    D entity2DO(E e);

    E DO2Entity(D d);

    default List<D> entity2DOForList(List<E> eList) {
        if (eList == null || eList.isEmpty()) {
            return new ArrayList<>();
        }
        return eList.stream().map(this::entity2DO).collect(Collectors.toList());
    }

    default List<E> DO2EntityForList(List<D> dList) {
        if (dList == null || dList.isEmpty()) {
            return new ArrayList<>();
        }
        return dList.stream().map(this::DO2Entity).collect(Collectors.toList());
    }

    default PageInfo<E> DOTransferToEntityForPage(@NonNull PageInfo<D> dPage) {

        checkNonNullAndThrow(dPage, new GlobalException("dPageInfo is null"));

        if (dPage.getSize() <= 0 && (dPage.getList() == null || dPage.getList().isEmpty())) {
            return PageInfo.of(new ArrayList<>());
        }
        PageInfo<E> result = new PageInfo<>();
        BeanUtils.copyProperties(dPage, result);
        result.setList(DO2EntityForList(dPage.getList()));
        return result;
    }

}
