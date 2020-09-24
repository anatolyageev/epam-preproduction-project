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
    private final DataSource ds;

    private DataSourceManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(INIT_CONTEXT_LOOKUP);
            ds = (DataSource) envContext.lookup(DATA_SOURCE);
            LOG.debug("Data source: " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public static synchronized DataSourceManager getInstance()  {
        if (instance == null) {
            try {
                instance = new DataSourceManager();
            } catch (DBException e) {
                LOG.error("Data source error " + e);
            }
        }
        return instance;
    }

    public static synchronized DataSource getDataSource() {
        return getInstance().ds;
    }
}
