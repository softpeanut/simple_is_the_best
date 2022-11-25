package karrotpay.assignment.igloomall.global.config

import karrotpay.assignment.igloomall.common.annotation.ReadOnlyUseCase
import karrotpay.assignment.igloomall.common.annotation.WritableUseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(
    basePackages = ["karrotpay.assignment.igloomall"],
    includeFilters = [
        Filter(
            type = FilterType.ANNOTATION,
            classes = [
                WritableUseCase::class,
                ReadOnlyUseCase::class
            ]
        )
    ]
)
class ComponentConfiguration