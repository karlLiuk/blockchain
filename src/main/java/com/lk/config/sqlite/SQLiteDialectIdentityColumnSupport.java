package com.lk.config.sqlite;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * @Description TODO
 * @Date 2020/6/30
 * @Author lk
 */
public class SQLiteDialectIdentityColumnSupport extends IdentityColumnSupportImpl {

    public SQLiteDialectIdentityColumnSupport() {
        super();
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public boolean supportsInsertSelectIdentity() {
        return true;
    }

    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    @Override
    public String getIdentitySelectString(final String table, final String column, final int type)
            throws MappingException {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(final int type) throws MappingException {
        return "integer";
    }
}
