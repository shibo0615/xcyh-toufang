package com.xcyh.xcyhtoufang.config.transactions;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * base
 * Created by q on 2019/12/30.
 */
public class BaseDataSourceTransaction {

    public TransactionInterceptor masterTxAdvice(DataSourceTransactionManager transactionManager) {

        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("create*", txAttrRequired);
        source.addTransactionalMethod("update*", txAttrRequired);
        source.addTransactionalMethod("save*", txAttrRequired);
        source.addTransactionalMethod("add*", txAttrRequired);
        source.addTransactionalMethod("delete*", txAttrRequired);
        source.addTransactionalMethod("increase*", txAttrRequired);
        source.addTransactionalMethod("decrease*", txAttrRequired);
        return new TransactionInterceptor(transactionManager, source);
    }

    public TransactionInterceptor slaveTxAdvice(DataSourceTransactionManager transactionManager) {
        DefaultTransactionAttribute txAttrNotSupported = new DefaultTransactionAttribute();
        txAttrNotSupported.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        txAttrNotSupported.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        source.addTransactionalMethod("get*", txAttrNotSupported);
        source.addTransactionalMethod("query*", txAttrNotSupported);
        source.addTransactionalMethod("find*", txAttrNotSupported);
        source.addTransactionalMethod("list*", txAttrNotSupported);
        source.addTransactionalMethod("count*", txAttrNotSupported);
        source.addTransactionalMethod("is*", txAttrNotSupported);
        return new TransactionInterceptor(transactionManager, source);
    }

}
