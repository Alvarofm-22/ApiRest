package api_rest.Rest.model;

import lombok.Data;

@Data
public class Info {

    private int count;
    private int pages;
    private String next;
    private String prev;
}
