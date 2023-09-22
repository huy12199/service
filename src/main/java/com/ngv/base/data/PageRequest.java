package com.ngv.base.data;

import static javax.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
public class PageRequest {
    @Min(1)
    @Max(1000)
    private Integer pageSize = 10;

    @Min(1)
    private Integer pageNo = 1;

    @Pattern(regexp = "^[a-z0-9]+:(asc|desc)$", flags = CASE_INSENSITIVE)
    private String sort;

    public Pageable pageable() {
        Sort srt = Sort.unsorted();
        if (this.sort != null) {
            String[] part = this.sort.split("_");
            for (String s : part) {
                String[] tmp = s.split(":");
                if (tmp.length == 2) {
                    srt = (Sort.by(Sort.Direction.fromString(tmp[1]), tmp[0]));
                }
            }
        }
        return org.springframework.data.domain.PageRequest.of(this.pageNo - 1, this.pageSize, srt);
    }
}
