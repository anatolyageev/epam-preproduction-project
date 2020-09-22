package com.epam.anatolii.ageev.repository.db;

import com.epam.anatolii.ageev.exeptions.DBException;
import com.epam.anatolii.ageev.exeptions.Messages;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static com.epam.anatolii.ageev.constants.DbConnectionConstants.DATA_SOURCE;
import static com.epam.anatolii.ageev.constants.DbConnectionConstants.INIT_CONTEXT_LOOKUP;

/**
 * DB manager. Works with Apache Derby DB. Class which gives connection to DB.
 *
 * @author A.Ageev
 */
public class DataSourceManager {
    private static final Logger LOG = Logger.getLogger(DataSourceManager.class);
    private static DataSourceManager instance;
    private DataSource ds;

    private DataSourceManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(INIT_CONTEXT_LOOKUP);
            ds = (DataSource) envContext.lookup(DATA_SOURCE);
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public static synchronized DataSourceManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DataSourceManager();
        }
        return instance;
    }

    public static synchronized DataSource getDataSource() throws DBException {
        return getInstance().ds;
    }
}
