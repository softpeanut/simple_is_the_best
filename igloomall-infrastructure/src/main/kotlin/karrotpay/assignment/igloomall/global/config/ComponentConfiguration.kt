package karrotpay.assignment.igloomall.global.config

import karrotpay.assignment.igloomall.common.annotation.ReadOnlyUseCase
import karrotpay.assignment.igloomall.common.annotation.UseCase
import karrotpay.assignment.igloomall.global.annotation.Mapper
import karrotpay.assignment.igloomall.global.annotation.PersistenceAdapter
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
                UseCase::class,
                ReadOnlyUseCase::class,
                Mapper::class,
                PersistenceAdapter::class
            ]
        )
    ]
)
class ComponentConfiguration