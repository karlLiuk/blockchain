package com.lk.config.sqlite;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.engine.jdbc.dialect.internal.DialectResolverSet;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description SQLite工具类
 * @Date 2020/7/1
 * @Author lk
 */
public class SQLiteMetadataBuilderInitializer implements MetadataBuilderInitializer {

    /**
     * LOG.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(SQLiteMetadataBuilderInitializer.class);

    /**
     * .
     * @param metadataBuilder
     * @param serviceRegistry
     * @return void
     **/
    @Override
    public void contribute(final MetadataBuilder metadataBuilder, final StandardServiceRegistry serviceRegistry) {
        final DialectResolver dialectResolver = serviceRegistry.getService(DialectResolver.class);

        if (!(dialectResolver instanceof DialectResolverSet)) {
            LOG.warn("DialectResolver '%s' is not an instance of DialectResolverSet, not registering SQLiteDialect",
                    dialectResolver);
            return;
        }

        ((DialectResolverSet) dialectResolver).addResolver(RESOLVER);
    }

    /**
     * SQLite方言对象.
     **/
    private static final SQLiteDialect DIALECT = new SQLiteDialect();

    private static final DialectResolver RESOLVER = (DialectResolver) info -> {
      if (info.getDatabaseName().equals("SQLite")) {
          return DIALECT;
      }
      return null;
    };
}
