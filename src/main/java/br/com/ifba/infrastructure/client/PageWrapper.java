package br.com.ifba.infrastructure.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PageWrapper<T> {
    private List<T> content;
    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    // O Spring retorna esses campos no JSON do Page
}