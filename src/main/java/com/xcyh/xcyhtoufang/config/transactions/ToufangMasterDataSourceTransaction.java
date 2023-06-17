package com.xcyh.xcyhtoufang.config.transactions;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * master data source transaction
 * Created by q on 2019/12/30.
 */
@Aspect
@Configuration
public class ToufangMasterDataSourceTransaction extends BaseDataSourceTransaction {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.xcyh.xcyhtoufang.service.*.*(..))";

    @Autowired
    @Qualifier("toufangMasterTransactionManager")
    private DataSourceTransactionManager transactionManager;

    @Bean("toufangMasterTxAdvice")
    public TransactionInterceptor txAdvice() {
        return super.masterTxAdvice(transactionManager);
    }

    @Bean("toufangMasterTxAdviceAdvisor")
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
