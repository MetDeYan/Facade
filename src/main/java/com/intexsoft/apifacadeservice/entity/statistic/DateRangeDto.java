package com.intexsoft.apifacadeservice.entity.statistic;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Optional parameter DateRange.
 */
@Data
@Builder
public class DateRangeDto {
    private Date dateStart;
    private Date dateFinish;
}
