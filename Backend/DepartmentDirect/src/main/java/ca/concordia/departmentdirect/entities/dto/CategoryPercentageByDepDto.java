package ca.concordia.departmentdirect.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryPercentageByDepDto {
    private Long count;
    private String category;
    private String departmentName;
    private BigDecimal percentage;
}
