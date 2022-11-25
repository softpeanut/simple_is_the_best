package karrotpay.assignment.igloomall.global.aop

import org.aspectj.lang.annotation.Aspect
import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource
import org.springframework.transaction.interceptor.RollbackRuleAttribute
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute
import org.springframework.transaction.interceptor.TransactionAttribute
import org.springframework.transaction.interceptor.TransactionInterceptor

@Configuration
@Aspect
class TransactionAspect(
    private val transactionManager: TransactionManager
) {

    @Bean
    fun writableTransactionAdviceAdvisor(): Advisor {
        val pointcut = AspectJExpressionPointcut().apply {
            expression = "@within(karrotpay.assignment.igloomall.common.annotation.WritableUseCase)"
        }

        return DefaultPointcutAdvisor(pointcut, writableTransactionAdvice())
    }

    @Bean
    fun readOnlyTransactionAdviceAdvisor(): Advisor {
        val pointcut = AspectJExpressionPointcut().apply {
            expression = "@within(karrotpay.assignment.igloomall.common.annotation.ReadOnlyUseCase)"
        }

        return DefaultPointcutAdvisor(pointcut, readOnlyTransactionAdvice())
    }

    @Bean
    fun writableTransactionAdvice(): TransactionInterceptor {
        val transactionAttribute = RuleBasedTransactionAttribute().apply {
            setName("Writable Transaction")
            propagationBehavior = TransactionAttribute.PROPAGATION_REQUIRED
            isolationLevel = TransactionAttribute.ISOLATION_REPEATABLE_READ // gap lock
            timeout = TransactionAttribute.TIMEOUT_DEFAULT
            rollbackRules = listOf(
                RollbackRuleAttribute(Exception::class.java)
            )
        }

        return TransactionInterceptor(transactionManager, source(transactionAttribute))
    }

    @Bean
    fun readOnlyTransactionAdvice(): TransactionInterceptor {
        val transactionAttribute = RuleBasedTransactionAttribute().apply {
            setName("Read-Only Transaction")
            propagationBehavior = TransactionAttribute.PROPAGATION_REQUIRED
            isolationLevel = TransactionAttribute.ISOLATION_READ_COMMITTED // record lock
            timeout = TransactionAttribute.TIMEOUT_DEFAULT
            rollbackRules = listOf(
                RollbackRuleAttribute(Exception::class.java)
            )
            isReadOnly = true
        }

        return TransactionInterceptor(transactionManager, source(transactionAttribute))
    }

    private fun source(
        transactionAttribute: TransactionAttribute
    ) = MatchAlwaysTransactionAttributeSource().apply {
        setTransactionAttribute(transactionAttribute)
    }
}