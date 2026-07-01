package com.ems.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeadcountTrendResponse {

    private long totalHires;
    private long totalInactive;
    private long netGrowth;

}