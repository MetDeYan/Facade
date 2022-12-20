package com.intexsoft.apifacadeservice.entity.statistic;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * The type Technology id dto.
 */
@Data
@Builder
@Jacksonized
public class TechnologyIdDto {
    private String id;
}
