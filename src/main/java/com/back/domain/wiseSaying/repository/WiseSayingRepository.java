package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int index = 1;

    public WiseSaying save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(index++);
            wiseSayings.add(wiseSaying);
        }

        return wiseSaying;
    }

    public Page<WiseSaying> findForList(Pageable pageable) {
        int totalCount = wiseSayings.size();

        List<WiseSaying> content = wiseSayings
                .reversed()
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNo(), pageable.getPageSize(), content);
    }

    public int findIndexById(int id) {
        return IntStream
                .range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public WiseSaying findById(int id) {
        int idx = findIndexById(id);

        if (idx == -1) return null;

        return wiseSayings.get(idx);
    }

    public void delete(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

    public Page<WiseSaying> findForListByContentContaining(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getContent().contains(keyword)
                )
                .toList();
        int totalCount = filtered.size();

        List<WiseSaying> content = filtered
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNo(), pageable.getPageSize(), content);
    }

    public Page<WiseSaying> findForListByAuthorContaining(String keyword, Pageable pageable) {
        List<WiseSaying> filtered = wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getAuthor().contains(keyword)
                )
                .toList();

        int totalCount = filtered.size();

        List<WiseSaying> content = filtered
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNo(), pageable.getPageSize(), content);
    }

    public Page<WiseSaying> findForListByContentContainingOrAuthorContaining(String keyword1, String keyword2, Pageable pageable) {
        List<WiseSaying> filtered = wiseSayings
                .reversed()
                .stream()
                .filter(
                        w -> w.getContent().contains(keyword1) || w.getAuthor().contains(keyword2)
                )
                .toList();

        int totalCount = filtered.size();

        List<WiseSaying> content = filtered
                .stream()
                .skip(pageable.getSkipCount())
                .limit(pageable.getPageSize())
                .toList();

        return new Page<>(totalCount, pageable.getPageNo(), pageable.getPageSize(), content);
    }
}